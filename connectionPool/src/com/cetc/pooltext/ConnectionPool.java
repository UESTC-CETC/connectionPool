package com.cetc.pooltext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ConnectionPool {
	private List<Integer> list = null;
	public static void main(String[] args){
		ConnectPoolImp cpi = ConnectPoolImp.getInstance();
		Connection con = cpi.getConnection();
		try {
			Statement state = con.createStatement();
			String sqlStr = "select username from user";
			ResultSet rs = state.executeQuery(sqlStr);
			while(rs.next()){
				System.out.println(rs.getString("username"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
