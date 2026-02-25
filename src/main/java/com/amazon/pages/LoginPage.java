package com.amazon.pages;

import com.amazon.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private WaitUtils wait;
    private By signInButton = By.id("nav-link-accountList-nav-line-1");
    private By mobileInput = By.id("ap_email_login");
    private By continueButton = By.xpath("//input[@aria-labelledby='continue-announce']");
    private By passwordInput = By.id("ap_password");
    private By hamburgerNav= By.id("nav-hamburger-menu");
    private By signOutButton = By.xpath("//a[text()='Sign Out']");
    private By signInSubmit = By.id("signInSubmit");
    private By invalidFormatError = By.xpath("//div[contains(text(),'Invalid')]");
   // private By invalidPhoneValidation = By.xpath("//div[contains(text(),'Invalid mobile')]");
    private By mandatoryFieldError = By.xpath("//div[contains(text(),'Enter your mobile number')]");
    //private By blankPasswordValidation = By.xpath("//div[contains(text(),'Enter your password')]");
    private By authenticationError = By.xpath("//div[contains(.,'Your password is incorrect') and contains(@class,'a-alert-content')]");

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }
    public void clickSignIn(){
        wait.waitForElementToBeClickable(signInButton);
        driver.findElement(signInButton).click();
    }
    public void enterMailORNum(String mailNumInput){
        wait.waitForElementToBeVisible(mobileInput);
        driver.findElement(mobileInput).sendKeys(mailNumInput);
    }
    public void clickContinue(){
        wait.waitForElementToBeClickable(continueButton);
       driver.findElement(continueButton).click();
    }
    public void enterPassword(String password){
        wait.waitForElementToBeVisible(passwordInput);
        driver.findElement(passwordInput).sendKeys(password);
    }
    public void clicksiginafterlogin(){
        wait.waitForElementToBeClickable(hamburgerNav);
        driver.findElement(hamburgerNav).click();
    }
    public Boolean locateSignoutButton(){
        wait.waitForElementToBeVisible(signOutButton);

        return driver.findElement(signOutButton).isDisplayed();
    }
    public void signInSubmit(){
        wait.waitForElementToBeClickable(signInSubmit);
            driver.findElement(signInSubmit).click();
    }
    public boolean passwordInputVisibilty(){
        wait.waitForElementToBeVisible(passwordInput);
        return driver.findElement(passwordInput).isDisplayed();
    }
    public boolean isInvalidFormatErrorDisplayed(){
        wait.waitForElementToBeVisible(invalidFormatError);
        return driver.findElement(invalidFormatError).isDisplayed();
    }
//    public boolean invalidPhoneValidation(){
//        wait.waitForElementToBeVisible(invalidPhoneValidation);
//        return driver.findElement(invalidPhoneValidation).isDisplayed();
//    }

    public boolean isMandatoryFieldErrorDisplayed(){
        wait.waitForElementToBeVisible(mandatoryFieldError);
        return driver.findElement(mandatoryFieldError).isDisplayed();
    }
//    public boolean blankPasswordValidation(){
//        wait.waitForElementToBeVisible(blankPasswordValidation);
//        return driver.findElement(blankPasswordValidation).isDisplayed();
//    }
    public boolean isAuthenticationErrorDisplayed(){
        wait.waitForElementToBeVisible(authenticationError);
        return driver.findElement(authenticationError).isDisplayed();
    }

}
