package com.jmoney.luckeylink.controller;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jmoney.luckeylink.util.DataBaseUtil;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

@SuppressWarnings("unused")
public class Login_Controller {
    private static ResultSet rs;
    private static Statement sm;
	private static Connection con;

	static String code = null;
	static String msg = null;
    static String first_name = null;
	static String last_name = null;
    static String phone = null;
    static String email = null;
    static String key = null;
    static String date_joined = null;
    static String userphoto = null;
    
	//根据数据版本连接对应数据库
    public static String GetSqlResult(String DataVersion, String id, String FieldName)throws IOException{
		      
		    String sql = "select su.phone as phone,su.role_id as roleId,sr.role_desc as roleName,su.id as salesId,su.user_name as userName,su.photo_name as photoName "
		    		+ "from dafy_sales.sys_user_list su "
		    		+ "inner join  dafy_sales.sys_role_list sr on sr.role_id=su.role_id "
		    		+ "WHERE su.id="+id+" and su.ROLE_ID = sr.ROLE_ID";
		    try{
		    	DataBaseUtil.checkConnection_Oracle(DataVersion);
		        rs = sm.executeQuery(sql);
		        while(rs.next()){
		            String pa = rs.getString(FieldName);
		            System.out.println(pa);
		            return pa; 
		        }
		    }catch(SQLException e){
		        e.printStackTrace();
		    }finally{
		    	DataBaseUtil.close(sm,rs);
		    }
			return null;   
	} 
    
    /**
       * GET
	   * 指定接口地址，获取登录用户的信息
	   * @param ApiUrl
	   * @return UserDetail
	   */  
	public static Object[] GetUserInfo(String ApiUrl) throws Exception{
		 Response response = given()
	             .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
	             .contentType("application/json")
	             .log().all()
	             .request()
	             .when()
	             .get(ApiUrl);

		 response.print();
	     String Json = response.asString();
	     String code = response.jsonPath().get("code");
		 String msg = response.jsonPath().get("msg");

	     if("成功".equals(msg)){
	    	 first_name = response.jsonPath().get("data.first_name");
		     last_name = response.jsonPath().get("data.last_name");
		     phone = response.jsonPath().get("data.phone");
		     email = response.jsonPath().get("data.email");
		     key = response.jsonPath().get("data.key");
		     date_joined = response.jsonPath().get("data.date_joined");
		     userphoto = response.jsonPath().get("data.userphoto");	
		 }
	       
	     Object[] UserInfo ={Json,code,msg,first_name,last_name,phone,email,key,date_joined,userphoto};
	     return UserInfo;
	}
	
	/**
       * POST
	   * 指定接口地址，获取登录用户的信息
	   * @param ApiUrl
	   * @return UserDetail
	   */ 
	public static Object[] GetUserInfo(int ID,String ApiUrl, String Param) throws Exception{
	   Response response = given()
	           .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
	           .contentType("application/x-www-form-urlencoded; charset=UTF-8")
	           .log().all()
	           .request()
	           .body(Param)
	           .when()
	           .post(ApiUrl);
	   
	    response.print();
        String Json = response.asString();

        if(ID==2||ID==3||ID==4){
        	 int code1 = response.jsonPath().getInt("code");
        	 code=String.valueOf(code1);
        }else{
        	 code = response.jsonPath().get("code");
             msg = response.jsonPath().get("msg");
        	 if("成功".equals(msg)){
     	    	first_name = response.jsonPath().get("data.first_name");
     		     last_name = response.jsonPath().get("data.last_name");
     		     phone = response.jsonPath().get("data.phone");
     		     email = response.jsonPath().get("data.email");
     		     key = response.jsonPath().get("data.key");
     		     date_joined = response.jsonPath().get("data.date_joined");
     		     userphoto = response.jsonPath().get("data.userphoto");
     	    }
        }
        Object[] UserInfo ={Json,code,msg,first_name,last_name,phone,email,key,date_joined,userphoto};
	    return UserInfo;
	}
}
