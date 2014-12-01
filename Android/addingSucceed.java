package com.example.bluetape;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import testQQ.CrazyitProtocol;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addingSucceed extends Activity{
	String detail;
	String search;
	String ID;
	String message;
	int SERVER_PORT = 30117;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addsucceed);
		// Get the message from the intent
		Intent intent = getIntent();
		detail = intent.getStringExtra(requirement.EXTRA_MESSAGE);
		search = intent.getStringExtra(requirement.STRING);
		ID = intent.getStringExtra(requirement.ID_USER);
		TextView textView=(TextView)findViewById(R.id.content);
		textView.setTextSize(32);
		Button back=(Button)findViewById(R.id.goback);
		Button anotherSearch=(Button)findViewById(R.id.anothersearch);
		Button viewFavorite=(Button)findViewById(R.id.viewfavorite);
		if(detail.equalsIgnoreCase("0")) {
			textView.setText("No matched result!");
			back.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					Intent intent = new Intent();
					intent.putExtra(requirement.ID_USER, ID);
					intent.setClass(addingSucceed.this, requirement.class);
					addingSucceed.this.startActivity(intent);
				}
			});
		}
		else if(search.equalsIgnoreCase("0")){
			textView.setText(detail);
			back.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					open("i"+ID+"i",ID);
					Intent intent = new Intent();
					intent.putExtra(requirement.EXTRA_MESSAGE, message);
					intent.putExtra(requirement.ID_USER, ID);
					intent.setClass(addingSucceed.this, viewMyFavorite.class);
					addingSucceed.this.startActivity(intent);
				}
			});
		}
		else{
			textView.setText(detail);
			back.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					open("o"+search+":::"+ID+"o",ID);
					Intent intent = new Intent();
					intent.putExtra(requirement.EXTRA_MESSAGE, message);
					intent.putExtra(requirement.ID_USER, ID);
					intent.putExtra(requirement.STRING, search);
					intent.setClass(addingSucceed.this, resultlist.class);
					addingSucceed.this.startActivity(intent);
				}
			});
		}
		anotherSearch.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent();
				intent.putExtra(requirement.ID_USER, ID);
				intent.setClass(addingSucceed.this, requirement.class);
				addingSucceed.this.startActivity(intent);
			}
		});
		viewFavorite.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent();
				open("i"+ID+"i",ID);
				if(message.equalsIgnoreCase("0")){
					intent.setClass(addingSucceed.this, display.class);
					addingSucceed.this.startActivity(intent);
				}
				else {
					intent.putExtra(requirement.ID_USER, ID);
					intent.putExtra(requirement.EXTRA_MESSAGE, message);
					intent.setClass(addingSucceed.this, viewMyFavorite.class);
					addingSucceed.this.startActivity(intent);
				}
			}
		});

	}
	
	public void open(String s,String ID){
    	Socket socket = null;  
        String userName = ID;
        try {  
            socket = new Socket("10.0.2.2", SERVER_PORT); 
            PrintWriter out = new PrintWriter(new BufferedWriter(  
                    new OutputStreamWriter(socket.getOutputStream())),  
                    true);  
            out.println(CrazyitProtocol.USER_ROUND + userName
				+ CrazyitProtocol.USER_ROUND); 

            BufferedReader br = new BufferedReader(  
                    new InputStreamReader(socket.getInputStream()));  
            String logIn = br.readLine();
            String keyIn=s;
            out.println(keyIn);
            out.close();  
            br.close();    
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
