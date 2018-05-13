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
    
    @Test
	public void Case4() throws Exception{
    	LoginHandler.Login(4);	
    	LoginHandler.resultCheck(4);
	}
    
    @Test
	public void Case5() throws Exception{
    	LoginHandler.Login(5);	
    	LoginHandler.resultCheck(5);
	}
    
    @Test
	public void Case6() throws Exception{
    	LoginHandler.Login(6);	
    	LoginHandler.resultCheck(6);
	}
    
    @Test
	public void Case7() throws Exception{
    	LoginHandler.Login(7);	
    	LoginHandler.resultCheck(7);
	}
    
    @Test
	public void Case8() throws Exception{
    	LoginHandler.Login(8);	
    	LoginHandler.resultCheck(8);
	}
    
    @Test
	public void Case9() throws Exception{
    	LoginHandler.Login(9);	
    	LoginHandler.resultCheck(9);
	}
    
    @Test
	public void Case_10() throws Exception{
    	LoginHandler.Login(10);	
    	LoginHandler.resultCheck(10);
	}
 
    @Test
	public void Case_11() throws Exception{
    	LoginHandler.Login(11);	
    	LoginHandler.resultCheck(11);
	}

    @Test
	public void Case_12() throws Exception{
    	LoginHandler.Login(12);	
    	LoginHandler.resultCheck(12);
	}

    @Test
	public void Case_13() throws Exception{
    	LoginHandler.Login(13);	
    	LoginHandler.resultCheck(13);
	}
    
    @Test
	public void Case_14() throws Exception{
    	LoginHandler.Login(14);	
    	LoginHandler.resultCheck(14);
	}
    
    @Test
	public void Case_15() throws Exception{
    	LoginHandler.Login(15);	
    	LoginHandler.resultCheck(15);
	}
    
    @Test
	public void Case_16() throws Exception{
    	LoginHandler.Login(16);	
    	LoginHandler.resultCheck(16);
	}
    
    @Test
	public void Case_17() throws Exception{
    	LoginHandler.Login(17);	
    	LoginHandler.resultCheck(17);
	}
 
    @Test
	public void Case_18() throws Exception{
    	LoginHandler.Login(18);	
    	LoginHandler.resultCheck(18);
	}
    
    @Test
	public void Case_19() throws Exception{
    	LoginHandler.Login(19);	
    	LoginHandler.resultCheck(19);
	}
    
    @Test
	public void Case_20() throws Exception{
    	LoginHandler.Login(20);	
    	LoginHandler.resultCheck(20);
	}
    
    @Test
	public void Case_21() throws Exception{
    	LoginHandler.Login(21);	
    	LoginHandler.resultCheck(21);
	}
    
	@AfterTest
	public void TearDown() throws Exception{
		LoginHandler.GetCaseInfo(1);
		LoginHandler.GetCaseInfo(2);
		LoginHandler.GetCaseInfo(3);
		LoginHandler.GetCaseInfo(4);
		LoginHandler.GetCaseInfo(5);
		LoginHandler.GetCaseInfo(6);
		LoginHandler.GetCaseInfo(7);
		LoginHandler.GetCaseInfo(8);
		LoginHandler.GetCaseInfo(9);
		LoginHandler.GetCaseInfo(10);
		LoginHandler.GetCaseInfo(11);
		LoginHandler.GetCaseInfo(12);
		LoginHandler.GetCaseInfo(13);
		LoginHandler.GetCaseInfo(14);
		LoginHandler.GetCaseInfo(15);
		LoginHandler.GetCaseInfo(16);
		LoginHandler.GetCaseInfo(17);
		LoginHandler.GetCaseInfo(18);
		LoginHandler.GetCaseInfo(19);
		LoginHandler.GetCaseInfo(20);
		LoginHandler.GetCaseInfo(21);
	}
}