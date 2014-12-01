package com.example.bluetape;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import testQQ.CrazyitProtocol;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.content.DialogInterface;
import android.content.Intent;


public class favoriteByDate extends Activity {
	int SERVER_PORT = 30117;
	String message;
	String ID;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the message from the intent
		Intent intent = getIntent();
		message = intent.getStringExtra(requirement.EXTRA_MESSAGE);
		ID = intent.getStringExtra(requirement.ID_USER);
		// Create the text view
		setContentView(R.layout.listview2);
		//��Layout�����ListView
		ListView list = (ListView) findViewById(R.id.ListView2);
		String [][] flyer;
		flyer=getInfo(message);
		//���ɶ�̬���飬��������
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		for(int i=0;i<flyer.length;i++){
			HashMap<String, Object> map = new HashMap<String, Object>();
			String eventTime=flyer[i][3].substring(4, 6)+"/"+flyer[i][3].substring(6, 8)+"/"+flyer[i][3].substring(0, 4)+" ";
			if(Integer.parseInt(flyer[i][3].substring(9, 11))<12&&Integer.parseInt(flyer[i][3].substring(9, 11))>0)
				eventTime=eventTime+flyer[i][3].substring(9, 11)+":"+flyer[i][3].substring(11, 13)+" am";
			else if(Integer.parseInt(flyer[i][3].substring(9, 11))==12)
				eventTime=eventTime+flyer[i][3].substring(9, 11)+":"+flyer[i][3].substring(11, 13)+" pm";
			else if(Integer.parseInt(flyer[i][3].substring(9, 11))==00)
				eventTime=eventTime+"12:"+flyer[i][3].substring(11, 13)+" am";
			else
				eventTime=eventTime+(Integer.parseInt(flyer[i][3].substring(9, 11))-12)+":"+flyer[i][3].substring(11, 13)+" pm";
			map.put("title", "Title: "+flyer[i][1]);
			map.put("sponsor", "Holder: "+flyer[i][5]);
			map.put("time", "Time: "+eventTime);
			map.put("location", "Location: "+flyer[i][4]);
			listItem.add(map);
		}
		//        for(int i=0;i<10;i++)
		//        {
		//        	HashMap<String, Object> map = new HashMap<String, Object>();
		//        	map.put("ItemText", "Finished in 1 Min 54 Secs, 70 Moves! ");
		//        	listItem.add(map);
		//        }
		//������������Item�Ͷ�̬�����Ӧ��Ԫ��
		SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//����Դ 
				R.layout.listfinal,//ListItem��XMLʵ��
				//��̬������ImageItem��Ӧ������        
				new String[] {"title","sponsor","time","location"}, 
				//ImageItem��XML�ļ������һ��ImageView,����TextView ID
				new int[] {R.id.showTitle,R.id.showSponsor,R.id.showTime,R.id.showLocation}
				);

		//��Ӳ�����ʾ
		list.setAdapter(listItemAdapter);

		//��ӵ��
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
//				setTitle("Click event No."+arg2);
				String [][] flyer;
				flyer=getInfo(message);
				Intent intent = new Intent();
				intent.setClass(favoriteByDate.this, viewFlyer.class);
				intent.putExtra(requirement.EXTRA_MESSAGE, flyer[arg2][7]);
				favoriteByDate.this.startActivity(intent); 
			}
		});

		//��ӳ������
		list.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
				menu.setHeaderTitle("Options"); 
				menu.add(0, 0, 0, "view details");
				menu.add(0, 1, 0, "view flyer"); 
				menu.add(0, 2, 0, "remove");
			}
		}); 
	}

	//�����˵���Ӧ����
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		//		setTitle("����˳����˵�����ĵ�"+item.getItemId()+"����Ŀ");
		ContextMenuInfo info = item.getMenuInfo();
		AdapterView.AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) info;
		// ��ȡѡ����λ��
		int position = contextMenuInfo.position;
		if(item.getItemId()==0){
			viewDetail(position);
		}
		if(item.getItemId()==1){
			String [][] flyer;
			flyer=getInfo(message);
			Intent intent = new Intent();
			intent.setClass(favoriteByDate.this, viewFlyer.class);
			intent.putExtra(requirement.EXTRA_MESSAGE, flyer[position][7]);
			favoriteByDate.this.startActivity(intent); 
		}
		if(item.getItemId()==2){
			showInfoF(position);
		}
		return super.onContextItemSelected(item);
	}

	public void viewDetail(int position){
		String [][] flyer;
		flyer=getInfo(message);
		new AlertDialog.Builder(this)
		.setTitle("Event details")
		.setMessage(flyer[position][2])
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		})
		.show();
	}

    public void showInfoF(int position){
    	String [][] flyer;
        flyer=getInfo(message);
        final String s="r"+flyer[position][8]+"r";
        new AlertDialog.Builder(this)
        .setTitle("Remove flyer")
        .setMessage("Do you want to remove this flyer?")
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            	Intent intent = new Intent();
            	intent.setClass(favoriteByDate.this, addingSucceed.class);
        		intent.putExtra(requirement.EXTRA_MESSAGE, "Remove suceessfully!");
        		intent.putExtra(requirement.STRING, "0");
        		intent.putExtra(requirement.ID_USER, ID);
        		favoriteByDate.this.startActivity(intent);  
            	open(s,ID);
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() { 
        public void onClick(DialogInterface dialog, int which) { 
         } 
         })
        .show();
    }

public String[][] getInfo(String message){
	String l="";
	int n=message.length()-1;
	while(message.charAt(n)!=';'){
		l=message.charAt(n)+l;
		n--;
	}
	int length=Integer.parseInt(String.valueOf(l));
	String [][] favorite=new String [length][9];
	String [] f=new String [length];
	for(int i=0;i<length;i++){
		f[i]=message.split(";;;")[i];
	}

	for(int i=0;i<length;i++){
		for(int j=0;j<9;j++){
			favorite[i][j]=f[i].split(":::")[j];
		}
	}
	return favorite;
}

public void open(String s,String ID){
	Socket socket = null;  
	String userName = ID;
	try {  
		// ����Socket
		socket = new Socket("10.0.2.2", SERVER_PORT); // �鿴����IP,ÿ�ο�����ͬ  
		// �������������Ϣ   
		PrintWriter out = new PrintWriter(new BufferedWriter(  
				new OutputStreamWriter(socket.getOutputStream())),  
				true);  
		out.println(CrazyitProtocol.USER_ROUND + userName
				+ CrazyitProtocol.USER_ROUND); 

		// �������Է���������Ϣ  
		BufferedReader br = new BufferedReader(  
				new InputStreamReader(socket.getInputStream()));  
		String logIn = br.readLine();
		out.println(s);
		message=br.readLine();
		//            message=br.readLine();
		//            message=br.readLine();
		//            message=br.readLine();
		//            String line = null;
		//			while((line = br.readLine())!= null){
		//				message+=line;
		//			}
		// �ر���  
		out.close();  
		br.close();  
		// �ر�Socket  
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
