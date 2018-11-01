package com.jmoney.luckeylink.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

@SuppressWarnings("unused")
public class DataBaseUtil {

    private static final Logger LOG = LoggerFactory.getLogger(DataBaseUtil.class);
    
    private static final String Oracle_FDRIVER = ConfigUtil.getProperty("Oracle.jdbc.Fdriver", ConstantsUtil.CONFIG_JDBC);
   
    private static final String Oracle_FURL = ConfigUtil.getProperty("Oracle.jdbc.Furl", ConstantsUtil.CONFIG_JDBC);
    
    private static final String Oracle_FUSERNAME = ConfigUtil.getProperty("Oracle.jdbc.Fusername", ConstantsUtil.CONFIG_JDBC);
    
    private static final String Oracle_FPASSWORD = ConfigUtil.getProperty("Oracle.jdbc.Fpassword", ConstantsUtil.CONFIG_JDBC);
    
    private static final String Oracle_DDRIVER = ConfigUtil.getProperty("Oracle.jdbc.Ddriver", ConstantsUtil.CONFIG_JDBC);
    
    private static final String Oracle_DURL = ConfigUtil.getProperty("Oracle.jdbc.Durl", ConstantsUtil.CONFIG_JDBC);
    
    private static final String Oracle_DUSERNAME = ConfigUtil.getProperty("Oracle.jdbc.Dusername", ConstantsUtil.CONFIG_JDBC);
    
    private static final String Oracle_DPASSWORD = ConfigUtil.getProperty("Oracle.jdbc.Dpassword", ConstantsUtil.CONFIG_JDBC);
    
   private static final String Oracle_TDRIVER = ConfigUtil.getProperty("Oracle.jdbc.Tdriver", ConstantsUtil.CONFIG_JDBC);
    
    private static final String Oracle_TURL = ConfigUtil.getProperty("Oracle.jdbc.Turl", ConstantsUtil.CONFIG_JDBC);
    
    private static final String Oracle_TUSERNAME = ConfigUtil.getProperty("Oracle.jdbc.Tusername", ConstantsUtil.CONFIG_JDBC);
    
    private static final String Oracle_TPASSWORD = ConfigUtil.getProperty("Oracle.jdbc.Tpassword", ConstantsUtil.CONFIG_JDBC);
    
    
    private static final String MySql_FDRIVER = ConfigUtil.getProperty("MySql.jdbc.Fdriver", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySql_FURL = ConfigUtil.getProperty("MySql.jdbc.Furl", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySql_FUSERNAME = ConfigUtil.getProperty("MySql.jdbc.Fusername", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySql_FPASSWORD = ConfigUtil.getProperty("MySql.jdbc.Fpassword", ConstantsUtil.CONFIG_JDBC);
   
    private static final String MySql_DDRIVER = ConfigUtil.getProperty("MySql.jdbc.Ddriver", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySql_DURL = ConfigUtil.getProperty("MySql.jdbc.Durl", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySql_DUSERNAME = ConfigUtil.getProperty("MySql.jdbc.Dusername", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySql_DPASSWORD = ConfigUtil.getProperty("MySql.jdbc.Dpassword", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySql_TDRIVER = ConfigUtil.getProperty("MySql.jdbc.Tdriver", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySql_TURL = ConfigUtil.getProperty("MySql.jdbc.Turl", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySql_TUSERNAME = ConfigUtil.getProperty("MySql.jdbc.Tusername", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySql_TPASSWORD = ConfigUtil.getProperty("MySql.jdbc.Tpassword", ConstantsUtil.CONFIG_JDBC);
    
    
    private static final String MySqlSSH_FHost = ConfigUtil.getProperty("MySql.ssh.Fhost", ConstantsUtil.CONFIG_JDBC);
    
    private static final int MySqlSSH_FPort = Integer.valueOf(ConfigUtil.getProperty("MySql.ssh.Fport", ConstantsUtil.CONFIG_JDBC));
    
    private static final String MySqlSSH_FUser = ConfigUtil.getProperty("MySql.ssh.Fuser", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySqlSSH_FPassword = ConfigUtil.getProperty("MySql.ssh.Fpassword", ConstantsUtil.CONFIG_JDBC);
   
    private static final String MySqlSSH_FKeyFile = ConfigUtil.getProperty("MySql.ssh.FkeyFile", ConstantsUtil.CONFIG_JDBC);
    
    private static final int MySqlSSH_FLport = Integer.valueOf(ConfigUtil.getProperty("MySql.ssh.Flport", ConstantsUtil.CONFIG_JDBC));
    
    private static final String MySqlSSH_FRhost = ConfigUtil.getProperty("MySql.ssh.Frhost", ConstantsUtil.CONFIG_JDBC);
    
    private static final int MySqlSSH_FRport = Integer.valueOf(ConfigUtil.getProperty("MySql.ssh.Frport", ConstantsUtil.CONFIG_JDBC));
    
	private static final String MySqlSSH_FDRIVER = ConfigUtil.getProperty("MySql.jdbc.Fdriver", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySqlSSH_FURL = ConfigUtil.getProperty("MySql.jdbc.Furl", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySqlSSH_FUSERNAME = ConfigUtil.getProperty("MySql.jdbc.Fusername", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySqlSSH_FPASSWORD = ConfigUtil.getProperty("MySql.jdbc.Fpassword", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySqlSSH_DHost = ConfigUtil.getProperty("MySql.ssh.Dhost", ConstantsUtil.CONFIG_JDBC);
    
    private static final int MySqlSSH_DPort = Integer.valueOf(ConfigUtil.getProperty("MySql.ssh.Dport", ConstantsUtil.CONFIG_JDBC));
    
    private static final String MySqlSSH_DUser = ConfigUtil.getProperty("MySql.ssh.Duser", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySqlSSH_DPassword = ConfigUtil.getProperty("MySql.ssh.Dpassword", ConstantsUtil.CONFIG_JDBC);
   
    private static final String MySqlSSH_DKeyFile = ConfigUtil.getProperty("MySql.ssh.DkeyFile", ConstantsUtil.CONFIG_JDBC);
    
    private static final int MySqlSSH_DLport = Integer.valueOf(ConfigUtil.getProperty("MySql.ssh.Dlport", ConstantsUtil.CONFIG_JDBC));
    
    private static final String MySqlSSH_DRhost = ConfigUtil.getProperty("MySql.ssh.Drhost", ConstantsUtil.CONFIG_JDBC);
    
    private static final int MySqlSSH_DRport = Integer.valueOf(ConfigUtil.getProperty("MySql.ssh.Drport", ConstantsUtil.CONFIG_JDBC));
    
	private static final String MySqlSSH_DDRIVER = ConfigUtil.getProperty("MySql.jdbc.Ddriver", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySqlSSH_DURL = ConfigUtil.getProperty("MySql.jdbc.Durl", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySqlSSH_DUSERNAME = ConfigUtil.getProperty("MySql.jdbc.Dusername", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySqlSSH_DPASSWORD = ConfigUtil.getProperty("MySql.jdbc.Dpassword", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySqlSSH_THost = ConfigUtil.getProperty("MySql.ssh.Thost", ConstantsUtil.CONFIG_JDBC);
    
    private static final int MySqlSSH_TPort = Integer.valueOf(ConfigUtil.getProperty("MySql.ssh.Tport", ConstantsUtil.CONFIG_JDBC));
    
    private static final String MySqlSSH_TUser = ConfigUtil.getProperty("MySql.ssh.Tuser", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySqlSSH_TPassword = ConfigUtil.getProperty("MySql.ssh.Tpassword", ConstantsUtil.CONFIG_JDBC);
   
    private static final String MySqlSSH_TKeyFile = ConfigUtil.getProperty("MySql.ssh.TkeyFile", ConstantsUtil.CONFIG_JDBC);
    
    private static final int MySqlSSH_TLport = Integer.valueOf(ConfigUtil.getProperty("MySql.ssh.Tlport", ConstantsUtil.CONFIG_JDBC));
    
    private static final String MySqlSSH_TRhost = ConfigUtil.getProperty("MySql.ssh.Trhost", ConstantsUtil.CONFIG_JDBC);
    
    private static final int MySqlSSH_TRport = Integer.valueOf(ConfigUtil.getProperty("MySql.ssh.Trport", ConstantsUtil.CONFIG_JDBC));
    
	private static final String MySqlSSH_TDRIVER = ConfigUtil.getProperty("MySql.jdbc.ssh.Tdriver", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySqlSSH_TURL = ConfigUtil.getProperty("MySql.jdbc.ssh.Turl", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySqlSSH_TUSERNAME = ConfigUtil.getProperty("MySql.jdbc.ssh.Tusername", ConstantsUtil.CONFIG_JDBC);
    
    private static final String MySqlSSH_TPASSWORD = ConfigUtil.getProperty("MySql.jdbc.ssh.Tpassword", ConstantsUtil.CONFIG_JDBC);
    
	private static ResultSet rs;
	private static Statement sm;
	private static Connection con;

    public static void checkConnection_Oracle(String DataVersion){
        try {
            if(con == null || con.isClosed())
            	Connect_Oracle(DataVersion);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

	private static void checkConnection_MySql(String DataVersion){
        try {
            if(con == null || con.isClosed())
            	Connect_MySql(DataVersion);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.fillInStackTrace());
        }
    }
    
	private static void checkConnect_SSHKeyMySql(String DataVersion){
        try {
            if(con == null || con.isClosed())
            	Connect_SSHKeyMySql(DataVersion);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.fillInStackTrace());
        }
    }
	
	private static void checkConnect_SSHPassWordMySql(String DataVersion){
        try {
            if(con == null || con.isClosed())
            	Connect_SSHPassWordMySql(DataVersion);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.fillInStackTrace());
        }
    }
    /**
     * 连接Oracle数据库
     * @throws Exception
     */
    public static void Connect_Oracle(String DataVersion) throws Exception{
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
    public static void Connect_MySql(String DataVersion) throws Exception{
    	try {
        	if("正式环境".equals(DataVersion)){
	    	    Class.forName(MySql_FDRIVER);
	    	    con = DriverManager.getConnection(MySql_FURL, MySql_FUSERNAME, MySql_FPASSWORD); 
	    	    sm = con.createStatement();
	    	}else if("测试环境".equals(DataVersion)){
	    		Class.forName(MySql_TDRIVER);
	    	    con = DriverManager.getConnection(MySql_TURL, MySql_TUSERNAME, MySql_TPASSWORD); 
	    	    sm = con.createStatement();
		    }else if("开发环境".equals(DataVersion)){
	    		Class.forName(MySql_DDRIVER);
	    	    con = DriverManager.getConnection(MySql_DURL, MySql_DUSERNAME, MySql_DPASSWORD); 
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
     * 连接SHHMySql数据库
     * @throws Exception
     */
    public static void Connect_SSHKeyMySql(String DataVersion) throws Exception{
    	try {
        	if("正式环境".equals(DataVersion)){
        		SSHKey(DataVersion);
        		Class.forName(MySqlSSH_TDRIVER);
	    	    con = DriverManager.getConnection(MySqlSSH_TURL,MySqlSSH_TUSERNAME,MySqlSSH_TPASSWORD);
	    	    sm = con.createStatement();
	    	}else if("测试环境".equals(DataVersion)){
	    		SSHKey(DataVersion);
        		Class.forName(MySqlSSH_TDRIVER);
	    	    con = DriverManager.getConnection(MySqlSSH_TURL,MySqlSSH_TUSERNAME,MySqlSSH_TPASSWORD);
	    	    sm = con.createStatement();
		    }else if("开发环境".equals(DataVersion)){
		    	SSHKey(DataVersion);
        		Class.forName(MySqlSSH_TDRIVER);
	    	    con = DriverManager.getConnection(MySqlSSH_TURL,MySqlSSH_TUSERNAME,MySqlSSH_TPASSWORD);
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

    public static void Connect_SSHPassWordMySql(String DataVersion) throws Exception{
    	try {
        	if("正式环境".equals(DataVersion)){
        		SSHPassWord(DataVersion);
        		Class.forName(MySqlSSH_TDRIVER);
	    	    con = DriverManager.getConnection(MySqlSSH_TURL,MySqlSSH_TUSERNAME,MySqlSSH_TPASSWORD);
	    	    sm = con.createStatement();
	    	}else if("测试环境".equals(DataVersion)){
	    		SSHPassWord(DataVersion);
        		Class.forName(MySqlSSH_TDRIVER);
	    	    con = DriverManager.getConnection(MySqlSSH_TURL,MySqlSSH_TUSERNAME,MySqlSSH_TPASSWORD);
	    	    sm = con.createStatement();
		    }else if("开发环境".equals(DataVersion)){
		    	SSHPassWord(DataVersion);
        		Class.forName(MySqlSSH_TDRIVER);
	    	    con = DriverManager.getConnection(MySqlSSH_TURL,MySqlSSH_TUSERNAME,MySqlSSH_TPASSWORD);
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
    
    public static void SSHKey(String DataVersion) throws Exception{
    	String passphrase = "11";
    	try {
    		if("正式环境".equals(DataVersion)){
    			JSch jsch = new JSch();
 	            jsch.addIdentity(MySqlSSH_FKeyFile);
 	            Session session = jsch.getSession(MySqlSSH_FUser, MySqlSSH_FHost, MySqlSSH_FPort);
 	            UserInfo ui = new MyUserInfo(passphrase);
 	            session.setUserInfo(ui);
 	            session.connect();
// 	            System.out.println("SSH服务器连接成功，版本信息为："+session.getServerVersion());//这里打印SSH服务器版本信息
 	            int assinged_port = session.setPortForwardingL(MySqlSSH_FLport, MySqlSSH_FRhost,MySqlSSH_FRport);
// 	            System.out.println("端口映射成功：localhost:" + assinged_port + " -> " + MySqlSSH_FRhost + ":" + MySqlSSH_FRport);
	    	}else if("开发环境".equals(DataVersion)){
	    		JSch jsch = new JSch();
 	            jsch.addIdentity(MySqlSSH_DKeyFile);
 	            Session session = jsch.getSession(MySqlSSH_DUser, MySqlSSH_DHost, MySqlSSH_DPort);
 	            UserInfo ui = new MyUserInfo(passphrase);
 	            session.setUserInfo(ui);
 	            session.connect();
// 	            System.out.println("SSH服务器连接成功，版本信息为："+session.getServerVersion());//这里打印SSH服务器版本信息
 	            int assinged_port = session.setPortForwardingL(MySqlSSH_DLport, MySqlSSH_DRhost,MySqlSSH_DRport);
// 	            System.out.println("端口映射成功：localhost:" + assinged_port + " -> " + MySqlSSH_DRhost + ":" + MySqlSSH_DRport);
		    }else if("测试环境".equals(DataVersion)){
		    	JSch jsch = new JSch();
 	            jsch.addIdentity(MySqlSSH_TKeyFile);
 	            Session session = jsch.getSession(MySqlSSH_TUser, MySqlSSH_THost, MySqlSSH_TPort);
 	            UserInfo ui = new MyUserInfo(passphrase);
 	            session.setUserInfo(ui);
 	            session.connect();
// 	            System.out.println("SSH服务器连接成功，版本信息为："+session.getServerVersion());//这里打印SSH服务器版本信息
 	            int assinged_port = session.setPortForwardingL(MySqlSSH_TLport, MySqlSSH_TRhost,MySqlSSH_TRport);
// 	            System.out.println("端口映射成功：localhost:" + assinged_port + " -> " + MySqlSSH_TRhost + ":" + MySqlSSH_TRport);
		    }  
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    public static void SSHPassWord(String DataVersion) throws Exception{
    	String passphrase = "11";
    	try {
    		if("正式环境".equals(DataVersion)){
    			JSch jsch = new JSch();  
                Session session = jsch.getSession(MySqlSSH_FUser, MySqlSSH_FHost, MySqlSSH_FPort);  
                session.setPassword(MySqlSSH_FPassword);  
                session.setConfig("StrictHostKeyChecking", "no");  
                session.connect();  
// 	            System.out.println("SSH服务器连接成功，版本信息为："+session.getServerVersion());//这里打印SSH服务器版本信息
 	            int assinged_port = session.setPortForwardingL(MySqlSSH_FLport, MySqlSSH_FRhost,MySqlSSH_FRport);
// 	            System.out.println("端口映射成功：localhost:" + assinged_port + " -> " + MySqlSSH_FRhost + ":" + MySqlSSH_FRport);
	    	}else if("开发环境".equals(DataVersion)){
	    		JSch jsch = new JSch();  
                Session session = jsch.getSession(MySqlSSH_DUser, MySqlSSH_DHost, MySqlSSH_DPort);  
                session.setPassword(MySqlSSH_DPassword);  
                session.setConfig("StrictHostKeyChecking", "no");  
                session.connect(); 
// 	            System.out.println("SSH服务器连接成功，版本信息为："+session.getServerVersion());//这里打印SSH服务器版本信息
 	            int assinged_port = session.setPortForwardingL(MySqlSSH_DLport, MySqlSSH_DRhost,MySqlSSH_DRport);
// 	            System.out.println("端口映射成功：localhost:" + assinged_port + " -> " + MySqlSSH_DRhost + ":" + MySqlSSH_DRport);
		    }else if("测试环境".equals(DataVersion)){
		    	JSch jsch = new JSch();  
	            Session session = jsch.getSession(MySqlSSH_TUser, MySqlSSH_THost, MySqlSSH_TPort);  
	            session.setPassword(MySqlSSH_TPassword);  
	            session.setConfig("StrictHostKeyChecking", "no");  
	            session.connect(); 
// 	            System.out.println("SSH服务器连接成功，版本信息为："+session.getServerVersion());//这里打印SSH服务器版本信息
 	            int assinged_port = session.setPortForwardingL(MySqlSSH_TLport, MySqlSSH_TRhost,MySqlSSH_TRport);
// 	            System.out.println("端口映射成功：localhost:" + assinged_port + " -> " + MySqlSSH_TRhost + ":" + MySqlSSH_TRport);
		    }  
        } catch (Exception e) {
                e.printStackTrace();
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
    
    public static class MyUserInfo implements UserInfo {
 	   private String passphrase = null;

 	   public MyUserInfo(String passphrase) {
 	    this.passphrase = passphrase;
 	   }

 	   public String getPassphrase() {
 	    return passphrase;
 	   }

 	   public String getPassword() {
 	    return null;
 	   }

 	   public boolean promptPassphrase(String s) {
 	    return true;
 	   }

 	   public boolean promptPassword(String s) {
 	    return true;
 	   }

 	   public boolean promptYesNo(String s) {
 	    return true;
 	   }

 	   public void showMessage(String s) {
 	    System.out.println(s);
 	   }
 }
}
