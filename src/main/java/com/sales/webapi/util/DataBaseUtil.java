package com.sales.webapi.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataBaseUtil {

    private static final Logger LOG = LoggerFactory.getLogger(DataBaseUtil.class);
    
    private static final String Oracle_FDRIVER = ConfigUtil.getProperty("Oracle.jdbc.Fdriver", ConstantsUtil.CONFIG_JDBC);
   
    private static final String Oracle_FURL = ConfigUtil.getProperty("Oracle.jdbc.Furl", ConstantsUtil.CONFIG_JDBC);
    
    private static final String Oracle_FUSERNAME = ConfigUtil.getProperty("Oracle.jdbc.Fusername", ConstantsUtil.CONFIG_JDBC);
    
    private static final String Oracle_FPASSWORD = ConfigUtil.getProperty("Oracle.jdbc.Fpassword", ConstantsUtil.CONFIG_JDBC);
    
    
    private static final String Oracle_TDRIVER = ConfigUtil.getProperty("Oracle.jdbc.Tdriver", ConstantsUtil.CONFIG_JDBC);
    
    private static final String Oracle_TURL = ConfigUtil.getProperty("Oracle.jdbc.Turl", ConstantsUtil.CONFIG_JDBC);
    
    private static final String Oracle_TUSERNAME = ConfigUtil.getProperty("Oracle.jdbc.Tusername", ConstantsUtil.CONFIG_JDBC);
    
    private static final String Oracle_TPASSWORD = ConfigUtil.getProperty("Oracle.jdbc.Tpassword", ConstantsUtil.CONFIG_JDBC);
    
    
    private static final String Oracle_DDRIVER = ConfigUtil.getProperty("Oracle.jdbc.Ddriver", ConstantsUtil.CONFIG_JDBC);
    
    private static final String Oracle_DURL = ConfigUtil.getProperty("Oracle.jdbc.Durl", ConstantsUtil.CONFIG_JDBC);
    
    private static final String Oracle_DUSERNAME = ConfigUtil.getProperty("Oracle.jdbc.Dusername", ConstantsUtil.CONFIG_JDBC);
    
    private static final String Oracle_DPASSWORD = ConfigUtil.getProperty("Oracle.jdbc.Dpassword", ConstantsUtil.CONFIG_JDBC);
    
    
    private static final String MySql_DRIVER = ConfigUtil.getProperty("MySql.jdbc.driver", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySql_URL = ConfigUtil.getProperty("MySql.jdbc.url", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySql_USERNAME = ConfigUtil.getProperty("MySql.jdbc.username", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySql_PASSWORD = ConfigUtil.getProperty("MySql.jdbc.password", ConstantsUtil.CONFIG_JDBC);
   
	private static ResultSet rs;
	private static Statement sm;
	private static Connection con;

	//根据数据版本连接对应数据库
    public static String GetSqlResult(String DataVersion, String id, String FieldName)throws IOException{
      
	    String sql = "select su.phone as phone,su.role_id as roleId,sr.role_desc as roleName,su.id as salesId,su.user_name as userName,su.photo_name as photoName "
	    		+ "from dafy_sales.sys_user_list su "
	    		+ "inner join  dafy_sales.sys_role_list sr on sr.role_id=su.role_id "
	    		+ "WHERE su.id="+id+" and su.ROLE_ID = sr.ROLE_ID";
	    try{
	    	checkConnection_Oracle(DataVersion);
	        ResultSet rs = sm.executeQuery(sql);
	        while(rs.next()){
	            String pa = rs.getString(FieldName);
	            System.out.println(pa);
	            return pa; 
	        }
	    }catch(SQLException e){
	        e.printStackTrace();
	    }finally{
	        close(sm,rs);
	    }
		return null;   
	}

	//对比用户名和密码是不匹配
	public static boolean logincompare1(String username, String password){
	    boolean m = false;
	    String sql = "select PASSWORD from dafy_sales.sys_user_list where id="+ username +"";
	    try{
	    	Class.forName(Oracle_TDRIVER);
    	    con = DriverManager.getConnection(Oracle_TURL, Oracle_TUSERNAME, Oracle_TPASSWORD); 
	    	sm = con.createStatement();
	        ResultSet rs = sm.executeQuery(sql);
	        if(rs.next()){
	            String pa = rs.getString(1);
	            System.out.println(pa + " " + password);
	            if(pa.equals(password)){
	                m = true;
	            }else {
	                   JOptionPane.showMessageDialog(null, "密码错误！");
	            }
	        }else {
	               JOptionPane.showMessageDialog(null, "用户名不存在！");
	        }
	        close(sm,rs);   
	    }catch(SQLException | ClassNotFoundException e){
	        e.printStackTrace();
	    }
	    return m;
	}
	
	//对比合同号是否匹配,判断合同是否存在
	public static boolean contractcompare(String DataVersion,String contract_no){
	    boolean m = false;
	    String sql = "select CONTRACT_NO from dafy_sales.cs_credit where contract_no="+ contract_no +"";
	    try{
	    	 checkConnection_Oracle(DataVersion);
	         ResultSet rs = sm.executeQuery(sql);
	         if(rs.next()){
	              String pa = rs.getString(1);
	              System.out.println(pa+" " +contract_no);
	            if(pa.equals(contract_no)){
	            m = true;
	            }else {
	                   JOptionPane.showMessageDialog(null, "合同号和数据库不匹配！");
	            }
	            }else {
	                   JOptionPane.showMessageDialog(null, "合同号不存在！");
	            }
	            close(sm,rs);
	      }catch(SQLException e){
	            e.printStackTrace();
	      }
	      return m;
	}

    public static void checkConnection_Oracle(String DataVersion){
        try {
            if(con == null || con.isClosed())
            	connect_Oracle(DataVersion);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unused")
	private static void checkConnection_MySql(){
        try {
            if(con == null || con.isClosed())
            	connect_MySql();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.fillInStackTrace());
        }
    }
    
    /**
     * 连接Oracle数据库
     * @throws Exception
     */
    public static void connect_Oracle(String DataVersion) throws Exception{
        try {
        	if("正式环境".equals(DataVersion)){
	    	    Class.forName(Oracle_FDRIVER);
	    	    con = DriverManager.getConnection(Oracle_FURL, Oracle_FUSERNAME, Oracle_FPASSWORD); 
	    	    sm = con.createStatement();
	    	}
	    	if("测试环境".equals(DataVersion)){
	    		Class.forName(Oracle_TDRIVER);
	    	    con = DriverManager.getConnection(Oracle_TURL, Oracle_TUSERNAME, Oracle_TPASSWORD); 
	    	    sm = con.createStatement();
		    }
	    	if("开发环境".equals(DataVersion)){
	    		Class.forName(Oracle_DDRIVER);
	    	    con = DriverManager.getConnection(Oracle_DURL, Oracle_DUSERNAME, Oracle_DPASSWORD); 
	    	    sm = con.createStatement();
		    }
	    	LOG.info("数据库连接成功");
        } catch (Exception e) {
            String message = "数据库连接失败";
            if(e instanceof ClassNotFoundException)
                message = "数据库驱动类未找到";
            throw new Exception(message, e.fillInStackTrace());
        } 
    }
    
    /**
     * 连接MySql数据库
     * @throws Exception
     */
    public static void connect_MySql() throws Exception{
        try {
            Class.forName(MySql_DRIVER);
            con = DriverManager.getConnection(MySql_URL, MySql_USERNAME, MySql_PASSWORD);
            LOG.info("数据库连接成功");
        } catch (Exception e) {
            String message = "数据库连接失败";
            if(e instanceof ClassNotFoundException)
                message = "数据库驱动类未找到";
            throw new Exception(message, e.fillInStackTrace());
        } 
    }
     
    /**
     * 释放资源并关闭数据库
     */
    public static void close(Statement sm, ResultSet rs){
    	try {
    		if(sm != null){
                sm.close();
                sm = null;
            }
            if(rs != null){
                rs.close();
                rs = null;
            }  
            LOG.info("数据库资源释放成功！");
        } catch (SQLException e) {
        	e.printStackTrace();
        	LOG.info("数据库资源释放失败！");
        }
        try {
            if(con != null && !con.isClosed())
            	con.close();
            con = null;
            LOG.info("数据库连接关闭成功！");
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info("数据库连接关闭失败！");
        }
    }
}
