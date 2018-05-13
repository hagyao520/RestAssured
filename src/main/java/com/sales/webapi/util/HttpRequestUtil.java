package com.sales.webapi.util;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HttpRequestUtil {
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param method
     *            指定请求方法：GET, POST 等
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数是 name1=value1&name2=value2 的形式。
     * @return result 返回结果
     */
    public static Map<String, String> sendPost(String method, String url, String param) {
        PrintWriter out = null;
        BufferedReader br = null;
        String result = "";
        int responseCode = 0;
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 打开和URL之间的连接
            HttpURLConnection httpConn = (HttpURLConnection) new URL(url)
                    .openConnection();

            // 发送POST请求必须设置如下两行
            // 设置可输入、 可输出
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);

            httpConn.setReadTimeout(150000);
            httpConn.setConnectTimeout(15000);

//            // 连接后不自动跳转
            httpConn.setInstanceFollowRedirects(false);
//
//            // 设置通用的请求属性
            httpConn.setRequestProperty("Accept-Charset", "utf-8");
            httpConn.setRequestProperty("User-Agent", "systempatch");
            httpConn.setRequestProperty("Accpet-Encoding", "gzip");
            httpConn.setRequestProperty("Charset", "utf-8");
            httpConn.setRequestProperty("Content-Language", "zh-cn");

            // 设置提交方式
            httpConn.setRequestMethod(method);

            httpConn.connect();
            
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());

            // 发送请求参数
            out.print(param);
            out.flush();
            responseCode = httpConn.getResponseCode();
            map.put("code", String.valueOf(responseCode));
            // 打印 http 状态码
             System.out.println("responseCode: " + responseCode);

            if (HttpURLConnection.HTTP_OK == responseCode) {
                // 定义BufferedReader输入流来读取URL的响应
                br = new BufferedReader(new InputStreamReader(
                        httpConn.getInputStream(), "UTF-8"));
                String responseCookie = httpConn.getHeaderField("Set-Cookie");// 取到所用的Cookie

                @SuppressWarnings("unused")
				String sessionIdString = "";

                if (responseCookie != null) {

                    sessionIdString = responseCookie.substring(0, responseCookie.indexOf(";"));

                }

                String strLine;
                StringBuffer responseBuf = new StringBuffer();

                while ((strLine = br.readLine()) != null) {
                    responseBuf.append(strLine);
                }

                result = responseBuf.toString();
                map.put("result", result);
//                System.out.println("result: " + result);
            }

        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return map;
    }
    
    public static Map<String, String> sendPostcookie(String method, String url, String param, String cookie) {
        PrintWriter out = null;
        BufferedReader br = null;
        String result = "";
        int responseCode = 0;
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 打开和URL之间的连接
            HttpURLConnection httpConn = (HttpURLConnection) new URL(url)
                    .openConnection();

            // 发送POST请求必须设置如下两行
            // 设置可输入、 可输出
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);

            httpConn.setReadTimeout(150000);
            httpConn.setConnectTimeout(15000);

//            // 连接后不自动跳转
            httpConn.setInstanceFollowRedirects(false);
//
//            // 设置通用的请求属性
            httpConn.setRequestProperty("Accept-Charset", "utf-8");
            httpConn.setRequestProperty("User-Agent", "systempatch");
            httpConn.setRequestProperty("Accpet-Encoding", "gzip");
            httpConn.setRequestProperty("Charset", "utf-8");
            httpConn.setRequestProperty("Content-Language", "zh-cn");
            httpConn.setRequestProperty("Cookie",cookie); 
            
            // 设置提交方式
            httpConn.setRequestMethod(method);

            httpConn.connect();
            
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());

            // 发送请求参数
            out.print(param);
            out.flush();
            responseCode = httpConn.getResponseCode();
            map.put("code", String.valueOf(responseCode));
            // 打印 http 状态码
             System.out.println("responseCode1: " + responseCode);

            if (HttpURLConnection.HTTP_OK == responseCode) {
                // 定义BufferedReader输入流来读取URL的响应
                br = new BufferedReader(new InputStreamReader(
                        httpConn.getInputStream(), "UTF-8"));
                String strLine;
                StringBuffer responseBuf = new StringBuffer();

                while ((strLine = br.readLine()) != null) {
                    responseBuf.append(strLine);
                }

                result = responseBuf.toString();
                map.put("result", result);
                System.out.println(result);
                System.out.println("==============================================================================================================================================");
            }

        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return map;
    }
    
    public static Map<String, String> sendGet() {
		String line = "";
		String httpResults = "";
		String url=("http://www.weather.com.cn/data/cityinfo/101280601.html");
        Map<String, String> map = new HashMap<String, String>();
		try {
			HttpURLConnection connection = URLConnectionUtil
					.getConnection(url);
//			DataOutputStream out = null;
			// 建立实际的连接
			connection.connect();
//			out = new DataOutputStream(connection.getOutputStream());
//			out.flush();
//			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				httpResults = httpResults + line.toString();
			}
			reader.close();
			// 断开连接
			connection.disconnect();
        } catch (Exception e) {
                 System.out.println(e.toString());
                 e.printStackTrace();
         }
		return map;
	}

    /**
     * 指定API接口URL,POST请求参数,获取Cookie
     * @param ApiUrl
     * @param Param
     * @return
     * @throws Exception
     */
    public static Map<String, String> getPostCcookie(String ApiUrl, String Param) throws Exception{

        Response response = given()
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .contentType("application/json")
                .request()
                .body(Param)
                .when()
                .post(ApiUrl);

        response.print();
        Map<String, String> allCookies=response.getCookies();
        System.out.println("allCookies"+allCookies);

        return allCookies;
    }

    /**
     * 指定API接口URL,POST请求参数,获取Cookie
     * @param ApiUrl
     * @param Param
     * @param Cookie
     * @return
     * @throws Exception
     */
    public static String GetJsonResult(String ApiUrl, String Param, Map<String, String> Cookie) throws Exception{

        Response response = given()
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .contentType("application/json")
                .log().all()
                .request()
                .body(Param)
                .cookies(Cookie)
                .when()
                .post(ApiUrl);

        // 打印出 response 的body
//        response1.print();
        String result = response.asString();
        System.out.println("返回的值Json:"+result);

        return result;
    }

    /**
     * 指定API接口URL,POST请求参数,获取Cookie
     * @param ApiUrl
     * @param Param
     * @param Cookie
     * @return
     * @throws Exception
     */
    public static int GetJsonIntValue(String ApiUrl, String Param, Map<String, String> Cookie, String Value) throws Exception{

        Response response = given()
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .contentType("application/json")
                .log().all()
                .request()
                .body(Param)
                .cookies(Cookie)
                .when()
                .post(ApiUrl);


        int Value1 = response.jsonPath().getInt(Value);
        System.out.println("Value:"+Value1);

        return Value1;
    }

    /**
     * 指定API接口URL,POST请求参数,获取Cookie
     * @param ApiUrl
     * @param Param
     * @param Cookie
     * @return
     * @throws Exception
     */
    public static String GetJsonStringValue(String ApiUrl, String Param, Map<String, String> Cookie, String Value) throws Exception{

        Response response = given()
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .contentType("application/json")
                .log().all()
                .request()
                .body(Param)
                .cookies(Cookie)
                .when()
                .post(ApiUrl);

        String Value1 = response.jsonPath().get(Value);
        System.out.println("Value:"+Value1);

        return Value1;
    }

    /**
     * 指定API接口URL,POST请求参数,获取Cookie
     * @param ApiUrl
     * @param Param
     * @param Cookie
     * @return
     * @throws Exception
     */
    public static int GetStatusCode(String ApiUrl, String Param, Map<String, String> Cookie) throws Exception{

        Response response = given()
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .contentType("application/json")
                .log().all()
                .request()
                .body(Param)
                .cookies(Cookie)
                .when()
                .post(ApiUrl);

        // 打印出 response 的statusCode
        int statusCode = response.getStatusCode();
        System.out.println("statusCode:" + statusCode);

        return statusCode;
    }

    /**
     * 指定API接口URL,POST请求参数,获取Cookie
     * @param Param
     * @param ApiUrl
     * @return
     * @throws Exception
     */
    public static String getPostcookie(String ApiUrl,String Param) throws Exception{  
    	
    	 String result = "";
         int responseCode = 0;
         BufferedReader br = null;
         Map<String, String> map = new HashMap<String, String>();
        //登录  
        URL url = new URL(ApiUrl);  
//        String param = "username="+username+"&password="+password;  
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
        conn.setDoInput(true);  
        conn.setDoOutput(true);  
        conn.setRequestMethod("POST");  
        OutputStream out = conn.getOutputStream();  
        out.write(Param.getBytes());  
        out.flush();  
        out.close();  
        String cookieVal = ""; 
        String sessionId = "";  
        String key = null;  
        //取cookie  
        for(int i = 1; (key = conn.getHeaderFieldKey(i)) != null; i++){  
            if(key.equalsIgnoreCase("set-cookie")){  
                cookieVal = conn.getHeaderField(i);  
                cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));  
                sessionId = sessionId + cookieVal + ";"; 
                System.out.println("cookieVal:"+cookieVal);
            }  
        }
        responseCode = conn.getResponseCode();
        map.put("code", String.valueOf(responseCode));
        // 打印 http 状态码
         System.out.println("responseCode: " + responseCode);

        if (HttpURLConnection.HTTP_OK == responseCode) {
            // 定义BufferedReader输入流来读取URL的响应
            br = new BufferedReader(new InputStreamReader(
            		conn.getInputStream(), "UTF-8"));
            String strLine;
            StringBuffer responseBuf = new StringBuffer();

            while ((strLine = br.readLine()) != null) {
                responseBuf.append(strLine);
            }

            result = responseBuf.toString();
            map.put("result", result);
            System.out.println(result);
        } 
		return sessionId;  
    }  
    
    /**  
     * 指定API接口URL,POST请求参数,获取Cookie
     * @param ApiUrl_Line
     * @param ArgLineIndex
     * @return  
     * @throws Exception  
     */  
    @SuppressWarnings("unused")
	public static String getPostcookie(int ApiUrl_Line,int ApiUrl_Column,
            int Act_Line,int Act_Column,int Method_Line,int Method_Column,
            int ArgLineIndex,int ArgColumnIndex,int TITLE_LINE_INDEX,
            String Param_Column1,String Param_Column2,String Param_Column3,String Param_Column4,String Param_Column5
            ) throws Exception {
    	
    	String ApiUrl = "";
    	String result = "";
    	String Act = "";
        String Method = "";
        int responseCode = 0;
        BufferedReader br = null;
        Map<String, String> map = new HashMap<String, String>();
        List<Map<String, String>> data = null;
        boolean Flag = false;
        
        ApiUrl = ExcelUtil.getInstance().readExcelCell(ApiUrl_Line-1, ApiUrl_Column-1);
        Act = ExcelUtil.getInstance().readExcelCell(Act_Line-1, Act_Column-1);
        Method = ExcelUtil.getInstance().readExcelCell(Method_Line-1, Method_Column-1);       
        Flag = MobileApiToolsUtil.isArgEquals(ArgLineIndex-1, ArgColumnIndex-1, TITLE_LINE_INDEX-1);

        if (ApiUrl.equals("") || Act.equals("") || Method.equals("") || !Flag) {
//            logger.error("请检查 Excel 中 Interface、Act、Method、ArgName 是否设置正确...");
            System.out.println("请检查 Excel 中 Interface、Act、Method、ArgName 是否设置正确...");
            System.exit(-1);
        }
        
        data = ExcelUtil.getInstance().readExcelAllData(TITLE_LINE_INDEX-1);
        
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
            	
            	//根据Excel列名称,获取单元格内容
                Map<String, String> map1 = data.get(i);
             
                //指定请求参数
                String Param ="userId=" + map1.get(Param_Column1) + "&" + "password=" + map1.get(Param_Column2) + "&" + "type=" + map1.get(Param_Column3)+ "&" + "version=" + map1.get(Param_Column4)+ "&" + "identification=" + map1.get(Param_Column5);
                
        //登录  
        URL url = new URL(ApiUrl);  
//        String param = "username="+username+"&password="+password;  
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
        conn.setDoInput(true);  
        conn.setDoOutput(true);  
        conn.setRequestMethod("POST");  
        OutputStream out = conn.getOutputStream();  
        out.write(Param.getBytes());  
        out.flush();  
        out.close();  
        String cookieVal = ""; 
        String sessionId = "";  
        String key = null;  
        //取cookie  
        for(int i1 = 1; (key = conn.getHeaderFieldKey(i1)) != null; i1++){  
            if(key.equalsIgnoreCase("set-cookie")){  
                cookieVal = conn.getHeaderField(i1);  
                cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));  
                sessionId = sessionId + cookieVal + ";"; 
                System.out.println("cookieVal:"+cookieVal);
            }  
        }
        responseCode = conn.getResponseCode();
        map.put("code", String.valueOf(responseCode));
        // 打印 http 状态码
         System.out.println("responseCode: " + responseCode);

        if (HttpURLConnection.HTTP_OK == responseCode) {
            // 定义BufferedReader输入流来读取URL的响应
            br = new BufferedReader(new InputStreamReader(
            		conn.getInputStream(), "UTF-8"));
            String strLine;
            StringBuffer responseBuf = new StringBuffer();

            while ((strLine = br.readLine()) != null) {
                responseBuf.append(strLine);
            }

            result = responseBuf.toString();
            map.put("result", result);
            System.out.println(result);
        } 
		return sessionId;  
    }
        }
		return Method; 
    } 
}