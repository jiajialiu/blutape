package com.example.bluetape;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import testQQ.CrazyitProtocol;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class resultlist extends ListActivity {
	int SERVER_PORT = 30117;
	String message;
	String ID;
	String search;
    private List<Map<String, Object>> mData;
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	Intent intent = getIntent();
	    message = intent.getStringExtra(requirement.EXTRA_MESSAGE);
	    ID = intent.getStringExtra(requirement.ID_USER);
	    search = intent.getStringExtra(requirement.STRING);
        mData = getData();
        MyAdapter adapter = new MyAdapter(this);
        setListAdapter(adapter);
	    
    }
    
    public String[][] getInformation(String message){
    	String l="";
		int n=message.length()-1;
		while(message.charAt(n)!=';'){
			l=message.charAt(n)+l;
			n--;
		}
		int length=Integer.parseInt(String.valueOf(l));
		String [][] flyer=new String [length][8];
		String [] f=new String [length];
//		System.out.println(r.split(";;;")[2]);
		for(int i=0;i<length;i++){
			f[i]=message.split(";;;")[i];
		}
	
		for(int i=0;i<length;i++){
			for(int j=0;j<8;j++){
				flyer[i][j]=f[i].split(":::")[j];
			}
		}
		return flyer;
    }
 
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String [][] flyer;
        flyer=getInformation(message);
        for(int i=0;i<flyer.length;i++){
        Map<String, Object> map = new HashMap<String, Object>();
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
        list.add(map);
        }
         
        return list;
    }
     
    // ListView 中某项被选中后的逻辑
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
         
        Log.v("MyListView-click", (String)mData.get(position).get("title"));
    }
     
    /**
     * listview中点击按键弹出对话框
     * @param message 
     */
   
    public void showInfoF(int position){
    	String [][] flyer;
        flyer=getInformation(message);
        final String s="p"+ID+":::"+flyer[position][0]+"p";
        new AlertDialog.Builder(this)
        .setTitle("Add to My Favotite")
        .setMessage("After you add this flyer to your favorite, you can see it by click \"View MyFavorite\"")
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            	open(s,ID);
            	Intent intent = new Intent();
            	intent.setClass(resultlist.this, addingSucceed.class);
        		intent.putExtra(requirement.EXTRA_MESSAGE, "Adding to My Favorite suceessfully!");
        		intent.putExtra(requirement.STRING, search);
        		intent.putExtra(requirement.ID_USER, ID);
        		resultlist.this.startActivity(intent);  
//            	try {
//					AndriodClient.readAndSend(s);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() { 
        public void onClick(DialogInterface dialog, int which) { 
         } 
         })
        .show();
    }
    
    public void showInfoB(int position){
    	String [][] flyer;
        flyer=getInformation(message);
        final String s="t"+ID+":::"+flyer[position][0]+"t";
        new AlertDialog.Builder(this)
        .setTitle("Add to blacklist")
        .setMessage("After you add this flyer to blacklist. It will not show up again. Are you sure you want to add it to Blacklist?")
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            	open(s,ID);
            	Intent intent = new Intent();
        		intent.setClass(resultlist.this, addingSucceed.class);
        		intent.putExtra(requirement.EXTRA_MESSAGE, "Adding to blacklist suceessfully");
        		intent.putExtra(requirement.STRING, search);
        		intent.putExtra(requirement.ID_USER, ID);
        		resultlist.this.startActivity(intent);     
//            	try {
//					AndriodClient.readAndSend(s);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() { 
        public void onClick(DialogInterface dialog, int which) { 
         } 
         })
        .show();
    }
    
    public void viewDetail(int position){
    	String [][] flyer;
        flyer=getInformation(message);
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
    
//    public void viewDetail(){
//    	Intent intent = new Intent();
//		intent.setClass(resultlist.this, viewDetail.class);
//		intent.putExtra(requirement.EXTRA_MESSAGE, message);
//		resultlist.this.startActivity(intent);
//    }
     
     
     
    public final class ViewHolder{
        public ImageView img;
        public TextView title;
        public TextView sponsor;
        public TextView location;
        public TextView time;
        public Button detail;
        public Button flyer;
        public Button favorite;
        public Button blacklist;
    }
     
     
    public class MyAdapter extends BaseAdapter{
 
        private LayoutInflater mInflater;
         
         
        public MyAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mData.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
             
            ViewHolder holder = null;
            if (convertView == null) {
                 
                holder=new ViewHolder();  
                 
                convertView = mInflater.inflate(R.layout.list, null);;
                holder.title = (TextView)convertView.findViewById(R.id.flyerTitle);
                holder.sponsor = (TextView)convertView.findViewById(R.id.flyerSponsor);
                holder.location = (TextView)convertView.findViewById(R.id.flyerLocation);
                holder.time = (TextView)convertView.findViewById(R.id.flyerTime);
                holder.detail = (Button)convertView.findViewById(R.id.button4);
                holder.flyer = (Button)convertView.findViewById(R.id.button5);
                holder.favorite = (Button)convertView.findViewById(R.id.button6);
                holder.blacklist = (Button)convertView.findViewById(R.id.button7);
                convertView.setTag(holder);
                 
            }else {
                 
                holder = (ViewHolder)convertView.getTag();
            }
             
             
            holder.title.setText((String)mData.get(position).get("title"));
            holder.sponsor.setText((String)mData.get(position).get("sponsor"));
            holder.location.setText((String)mData.get(position).get("location"));
            holder.time.setText((String)mData.get(position).get("time"));
             
            holder.detail.setOnClickListener(new View.OnClickListener() {
                 
                @Override
                public void onClick(View v) {
                	viewDetail(position);                 
                }
            });
            holder.flyer.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                	String [][] flyer;
                    flyer=getInformation(message);
                	Intent intent = new Intent();
            		intent.setClass(resultlist.this, viewFlyer.class);
            		intent.putExtra(requirement.EXTRA_MESSAGE, flyer[position][7]);
            		resultlist.this.startActivity(intent);                 
                }
            });
            holder.favorite.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    showInfoF(position);                 
                }
            });
            holder.blacklist.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    showInfoB(position);                 
                }
            });
             
             
            return convertView;
        }
         
    }
    
    public void open(String s,String ID){
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
            out.println(s);
            message=br.readLine();
//            message=br.readLine();
//            message=br.readLine();
//            message=br.readLine();
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

     
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }
     
     
}
