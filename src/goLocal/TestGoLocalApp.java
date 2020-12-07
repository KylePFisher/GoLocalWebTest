package goLocal;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TestGoLocalApp {
   private WebDriver driver;
   private boolean acceptNextAlert = true;
   private StringBuffer verificationErrors = new StringBuffer();

   @Before
   public void setUp() throws Exception {
      System.setProperty("webdriver.chrome.driver", "C:\\Users\\Kyle\\Downloads\\chromedriver_win32\\chromedriver.exe");
      driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
   }
   
   @Test 
   public void testCategoriesSelectable() throws Exception {
	   driver.get("http://fishertodoapp.ddns.net:8080/GoLocal/");
	   Thread.sleep(1000);
	   Select mainCategories = new Select(driver.findElement(By.id("categorySelect")));
	   
	   String[] expectedValues = {"Bar", "Food & Beverage", "Restaurant", "Retail"};
	   List<WebElement> mainCategoryValues = mainCategories.getOptions();

	   for(int i = 0; i < mainCategoryValues.size(); i++) {
		    Assert.assertEquals(expectedValues[i], mainCategoryValues.get(i).getText());
		}
   }

   @Test
   public void testSubCategoriesSelectable() throws Exception {
      driver.get("http://fishertodoapp.ddns.net:8080/GoLocal/");
      Thread.sleep(1000);
      new Select(driver.findElement(By.id("categorySelect"))).selectByVisibleText("Restaurant");
      Thread.sleep(1000);
      Select subCategories = new Select(driver.findElement(By.id("subCategorySelect")));
      
      String[] expectedValues = {" American", " Breakfast", " Seafood", "Greek", "Indian", "Japanese", "Pizza", "Ramen", "Restaurant", "Seafood", "Sushi"};
      List<WebElement> subCategoryValues = subCategories.getOptions();

	  for(int i = 0; i < subCategoryValues.size(); i++) {
		  Assert.assertEquals(expectedValues[i], subCategoryValues.get(i).getText());
	  }
   }
   
   @Test
   public void testSubCategoriesChange() throws Exception {
      driver.get("http://fishertodoapp.ddns.net:8080/GoLocal/");
      Thread.sleep(1000);
      new Select(driver.findElement(By.id("categorySelect"))).selectByVisibleText("Restaurant");
      Thread.sleep(1000);
      new Select(driver.findElement(By.id("categorySelect"))).selectByVisibleText("Retail");
      Thread.sleep(1000);
      Select subCategories = new Select(driver.findElement(By.id("subCategorySelect")));
      
      String[] expectedValues = {" Boutique", "Bicycle Shop", "Boutique", "Clothes", "Clothing", "Pet Supply"};
      List<WebElement> subCategoryValues = subCategories.getOptions();

	  for(int i = 0; i < subCategoryValues.size(); i++) {
		  Assert.assertEquals(expectedValues[i], subCategoryValues.get(i).getText());
	  }
   }
   
   @Test
   public void testMarkersArePresent() throws Exception {
      driver.get("http://fishertodoapp.ddns.net:8080/GoLocal/");
      Thread.sleep(1000);
      new Select(driver.findElement(By.id("categorySelect"))).selectByVisibleText("Restaurant");
      Thread.sleep(1000);
      
      Assert.assertNotNull(driver.findElements(By.xpath("//img[@src='https://maps.gstatic.com/mapfiles/api-3/images/spotlight-poi2.png']")).get(0));
   }
   
   @Test
   public void testMarkersBringSidebarWithPhoto() throws Exception {
	  driver.get("http://fishertodoapp.ddns.net:8080/GoLocal/");
      Thread.sleep(1000);
      new Select(driver.findElement(By.id("categorySelect"))).selectByVisibleText("Restaurant");
      Thread.sleep(1000);
      
      List<WebElement> markers = driver.findElements(By.xpath("//img[@src='https://maps.gstatic.com/mapfiles/api-3/images/spotlight-poi2.png']"));
      Actions builder = new Actions(driver);   
      builder.moveToElement(markers.get(0), 1, 1).click().build().perform();
      Assert.assertTrue(driver.findElement(By.id("storeImage")).isDisplayed());
   }
   
   @Test
   public void testMarkersBringSidebarWithName() throws Exception {
	  driver.get("http://fishertodoapp.ddns.net:8080/GoLocal/");
	  Thread.sleep(1000);
      new Select(driver.findElement(By.id("categorySelect"))).selectByVisibleText("Restaurant");
      Thread.sleep(1000);
      
      List<WebElement> markers = driver.findElements(By.xpath("//img[@src='https://maps.gstatic.com/mapfiles/api-3/images/spotlight-poi2.png']"));
      Actions builder = new Actions(driver);   
      builder.moveToElement(markers.get(0), 1, 1).click().build().perform();
      Assert.assertEquals("Bailey's Breakfast & Lunch", driver.findElement(By.id("storeName")).getText());
   }
   
   @Test
   public void testMarkersBringSidebarWithRating() throws Exception {
	  driver.get("http://fishertodoapp.ddns.net:8080/GoLocal/");
      Thread.sleep(1000);
      new Select(driver.findElement(By.id("categorySelect"))).selectByVisibleText("Restaurant");
      Thread.sleep(1000);
      
      List<WebElement> markers = driver.findElements(By.xpath("//img[@src='https://maps.gstatic.com/mapfiles/api-3/images/spotlight-poi2.png']"));
      Actions builder = new Actions(driver);   
      builder.moveToElement(markers.get(0), 1, 1).click().build().perform();
      Assert.assertEquals("Bailey's Breakfast & Lunch", driver.findElement(By.id("storeName")).getText());
      Assert.assertTrue(driver.findElement(By.id("ratingAverage")).isDisplayed());
   }
   
   @Test
   public void testMarkersBringSidebarWithReviews() throws Exception {
	  driver.get("http://fishertodoapp.ddns.net:8080/GoLocal/");
      Thread.sleep(1000);
      new Select(driver.findElement(By.id("categorySelect"))).selectByVisibleText("Restaurant");
      Thread.sleep(1000);
      
      List<WebElement> markers = driver.findElements(By.xpath("//img[@src='https://maps.gstatic.com/mapfiles/api-3/images/spotlight-poi2.png']"));
      Actions builder = new Actions(driver);   
      builder.moveToElement(markers.get(0), 1, 1).click().build().perform();
      Assert.assertTrue(driver.findElement(By.id("reviewsHeader")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.id("reviewAuthor")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.id("reviewRating")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.id("reviewDate")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.id("reviewText")).isDisplayed());
   }
   
   @Test
   public void testMarkersChangeAfterMainCategoryIsClicked() throws Exception {
	  driver.get("http://fishertodoapp.ddns.net:8080/GoLocal/");
      Thread.sleep(1000);
      new Select(driver.findElement(By.id("categorySelect"))).selectByVisibleText("Restaurant");
      Thread.sleep(1000);
      new Select(driver.findElement(By.id("categorySelect"))).selectByVisibleText("Retail");
      Thread.sleep(1000);
      
      List<WebElement> markers = driver.findElements(By.xpath("//img[@src='https://maps.gstatic.com/mapfiles/api-3/images/spotlight-poi2.png']"));
      Actions builder = new Actions(driver);   
      builder.moveToElement(markers.get(0), 1, 1).click().build().perform();
      Thread.sleep(1000);
      
      ArrayList<String> retailNames = new ArrayList<String>();
      retailNames.add("Beyourself Boutique");
      retailNames.add("Four Sisters Boutique");
      retailNames.add("Apricot Lane");
      retailNames.add("All About Me Boutique");

      Assert.assertTrue(retailNames.contains(driver.findElement(By.id("storeName")).getText()));
      Assert.assertTrue(driver.findElement(By.id("storeImage")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.id("ratingAverage")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.id("reviewsHeader")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.id("reviewAuthor")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.id("reviewRating")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.id("reviewDate")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.id("reviewText")).isDisplayed());
   }
   
   @Test
   public void testMarkersChangeAfterSubCategoryIsClicked() throws Exception {
	  driver.get("http://fishertodoapp.ddns.net:8080/GoLocal/");
      Thread.sleep(1000);
      new Select(driver.findElement(By.id("categorySelect"))).selectByVisibleText("Restaurant");
      Thread.sleep(1000);
      new Select(driver.findElement(By.id("subCategorySelect"))).selectByVisibleText("Greek");
      Thread.sleep(1000);
      
      List<WebElement> markers = driver.findElements(By.xpath("//img[@src='https://maps.gstatic.com/mapfiles/api-3/images/spotlight-poi2.png']"));
      Actions builder = new Actions(driver);   
      builder.moveToElement(markers.get(0), 1, 1).click().build().perform();
      Thread.sleep(1000);
      
      ArrayList<String> retailNames = new ArrayList<String>();

      Assert.assertTrue((driver.findElement(By.id("storeName")).getText() != "Bailey's Breakfast & Lunch"));
      Assert.assertTrue(driver.findElement(By.id("storeImage")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.id("ratingAverage")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.id("reviewsHeader")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.id("reviewAuthor")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.id("reviewRating")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.id("reviewDate")).isDisplayed());
      Assert.assertTrue(driver.findElement(By.id("reviewText")).isDisplayed());
   }

   @After
   public void tearDown() throws Exception {
      Thread.sleep(1000);
      driver.quit();
      String verificationErrorString = verificationErrors.toString();
      if (!"".equals(verificationErrorString)) {
         fail(verificationErrorString);
      }
   }
}
