package com.example.bluetape;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

import testQQ.CrazyitProtocol;
import android.util.Log;


import java.net.*;
import java.io.*;
import javax.swing.*;

public class AndriodClient {
	private static final int SERVER_PORT = 30117;
	private Socket socket;
	private static PrintWriter out;
	private static BufferedReader br;


	public static void logIn(String userName){
		try{ 
			Socket socket = new Socket("10.0.2.2", 30117); // 查看本机IP,每次开机都同  
			out = new PrintWriter(new BufferedWriter(  
					new OutputStreamWriter(socket.getOutputStream())),  
					true);  
			out.println(CrazyitProtocol.USER_ROUND + userName
					+ CrazyitProtocol.USER_ROUND); 
			br = new BufferedReader(  
					new InputStreamReader(socket.getInputStream()));
			String logIn = br.readLine();
		}
		catch (UnknownHostException ex)
		{
			System.out.println("can not find the driver");
			System.exit(1);
		}
		catch (IOException ex)
		{
			System.out.println("network anomally");
			System.exit(1);
		}
	}

	public static String readAndSend(String keyIn) throws IOException{
		while(keyIn!= null){
			if (keyIn.indexOf(":") > 0 && keyIn.startsWith("/")){
				keyIn = keyIn.substring(1);
				out.println(CrazyitProtocol.PRIVATE_ROUND + 
						keyIn.split(":")[0] + CrazyitProtocol.SPLIT_SIGN
						+ keyIn.split(":")[1] + CrazyitProtocol.PRIVATE_ROUND);//这里让网页传过来的数据自己用冒号隔开就好了
			}
			else{
				out.println(keyIn);
			}
		}
		return br.readLine();
	}

}