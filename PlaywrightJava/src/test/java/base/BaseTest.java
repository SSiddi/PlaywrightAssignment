package base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BaseTest {


	protected Page page;
	
	
	@BeforeClass
	public Page setup() {
		Playwright playright = Playwright.create();
		Browser browser = playright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
		page = browser.newPage();
		page.navigate("https://www.saucedemo.com/");
		return page;	
	}
	
	@AfterClass
	public void tearDown() {
		System.out.println("Closing the browser");
		page.close();
	}
	
}
