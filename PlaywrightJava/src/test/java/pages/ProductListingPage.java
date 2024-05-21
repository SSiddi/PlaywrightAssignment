package pages;

import com.microsoft.playwright.Page;

public class ProductListingPage {

	private static final String product_detail_page_image_path =".inventory_details_img";
	private static final String back_to_product_link ="#back-to-products";
	
	public static void click_on_product(Page page, String imgLink)
	{
		String linkToCLick="//img[@src='"+imgLink+"']";
		page.click(linkToCLick);
	}
	
	public static void click_on_back_to_products_link(Page page)
	{
		page.click(back_to_product_link);
	}
}
