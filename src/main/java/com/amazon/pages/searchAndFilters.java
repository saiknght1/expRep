package com.amazon.pages;

import com.amazon.utils.CleanersandConvertors;
import com.amazon.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class searchAndFilters {
    private WebDriver driver;
    private WaitUtils wait;
    private By searchInput = By.id("twotabsearchtextbox");
    private By searchButton = By.id("nav-search-submit-button");
    private By resultsPageText = By.xpath("//h2[text()='Results']");
    private By prodCard = By.xpath("//div[contains(@class,'puis-card-container')]");
    private By prodCardText = By.xpath("//div[contains(@class,'puis-card-container')]//div[@data-cy='title-recipe']");
    private By resultQueryText = By.xpath("//span[@data-component-type='s-result-info-bar']//span[contains(@class,'a-text-bold')]");
    private static final String homePageURL = "https://www.amazon.in/";
    private By lowerPriceSlider = By.xpath("//input[@aria-label='Minimum price']");
    private By higherPriceSlider = By.xpath("//input[@aria-label='Maximum price']");
    private By prodPrice= By.xpath("//div[contains(@class,'puis-card-container')]//span[@class='a-price-whole']");

   // private By brandFilterLabel = By.xpath("//span[text()='"+brand+"']/ancestor::a[contains(@aria-label,'Apply the filter')]//label");

    public searchAndFilters(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void enterSearchQuery(String query) {
        wait.waitForElementToBeVisible(searchInput);
        driver.findElement(searchInput).sendKeys(query);
    }

    public void clickSearchButton() {
        wait.waitForElementToBeClickable(searchInput);
        driver.findElement(searchButton).click();
    }

//public Boolean isResultsPageVisible(){
//wait.waitForElementToBeVisible(resultsPageText);
//return driver.findElement(resultsPageText).isDisplayed();
//}

    public boolean areResultsDisplayed() {
        wait.waitForElementToBeVisible(prodCard);
        List<WebElement> prodCards = driver.findElements(prodCard);
        int prodCardSize = prodCards.size();
        System.out.println(prodCardSize);
        return prodCardSize > 0;
    }

    public boolean isSearchQueryRetained(String query) {
        wait.waitForElementToBeVisible(searchInput);
        String searchInputValue = driver.findElement(searchInput).getAttribute("value");
        System.out.println(searchInputValue);
        System.out.println(query);
//System.out.println(driver.findElement(searchInput).getAttribute("value")==query.trim());
        return searchInputValue.trim().equals(query.trim());
    }

    public boolean isSearchQueryReflectedInResultsPage(String query) {
        wait.waitForElementToBeVisible(resultQueryText);
        String rqs = driver.findElement(resultQueryText).getText();
        System.out.println(rqs);

        return rqs.trim().contains(query);
    }

    public boolean isHomePageURLLoaded() throws InterruptedException {
        wait.waitForURLToBe(homePageURL);

        String currentURL = getCurrentURL();
        return currentURL.equals(homePageURL);
    }

    public String getCurrentURL() {

        return driver.getCurrentUrl();
    }

    public boolean isURLRefreshed(String urlBeforeSearch) {
        return getCurrentURL().equals(urlBeforeSearch);

    }


    public int setLowerPriceRange(int lr) {
        CleanersandConvertors cc= new CleanersandConvertors();
        wait.waitForElementToBeVisible(lowerPriceSlider);
        String lowerPrice = driver.findElement(lowerPriceSlider).getAttribute("aria-valuetext");
        lowerPrice = cc.priceStringCleaner(lowerPrice);
        int lp= cc.stringToIntConvertor(lowerPrice);


        while (lp < lr) {
            driver.findElement(lowerPriceSlider).sendKeys(Keys.ARROW_RIGHT);
            wait.waitForElementToBeVisible(lowerPriceSlider);
            lowerPrice = driver.findElement(lowerPriceSlider).getAttribute("aria-valuetext");
            lowerPrice = cc.priceStringCleaner(lowerPrice);// lowerPrice.replaceAll("[^0-9]", "");
            lp = cc.stringToIntConvertor(lowerPrice);
        }
        System.out.println(lp);
        return lp;
    }

    public int setHigherPriceRange(int hr) {
        CleanersandConvertors cc= new CleanersandConvertors();
        wait.waitForElementToBeVisible(higherPriceSlider);
        String higherPrice = driver.findElement(higherPriceSlider).getAttribute("aria-valuetext");
        higherPrice  = cc.priceStringCleaner(higherPrice);
        int hp = cc.stringToIntConvertor(higherPrice);

         int prev_hp =0;
        while (hp > hr && prev_hp!=hp) {
            prev_hp=hp;
            driver.findElement(higherPriceSlider).sendKeys(Keys.ARROW_LEFT);
            wait.waitForElementToBeVisible(higherPriceSlider);
            higherPrice = driver.findElement(higherPriceSlider).getAttribute("aria-valuetext");
            higherPrice = cc.priceStringCleaner(higherPrice);//higherPrice.replaceAll("[^0-9]", "");;//higherPrice.replaceAll("[^0-9]", "");
            hp = cc.stringToIntConvertor(higherPrice);//parseInt(higherPrice);;// parseInt(higherPrice);
        }
        System.out.println(hp);
        return hp;
    }

    public boolean isPriceWithinTheSetRange(int lr, int hr) throws InterruptedException {
        CleanersandConvertors cc= new CleanersandConvertors();
        wait.waitForElementToBeVisible(prodPrice);
        Thread.sleep(5000);
      List<WebElement>prodPrices = driver.findElements(prodPrice);


//        wait.until(driver -> {
//            String priceText = driver.findElements(prodPrice).get(0).getText();
//            return !priceText.isEmpty();
//        });//this is correct logic , fix in waitutils
        int cnt =0;
        for(int i =0;i<=5;i++){
            String price= prodPrices.get(i).getText();
            String cleanedPrice=cc.priceStringCleaner(price);
            int finalPrice = cc.stringToIntConvertor(cleanedPrice);
            System.out.println(finalPrice);
            if(finalPrice>=lr&&finalPrice<=hr){
                cnt++;
            }
        }

       /* for(WebElement pp: prodPrices ){

            String price=pp.getText();
            String cleanedPrice=cc.priceStringCleaner(price);//price.replaceAll("[^0-9]", "");
            int finalPrice = cc.stringToIntConvertor(cleanedPrice);
            System.out.println(finalPrice);
            if(finalPrice>=lr&&finalPrice<=hr){
              cnt++;
            }
        }*/
        return cnt>= 2;

    }

    public void selectBrandFilter(String brand) {

            wait.waitForElementToBeClickable(By.xpath("//span[text()='" + brand + "']/ancestor::a[contains(@aria-label,'Apply the filter')]//label"));



        driver.findElement(By.xpath("//span[text()='" + brand + "']/ancestor::a[contains(@aria-label,'Apply the filter')]//label")).click();

    }

    public boolean isBrandFilterSelected(String brand) throws InterruptedException {
        Thread.sleep(3000);

//        By brandCheckbox =
//                By.xpath("//span[text()='" + brand + "']/ancestor::a//input[@type='checkbox']");
//
//        wait.until(driver ->
//                driver.findElement(brandCheckbox).isSelected()
//        );
             wait.waitForElementToBePresent(By.xpath("//div[@id='brandsRefinements']//a[contains(@aria-label,'" + brand + "')]//input[@type='checkbox']"));
             return driver.findElement(By.xpath("//div[@id='brandsRefinements']//a[contains(@aria-label,'" + brand + "')]//input[@type='checkbox']")).isSelected();
//             System.out.println(checked);
//             if(checked.equals("true"))
//             {
//                 return true;
//             }
//             else{
//                 return false;
//             }
    }

    }


