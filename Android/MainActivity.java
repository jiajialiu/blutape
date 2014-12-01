package com.example.bluetape;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import testQQ.CrazyitProtocol;


import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {
	String ID="6";
	String keyIn;
	String message="0";
	private Button PressS=null;
	int SERVER_PORT = 30117;
//	static Socket socket = null; 
	//BufferedReader keyIn;
	//	public EditText dateEt=null;
	//	public EditText timeEt=null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build()); 
		open(ID);
		//		AndriodClient.logIn("7");
		//		setContentView(R.layout.list);
		//		setContentView(R.layout.activity_requirement);
		PressSClick(); 
		PressVClick(); 
		////				setContentView(R.layout.activity_requirement);
		//		        dateEt=(EditText)findViewById(R.id.dateEt);
		//		        timeEt=(EditText)findViewById(R.id.timeEt);
		//		        DatePicker datePicker=(DatePicker)findViewById(R.id.datePicker);
		//		        TimePicker timePicker=(TimePicker)findViewById(R.id.timePicker);
		//		        
		//		        Calendar calendar=Calendar.getInstance();
		//		        int year=calendar.get(Calendar.YEAR);
		//		        int monthOfYear=calendar.get(Calendar.MONTH);
		//		        int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
		//		        datePicker.init(year, monthOfYear, dayOfMonth, new OnDateChangedListener(){
		//		
		//		            public void onDateChanged(DatePicker view, int year,
		//		                    int monthOfYear, int dayOfMonth) {
		//		                dateEt.setText("The date you choose is："+year+"."+(monthOfYear+1)+"."+dayOfMonth);
		//		            }
		//		            
		//		        });
		//		        
		//		        timePicker.setOnTimeChangedListener(new OnTimeChangedListener(){
		//		
		//		            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		//		                timeEt.setText("The time you choose is："+hourOfDay+":"+minute);
		//		            }
		//		            
		//		        });
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//	private void PressSClick(){
	//		PressS=(Button)findViewById(R.id.button2); 
	//		PressS.setOnClickListener(new OnClickListener(){
	//			public void onClick(View v){
	//				setContentView(R.layout.activity_requirement);
	//			}
	//		});
	//	}

	private void PressSClick(){
		PressS=(Button)findViewById(R.id.button2);
		PressS.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent();
				intent.putExtra(requirement.ID_USER, ID);
				intent.setClass(MainActivity.this, requirement.class);
				MainActivity.this.startActivity(intent);
			}
		});
	}

	private void PressVClick(){
		PressS=(Button)findViewById(R.id.button1);
		PressS.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				if(message.equalsIgnoreCase("0")){
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, display.class);
					MainActivity.this.startActivity(intent);
				}
				else {
					Intent intent = new Intent();
					intent.putExtra(requirement.ID_USER, ID);
					intent.putExtra(requirement.EXTRA_MESSAGE, message);
					intent.setClass(MainActivity.this, viewMyFavorite.class);
					MainActivity.this.startActivity(intent);
				}
			}
		});
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
}

