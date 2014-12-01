package sql;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;
@SuppressWarnings("serial")
public class httpserver extends HttpServlet {

	/**
	 * 
	 */
	//		private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ServletOutputStream out = response.getOutputStream();
//		PrintWriter writer = response.getWriter(); 
//		response.setContentType("text/html"); //required
//		String[] infor = new String[15];
//		String[] type = new String[15];
//		type[0] = "Request";
//		type[1] = "ID";
//		type[2] = "Email_address";// email address
//		type[3] = "User_password";
//		type[4] = "FirstName";
//		type[5] = "LastName";
//		type[6] = "Gender";
//		type[7] = "Department";
//		infor[0] = request.getParameter("Request");
//		infor[1] = request.getParameter("ID");                         
//		infor[2] = request.getParameter("Email_address");
//		infor[3] = request.getParameter("User_password");
//		infor[4] = request.getParameter("FirstName");
//		infor[5] = request.getParameter("LastName");
//		infor[6] = request.getParameter("Gender");
//		infor[7] = request.getParameter("Department");
//		//				else if (infor.startsWith(Protocol.PRIVATE_ROUND)
//		//					&& infor.endsWith(Protocol.PRIVATE_ROUND)){
//		//					String userAndMsg = getRealMsg(infor);
//		//					String user = userAndMsg.split(Protocol.SPLIT_SIGN)[0];
//		//					String msg = userAndMsg.split(Protocol.SPLIT_SIGN)[1];
//		//					Server.clients.get(user).println(Server.clients
//		//						.getKeyByValue(ps) + "..." + msg);
//		//				}
//		int result=5;
//		ResultSet resultSet=null;
//		if(infor[0].equals("Add"))
//			try {
//				result = User.addData(infor[2],infor[3],infor[4],infor[5],infor[6],infor[7]);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		else if(infor[0].equals("Update")){
//			for(int i =2; i<=7; i++){
//				if (infor[i]!=null) result = User.updateData(infor[1],type[i],infor[i]);
//			}
//		}
//		else if(infor[0].equals("Delete")){
//			try {
//				result = User.deleteData(infor[2]);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		//		else if(infor.startsWith(Protocol.SingleSearch_SIGN)){
//		//			try {
//		//				resultSet = User.singleSearch(request.split(Protocol.SPLIT_SIGN)[0], request.split(Protocol.SPLIT_SIGN)[1]);
//		//				ps.println(resultSet);
//		//			} catch (SQLException e) {
//		//				// TODO Auto-generated catch block
//		//				e.printStackTrace();
//		//			}
//		//		}
//		else if(infor[0].equals("MultipleSearch")){
//			String sql ="select * from Personal_information where ";
//			if(infor[2]!=null){
//				sql+=type[2] +" = ("+infor[2] + ") AND ";
//			}
//			for(int i = 3; i<=7; i++){
//				if(infor[i]!=null){
//					sql+=type[i] +" = '"+infor[i] + "' AND ";
//				}
//			}
//			sql+="ID not in (1)";
//			resultSet = User.MultipleSearch(sql);
//		}
//
//
//		if(result!=5) writer.print("result");
//		else
//			try {
//				writer.print(xml.generateXML(resultSet));
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} //print() or println() same
//
//		try {
//			User.conn.close();		//out.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter writer = response.getWriter(); 
		response.setContentType("text/html"); //required
		String[] infor = new String[15];
		String[] type = new String[15];
		type[0] = "Request";
		type[1] = "ID";
		type[2] = "Email_address";// email address
		type[3] = "User_password";
		type[4] = "FirstName";
		type[5] = "LastName";
		type[6] = "Gender";
		type[7] = "Department";
		infor[0] = request.getParameter("Request");
		infor[1] = request.getParameter("ID");                         
		infor[2] = request.getParameter("Email_address");
		infor[3] = request.getParameter("User_password");
		infor[4] = request.getParameter("FirstName");
		infor[5] = request.getParameter("LastName");
		infor[6] = request.getParameter("Gender");
		infor[7] = request.getParameter("Department");
		
		int result=5;
		ResultSet resultSet=null;
		System.out.println("Go into the server");
		if(infor[0].equals("Add"))
			try {
				System.out.println("I got the request");
				result = User.addData(infor[2],infor[3],infor[4],infor[5],infor[6],infor[7]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else if(infor[0].equals("Update")){
			for(int i =3; i<=7; i++){
				if (infor[i]!=null) result = User.updateData(infor[2],type[i],infor[i]);
			}
		}
		else if(infor[0].equals("Delete")){
			try {
				result = User.deleteData(infor[2]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(infor[0].equals("MultipleSearch")){
			String sql ="select * from Personal_information where ";
//			if(infor[2]!=null){
//				sql+=type[2] +" = ("+infor[2] + ") AND ";
//			}
			for(int i = 2; i<=7; i++){
				if(infor[i]!=null&&infor[i].length()>0)
					sql+=type[i] +" = '"+infor[i] + "' AND ";
			}
			sql+="ID not in (1)";
			System.out.println(sql);
			resultSet = User.MultipleSearch(sql);
			try {
				if(resultSet.next()==false) result=-1;
				else resultSet.previous();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			}


		if(result!=5) writer.print(result);
		else
			try {
				writer.print(xml.generateXML(resultSet));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //print() or println() same

		try {
			User.conn.close();		//out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

