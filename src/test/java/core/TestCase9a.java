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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


//1) на странице http://localhost/litecart/admin/?app=countries&doc=countries
//а) проверить, что страны расположены в алфавитном порядке



/**
 * @author Dmitry Nakhabtsev
 */
public class TestCase9a {

public WebDriver driver;
public WebDriverWait wait;

public boolean areElementsPresent(By locator){
    return driver.findElements(locator).size() > 0;}

public boolean isElementPresent(By locator){
    try{driver.findElement(locator);return true;}
    catch(NoSuchElementException ex){return false;}}

@Test
public void test() throws InterruptedException{

	String url="http://localhost/litecart/admin/?app=countries&doc=countries";
	List<String> countryNames=new ArrayList<String>();
	List<String> sortedCountryNames=new ArrayList<String>();

    driver = new EdgeDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
	driver.get(url); 
	
	driver.findElement(By.name("username")).sendKeys("admin");
	driver.findElement(By.name("password")).sendKeys("admin");
	driver.findElement(By.name("login")).click();
	
    List<WebElement> countries = driver.findElements(By.cssSelector(".row"));
    System.out.println("countries="+countries.size());
    	
    	for(WebElement el:countries){
    		countryNames      .add(el.findElement(By.cssSelector("td:nth-child(5) a")).getAttribute("text"));
    		sortedCountryNames.add(el.findElement(By.cssSelector("td:nth-child(5) a")).getAttribute("text"));
    		}
 
    	Collections.sort(sortedCountryNames);
    	Assert.assertEquals(countryNames,sortedCountryNames);
    	
//    	for(int j=0;j<countries.size();j++){
//    		System.out.println(countryNames.get(j));
//    		System.out.println(sortedCountryNames.get(j));
//    		System.out.println();
//    	}
	
	driver.quit();
	driver=null;

}
}