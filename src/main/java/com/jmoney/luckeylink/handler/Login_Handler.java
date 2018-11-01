package com.jmoney.luckeylink.handler;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;

import com.jmoney.luckeylink.controller.Login_Controller;
import com.jmoney.luckeylink.util.ExcelUtil;
import com.jmoney.luckeylink.util.MobileApiToolsUtil;
import com.jmoney.luckeylink.util.StringUtil;
import com.jmoney.luckeylink.util.DecodeUnicodeUtil;

public class Login_Handler {
    private static Logger logger = Logger.getLogger(Login_Handler.class);
    static int TITLE_LINE_INDEX=5;//接口用例标题所在行
    static int ArgName_Number=2;//接口所需参数的个数
    static int Act_Number=8;//接口需要效验参数的个数
    
    static String Param = null;//接口参数
    static String msg_Act = null;
    
	/**
	 * <br>获取Excel数据</br>
	 * @author  刘智King
	 * @date     2018年4月20日 下午6:01:04
	 */
    public static void GetExcelInstance() {
        logger.info(Login_Handler.class);
        System.out.println(Login_Handler.class);
		ExcelUtil.getInstance().setFilePath("src/test/java/TestCasexls/JMoney.Luckeylink.Api.xlsm");
		ExcelUtil.getInstance().setSheetName("Login");
    }
    
	/**
	 * <br>初始化Excel数据</br>
	 * @author  刘智King
	 * @date     2018年4月20日 下午6:01:04
	 */
    public static void InitializeExcelData() { 
    	GetExcelInstance();
    	int color = 4;
        try {
            logger.info("初始化: " + ExcelUtil.getInstance().getFilePath() + ", " + ExcelUtil.getInstance().getSheetName());
            System.out.println("开始初始化: " + ExcelUtil.getInstance().getFilePath() + ", " + ExcelUtil.getInstance().getSheetName());

            MobileApiToolsUtil.initializeData(TITLE_LINE_INDEX, ""+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3)+"", "", color);
            System.out.println("正在初始化："+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3)+"列");
            if(Act_Number<1){
            	for(int i =1;i<Act_Number+6;i++){
                	MobileApiToolsUtil.initializeData(TITLE_LINE_INDEX, ""+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+Act_Number+i)+"", "", color);
                	System.out.println("正在初始化："+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+Act_Number+i)+"列");
            	}
            }else if(Act_Number==1){
            	for(int i =1;i<Act_Number+6;i++){
                	MobileApiToolsUtil.initializeData(TITLE_LINE_INDEX, ""+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+Act_Number+i)+"", "", color);
                	System.out.println("正在初始化："+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+Act_Number+i)+"列");
            	}
            }else if(Act_Number>1){
            	for(int i =1;i<Act_Number+1;i++){
            		if(StringUtil.isEqual(ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+2*i), ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+2*i+2))){
            			System.out.println("效验值："+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+2*i)+"重复，请检查后重试！");
            			System.exit(0);
            		}else{
            			MobileApiToolsUtil.initializeData(TITLE_LINE_INDEX, ""+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+2*i)+"", "", color);
                    	System.out.println("正在初始化："+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+2*i)+"列");
            		}
            	}
            	MobileApiToolsUtil.initializeData(TITLE_LINE_INDEX, ""+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+Act_Number*2+1)+"", "", color);
            	System.out.println("正在初始化："+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+Act_Number*2+1)+"列");
            	MobileApiToolsUtil.initializeData(TITLE_LINE_INDEX, ""+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+Act_Number*2+2)+"", "", color);
            	System.out.println("正在初始化："+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+Act_Number*2+2)+"列");
            	MobileApiToolsUtil.initializeData(TITLE_LINE_INDEX, ""+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+Act_Number*2+3)+"", "", color);
            	System.out.println("正在初始化："+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+Act_Number*2+3)+"列");
            	MobileApiToolsUtil.initializeData(TITLE_LINE_INDEX, ""+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+Act_Number*2+4)+"", "", color);
            	System.out.println("正在初始化："+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+Act_Number*2+4)+"列");
            	MobileApiToolsUtil.initializeData(TITLE_LINE_INDEX, ""+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+Act_Number*2+5)+"", "", color);
            	System.out.println("正在初始化："+ExcelUtil.getInstance().readExcelCell(TITLE_LINE_INDEX, 3+ArgName_Number+Act_Number*2+5)+"列");
            }            
            logger.info(ExcelUtil.getInstance().getFilePath() + ", " + ExcelUtil.getInstance().getSheetName() + " 初始化完成");
            System.out.println(ExcelUtil.getInstance().getFilePath() + ", " + ExcelUtil.getInstance().getSheetName() + " 初始化完成");
            System.out.println("==================================================================");
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
    	
    	List<Map<String, String>> data = null;
        data = ExcelUtil.getInstance().readExcelAllData(TITLE_LINE_INDEX);
        
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
        	      
        	      String phone_Exp= Login_Controller.GetSqlResult(DataVersion, Sql,"phone");
        	      MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "phone_Exp", phone_Exp);

        	      String roleId_Exp= Login_Controller.GetSqlResult(DataVersion, Sql,"roleId");
        	      MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "roleId_Exp", roleId_Exp);
        	
        	      String roleName_Exp= Login_Controller.GetSqlResult(DataVersion, Sql,"roleName");
        	      MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "roleName_Exp", roleName_Exp);
        	      
        	      String salesId_Exp= Login_Controller.GetSqlResult(DataVersion, Sql,"salesId");
        	      MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "salesId_Exp", salesId_Exp);
        	      
        	      String userName_Exp= Login_Controller.GetSqlResult(DataVersion, Sql,"userName");
        	      MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "userName_Exp", userName_Exp);
        	      
//        	      String identification_Exp= DataBase_Util.GetSqlResult(DataVersion, Sql,"identification");
        	      MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "identification_Exp", "string");
        	      
        	      String photoName_Exp= Login_Controller.GetSqlResult(DataVersion, Sql,"photoName");
        	      MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "photoName_Exp", "http://10.10.11.136/"+photoName_Exp);
        	      
                 logger.info("对应用例预期值,写入成功【OK】");
                 System.out.println("对应预期值,写入成功【OK】");
                 System.out.println("==================================================================");
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
    	GetExcelInstance();
        boolean ArgName = false;
        List<Map<String, String>> data = null;
        
        String ApiUrl = ExcelUtil.getInstance().readExcelCell(1, 2);
        String Act = ExcelUtil.getInstance().readExcelCell(2, 2);
        String Method = ExcelUtil.getInstance().readExcelCell(3, 2);
        ArgName = MobileApiToolsUtil.isArgEquals(4, 2, TITLE_LINE_INDEX);

        if (ApiUrl.equals("") || Act.equals("") || Method.equals("") || !ArgName) {
            logger.error("请检查 Excel 中 ApiUrl、Act、Method、ArgName 是否设置正确...");
            System.out.println("请检查 Excel 中 ApiUrl、Act、Method、ArgName 是否设置正确...");
            System.exit(-1);
        }

        data = ExcelUtil.getInstance().readExcelAllData(TITLE_LINE_INDEX);

        if (data != null) {
            int i = ID;
            //根据Excel列名称,获取单元格内容
            Map<String, String> map = data.get(i-1);//从第一个用例开始，0代表就是第一个
            String CaseID = map.get("CaseID");
            String CaseName = map.get("CaseName");
            String username = map.get("username");
            String password = map.get("password");
            
            //写入Run列, 执行纪录，Y代表已执行
            MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "Run", "Y");

            //指定请求的Api地址
            Param = "username="+username+"&password="+password+"";

            //请求接口，获取UserInfo数组
            Object[] UserInfo = Login_Controller.GetUserInfo(ID,ApiUrl,Param);

            //从UserInfo数组中，读取StatusCode并写入
            String code = (String) UserInfo[1];
            MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "StatusCode",code);
            
            //判断StatusCode结果是否等于999999，成立则TestResult写入OK并设置单元格颜色为绿色，反则写入NG并设置单元格颜色为红色，并写入失败消息提示
            if ("999999".equals(code)){
            	ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX, "StatusCode",TITLE_LINE_INDEX + i, 1);
            	MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "TestResult", "OK");
                ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX, "TestResult",TITLE_LINE_INDEX + i, 1);
                //从UserInfo数组中，读取msg_Act结果并写入       
                msg_Act = (String) UserInfo[2];
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "msg_Act", msg_Act);
            }else if ("400".equals(code)){
            	ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX, "StatusCode",TITLE_LINE_INDEX + i, 0);
            	MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "TestResult", "NG");
            	ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX, "TestResult",TITLE_LINE_INDEX + i, 0);
            	//从UserInfo数组中，读取message消息结果并写入
                String message = (String) UserInfo[0];
                String NewMessage=DecodeUnicodeUtil.decodeUnicode(message);
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "FailHint", NewMessage);
                msg_Act=null;
            }else{
            	ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX, "StatusCode",TITLE_LINE_INDEX + i, 0);
            	MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "TestResult", "NG");
            	ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX, "TestResult",TITLE_LINE_INDEX + i, 0);
            	//从UserInfo数组中，读取message消息结果并写入
            	msg_Act = (String) UserInfo[2];
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "msg_Act", msg_Act);
            }

            //写入执行时间
            MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "RunningTime", MobileApiToolsUtil.getDate());

            //从UserInfo数组中，读取JSON结果并编码转换后写入
            String Json = (String) UserInfo[0];
            String NewJson=DecodeUnicodeUtil.decodeUnicode(Json);
            MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "Json", NewJson);
            
            if("成功".equals(msg_Act)){
            	//从UserInfo数组中，读取first_name_Act结果并写入       
            	String first_name_Act = (String) UserInfo[3];
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "first_name_Act", first_name_Act);
                
                //从UserInfo数组中，读取last_name_Act结果并写入       
                String last_name_Act = (String) UserInfo[4];
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "last_name_Act", last_name_Act);
                
                //从UserInfo数组中，读取phone_Act结果并写入       
                String phone_Act = (String) UserInfo[5];
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "phone_Act", phone_Act);

                //从UserInfo数组中，读取email_Act结果并写入       
                String email_Act = (String) UserInfo[6];
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "email_Act", email_Act);

                //从UserInfo数组中，读取key_Act结果并写入       
                String key_Act = (String) UserInfo[7];
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "key_Act", key_Act);
                
                //从UserInfo数组中，读取date_joined_Act结果并写入       
                String date_joined_Act = (String) UserInfo[8];
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "date_joined_Act", date_joined_Act);
                
                //从UserInfo数组中，读取userphoto_Act结果并写入       
                String userphoto_Act = (String) UserInfo[9];
                MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "userphoto_Act", userphoto_Act);

                Reporter.log("用例ID: "+ CaseID);        
    	        Reporter.log("用例名称: "+ CaseName);
    	        Reporter.log("请求地址: "+ ApiUrl);
    	        Reporter.log("请求方式: "+ Method);
    	        Reporter.log("请求参数: "+ Param);
    	        Reporter.log("返回结果: "+ NewJson);
    	        
                String first_name_Exp = map.get("first_name_Exp");
                String last_name_Exp = map.get("last_name_Exp");
                String phone_Exp = map.get("phone_Exp");
                String email_Exp = map.get("email_Exp");
                String key_Exp = map.get("key_Exp");
                String date_joined_Exp = map.get("date_joined_Exp");
                String userphoto_Exp = map.get("userphoto_Exp");

                //检查预期结果和实际结果是否正确
    	        checkEquals(first_name_Exp,first_name_Act,"first_name_Exp","first_name_Act",ID);	 
    	        checkEquals(last_name_Exp,last_name_Act,"last_name_Exp","last_name_Act",ID);	 
    	        checkEquals(phone_Exp,phone_Act,"phone_Exp","phone_Act",ID);	 
    	        checkEquals(email_Exp,email_Act,"email_Exp","email_Act",ID);	 
    	        checkEquals(key_Exp,key_Act,"key_Exp","key_Act",ID);	 
    	        checkEquals(date_joined_Exp,date_joined_Act,"date_joined_Exp","date_joined_Act",ID);	 
    	        checkEquals(userphoto_Exp,userphoto_Act,"userphoto_Exp","userphoto_Act",ID);	 
            }else{
            	Reporter.log("用例ID: "+ CaseID);        
    	        Reporter.log("用例名称: "+ CaseName);
    	        Reporter.log("请求地址: "+ ApiUrl);
    	        Reporter.log("请求方式: "+ Method);
    	        Reporter.log("请求参数: "+ Param);
    	        Reporter.log("返回结果: "+ NewJson);
    	        
            	String msg_Exp = map.get("msg_Exp");
    	        checkEquals(msg_Exp,msg_Act,"msg_Exp","msg_Act",ID);	 
            }
        }
    }

	/**
	 * <br>根据用例ID，检查预期与实际是否相等，不等则提示错误信息，并写入结果</br>
	 * @author  刘智King
	 * @date     2018年4月20日 下午6:01:04
	 * @param  ID
	 */
	public static void checkEquals(String Expected,String Actual,String ExpectedList,String ActualList,int ID) throws Exception{
		int i = ID;
		try {
			Assert.assertEquals(Expected,Actual);
			MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "TestResult", "OK");
            ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX, "TestResult",TITLE_LINE_INDEX + i, 1);
			Reporter.log("用例结果: 〖"+ExpectedList.replace("_Exp", "")+"〗=>Expected: " + "【"+Expected+"】" + ", Actual: "+ "【"+Actual+"】");
	        System.out.println("用例结果: 【"+ExpectedList.replace("_Exp", "")+"】=>Expected: " + "【"+Expected+"】" + ", Actual: "+ "【"+Actual+"】");
        }catch (Error e)  {
 	    	MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "TestResult", "NG");
        	ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX, "TestResult",TITLE_LINE_INDEX + i, 0);
 	    	ExcelUtil.getInstance().setCellBackgroundColor(TITLE_LINE_INDEX, ActualList,TITLE_LINE_INDEX + i, 3);
 	    	MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX, TITLE_LINE_INDEX + i, "FailHint", "【"+ExpectedList+"】和【"+ActualList+"】不一致");
 	    	Assert.fail(""+ExpectedList.replace("_Exp", "")+" =>Expected 【"+ Expected +"】"+" "+"but found 【"+ Actual +"】");
 	    }
	}
	
	/**
	 * <br>根据用例ID，获取用例名称信息，打印到控制台并写入Excel</br>
	 * @author  刘智King
	 * @date     2018年4月20日 下午6:01:04
	 * @param  ID
	 */
    public static void GetCaseInfo(int ID) throws Exception {
    	GetExcelInstance();
    	List<Map<String, String>> data = null;
    	data = ExcelUtil.getInstance().readExcelAllData(TITLE_LINE_INDEX);

    	if (data != null) {
                int i = ID;
            	//根据Excel列名称,获取单元格内容
                Map<String, String> map = data.get(i-1);
                String CaseID = map.get("CaseID");
                String CaseName = map.get("CaseName");
  
                String msg_Exp = map.get("msg_Exp");
                String first_name_Exp = map.get("first_name_Exp");
                String last_name_Exp = map.get("last_name_Exp");
                String phone_Exp = map.get("phone_Exp");
                String email_Exp = map.get("email_Exp");
                String key_Exp = map.get("key_Exp");
                String date_joined_Exp = map.get("date_joined_Exp");
                String userphoto_Exp = map.get("userphoto_Exp");
                
                String msg_Act = map.get("msg_Act");
                String first_name_Act = map.get("first_name_Act");
                String last_name_Act = map.get("last_name_Act");
                String phone_Act = map.get("phone_Act");
                String email_Act = map.get("email_Act");
                String key_Act = map.get("key_Act");
                String date_joined_Act = map.get("date_joined_Act");
                String userphoto_Act = map.get("userphoto_Act");
                
                String StatusCode = map.get("StatusCode");
                String TestResult = map.get("TestResult");
                String FailHint = map.get("FailHint");

    	        //打印日志
    	        logger.info("CaseID: " + CaseID + ", CaseName: " + CaseName +  
    	        		", msg_Exp: " + msg_Exp +", msg_Act: " + msg_Act+ 
    	                ", first_name_Exp: " + first_name_Exp +", first_name_Act: " + first_name_Act+ 
    	                ", last_name_Exp: " + last_name_Exp +", last_name_Act: " + last_name_Act+
    	                ", phone_Exp: " + phone_Exp +", phone_Act: " + phone_Act+
    	                ", email_Exp: " + email_Exp +", email_Act: " + email_Act+
    	                ", key_Exp: " + key_Exp +", key_Act: " + key_Act+
    	                ", date_joined_Exp: " + date_joined_Exp +", date_joined_Act: " + date_joined_Act+
    	                ", userphoto_Exp: " + userphoto_Exp +", userphoto_Act: " + userphoto_Act+
    	                ", StatusCode: " + StatusCode +", TestResult: " + TestResult + ", FailHint: " + FailHint + "");
    	        logger.info("==================================================================");

                 System.out.println("CaseID: " + CaseID + ", CaseName: " + CaseName +  
                		", msg_Exp: " + msg_Exp +", msg_Act: " + msg_Act+ 
     	                ", first_name_Exp: " + first_name_Exp +", first_name_Act: " + first_name_Act+ 
     	                ", last_name_Exp: " + last_name_Exp +", last_name_Act: " + last_name_Act+
     	                ", phone_Exp: " + phone_Exp +", phone_Act: " + phone_Act+
     	                ", email_Exp: " + email_Exp +", email_Act: " + email_Act+
     	                ", key_Exp: " + key_Exp +", key_Act: " + key_Act+
     	                ", date_joined_Exp: " + date_joined_Exp +", date_joined_Act: " + date_joined_Act+
     	                ", userphoto_Exp: " + userphoto_Exp +", userphoto_Act: " + userphoto_Act+
                        ", StatusCode: " + StatusCode +", TestResult: " + TestResult + ", FailHint: " + FailHint + "");
                  System.out.println("==================================================================");   
            }
    }
}