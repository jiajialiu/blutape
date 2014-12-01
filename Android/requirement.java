package com.example.bluetape;

import android.os.Bundle;
import android.os.StrictMode;

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
import java.util.Calendar;
import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker.OnTimeChangedListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;


import testQQ.ClientThread;
import testQQ.CrazyitProtocol;




public class requirement extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.bluetape.MESSAGE";
	public final static String ID_USER = "com.example.bluetape.IDUSER";
	public final static String STRING = "com.example.bluetape.STRING";
	public String beginTime="";
	public String endTime="";
	public EditText dateEt=null;
	public EditText timeEt=null;
	CheckBox Sports;
	CheckBox freefood;
	CheckBox lecture;
	CheckBox party;
	CheckBox other;
	CheckBox ArtScience;
	CheckBox Social;
	CheckBox Engineering;
	CheckBox DesignArt;
	CheckBox Law;
	CheckBox Medicine;
	CheckBox Business;
	CheckBox Union;
	CheckBox Other;
	int SERVER_PORT = 30117;
	Socket socket;
	PrintStream ps;
	BufferedReader brServer = null;
	//BufferedReader keyIn;
	String ID;
	String keyIn="cjia:helloc";
	String message="unchanged";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_requirement);
		Intent intent = getIntent();
		ID = intent.getStringExtra(requirement.ID_USER);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build()); 
		//        Socket socket = null;  
		//        String userName = "jia";  
		//        try {  
		//            // 创建Socket 
		//            socket = new Socket("10.0.2.2", SERVER_PORT); // 查看本机IP,每次开机都同  
		//            // 向服务器发送消息   
		//            PrintWriter out = new PrintWriter(new BufferedWriter(  
		//                    new OutputStreamWriter(socket.getOutputStream())),  
		//                    true);  
		//            out.println(CrazyitProtocol.USER_ROUND + userName
		//				+ CrazyitProtocol.USER_ROUND); 
		//
		//            // 接收来自服务器的消息  
		//            BufferedReader br = new BufferedReader(  
		//                    new InputStreamReader(socket.getInputStream()));  
		//            String logIn = br.readLine();
		//            out.println(keyIn);
		//            message=br.readLine();


		//            // 关闭流  
		//            out.close();  
		//            br.close();  
		//            // 关闭Socket  
		//            socket.close();  
		//        } catch (Exception e) {  
		//            // TODO: handle exception  
		//            Log.e("", e.toString());  
		//        }



		//		try{
		//			//		System.out.println("please enter...");
		//			//	keyIn = new BufferedReader(
		//			//			new InputStreamReader(System.in));
		//			keyIn = "cjia:helloc";
		//			socket = new Socket("10.0.2.2", SERVER_PORT);
		//			ps = new PrintStream(socket.getOutputStream());
		//			brServer = new BufferedReader(
		//					new InputStreamReader(socket.getInputStream()));
		//			String tip = "";
		//			while(true)
		//			{
		//				//		String userName = JOptionPane.showInputDialog(tip 
		//				//				+ "enter the username");
		//				String userName="jia";
		//				ps.println(CrazyitProtocol.USER_ROUND + userName
		//						+ CrazyitProtocol.USER_ROUND);
		//				String result = brServer.readLine();
		//				if (result.equals(CrazyitProtocol.NAME_REP))
		//				{
		//					tip = "username exist!enter again";
		//					continue;
		//				}
		//				if (result.equals(CrazyitProtocol.LOGIN_SUCCESS))
		//				{
		//					break;
		//				}
		//			}
		//		}
		//		catch (UnknownHostException ex)
		//		{
		//			System.out.println("can not find the driver");
		//			//	closeRs();
		//			System.exit(1);
		//		}
		//		catch (IOException ex)
		//		{
		//			System.out.println("network anomally");
		//			//	closeRs();
		//			System.exit(1);
		//		}
		//		//new testQQ.ClientThread(brServer).start();
		//
		//
		//
		//
		//		String line = keyIn;
		//		while(line!= null){
		//			if (line.indexOf(":") > 0 && line.startsWith("/")){
		//				line = line.substring(1);
		//				ps.println(CrazyitProtocol.PRIVATE_ROUND + 
		//						line.split(":")[0] + CrazyitProtocol.SPLIT_SIGN
		//						+ line.split(":")[1] + CrazyitProtocol.PRIVATE_ROUND);//鏉╂瑩鍣风拋鈺冪秹妞ゅ吀绱舵潻鍥ㄦ降閻ㄥ嫭鏆熼幑顔垮殰瀹歌京鏁ら崘鎺戝娇闂呮柨绱戠亸鍗炪偨娴滐拷
		//			}
		//			else{
		//				ps.println(line);
		//			}
		//		}
		//		
		//		
		//		try {
		//			message = brServer.readLine();
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//
		//
		//		//
		//		//if (keyIn != null){
		//		//	ps.close();
		//		//}
		//		//if (brServer != null){
		//		//	ps.close();
		//		//}
		//		//if (ps != null){
		//		//	ps.close();
		//		//}
		//		////	if (socket != null){
		//		////		keyIn.close();
		//		////	}
		//		//
		//		//
		//		//try{
		//		//	String line2 = null;
		//		//	while((line2 = brServer.readLine())!= null){
		//		////		System.out.println(line);
		//		//	}
		//		//}
		//		//catch (IOException ex){
		//		//	ex.printStackTrace();
		//		//}
		//		//finally{
		//		//	try{
		//		//		if (brServer != null){
		//		//			brServer.close();
		//		//		}
		//		//	}
		//		//	catch (IOException ex){
		//		//		ex.printStackTrace();
		//		//	}
		//		//}


		Sports=(CheckBox)findViewById(R.id.checkBox1);
		freefood=(CheckBox)findViewById(R.id.checkBox2);
		lecture=(CheckBox)findViewById(R.id.checkBox3);
		party=(CheckBox)findViewById(R.id.checkBox4);
		other=(CheckBox)findViewById(R.id.checkBox5);
		ArtScience=(CheckBox)findViewById(R.id.checkBox6);
		Social=(CheckBox)findViewById(R.id.checkBox7);
		Engineering=(CheckBox)findViewById(R.id.checkBox8);
		DesignArt=(CheckBox)findViewById(R.id.checkBox9);
		Law=(CheckBox)findViewById(R.id.checkBox10);
		Medicine=(CheckBox)findViewById(R.id.checkBox11);
		Business=(CheckBox)findViewById(R.id.checkBox12);
		Union=(CheckBox)findViewById(R.id.checkBox13);
		Other=(CheckBox)findViewById(R.id.checkBox14);
		dateEt=(EditText)findViewById(R.id.dateEt);
		timeEt=(EditText)findViewById(R.id.timeEt);
		DatePicker datePicker=(DatePicker)findViewById(R.id.datePicker);
		TimePicker timePicker=(TimePicker)findViewById(R.id.timePicker);

		Calendar calendar=Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		int monthOfYear=calendar.get(Calendar.MONTH); 
		int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
		datePicker.init(year, monthOfYear, dayOfMonth, new OnDateChangedListener(){

			public void onDateChanged(DatePicker view, int year,
					int monthOfYear, int dayOfMonth) {
				dateEt.setText("The date you choose is："+year+"."+(monthOfYear+1)+"."+dayOfMonth);
			}

		});

		timePicker.setOnTimeChangedListener(new OnTimeChangedListener(){

			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				timeEt.setText("The time you choose is："+hourOfDay+":"+minute);
			}

		});


		Button.OnClickListener beginSearch_listener = new Button.OnClickListener(){
			public void onClick(View v){
				String s = "";
				int count =0;
				if(Sports.isChecked()){
					if(count==0) s+="(";
					else s+="%";
					s+="Catagory=sports";
					count++;
				}
				if(freefood.isChecked()){
					if(count==0) s+="(";
					else s+="%";
					s+="Catagory=freefood";
					count++;
				}
				if(lecture.isChecked()){
					if(count==0) s+="(";
					else s+="%";
					s+="Catagory=lecture";
					count++;
				}
				if(party.isChecked()){
					if(count==0) s+="(";
					else s+="%";
					s+="Catagory=party";
					count++;
				}
				if(other.isChecked()){
					if(count==0) s+="(";
					else s+="%";
					s+="Catagory=other";
					count++;
				}
				if(count!=0) s+=")";
				count=0; 
				if(ArtScience.isChecked()){
					if(count==0) {
						if(s.equalsIgnoreCase("")) s+="(";
						else s+="*(";
					}
					else s+="%";
					s+="Sponsor=Art and Science";
					count++;
				}
				if(Social.isChecked()){
					if(count==0)  {
						if(s.equalsIgnoreCase("")) s+="(";
						else s+="*(";
					}
					else s+="%";
					s+="Sponsor=Brown School of Social Work";
					count++;
				}
				if(Engineering.isChecked()){
					if(count==0)  {
						if(s.equalsIgnoreCase("")) s+="(";
						else s+="*(";
					}
					else s+="%";
					s+="Sponsor=Engineering and Applied Science";
					count++;
				}
				if(DesignArt.isChecked()){
					if(count==0)  {
						if(s.equalsIgnoreCase("")) s+="(";
						else s+="*(";
					}
					else s+="%";
					s+="Sponsor=Sam Fox School of Design and Visual Arts";
					count++;
				}
				if(Law.isChecked()){
					if(count==0)  {
						if(s.equalsIgnoreCase("")) s+="(";
						else s+="*(";
					}
					else s+="%";
					s+="Sponsor=School of Law";
					count++;
				}
				if(Medicine.isChecked()){
					if(count==0)  {
						if(s.equalsIgnoreCase("")) s+="(";
						else s+="*(";
					}
					else s+="%";
					s+="Sponsor=School of Medicine";
					count++;
				}
				if(Business.isChecked()){
					if(count==0)  {
						if(s.equalsIgnoreCase("")) s+="(";
						else s+="*(";
					}
					else s+="%";
					s+="Sponsor=Olin Business School";
					count++;
				}
				if(Union.isChecked()){
					if(count==0)  {
						if(s.equalsIgnoreCase("")) s+="(";
						else s+="*(";
					}
					else s+="%";
					s+="Sponsor=Student union/association/organization/club";
					count++;
				}
				if(Other.isChecked()){
					if(count==0)  {
						if(s.equalsIgnoreCase("")) s+="(";
						else s+="*(";
					}
					else s+="%";
					s+="Sponsor=Other";
					count++;
				}
				if(count!=0) s+=")";
				count=0;
				EditText location1 = (EditText) findViewById(R.id.edit_message1);
				EditText location2 = (EditText) findViewById(R.id.edit_message2);
				EditText location3 = (EditText) findViewById(R.id.edit_message3);
				EditText location4 = (EditText) findViewById(R.id.edit_message4);
				String message1 = location1.getText().toString();
				String message2 = location2.getText().toString();
				String message3 = location3.getText().toString();
				String message4 = location4.getText().toString();
				if(!message1.equalsIgnoreCase("")){
					if(count==0)  {
						if(s.equalsIgnoreCase("")) s+="(";
						else s+="*(";
					}
					else s+="%";
					s=s+"!Location="+message1;
					count++;	
				}
				if(!message2.equalsIgnoreCase("")){
					if(count==0)  {
						if(s.equalsIgnoreCase("")) s+="(";
						else s+="*(";
					}
					else s+="%";
					s=s+"!Location="+message2;
					count++;	
				}
				if(!message3.equalsIgnoreCase("")){
					if(count==0)  {
						if(s.equalsIgnoreCase("")) s+="(";
						else s+="*(";
					}
					else s+="%";
					s=s+"!Location="+message3;
					count++;	
				}
				if(!message4.equalsIgnoreCase("")){
					if(count==0)  {
						if(s.equalsIgnoreCase("")) s+="(";
						else s+="*(";
					}
					else s+="%";
					s=s+"!Location="+message4;
					count++;	
				}
				if(count!=0) s+=")";


				String endTime="";
				String beginTime="";
				EditText dateEt=(EditText)findViewById(R.id.dateEt);
				EditText timeEt=(EditText)findViewById(R.id.timeEt);
				String beginTime1=dateEt.getText().toString();
				String beginTime2=timeEt.getText().toString();
				Calendar c=Calendar.getInstance();
				String time=c.get(Calendar.YEAR)+""; 
				if(c.get(Calendar.MONTH)+1<10) time=time+"0"+(c.get(Calendar.MONTH)+1);
				else time+=(c.get(Calendar.MONTH)+1);
				if(c.get(Calendar.DAY_OF_MONTH)<10) time=time+"0"+c.get(Calendar.DAY_OF_MONTH);
				else time+=c.get(Calendar.DAY_OF_MONTH);
				if(!beginTime1.equalsIgnoreCase("")){
					beginTime=beginTime+beginTime1.charAt(23)+beginTime1.charAt(24)+beginTime1.charAt(25)+beginTime1.charAt(26);
					if(beginTime1.charAt(29)=='.'){
						beginTime=beginTime+"0"+beginTime1.charAt(28);
						if(beginTime1.length()==31) beginTime=beginTime+"0"+beginTime1.charAt(30);
						else beginTime=beginTime+beginTime1.charAt(30)+beginTime1.charAt(31);
					}
					else{
						beginTime=beginTime+beginTime1.charAt(28)+beginTime1.charAt(29);
						if(beginTime1.length()==32) beginTime=beginTime+"0"+beginTime1.charAt(31);
						else beginTime=beginTime+beginTime1.charAt(31)+beginTime1.charAt(32);
					}
					beginTime+=".";
				}
				else beginTime=time+".";
				if(!beginTime2.equalsIgnoreCase("")){
					if(beginTime2.charAt(24)==':'){
						beginTime=beginTime+"0"+beginTime2.charAt(23);
						if(beginTime2.length()==26) beginTime=beginTime+"0"+beginTime2.charAt(25);
						else beginTime=beginTime+beginTime2.charAt(25)+beginTime2.charAt(26);
					}
					else{
						beginTime=beginTime+beginTime2.charAt(23)+beginTime2.charAt(24);
						if(beginTime2.length()==27) beginTime=beginTime+"0"+beginTime2.charAt(26);
						else beginTime=beginTime+beginTime2.charAt(26)+beginTime2.charAt(27);
					}
				}
				else beginTime+="0000"; 
				if(s.equalsIgnoreCase(""))  s=s+"(?"+beginTime+"<Time<99991232.0000)";
				else s=s+"*(?"+beginTime+"<Time<99991232.0000)";



				s+="#";
				EditText r=(EditText)findViewById(R.id.result);
//				r.setText(s);


				/**
				 * 				
				 */
				//		        Socket socket = null;  
				//		        String userName = "jia";
				//		        try {  
				//		            // 创建Socket
				//		            socket = new Socket("10.0.2.2", SERVER_PORT); // 查看本机IP,每次开机都同  
				//		            // 向服务器发送消息   
				//		            PrintWriter out = new PrintWriter(new BufferedWriter(  
				//		                    new OutputStreamWriter(socket.getOutputStream())),  
				//		                    true);  
				//		            out.println(CrazyitProtocol.USER_ROUND + userName
				//						+ CrazyitProtocol.USER_ROUND); 
				//		
				//		            // 接收来自服务器的消息  
				//		            BufferedReader br = new BufferedReader(  
				//		                    new InputStreamReader(socket.getInputStream()));  
				//		            String logIn = br.readLine();
				//		            keyIn="o"+s+":"+ID+"o";
				//		            out.println(keyIn);
				//		            message=br.readLine();
				////		            message=br.readLine();
				////		            message=br.readLine();
				////		            message=br.readLine();
				////		            String line = null;
				////					while((line = br.readLine())!= null){
				////						message+=line;
				////					}
				//		            r.setText(message);
				//		            // 关闭流  
				//		            out.close();  
				//		            br.close();  
				//		            // 关闭Socket  
				//		            socket.close();  
				//		        } catch (Exception e) {  
				//		            // TODO: handle exception  
				//		            Log.e("", e.toString());  
				//		        }



				open(s,ID);
				//				String keyIn="o"+s+":"+ID+"o";
				//				try {
				//					AndriodClient.readAndSend(keyIn);
				//				} catch (IOException e) {
				//					// TODO Auto-generated catch block
				//					e.printStackTrace();
				//				}

				if(message.equalsIgnoreCase("0")){
					Intent noResult = new Intent();
					noResult.setClass(requirement.this, addingSucceed.class);
					noResult.putExtra(ID_USER, ID);
					noResult.putExtra(STRING, s);
					noResult.putExtra(EXTRA_MESSAGE, message);
					requirement.this.startActivity(noResult);
				}
				else{
					Intent intent = new Intent();
					intent.setClass(requirement.this, resultlist.class);
					intent.putExtra(EXTRA_MESSAGE, message);
					intent.putExtra(ID_USER, ID);
					intent.putExtra(STRING, s);
					requirement.this.startActivity(intent);
				}
			}

		};

		Button beginSearch = (Button)findViewById(R.id.button3);
		beginSearch.setOnClickListener(beginSearch_listener);
		//	Click();

	}


	public void open(String s,String ID){
		Socket socket = null;  
		String userName = ID;
		try {  
			// 创建Socket
			socket = new Socket("10.0.2.2", SERVER_PORT);// 查看本机IP,每次开机都同  
//			socket=MainActivity.socket;
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
			keyIn="o"+s+":::"+ID+"o";
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



	//public void init(){
	//	
	//	try{System.out.println("please enter...");
	//		keyIn = new BufferedReader(
	//				new InputStreamReader(System.in));
	//		socket = new Socket("127.0.0.1", SERVER_PORT);
	//		ps = new PrintStream(socket.getOutputStream());
	//		brServer = new BufferedReader(
	//				new InputStreamReader(socket.getInputStream()));
	//		String tip = "";
	//		while(true)
	//		{
	//			String userName = JOptionPane.showInputDialog(tip 
	//					+ "enter the username");
	//			ps.println(CrazyitProtocol.USER_ROUND + userName
	//					+ CrazyitProtocol.USER_ROUND);
	//			String result = brServer.readLine();
	//			if (result.equals(CrazyitProtocol.NAME_REP))
	//			{
	//				tip = "username exist!enter again";
	//				continue;
	//			}
	//			if (result.equals(CrazyitProtocol.LOGIN_SUCCESS))
	//			{
	//				break;
	//			}
	//		}
	//	}
	//	catch (UnknownHostException ex)
	//	{
	//		System.out.println("can not find the driver");
	//		closeRs();
	//		System.exit(1);
	//	}
	//	catch (IOException ex)
	//	{
	//		System.out.println("network anomally");
	//		closeRs();
	//		System.exit(1);
	//	}
	//	new testQQ.ClientThread(brServer).start();
	//}
	//public void readAndSend(){
	//	try{
	//		String line = null;
	//		while((line = keyIn.readLine()) != null){
	//			if (line.indexOf(":") > 0 && line.startsWith("/")){
	//				line = line.substring(1);
	//				ps.println(CrazyitProtocol.PRIVATE_ROUND + 
	//						line.split(":")[0] + CrazyitProtocol.SPLIT_SIGN
	//						+ line.split(":")[1] + CrazyitProtocol.PRIVATE_ROUND);//鏉╂瑩鍣风拋鈺冪秹妞ゅ吀绱舵潻鍥ㄦ降閻ㄥ嫭鏆熼幑顔垮殰瀹歌京鏁ら崘鎺戝娇闂呮柨绱戠亸鍗炪偨娴滐拷
	//			}
	//			else{
	//				ps.println(line);
	//			}
	//		}
	//	}
	//	catch (IOException ex){
	//		System.out.println("network anomally");
	//		closeRs();
	//		System.exit(1);
	//	}
	//}
	//private void closeRs(){
	//	try{
	//		if (keyIn != null){
	//			ps.close();
	//		}
	//		if (brServer != null){
	//			ps.close();
	//		}
	//		if (ps != null){
	//			ps.close();
	//		}
	//		if (socket != null){
	//			keyIn.close();
	//		}
	//	}
	//	catch (IOException ex){
	//		ex.printStackTrace();
	//	}
	//}

	//	private Button click=null;
	//	private void Click(){
	//		click=(Button)findViewById(R.id.button3);
	//		click.setOnClickListener(new OnClickListener(){
	//			public void onClick(View v){
	//				Intent intent = new Intent();
	//				intent.setClass(requirement.this, resultlist.class);
	//				requirement.this.startActivity(intent);
	//			}
	//		});
	//	}

}

