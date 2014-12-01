package com.example.bluetape;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import testQQ.CrazyitProtocol;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class viewMyFavorite extends ListActivity {
	String ID;
	String beginTime="";
	private List<Map<String, Object>> myData;

	//BufferedReader keyIn;
	String keyIn="";
	String message="";
	int SERVER_PORT = 30117;
	int count=0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		ID = intent.getStringExtra(requirement.ID_USER);
		message= intent.getStringExtra(requirement.EXTRA_MESSAGE);
		Calendar c=Calendar.getInstance();
		beginTime=c.get(Calendar.YEAR)+""; 
	    if(c.get(Calendar.MONTH)+1<10) beginTime=beginTime+"0"+(c.get(Calendar.MONTH)+1);
	    else beginTime+=(c.get(Calendar.MONTH)+1);
	    if(c.get(Calendar.DAY_OF_MONTH)<10) beginTime=beginTime+"0"+c.get(Calendar.DAY_OF_MONTH);
	    else beginTime+=c.get(Calendar.DAY_OF_MONTH);
	    beginTime+=".0000";
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
//		open(ID);
		myData = getMyData();
		MyAdapter myAdapter = new MyAdapter(this);
		setListAdapter(myAdapter);
	}

	private List<Map<String, Object>> getMyData() {
		List<Map<String, Object>> listf = new ArrayList<Map<String, Object>>();
		String [] favorite;
		favorite=getInfo(message);
		for(int i=0;i<favorite.length;i++){
			if(Double.parseDouble(favorite[i].split(":::")[3])>Double.parseDouble(beginTime)){
				Map<String, Object> map = new HashMap<String, Object>();
				String eventTime=favorite[i].split(":::")[3].substring(4, 6)+"/"+favorite[i].split(":::")[3].substring(6, 8)+"/"+favorite[i].split(":::")[3].substring(0, 4)+" ";
				map.put("time", "Time(MM/DD/YYYY): "+eventTime);
				listf.add(map);
				count++;
			}
		}
		if(count==0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("time", "No non-expired flyers exist");
			listf.add(map);
		}

		return listf;
	}

	// ListView 中某项被选中后
	@Override

	protected void onListItemClick(ListView l, View v, int position, long id) {
		//		setTitle("点击第"+position+"个项目");  
		Intent intent = new Intent();
		String s="";
		String [] favorite=getInfo(message);
		intent.putExtra(requirement.EXTRA_MESSAGE, favorite[position]);
		intent.putExtra(requirement.ID_USER, ID);
		intent.setClass(viewMyFavorite.this, favoriteByDate.class);
		viewMyFavorite.this.startActivity(intent);
	}


	public final class ViewHolder{
		public TextView time;

	}





	public class MyAdapter extends BaseAdapter{
		private LayoutInflater mInflater;
		public MyAdapter(Context context){
			this.mInflater = LayoutInflater.from(context);
		}

		@Override

		public int getCount() {
			// TODO Auto-generated method stub
			return myData.size();

		}



		@Override

		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;

		}



		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;

		}



		@Override

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder=new ViewHolder();  
				convertView = mInflater.inflate(R.layout.listdate, null);
				holder.time = (TextView)convertView.findViewById(R.id.ItemText);
				convertView.setTag(holder);
			}
			else {
				holder = (ViewHolder)convertView.getTag();
			}
			holder.time.setText((String)myData.get(position).get("time"));
			return convertView;
		}
	}






	public String[] getInfo(String message){
		String l="";
		int n=message.length()-1;
		while(message.charAt(n)!=';'){
			l=message.charAt(n)+l;
			n--;
		}
		int length=Integer.parseInt(String.valueOf(l));
		//	String [][] favorite=new String [length][8];
		String [] f=new String [length];
		for(int i=0;i<length;i++){
			f[i]=message.split(";;;")[i];
		}
		int count=0;
		for (int i = 0; i < length-1; i++) {
			for (int j = i + 1; j < length; j++) {
				if (Double.parseDouble(f[i].split(":::")[3]) > Double.parseDouble(f[j].split(":::")[3])) {
					String temp = f[i];
					f[i] = f[j];
					f[j] = temp;
				}
			}
		}
		int number=1;
		for (int i = 0; i < length; i++) {
			if(i==0) f[count]=f[i];
			else {
				if(f[i].split(":::")[3].substring(0, 8).equalsIgnoreCase(f[i-1].split(":::")[3].substring(0, 8))) {
					f[count]=f[count]+";;;"+f[i];
					number++;
				}
				else{
					f[count]=f[count]+";;;"+number;
					count++;
					number=1;
					f[count]=f[i];
				}
			}
			if(i==length-1) f[count]=f[count]+";;;"+number;
		}
		String [] result = new String [count+1];
		for (int i = 0; i <= count; i++){
			result[i]=f[i];
		}
		//	for(int i=0;i<length;i++){
		//		for(int j=0;j<8;j++){
		//			favorite[i][j]=f[i].split(":::")[j];
		//		}
		//	}
		return result;
	}

	public void open(String ID){
		Socket socket = null;  
		String userName = ID;
		try {  
			// 创建Socket
			socket = new Socket("10.0.2.2", SERVER_PORT); // 查看本机IP,每次开机都同  
			// 向服务器发送消息   
			PrintWriter out = new PrintWriter(new BufferedWriter(  
					new OutputStreamWriter(socket.getOutputStream())),  
					true);  
			out.println(CrazyitProtocol.USER_ROUND + userName
					+ CrazyitProtocol.USER_ROUND); 

			// 接收来自服务器的消息  
			BufferedReader br = new BufferedReader(  
					new InputStreamReader(socket.getInputStream()));  
			String logIn = br.readLine();
			keyIn="i"+ID+"i";
			out.println(keyIn);
			message=br.readLine();
			//            String line = null;
			//			while((line = br.readLine())!= null){
			//				message+=line;
			//			}
			// 关闭流  
			out.close();  
			br.close();  
			// 关闭Socket  
			socket.close();  
		} catch (Exception e) {  
			// TODO: handle exception  
			Log.e("", e.toString());  
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
