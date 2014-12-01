package sql;

import java.sql.*;

public class xml {
	public static String generateXML(ResultSet rs) throws SQLException { 

		final StringBuffer buffer = new StringBuffer(1024 * 4); 
		//		if (rs == null) return ""; 
		//		if (!rs.next()) return ""; 
		buffer.append("<?xml version=\1.0\" encoding=\"UTF-8\"?>\n"); //XML的头部信息 
		buffer.append("<ResultSet>\n"); 
		ResultSetMetaData rsmd = rs.getMetaData(); //得到结果集的定义结构 
		int colCount = rsmd.getColumnCount(); //得到列的总数 
		int id=0;
		while(rs.next()){
			// 对放回的全部数据逐一处理 
			//			格式为row id , col name, col context 
			buffer.append("\t<row id=\"").append(id).append("\">\n"); 
			for (int i = 1; i <= colCount; i++) { 
				int type = rsmd.getColumnType(i); //获取字段类型 
				buffer.append("\t\t<col name=\"" + rsmd.getColumnName(i) + "\">"); 
				buffer.append(getvalue(rs, i, type)); 
				buffer.append("</col>\n"); 
			} 
			buffer.append("\t</row>\n");
			id++;
		} 
		buffer.append("</ResultSet>"); 
		//		rs.close(); 
		return buffer.toString(); 
	} 

	/** 
	 * This method gets the value of the specified column 
	 * 通用的读取结果集某一列的值并转化为String表达 
	 * @param ResultSet rs 输入的纪录集 
	 * @param int colNum 第几列 
	 * @param int type 数据类型 
	 */ 

//	public static boolean isNumeric(String str){
//		int a = 0;
//		for (int i = str.length();--i>=0;){   
//			if (Character.isDigit(str.charAt(i))){
//				a++;
//			}
//		}
//		if(a>=8)
//			return true;
//		else
//			return false;
//	}
	private static String getvalue(final ResultSet rs, int colNum, int type) throws SQLException { 
		switch (type) { 
		case Types.ARRAY: 
		case Types.BLOB: 
		case Types.CLOB: 
		case Types.DISTINCT: 
		case Types.LONGVARBINARY: 
		case Types.VARBINARY: 
		case Types.BINARY: 
		case Types.REF: 
		case Types.STRUCT: 
			return "undefined"; 
		default: { 
			Object value = rs.getObject(colNum); 
			if (rs.wasNull() || (value == null)) 
				return ("null"); 
			else 
				return (value.toString()); 
		} 
		} 
	}



}
