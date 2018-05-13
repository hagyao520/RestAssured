# 欢迎查阅Rest Assured（API接口自动化测试框架体系）

### Rest Assured
![](https://testerhome.com/uploads/photo/2018/687752c9-83c2-4454-88f6-db4262e55822.png!large)

    现在，越来越多的 Web 应用转向了 RESTful 的架构，很多产品和应用暴露给用户的往往就是一组 REST API，这样有一个好处，用户可以根据需要，调用不同的 API，整合出自己的应用出来。从这个角度来讲，Web 开发的成本会越来越低，人们不必再维护自己的信息孤岛，而是使用 REST API 互联互通
    那么，作为 REST API 的提供者，如何确保 API 的稳定性与正确性呢？全面系统的测试是必不可少的。Java 程序员常常借助于 JUnit 来测试自己的 REST API，不，应该这样说，Java 程序员常常借助于 JUnit 来测试 REST API 的实现！从某种角度来说，这是一种“白盒测试”，Java 程序员清楚地知道正在测试的是哪个类、哪个方法，而不是从用户的角度出发，测试的是哪个 REST API
    Rest-Assured 是一套由 Java 实现的 REST API 测试框架，它是一个轻量级的 REST API 客户端，可以直接编写代码向服务器端发起 HTTP 请求，并验证返回结果；它的语法非常简洁，是一种专为测试 REST API 而设计的 DSL
    使用 Rest-Assured 测试 REST API，就和真正的用户使用 REST API 一样，只不过 Rest-Assured 让这一切变得自动化了

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

### 主要功能：
    1. 实现了基于Rest Assured，HttpClient等常用接口请求方法的二次封装，包括(SendPost，SendGet，GetJsonResult)等，使用起来更简便
    2. 实现的基于Oracle，MySql等常用数据库SQL操作，包含（Insert into，Delete，Update，Query）和执行"存储过程"操作等
    3. 实现了基于Oracle，MySql等常用数据库数据获取功能，获取数据库字段值，写入到Excle文档所需参数中，然后进行接口请求参数调用
    4. 实现了基于Excel文档信息的读取和写入，包括(SheetName，ReadData，WriteData)等，基本内容符合测试用例编写步骤，编写测试用例脚本更简单
    5. 实现了基于Excel文档数据断言功能，预期结果和实际结果比对效验，检查点失败自动写入结果，可在测试报告中查看，一个检查点失败不影响后续用例执行
    6. 实现了基于ExtentReports，TestNG生成的测试报告二次美化功能，界面更美观，内容清晰

### 环境配置：
   1. [JDK1.7以上](http://www.Oracle.com/technetwork/Java/javase/downloads/index.html)
   2. [Eclipse](http://www.eclipse.org/downloads)/[IDEA](https://www.jetbrains.com/idea/)
   3. [Rest Assured](http://rest-assured.io)
   4. [Maven](http://maven.apache.org/download.cgi) 
   5. [Git](https://git-scm.com/) 
   6. [Ant](https://ant.apache.org) 
   7. [Jenkins](https://jenkins.io) 

 - 部分网站需要翻墙，具体安装参考：https://www.ibm.com/developerworks/cn/java/j-lo-rest-assured
  
### 注意事项：
 - 工程项目编码需要设置成UTF-8，否则会出现中文乱码情况

### 一、创建测试对象处理程序类，例如【LoginHandler.java】
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
        t    ry {
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
                
    //                String identification_Exp= DataBase_Util.GetSqlResult(DataVersion, Sql,"identification");
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


### 二、创建测试对象脚本用例类，例如【LoginTest.java】
    package TestCase;

    import org.testng.annotations.AfterTest;
    import org.testng.annotations.BeforeTest;
    import org.testng.annotations.Test;

    import com.sales.webapi.handler.LoginHandler;

    public class LoginTest {
     
        @BeforeTest
        private void Stup() throws Exception{
            LoginHandler.GetExcelInstance();
            LoginHandler.InitializeExcelData();
            LoginHandler.WriteExcelExpected("测试环境",12);
        }
    
        @Test
        public void Case1() throws Exception{
            LoginHandler.Login(1);  
            LoginHandler.resultCheck(1);
        }
  
        @Test
        public void Case2() throws Exception{
             LoginHandler.Login(2);  
             LoginHandler.resultCheck(2);
        }
    
        @Test
        public void Case3() throws Exception{
            LoginHandler.Login(3);  
            LoginHandler.resultCheck(3);
        }
  
        @AfterTest
        public void TearDown() throws Exception{
            LoginHandler.GetCaseInfo(1);
            LoginHandler.GetCaseInfo(2);
            LoginHandler.GetCaseInfo(3);
        }
    }

![](https://testerhome.com/uploads/photo/2018/6142f15f-a384-406e-82d9-eeadb7f73a37.png!large)
![](https://testerhome.com/uploads/photo/2018/126d205b-92da-4bd0-b783-65bad0071367.png!large)
#### 以上只是单个案例，相关的接口参数定义根据具体接口文档进行设置，是不是很简单，和写测试用例基本一致
 - 具体脚本编方法请参考: https://pan.baidu.com/s/1fBMX2ET7oHzZW-hsvCwk-A
 - 官方使用指南请参考: https://testerhome.com/topics/7060

### 三、Rest Assured测试用例文档配置：
    public static void GetExcelInstance() {
        logger.info(LoginHandler.class);
        System.out.println(LoginHandler.class);
        ExcelUtil.getInstance().setFilePath("src/test/resources/Excel/GiveU.Sales.WebApi.xlsx");
        ExcelUtil.getInstance().setSheetName("Login");
    }

 - 测试执行时需要在指定对应Excel测试用例文档路径和Sheet工作表名，当前为GiveU.Sales.WebApi.xlsx，和Login工作表

### 四、执行用例：
 - 编写完对应测试对象处理程序类【LoginHandler.java】，和测试对象脚本用例类【LoginTest.java】后，在Eclipse项目下选择LoginTest.java右键使用TestNG运行即可
![](https://testerhome.com/uploads/photo/2018/d1e4ae98-55b3-482a-914e-211cc27f16ca.png!large)

### 五、测试报告：
 - 测试报告分为两种，一种是TestNG自带的TestngReport测试报告，另外一种则是调用ExtentReports生成的报告，第二种更加美观

#### [TestngReport](https://testerhome.com/uploads/photo/2018/8da0567e-881e-4e01-af90-3928b1456d8e.png!large)
    <?xml version="1.0" encoding="UTF-8"?>
    <suite name="Rest Assured测试报告" parallel="false" configfailurepolicy ="continue">
        <test name="销售服务系统-登录接口测试场景点" junit="false" verbose="3" parallel="false" thread-count="5" annotations="javadoc" time-out="60000" enabled="true" skipfailedinvocationcounts="true" preserve-order="true" allow-return-values="true">
            <classes>
                <class name="TestCase.LoginTest"/>
                    <methods>
                        <include name="case1" />
                        <include name="case2" />
                        <include name="case3" />
                    </methods>
            </classes>
        </test>  
     ------------------------------------------------------------------------------------------------------
    <!-- 调用的监听 -->    
        <listeners>
            <listener class-name="org.uncommons.reportng.HTMLReporter" />
            <listener class-name="org.uncommons.reportng.JUnitXMLReporter" />
        </listeners>      
</suite>

![](https://testerhome.com/uploads/photo/2018/8da0567e-881e-4e01-af90-3928b1456d8e.png!large)

#### [ExtentReports](https://testerhome.com/uploads/photo/2018/128a4480-580f-43ae-af52-5087ae0102e6.png!large)
    <?xml version="1.0" encoding="UTF-8"?>
    <suite name="Suite" verbose="1" preserve-order="true" parallel="false">
        <suite-files>
            <suite-file path="TestngReport.xml"></suite-file>
        </suite-files>
        <listeners>
            <listener class-name="com.jmoney.xiyuyou.service.ExtentReportGenerateService"></listener>
        </listeners> 
        <!-- C:\Windows\System32\drivers\etc
        151.139.237.11    cdn.rawgit.com -->
    </suite>

![](https://testerhome.com/uploads/photo/2018/128a4480-580f-43ae-af52-5087ae0102e6.png!large)
 - 第二种测种试报告，需要翻墙才能正常显示，否则页面显示乱码，因为是国外的东西
 - 或者在C:\Windows\System32\drivers\etc    host文件末尾添加151.139.237.11   cdn.rawgit.com
 - 
### 六、感谢
#### 如果您觉得这个框架不错，您可以捐赠下我，让我有理由继续下去。
![](https://testerhome.com/uploads/photo/2018/26d494a6-7b4f-4b69-8db1-0a3b45f886b7.png!large)

**非常感谢您花费时间阅读，祝您在这里记录、阅读、分享愉快！**
**欢迎留言评论，有问题也可以联系我或者加群交流....**

作者：[@刘智King](http://shang.qq.com/email/stop/email_stop.html?qq=1306086303&sig=a1c657365db7e82805ea4b2351081fc3ebcde159f8ae49b1&tttt=1)         
QQ：1306086303     
Email：hagyao520@163.com

> QQ官方交流群 <a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=346d11a1a76d05086cd48bc8249126f514248479b50f96288189ab5ae0ca7ba5"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="126325132" title="126325132"></a>