package core;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


//1) на странице http://localhost/litecart/admin/?app=countries&doc=countries
//б) проверить, что для тех стран, у которых количество зон отлично от нуля - открыть страницу 
//   этой страны и там проверить, что зоны расположены в алфавитном порядке


/**
 * @author Dmitry Nakhabtsev
 */
public class TestCase9b {

public WebDriver driver;
public WebDriverWait wait;

public boolean areElementsPresent(By locator){
    return driver.findElements(locator).size() > 0;}

public boolean isElementPresent(By locator){
    try{driver.findElement(locator);return true;}
    catch(NoSuchElementException ex){return false;}}

@Test
public void test() throws InterruptedException{

	Integer x = 0;
	String url="http://localhost/litecart/admin/?app=countries&doc=countries";
	
	List<WebElement> innerZones=new ArrayList<WebElement>();
	List<String> zoneCountries = new ArrayList<>();


    driver = new EdgeDriver();
//	driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
	driver.get(url); 
	
	driver.findElement(By.name("username")).sendKeys("admin");
	driver.findElement(By.name("password")).sendKeys("admin");
	driver.findElement(By.name("login")).click();
	
	
	List<WebElement> countries = driver.findElements(By.cssSelector(".row"));
    System.out.println("countries="+countries.size());
    	
    	for(WebElement c:countries){
    		x = Integer.valueOf(c.findElement(By.cssSelector("td:nth-child(6)")).getAttribute("textContent"));
    		if(x!=0){zoneCountries.add(c.findElement(By.cssSelector("a")).getAttribute("innerText"));}
    	}

    	System.out.println("Zone countries="+zoneCountries.size());	
        for (String zc : zoneCountries){
            System.out.println(zc);
        	driver.findElement(By.linkText(zc)).click();
            innerZones=driver.findElements(By.cssSelector("#table-zones td:nth-child(3) input[type=hidden]"));
            System.out.println("country zones="+innerZones.size());
            
        	List<String> zones=new ArrayList<String>();
        	List<String> sortedZones=new ArrayList<String>();
            for(WebElement iz:innerZones){
            	zones      .add(iz.getAttribute("value"));
            	sortedZones.add(iz.getAttribute("value"));
            }
            Collections.sort(sortedZones);
//          System.out.println(zones);
//          System.out.println(sortedZones);
            Assert.assertEquals(sortedZones,zones);
            driver.navigate().back();
        }
        
    System.out.println("Congratulations!");
	driver.quit();
	driver = null;

}
}