package com.abbydemo.tests;

import com.abbydemo.pages.HomePage;
import com.abbydemo.pages.LoginPage;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginAndPhiTableTest extends BaseTest {

    @Test
    public void userCanLoginAndSeePhiRecordsTable() {
        HomePage homePage = new LoginPage(driver)
                .open()
                .loginAs("admin", "admin123");

        Assert.assertTrue(homePage.isLoaded(), "Home page or PHI table was not loaded.");

        List<String> records = homePage.getPhiRecordsAsRawRows();
        Assert.assertFalse(records.isEmpty(), "Expected at least one PHI record row.");
    }
}
