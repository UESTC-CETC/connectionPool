package com.cetc.pooltext;

/**
 * create connection pool
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ConnectPoolImp {
	private List<Connection> poolList = null;
	private static int poolSize = 1;
	private Connection conn;
	private static ConnectPoolImp connectPool = null;
	
	private ConnectPoolImp(){
		poolList = new ArrayList<Connection>();
		createConnection();
	}
	public static ConnectPoolImp getInstance(){
		if(connectPool == null){
			connectPool = new ConnectPoolImp(); 
		}
		return connectPool;
	}
	private void createConnection(){
		int size = Integer.valueOf(ResourceManager.getSize());
		if(size>0){
			poolSize = size;
		}
		for(int i=0;i<poolSize;i++){
			try{
				Class.forName(ResourceManager.getDriverClass());
				Connection con = DriverManager.getConnection(ResourceManager.getUrl(),
						ResourceManager.getUserName(),ResourceManager.getPassword());
				poolList.add(con);
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public synchronized Connection getConnection(){
		if(poolList.size()>0){
				Connection con = poolList.get(0);
				poolList.remove(con);
				return con;
		}
		return null;
	}
	
	public synchronized void release(Connection con){
		poolList.add(con);
	}
	
	public synchronized void closePool(){
		int k = poolList.size();
		for(int i=0;i<k;i++){
			try {
				
				conn = (Connection) poolList.get(0);
				conn.close();
				poolList.remove(0);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
