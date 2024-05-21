package com.saucedemo.PlayrightJava;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.ElementHandle;

import base.BaseTest;
import pages.LoginPageClass;
import pages.ProductListingPage;

public class AppTest extends BaseTest {

	JSONParser jsonP = new JSONParser();
	SoftAssert softAssert = new SoftAssert();

	@Test(priority = 1)
	public void verifyImagesForDifferentUsers() {
		try {
			FileReader reader = new FileReader(
					"D:\\Shabnam Project\\PlayrightJava\\src\\test\\java\\testData\\testLogin.json");
			Object obj = jsonP.parse(reader);
			JSONArray loginlist = (JSONArray) obj;

			System.out.println(loginlist);

			for (Object obj1 : loginlist) {
				JSONObject jsonObject = (JSONObject) obj1;
				String user_name = (String) jsonObject.get("username");
				String password = (String) jsonObject.get("password");

				System.out.println("user_name: " + user_name);
				System.out.println("passord: " + password);

				LoginPageClass.provide_login_details(page, user_name, password);

				List<ElementHandle> allImageElements = page.querySelectorAll("img");
				Set<String> uniqueImageUrls = new HashSet<>();
				for (ElementHandle imageElement : allImageElements) {
					String srcAttribute = imageElement.getAttribute("src");
					System.out.println("Image Source: " + srcAttribute);
					if (srcAttribute != null && !srcAttribute.isEmpty()) {
						uniqueImageUrls.add(srcAttribute);
					}
				}

				Assert.assertEquals(uniqueImageUrls.size(), allImageElements.size());
				LoginPageClass.click_logout_button(page);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void verifyImageOnListingAndDetailsPage() {
		try {
			FileReader reader = new FileReader(
					"D:\\Shabnam Project\\PlayrightJava\\src\\test\\java\\testData\\testLogin.json");
			Object obj = jsonP.parse(reader);
			JSONArray loginlist = (JSONArray) obj;

			for (Object obj1 : loginlist) {
				JSONObject jsonObject = (JSONObject) obj1;
				String user_name = (String) jsonObject.get("username");
				String password = (String) jsonObject.get("password");

				LoginPageClass.provide_login_details(page, user_name, password);

				List<ElementHandle> allImageElements = page.querySelectorAll("//img[@class='inventory_item_img']");

				System.out.println("allImageElements size is  " + allImageElements.size());

				for (ElementHandle imageElement : allImageElements) {
					String srcAttribute = imageElement.getAttribute("src");

					ProductListingPage.click_on_product(page, srcAttribute);

					ElementHandle img1 = page.querySelector("//img[@class='inventory_details_img']");
					String detailPageSrc = img1.getAttribute("src");

					ProductListingPage.click_on_back_to_products_link(page);
					softAssert.assertTrue(srcAttribute.equals(detailPageSrc),
							"The image do no match with the listing page");

				}
				softAssert.assertAll();
				LoginPageClass.click_logout_button(page);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
