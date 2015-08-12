package org.phoenix.extend.druid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 


import com.alibaba.druid.pool.DruidDataSource;
/**
 * 对Druid的封装，便于使用
 * @author mengfeiyang
 *
 */
public class DruidUtil{
    private static DruidDataSource dataSource  = new DruidDataSource();
    public static ThreadLocal<Connection> container = new ThreadLocal<Connection>();
    
    /**
     * 初始化配置，其他的一些初始配置：
     *  dataSource.setInitialSize(2);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setPoolPreparedStatements(false);
     * @param url
     * @param username
     * @param password
     */
    public static void config(String url,String username, String password){
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setPoolPreparedStatements(false);
    }
 
    /**
     * 获取数据连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn =null;
        try{
            conn = dataSource.getConnection();
            container.set(conn);
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    /***获取当前线程上的连接开启事务*/
    public static void startTransaction(){
        Connection conn=container.get();//首先获取当前线程的连接
        if(conn==null){//如果连接为空
            conn=getConnection();//从连接池中获取连接
            container.set(conn);//将此连接放在当前线程上
        }
        try{
            conn.setAutoCommit(false);//开启事务
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //提交事务
    public static void commit(){
        try{
            Connection conn=container.get();//从当前线程上获取连接if(conn!=null){//如果连接为空，则不做处理
            if(null!=conn){
                conn.commit();//提交事务
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
 
 
    /***回滚事务*/
    public static void rollback(){
        try{
            Connection conn=container.get();//检查当前线程是否存在连接
            if(conn!=null){
                conn.rollback();//回滚事务
                container.remove();//如果回滚了，就移除这个连接
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static ResultSet getQueryResultSet(String sql){
        PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
	        return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public static PreparedStatement getPreparedStatement(String sql){
        PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement(sql);
	        return ps;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public static boolean executeSql(String sql){
        PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement(sql);
			boolean result = ps.execute(sql);
	        close();
	        return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    }
    /***关闭连接*/
    public static void close(){
        try{
            Connection conn=container.get();
            if(conn!=null){
                conn.close();
            }
        }catch(SQLException e){
            throw new RuntimeException(e.getMessage(),e);
        }finally{
            try{
                container.remove();//从当前线程移除连接切记
            } catch(Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}

