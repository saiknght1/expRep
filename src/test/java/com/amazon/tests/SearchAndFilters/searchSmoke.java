package com.amazon.tests.SearchAndFilters;

import com.amazon.base.BaseTest;
import com.amazon.pages.searchAndFilters;
import org.testng.Assert;
import org.testng.annotations.Test;

public class searchSmoke extends BaseTest {
    private String query = "iPhone";
    @Test
    public void verifySearchFunctionality(){
        searchAndFilters sf = new searchAndFilters(driver);
        sf.enterSearchQuery(query);
        sf.clickSearchButton();
        //Assert.assertTrue(sf.isResultsPageVisible(),"Search smoke failed");
        Assert.assertTrue(sf.areResultsDisplayed(),"Search smoke failed");
    }
}
