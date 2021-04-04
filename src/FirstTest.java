import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class FirstTest {

	WebDriver driver;
	
	@BeforeTest
	public void launchTask() {
		
		System.setProperty("webdriver.chrome.driver", "D:\\New folder\\chromedriver.exe");

		driver= new ChromeDriver();
		
		
		//driver.manage().window().maximize();
		
		driver.get("http://automationpractice.com/index.php");
		
		String winTitle = driver.getTitle();
		System.out.println("title =" +winTitle);
	}
	
	
	@Test
	public void Task() {
		
		driver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[1]/a")).click(); //selecting and clicking the signin button
		
		//generating random number
		Random randomGenerator = new Random();  
		int randomInt = randomGenerator.nextInt(1000);
		
		//email
		WebElement email = driver.findElement(By.xpath("//*[@id=\"email_create\"]"));
		email.sendKeys("syed"+ randomInt +"@gmail.com");
		
		//create an account button
		WebElement enter = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div[1]/form/div/div[3]/button/span"));
				enter.click();
		
		//getting the title of the page
		String winTitle02 = driver.getTitle();
		System.out.println("Wait a moment, we are landing to the page named " + winTitle02);
		
		//setting an implicitly wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//form input
		driver.findElement(By.xpath("//*[@id=\"id_gender1\"]")).click();
		driver.findElement(By.xpath("//input[@name=\"customer_firstname\"]")).sendKeys("Syed");
		driver.findElement(By.xpath("//*[@id=\"customer_lastname\"]")).sendKeys("Hossain");
		
		driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("1234" + randomInt);
		
		//Day, Month, Year 10th August, 1985
		Select selDay = new Select(driver.findElement(By.xpath("//*[@id=\"days\"]")));
		selDay.selectByIndex(10);
		Select selMonth = new Select(driver.findElement(By.xpath("//*[@id=\"months\"]")));
		selMonth.selectByIndex(8);
		Select selYear = new Select(driver.findElement(By.xpath("//*[@id=\"years\"]")));
		selYear.selectByValue("1985");
		
		//Signup with newsletter
		driver.findElement(By.xpath("//*[@id=\"newsletter\"]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"address1\"]")).sendKeys("Kallyanpur, Mirpur, Dhaka");
		driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys("Dora");
		
		//State = Arizona
		Select state = new Select(driver.findElement(By.xpath("//*[@id=\"id_state\"]")));
		state.selectByVisibleText("Arizona");
		driver.findElement(By.xpath("//*[@id=\"postcode\"]")).sendKeys("00001");
		
		driver.findElement(By.xpath("//*[@id=\"phone_mobile\"]")).sendKeys("0000187867");
		
		//submit button
		driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/form/div[4]/button/span")).click();
		
	}
	
	
	@AfterTest
	public void verify() {
		
		String ActualURL = "http://automationpractice.com/index.php?controller=my-account";
		String ExpectedURL = driver.getCurrentUrl();
		
		//assertion to verify the new user
		Assert.assertEquals(ActualURL, ExpectedURL);
		
		if(ActualURL.equals(ExpectedURL)) {
			System.out.println("New User is Verified");
			driver.close();
		}else {
			System.out.println("Error");
			driver.close();
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FirstTest obj = new FirstTest();
		obj.launchTask();
		obj.Task();
		obj.verify();

	}

}
