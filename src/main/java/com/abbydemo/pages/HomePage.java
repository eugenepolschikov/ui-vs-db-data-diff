package com.abbydemo.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private final By pageTitle = By.cssSelector("h1.home-title");
    private final By phiTable = By.id("phi-records-table");
    private final By phiTableRows = By.cssSelector("#phi-records-table tbody tr");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isVisible(pageTitle) && isVisible(phiTable);
    }

    public List<String> getPhiRecordsAsRawRows() {
        waitUntilTableLoaded(phiTableRows);
        List<WebElement> rows = waitForAllVisible(phiTableRows);
        List<String> data = new ArrayList<>();

        for (WebElement row : rows) {
            data.add(row.getText().trim());
        }
        return data;
    }
}
