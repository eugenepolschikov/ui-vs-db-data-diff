package com.abbydemo.pages;

import com.abbydemo.models.PhiDto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private final By pageTitle = By.cssSelector("h1.home-title");
    private final By phiTable = By.id("phi-records-table");
    private final By phiTableRows = By.cssSelector("#phi-records-table tbody tr");
    private static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";

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

    public Set<PhiDto> getPhiRecordsAsSet() {
        waitUntilTableLoaded(phiTableRows);
        List<WebElement> rows = waitForAllVisible(phiTableRows);
        Set<PhiDto> result = new HashSet<>();

        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            if (columns.size() < 4) {
                continue;
            }

            Integer id = Integer.parseInt(columns.get(0).getText().trim());
            String description = columns.get(1).getText().trim();
            Date timestamp = parseTimestamp(columns.get(2).getText().trim());
            String severity = columns.get(3).getText().trim();

            result.add(new PhiDto(id, description, timestamp, severity));
        }
        return result;
    }

    private Date parseTimestamp(String timestampRaw) {
        String format = System.getProperty("ui.timestamp.format", DEFAULT_TIMESTAMP_FORMAT);
        try {
            return new SimpleDateFormat(format).parse(timestampRaw);
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Failed to parse UI timestamp: " + timestampRaw, ex);
        }
    }
}
