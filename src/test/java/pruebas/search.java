package pruebas;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import Tools.EnumBrowser;
import Tools.SeleniumTools;

	//-----------------------------------------------------CODIGO BASE-------------------------------------- 
	//-----------------------------------------------------INICIA CLASE PARAMETRIZADA-------------------------------------- 
@RunWith(Parameterized.class)
public class search {
	@Parameter
	public static String browser;
	public static WebDriver driver = null;
	//-----------------------------------------------------SE DEFINEN LOS BROWSERS COMO PARAMETROS-------------------------------------- 	
	@Parameters
	public static Object[] getBrowser(){
		return new Object[]{ 
				EnumBrowser.CHROME.toString(), 
				EnumBrowser.FIREFOX.toString(),
		};
	}
	//-----------------------------------------------------DEFINICION DE LOS NAVEGADORES A UTILIZAR-------------------------------------- 
	@Before
	public void setUpDriver() {
		if(browser.equals(EnumBrowser.CHROME.toString())) {
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}else if(browser.equals(EnumBrowser.FIREFOX.toString())) {
			System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe"); 
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
	}
	
	//-----------------------------------------------------CODIGO A AUTOMATIZAR-------------------------------------- 
	@Test
	public void SearchAmazon() {
		try {
			SeleniumTools.get("https://www.amazon.com/-/es/",driver);
			SeleniumTools.findElementById("twotabsearchtextbox",driver).clear();
			SeleniumTools.findElementById("twotabsearchtextbox",driver).sendKeys("iphone 14 pro");
			SeleniumTools.findElementById("nav-search-submit-button",driver).click();
			SeleniumTools.findElementById("a-autoid-0-announce",driver).click();
			SeleniumTools.findElementById("s-result-sort-select_1",driver).click();
			Thread.sleep(3000);
		    SeleniumTools.findElementByXpath("//div[@id='search']/div/div/div/span/div/div[3]/div/div/div/div/div/div[2]/div/div/div/h2/a/span",driver).click();
		    SeleniumTools.findElementById("add-to-cart-button",driver).click();
		    SeleniumTools.findElementByXpath("//span[@id='sw-gtc']/span/a",driver).click();
		    assertEquals("Subtotal (1 producto):", SeleniumTools.findElementById("sc-subtotal-label-activecart",driver).getText());
		    Thread.sleep(3000);
		    SeleniumTools.findElementByXpath("/html/body/div[1]/div[2]/div[3]/div[4]/div/div[2]/div[1]/div/form/div[2]/div[3]/div[4]/div/div[2]/div[1]/span[2]/span/input",driver).click();
		    assertEquals("Tu carrito de Amazon está vacío.", SeleniumTools.findElementByXpath("//div[@id='sc-active-cart']/div/div/div/h1",driver).getText());
		  
		    
		//-----------------------------------------------------CIERRE DE CODIGO A AUTOMATIZAR--------------------------------------   
		    
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@After
	public void tearDown() {
		SeleniumTools.closeDriver(driver);
		
	}


}
