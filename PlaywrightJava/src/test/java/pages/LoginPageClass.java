package pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

public class LoginPageClass 
{

	private static final String username_textbox = "#user-name";
	private static final String password_textbox = "#password";
	private static final String login_button = "#login-button";
	private static final String bread_crumb_button = "#react-burger-menu-btn";
	private static final String logout_link = "#logout_sidebar_link";

	public static void provide_username(Page page, String name)
			{
		
		page.locator(username_textbox).fill(name);
			}
	
	public static void provide_password(Page page,String pass)
	{
		page.locator(password_textbox).fill(pass);
	}
	
	public static void click_login_button(Page page)
	{
		page.click(login_button);
	}
	
	public static void provide_login_details(Page page, String name, String pass)
	{
		provide_username(page, name);
		provide_password(page, pass);
		click_login_button(page);
	}
	
	public static void click_logout_button(Page page) {
		page.click(bread_crumb_button);
		page.click(logout_link);
	}
}
