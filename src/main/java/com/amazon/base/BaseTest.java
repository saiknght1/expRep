package com.amazon.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
   public static int m ;
    public static void m2(){
System.out.println("login in");
    }
    @BeforeMethod
    public void setup(){
     driver= new ChromeDriver();
     driver.manage().window().maximize();
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
     driver.get("https://www.amazon.in/");
    }
    @AfterMethod
    public void teardown(){
        if(driver!=null){
            driver.quit();
        }


    }

}
