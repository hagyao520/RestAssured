package com.sales.webapi.handler;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;

import com.sales.webapi.util.HttpRequestUtil;
import com.sales.webapi.util.MobileApiToolsUtil;
import com.sales.webapi.util.DataBaseUtil;
import com.sales.webapi.util.ExcelUtil;

public class LoginHandler {
    private static Logger logger = Logger.getLogger(LoginHandler.class);

	/**
	 * <br>获取Excel数据</br>
	 * @author  刘智King
	 * @date     2018年4月20日 下午6:01:04
	 */
    public static void GetExcelInstance() {
        logger.info(LoginHandler.class);
        System.out.println(LoginHandler.class);
		ExcelUtil.getInstance().setFilePath("src/test/resources/Excel/GiveU.Sales.WebApi.xlsx");
		ExcelUtil.getInstance().setSheetName("Login");
    }
    
	/**
	 * <br>初始化Excel数据</br>
	 * @author  刘智King
	 * @date     2018年4月20日 下午6:01:04
	 */
    public static void InitializeExcelData() { 
        try {
            logger.info("初始化: " + ExcelUtil.getInstance().getFilePath() + ", " + ExcelUtil.getInstance().getSheetName());
            System.out.println("初始化: " + ExcelUtil.getInstance().getFilePath() + ", " + ExcelUtil.getInstance().getSheetName());
            MobileApiToolsUtil.initializeData(6-1, "Run", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "$id_Exp", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "$id_Act", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "phone_Exp", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "phone_Act", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "roleId_Exp", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "roleId_Act", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "roleName_Exp", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "roleName_Act", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "salesId_Exp", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "salesId_Act", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "userName_Exp", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "userName_Act", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "identification_Exp", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "identification_Act", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "photoName_Exp", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "photoName_Act", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "ActualResult", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "ResultCode", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "TestResult", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "RunningTime", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "Json", "", 4);
            MobileApiToolsUtil.initializeData(6-1, "FailHint", "", 4);
            logger.info(ExcelUtil.getInstance().getFilePath() + ", " + ExcelUtil.getInstance().getSheetName() + "初始化完成");
            System.out.println(ExcelUtil.getInstance().getFilePath() + ", " + ExcelUtil.getInstance().getSheetName() + "初始化完成");
            System.out.println("==============================================================================================================================================");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * <br>根据数据库版本和用例ID，从数据库获取信息并写入到Excel预期值</br>
	 * @author  刘智King
	 * @date     2018年4月20日 下午6:01:04
	 * @param  DataVersion
	 * @param  ID
	 */
    public static void WriteExcelExpected(String DataVersion,int ID){ 
    	
    	int TITLE_LINE_INDEX =6;
    	List<Map<String, String>> data = null;
        data = ExcelUtil.getInstance().readExcelAllData(TITLE_LINE_INDEX-1);
        
        try {
        	if (data != null) {
//                for (int i = 0; i < data.size(); i++) {
        		  int i = ID;
        	      Map<String, String> map = data.get(i);
                  String Sql =map.get("userId");

                  logger.info("根据数据库查询结果, 开始写入预期值【Waiting...】");
                  System.out.println("根据数据库查询结果, 开始写入预期值【Waiting...】");
                  
//                  String $id_Exp= DataBase_Util.GetSqlResult(DataVersion, Sql,"$id_Exp");
        	      MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "$id_Exp", "2");
        	      
        	      String phone_Exp= DataBaseUtil.GetSqlResult(DataVersion, Sql,"phone");
        	      MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "phone_Exp", phone_Exp);

        	      String roleId_Exp= DataBaseUtil.GetSqlResult(DataVersion, Sql,"roleId");
        	      MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "roleId_Exp", roleId_Exp);
        	
        	      String roleName_Exp= DataBaseUtil.GetSqlResult(DataVersion, Sql,"roleName");
        	      MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "roleName_Exp", roleName_Exp);
        	      
        	      String salesId_Exp= DataBaseUtil.GetSqlResult(DataVersion, Sql,"salesId");
        	      MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "salesId_Exp", salesId_Exp);
        	      
        	      String userName_Exp= DataBaseUtil.GetSqlResult(DataVersion, Sql,"userName");
        	      MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "userName_Exp", userName_Exp);
        	      
//        	      String identification_Exp= DataBase_Util.GetSqlResult(DataVersion, Sql,"identification");
        	      MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "identification_Exp", "string");
        	      
        	      String photoName_Exp= DataBaseUtil.GetSqlResult(DataVersion, Sql,"photoName");
        	      MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "photoName_Exp", "http://10.10.11.136/"+photoName_Exp);
        	      
                 logger.info("对应用例预期值,写入成功【OK】");
                 System.out.println("对应预期值,写入成功【OK】");
                 System.out.println("==============================================================================================================================================");
               }
//            } 
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	/**
	 * <br>根据用例ID，执行Login相关接口请求，获取Json信息，并写入结果到Excel</br>
	 * @author  刘智King
	 * @date     2018年4月20日 下午6:01:04
	 * @param  ID
	 */
    public static void Login(int ID) throws Exception {

        int TITLE_LINE_INDEX =6;
        String PremiseUrl = "";
        String ApiUrl = "";
        String Act = "";
        String Method = "";
        List<Map<String, String>> data = null;
        boolean Flag = false;

        PremiseUrl = ExcelUtil.getInstance().readExcelCell(1-1, 2-1);
        ApiUrl = ExcelUtil.getInstance().readExcelCell(2-1, 2-1);
        Act = ExcelUtil.getInstance().readExcelCell(3-1, 2-1);
        Method = ExcelUtil.getInstance().readExcelCell(4-1, 2-1);
        Flag = MobileApiToolsUtil.isArgEquals(5-1, 2-1, TITLE_LINE_INDEX-1);

        if (PremiseUrl.equals("") ||ApiUrl.equals("") || Act.equals("") || Method.equals("") || !Flag) {
            logger.error("请检查 Excel 中 Interface、Act、Method、ArgName 是否设置正确...");
            System.out.println("请检查 Excel 中 Interface、Act、Method、ArgName 是否设置正确...");
            System.exit(-1);
        }

        data = ExcelUtil.getInstance().readExcelAllData(TITLE_LINE_INDEX-1);

        if (data != null) {
//            for (int i = 0; i < data.size(); i++) {
        	
            //根据Excel列名称,获取单元格内容
            Map<String, String> map1 = data.get(0);
            String userId1 = map1.get("userId");
            String password1 = map1.get("password");
            String type1 = map1.get("type");
            String version1 = map1.get("version");
            String identification1 = map1.get("identification");
 
            int i = ID;
            //根据Excel列名称,获取单元格内容
            Map<String, String> map = data.get(i);
            String userId = map.get("userId");
            String password = map.get("password");
            String type = map.get("type");
            String version = map.get("version");
            String identification = map.get("identification");
            
            //指定请求参数
//            final String Param1 = "{" +
//                    "\"userId\": \"666666\",\"password\": \"612426\",\"type\": \"string\",\"version\": \"string\",\"identification\": \"string\"}";
            
            final String Param1 = "{" +
                    "\"userId\": \""+userId1+"\",\"password\": \""+password1+"\",\"type\": \""+type1+"\",\"version\": \""+version1+"\",\"identification\": \""+identification1+"\"}";
            
            final String Param = "{" +
                    "\"userId\": \""+userId+"\",\"password\": \""+password+"\",\"type\": \""+type+"\",\"version\": \""+version+"\",\"identification\": \""+identification+"\"}";
            
            //指定请求方式,API地址,请求参数, 发起请求,获取Cookie值
            Map<String, String> CookieVal = HttpRequestUtil.getPostCcookie(PremiseUrl,Param1);

            String RsTmp = HttpRequestUtil.GetJsonResult(ApiUrl, Param,CookieVal);
//          int Code = HttpRequest_Util.GetStatusCode(ApiUrl, Param1, CookieVal);
            int Code = HttpRequestUtil.GetJsonIntValue(ApiUrl, Param, CookieVal,"code");  
            String message = HttpRequestUtil.GetJsonStringValue(ApiUrl, Param, CookieVal,"message");

            //写入Run列, 执行纪录
            MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "Run", "Y");

            //写入ResultCode列,返回的结果代码
            MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "ResultCode", String.valueOf(Code));

            //设置ResultCode单元格颜色
            if (Code == 1000){
                ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX-1, "ResultCode",TITLE_LINE_INDEX + i, 1);
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "TestResult", "OK");
                ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX-1, "TestResult",TITLE_LINE_INDEX + i, 1);
                
                String id_Act = HttpRequestUtil.GetJsonStringValue(ApiUrl, Param, CookieVal,"data.$id");
                String phone_Act = HttpRequestUtil.GetJsonStringValue(ApiUrl, Param, CookieVal,"data.phone");
                String roleId_Act = HttpRequestUtil.GetJsonStringValue(ApiUrl, Param, CookieVal,"data.roleId");
                String roleName_Act = HttpRequestUtil.GetJsonStringValue(ApiUrl, Param, CookieVal,"data.roleName");
                int salesId_Act = HttpRequestUtil.GetJsonIntValue(ApiUrl, Param, CookieVal,"data.salesId");
                String userName_Act = HttpRequestUtil.GetJsonStringValue(ApiUrl, Param, CookieVal,"data.userName");
                String identification_Act = HttpRequestUtil.GetJsonStringValue(ApiUrl, Param, CookieVal,"data.identification");
                String photoName_Act = HttpRequestUtil.GetJsonStringValue(ApiUrl, Param, CookieVal,"data.photoName");

                //期望结果与实际结果比较
                String $id_Exp = MobileApiToolsUtil.assertResult(map.get("$id_Exp"),id_Act);
                String phone_Exp = MobileApiToolsUtil.assertResult(map.get("phone_Exp"),phone_Act);
                String roleId_Exp = MobileApiToolsUtil.assertResult(map.get("roleId_Exp"),roleId_Act);
                String roleName_Exp = MobileApiToolsUtil.assertResult(map.get("roleName_Exp"),roleName_Act);
                String salesId_Exp = MobileApiToolsUtil.assertResult(map.get("salesId_Exp"), String.valueOf(salesId_Act));
                String userName_Exp = MobileApiToolsUtil.assertResult(map.get("userName_Exp"),userName_Act);
                String identification_Exp = MobileApiToolsUtil.assertResult(map.get("identification_Exp"),identification_Act);
                String photoName_Exp = MobileApiToolsUtil.assertResult(map.get("photoName_Exp"),photoName_Act);
                
                //写入实际结果
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "$id_Act", id_Act);
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "phone_Act", phone_Act);
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "roleId_Act", roleId_Act);
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "roleName_Act", roleName_Act);
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "salesId_Act", String.valueOf(salesId_Act));
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "userName_Act", userName_Act);
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "identification_Act", identification_Act);
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "photoName_Act", photoName_Act);

                //写入测试结果单元格背景色
                if ($id_Exp.equals("OK")){}
                else{
                	ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX-1, "$id_Act",TITLE_LINE_INDEX + i, 0);}

                //写入测试结果单元格背景色
                if (phone_Exp.equals("OK")){}
                else{
                	ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX-1, "phone_Act",TITLE_LINE_INDEX + i, 0);}

                //写入测试结果单元格背景色
                if (roleId_Exp.equals("OK")){}
                else{
                	ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX-1, "roleId_Act",TITLE_LINE_INDEX + i, 0);}
                
                //写入测试结果单元格背景色
                if (roleName_Exp.equals("OK")){}
                else{
                	ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX-1, "roleName_Act",TITLE_LINE_INDEX + i, 0);}
                
                //写入测试结果单元格背景色
                if (salesId_Exp.equals("OK")){}
                else{
                	ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX-1, "salesId_Act",TITLE_LINE_INDEX + i, 0);}
                
                //写入测试结果单元格背景色
                if (userName_Exp.equals("OK")){}
                else{
                	ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX-1, "userName_Act",TITLE_LINE_INDEX + i, 0);}
                
                //写入测试结果单元格背景色
                if (identification_Exp.equals("OK")){}
                else{
                	ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX-1, "identification_Act",TITLE_LINE_INDEX + i, 0);}
                
                //写入测试结果单元格背景色
                if (photoName_Exp.equals("OK")){}
                else{
                	ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX-1, "photoName_Act",TITLE_LINE_INDEX + i, 0);}
                
                //写入测试通过与否,设置测试结果单元格背景色
//              &是位与操作，一定会执行； &&是逻辑与操作，如果&&的前面为false了，后面的就不会执行了。
//              |是位或操作、一定会执行； || 是逻辑或操作，如果||的前面为true了，||的后面就不会执行了
                if ($id_Exp.equals("OK")&phone_Exp.equals("OK")&roleId_Exp.equals("OK")&roleName_Exp.equals("OK")&
                        salesId_Exp.equals("OK")&userName_Exp.equals("OK")&identification_Exp.equals("OK")&photoName_Exp.equals("OK")){
                    MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "TestResult", "OK");
                    ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX-1, "TestResult",TITLE_LINE_INDEX + i, 1);}
                else{
                	MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "TestResult", "NG");
                	ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX-1, "TestResult",TITLE_LINE_INDEX + i, 0);}
                
                //写入执行时间
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "RunningTime", MobileApiToolsUtil.getDate());

                //写入转换的JSON结果
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "Json", RsTmp);
                
            }else{
                ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX-1, "ResultCode",TITLE_LINE_INDEX + i, 0);
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "TestResult", "NG");
            	ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX-1, "TestResult",TITLE_LINE_INDEX + i, 0);
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "FailHint", "【message】"+message+"");
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "RunningTime", MobileApiToolsUtil.getDate());
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "Json", RsTmp);
            }   
        }
//        }
    }
    
	/**
	 * <br>根据用例ID，获取用例名称信息，打印到控制台并写入Excel</br>
	 * @author  刘智King
	 * @date     2018年4月20日 下午6:01:04
	 * @param  ID
	 */
    public static void GetCaseInfo(int ID) throws Exception {
    	
    	int TITLE_LINE_INDEX=6;
    	List<Map<String, String>> data = null;
    	data = ExcelUtil.getInstance().readExcelAllData(TITLE_LINE_INDEX-1);
    	if (data != null) {
//            for (int i = 0; i < data.size(); i++) {
                int i = ID;
                
            	//根据Excel列名称,获取单元格内容
                Map<String, String> map = data.get(i);
                String CaseID = map.get("CaseID");
                String CaseName = map.get("CaseName");
                String $id_Exp = map.get("$id_Exp");
                String $id_Act = map.get("$id_Act");
                String phone_Exp = map.get("phone_Exp");
                String phone_Act = map.get("phone_Act");
                String roleId_Exp = map.get("roleId_Exp");
                String roleId_Act = map.get("roleId_Act");
                String roleName_Exp = map.get("roleName_Exp");
                String roleName_Act = map.get("roleName_Act");
                String salesId_Exp = map.get("salesId_Exp");
                String salesId_Act = map.get("salesId_Act");
                String userName_Exp = map.get("userName_Exp");
                String userName_Act = map.get("userName_Act");
                String identification_Exp = map.get("identification_Exp");
                String identification_Act = map.get("identification_Act");
                String photoName_Exp = map.get("photoName_Exp");
                String photoName_Act = map.get("photoName_Act");
                String ResultCode = map.get("ResultCode");
                String TestResult = map.get("TestResult");
                String FailHint = map.get("FailHint");
                
                //打印Caseinfo
                logger.info("CaseID: " + CaseID + ", CaseName: " + CaseName + ", $id_Exp: " + $id_Exp + ", $id_Act: " + $id_Act + ", phone_Exp: " + phone_Exp + ", phone_Act: " + phone_Act +
                ", roleId_Exp: " + roleId_Exp + ", roleId_Act: " + roleId_Act + ", roleName_Exp: " + roleName_Exp + ", roleName_Act: " + roleName_Act + ", salesId_Exp: " + salesId_Exp + ", salesId_Act: " + salesId_Act +
                ", userName_Exp: " + userName_Exp +", userName_Act: " + userName_Act + ", identification_Exp: " + identification_Exp +", identification_Act: " + identification_Act + ", photoName_Exp: " + photoName_Exp +", photoName_Act: " + photoName_Act +
                ", ResultCode: " + ResultCode +", TestResult: " + TestResult+ ", FailHint: " + FailHint);
                logger.info("==============================================================================================================================================");

                
                System.out.println("CaseID: " + CaseID + ", CaseName: " + CaseName + ", $id_Exp: " + $id_Exp + ", $id_Act: " + $id_Act + ", phone_Exp: " + phone_Exp + ", phone_Act: " + phone_Act +
                ", roleId_Exp: " + roleId_Exp + ", roleId_Act: " + roleId_Act + ", roleName_Exp: " + roleName_Exp + ", roleName_Act: " + roleName_Act + ", salesId_Exp: " + salesId_Exp + ", salesId_Act: " + salesId_Act +
                ", userName_Exp: " + userName_Exp +", userName_Act: " + userName_Act + ", identification_Exp: " + identification_Exp +", identification_Act: " + identification_Act + ", photoName_Exp: " + photoName_Exp +", photoName_Act: " + photoName_Act +
                ", ResultCode: " + ResultCode +", TestResult: " + TestResult + ", FailHint: " + FailHint);
                System.out.println("==============================================================================================================================================");
            }
//        }
    }
    
	/**
	 * <br>根据用例ID，检查预期与实际是否相等，不等则提示错误信息，并写入结果</br>
	 * @author  刘智King
	 * @date     2018年4月20日 下午6:01:04
	 * @param  ID
	 */
	public static void resultCheck(int ID) throws IOException{
		
		String ApiUrl = "";
		List<Map<String, String>> data = null;
		
		ApiUrl = ExcelUtil.getInstance().readExcelCell(2-1, 2-1);
		
    	data = ExcelUtil.getInstance().readExcelAllData(6-1);
    	  	
    	if (data != null) {
//            for (int i = 0; i < data.size(); i++) {
            int i = ID;
            	
            	//根据Excel列名称,获取单元格内容
                Map<String, String> map = data.get(i);
                String CaseID = map.get("CaseID");
                String CaseName = map.get("CaseName");
                String userId = map.get("userId");
                String password = map.get("password");
                String type = map.get("type");
                String version = map.get("version");
                String identification = map.get("identification");
                String $id_Exp = map.get("$id_Exp");
                String $id_Act = map.get("$id_Act");
                String phone_Exp = map.get("phone_Exp");
                String phone_Act = map.get("phone_Act");
                String roleId_Exp = map.get("roleId_Exp");
                String roleId_Act = map.get("roleId_Act");
                String roleName_Exp = map.get("roleName_Exp");
                String roleName_Act = map.get("roleName_Act");
                String salesId_Exp = map.get("salesId_Exp");
                String salesId_Act = map.get("salesId_Act");
                String userName_Exp = map.get("userName_Exp");
                String userName_Act = map.get("userName_Act");
                String identification_Exp = map.get("identification_Exp");
                String identification_Act = map.get("identification_Act");
                String photoName_Exp = map.get("photoName_Exp");
                String photoName_Act = map.get("photoName_Act");
                String Json = map.get("Json");
             
                Reporter.log("用例ID: "+ CaseID);        
		        Reporter.log("用例名称:"+ CaseName);
		        Reporter.log("请求地址: "+ ApiUrl);
		        Reporter.log("请求参数: "+ "userId: " + userId + ", password: " + password + ", type: " + type + 
		        		     ", version: " + version + ", identification: "+ identification);
		        Reporter.log("返回结果: "+ Json);
		        
		        checkEquals("$id",$id_Exp,$id_Act,ID);	
		        checkEquals("phone",phone_Exp,phone_Act,ID);
		        checkEquals("roleId",roleId_Exp,roleId_Act,ID);
		        checkEquals("roleName",roleName_Exp,roleName_Act,ID);
		        checkEquals("salesId",salesId_Exp,salesId_Act,ID);
		        checkEquals("userName",userName_Exp,userName_Act,ID);
		        checkEquals("identification",identification_Exp,identification_Act,ID);		        
		        checkEquals("photoName",photoName_Exp,photoName_Act,ID);
            }
//        }
	}

	public static void checkEquals(String Expected,String Actual,String FailHint,int ID){
		int i = ID;
		int TITLE_LINE_INDEX =6;
		try {
			Assert.assertEquals(Expected,Actual);
			Reporter.log("用例结果: 〖"+FailHint+"〗=>Expected: " + "【"+Expected+"】" + ", Actual: "+ "【"+Actual+"】");
	        System.out.println("用例结果: 【"+FailHint+"】=>Expected: " + "【"+Expected+"】" + ", Actual: "+ "【"+Actual+"】");
        }
 	    catch (Error e)  {
 	    	MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "FailHint", "【"+FailHint+"】预期结果和实际结果不一致");
 	    	Assert.fail(""+FailHint+" =>Expected 【"+ Expected +"】"+" "+"but found 【"+ Actual +"】");
 	    }
	}
	
}