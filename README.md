# 欢迎查阅Rest Assured（API接口自动化测试框架体系）

 ---
### Rest Assured
![](https://testerhome.com/uploads/photo/2018/687752c9-83c2-4454-88f6-db4262e55822.png!large)

    现在，越来越多的 Web 应用转向了 RESTful 的架构，很多产品和应用暴露给用户的往往就是一组 REST API，这样有一个好处，用户可以根据需要，调用不同的 API，整合出自己的应用出来。从这个角度来讲，Web 开发的成本会越来越低，人们不必再维护自己的信息孤岛，而是使用 REST API 互联互通
    那么，作为 REST API 的提供者，如何确保 API 的稳定性与正确性呢？全面系统的测试是必不可少的。Java 程序员常常借助于 JUnit 来测试自己的 REST API，不，应该这样说，Java 程序员常常借助于 JUnit 来测试 REST API 的实现！从某种角度来说，这是一种“白盒测试”，Java 程序员清楚地知道正在测试的是哪个类、哪个方法，而不是从用户的角度出发，测试的是哪个 REST API
    Rest-Assured 是一套由 Java 实现的 REST API 测试框架，它是一个轻量级的 REST API 客户端，可以直接编写代码向服务器端发起 HTTP 请求，并验证返回结果；它的语法非常简洁，是一种专为测试 REST API 而设计的 DSL
    使用 Rest-Assured 测试 REST API，就和真正的用户使用 REST API 一样，只不过 Rest-Assured 让这一切变得自动化了

 ---
### 框架介绍：
    Java + Rest Assured + Maven + TestNG + JDBC + Excel+ Git + +Ant + Jenkins** 
        •  使用Java作为项目编程语言
        •  使用Rest Assured作为API接口自动化项目底层服务驱动框架
        •  使用Maven作为项目类型，方便管理架包
        •  使用TestNG作为项目运行框架，方便执行测试用例，生成测试报告
        •  使用JDBC作为数据库管理工具，方便连接数据库，执行SQL
        •  使用Xml作为用例管理工具，方便编写测试用例，维护测试脚本
        •  使用Git作为仓库管理工具，方便管理项目代码
        •  使用Ant作为Java的build打包工具，方便项目代码打包
        •  使用Jenkins作为自动化持续集成平台，方便自动编译，自动打包，自动运行测试脚本，邮件发送测试报告

 ---
### 主要功能：
    1. 实现了基于Rest Assured，HttpClient等常用接口请求方法的二次封装，包括(SendPost，SendGet，GetJsonResult)等，使用起来更简便
    2. 实现的基于Oracle，MySql等常用数据库SQL操作，包含（Insert into，Delete，Update，Query）和执行"存储过程"操作等
    3. 实现了基于Oracle，MySql等常用数据库数据获取功能，获取数据库字段值，写入到Excle文档所需参数中，然后进行接口请求参数调用
    4. 实现了基于Excel文档信息的读取和写入，包括(SheetName，ReadData，WriteData)等，基本内容符合测试用例编写步骤，编写测试用例脚本更简单
    5. 实现了基于Excel文档数据断言功能，预期结果和实际结果比对效验，检查点失败自动写入结果，可在测试报告中查看，一个检查点失败不影响后续用例执行
    6. 实现了基于ExtentReports，TestNG生成的测试报告二次美化功能，界面更美观，内容清晰

 ---
### 环境配置：
   1. [JDK1.7以上](http://www.Oracle.com/technetwork/Java/javase/downloads/index.html)
   2. [Eclipse](http://www.eclipse.org/downloads)/[IDEA](https://www.jetbrains.com/idea/)
   3. [Rest Assured](http://rest-assured.io)
   4. [Maven](http://maven.apache.org/download.cgi) 
   5. [Git](https://git-scm.com/) 
   6. [Ant](https://ant.apache.org) 
   7. [Jenkins](https://jenkins.io) 

 - 部分网站需要翻墙，具体安装参考：https://www.ibm.com/developerworks/cn/java/j-lo-rest-assured
  
 ---
### 注意事项：
 - 工程项目编码需要设置成UTF-8，否则会出现中文乱码情况

 ---
### 一、创建测试对象处理程序类，例如【Login_Handler.java】
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
                      int i = ID;
                      Map<String, String> map = data.get(i);
                      String Sql =map.get("userId");

                      logger.info("根据数据库查询结果, 开始写入预期值【Waiting...】");
                      System.out.println("根据数据库查询结果, 开始写入预期值【Waiting...】");
                  
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
                  
                      MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "identification_Exp", "string");
                  
                      String photoName_Exp= Login_Controller.GetSqlResult(DataVersion, Sql,"photoName");
                      MobileApiToolsUtil.writeResult(TITLE_LINE_INDEX-1, TITLE_LINE_INDEX + i, "photoName_Exp", "http://10.10.11.136/"+photoName_Exp);
                  
                     logger.info("对应用例预期值,写入成功【OK】");
                     System.out.println("对应预期值,写入成功【OK】");
                     System.out.println("==================================================================");
                   }
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

 ---
### 二、创建测试对象脚本用例类，例如【Login.java】
    package TestCases;

    import org.testng.annotations.AfterTest;
    import org.testng.annotations.BeforeTest;
    import org.testng.annotations.Test;

    import com.jmoney.luckeylink.handler.Login_Handler;

    public class Login {
     
        @BeforeTest
        private void Stup() throws Exception{
            Login_Handler.InitializeExcelData();
        }
    
        @Test
        public void Case1() throws Exception{
            Login_Handler.Login(1); 
        }
    
        @Test
        public void Case2() throws Exception{
            Login_Handler.Login(2); 
        }
    
        @Test
        public void Case3() throws Exception{
            Login_Handler.Login(3); 
        }
    
        @Test
        public void Case4() throws Exception{
            Login_Handler.Login(4); 
        }
    
        @Test
        public void Case5() throws Exception{
            Login_Handler.Login(5); 
        }
    
        @Test
        public void Case6() throws Exception{
            Login_Handler.Login(6); 
        }
    
        @Test
        public void Case7() throws Exception{
            Login_Handler.Login(7); 
        }
    
        @AfterTest
        public void TearDown() throws Exception{
            Login_Handler.GetCaseInfo(1);
            Login_Handler.GetCaseInfo(2);
            Login_Handler.GetCaseInfo(3);
            Login_Handler.GetCaseInfo(4);
            Login_Handler.GetCaseInfo(5);
            Login_Handler.GetCaseInfo(6);
            Login_Handler.GetCaseInfo(7);
        }
    }

![](https://testerhome.com/uploads/photo/2018/6142f15f-a384-406e-82d9-eeadb7f73a37.png!large)
![](https://testerhome.com/uploads/photo/2018/126d205b-92da-4bd0-b783-65bad0071367.png!large)
#### 以上只是单个案例，相关的接口参数定义根据具体接口文档进行设置，是不是很简单，和写测试用例基本一致
 - 具体脚本编方法请参考: https://pan.baidu.com/s/1fBMX2ET7oHzZW-hsvCwk-A
 - 官方使用指南请参考: https://testerhome.com/topics/7060

 --- 
### 三、Rest Assured测试用例文档配置：
    public static void GetExcelInstance() {
        logger.info(Login_Handler.class);
        System.out.println(Login_Handler.class);
        ExcelUtil.getInstance().setFilePath("src/test/java/TestCasexls/JMoney.Luckeylink.Api.xlsm");
        ExcelUtil.getInstance().setSheetName("Login");
    }

 - 测试执行时需要在指定对应Excel测试用例文档路径和Sheet工作表名，当前为JMoney.Luckeylink.Api.xlsm，和Login工作表

 ---
### 四、执行用例：
 - 编写完对应测试对象处理程序类【LoginHandler.java】，和测试对象脚本用例类【Login.java】后，在Eclipse项目下选择Login.java右键使用TestNG运行即可
![](https://testerhome.com/uploads/photo/2018/d1e4ae98-55b3-482a-914e-211cc27f16ca.png!large)

 ---
### 五、测试报告：
 - 测试报告分为两种，一种是TestNG自带的TestngReport测试报告，另外一种则是调用ExtentReports生成的报告，第二种更加美观

#### [TestngReport](https://testerhome.com/uploads/photo/2018/8da0567e-881e-4e01-af90-3928b1456d8e.png!large)
    <?xml version="1.0" encoding="UTF-8"?>
    <suite name="Web后端-接口自动化测试" parallel="false" configfailurepolicy ="continue">
    <test name="用户登录接口" junit="false" verbose="3" parallel="false" thread-count="5" annotations="javadoc" time-out="6000000" enabled="true" skipfailedinvocationcounts="true" preserve-order="true" allow-return-values="true">
        <classes>
            <class name="TestCases.Login"/>
                <methods>
                    <include name="case1" />
                    <include name="case2" />
                    <include name="case3" />
                    <include name="case4" />
                    <include name="case5" />
                    <include name="case6" />
                    <include name="case7" />
                    <exclude name="" />
                </methods>
        </classes>
    </test>
    <!-- —————————————————————————— 分           界             线  ———————————————————————————— -->
    <!-- 调用的监听 -->    
    <listeners>
        <listener class-name="org.uncommons.reportng.HTMLReporter" />
        <listener class-name="org.uncommons.reportng.JUnitXMLReporter" />
    </listeners>      
</suite>

![](https://testerhome.com/uploads/photo/2018/50f138e2-061d-477e-abc8-9010faf50558.png!large)

 ---
#### [ExtentReports](https://testerhome.com/uploads/photo/2018/128a4480-580f-43ae-af52-5087ae0102e6.png!large)
    <?xml version="1.0" encoding="UTF-8"?>
    <suite name="Suite" verbose="1" preserve-order="true" parallel="false">
        <suite-files>
            <suite-file path="Login.xml"></suite-file>
        </suite-files>
        <listeners>
            <listener class-name="com.jmoney.luckeylink.service.ExtentReportGenerateService"></listener>
        </listeners> 
        <!-- C:\Windows\System32\drivers\etc
        151.139.237.11    cdn.rawgit.com -->
    </suite>

![](https://testerhome.com/uploads/photo/2018/d709087f-0a50-4a2b-b3c6-39085a02977e.png!large)
 - 第二种测种试报告，需要翻墙才能正常显示，否则页面显示乱码，因为是国外的东西
 - 或者在C:\Windows\System32\drivers\etc    host文件末尾添加151.139.237.11   cdn.rawgit.com

 ---
### 六、Jnekins持续集成：
![](https://testerhome.com/uploads/photo/2018/6c209373-80f5-47f3-a9d7-e7dbfe3ea523.png!large)
![](https://testerhome.com/uploads/photo/2018/e24ab598-67da-471d-a924-cc5360c92ec7.png!large)
 - 搭建Jenkins环境，具体请参考: https://blog.csdn.net/wuxuehong0306/article/details/50016547
 - 配置Jenkins自动化持续集成项目，即可实现远程服务器自动（构建，编译，打包）运行脚本，发送邮件测试报告等
 
 ---
### 七、感谢
#### 如果您觉得这个框架不错，您可以捐赠下我，让我有理由继续下去。
![](https://testerhome.com/uploads/photo/2018/26d494a6-7b4f-4b69-8db1-0a3b45f886b7.png!large)

**非常感谢您花费时间阅读，祝您在这里记录、阅读、分享愉快！**
**欢迎留言评论，有问题也可以联系我或者加群交流....**

作者：[@刘智King](http://shang.qq.com/email/stop/email_stop.html?qq=1306086303&sig=a1c657365db7e82805ea4b2351081fc3ebcde159f8ae49b1&tttt=1)         
QQ：1306086303     
Email：hagyao520@163.com

> QQ官方交流群 126325132
<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=346d11a1a76d05086cd48bc8249126f514248479b50f96288189ab5ae0ca7ba5"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="软件测试开发交流群" title="软件测试开发交流群"></a>