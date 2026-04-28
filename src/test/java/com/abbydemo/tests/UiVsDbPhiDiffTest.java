package com.abbydemo.tests;

import com.abbydemo.db.DbConfig;
import com.abbydemo.db.MySqlPhiRepository;
import com.abbydemo.models.PhiDto;
import com.abbydemo.pages.HomePage;
import com.abbydemo.pages.LoginPage;
import java.util.HashSet;
import java.util.Set;
import org.testng.annotations.Test;

public class UiVsDbPhiDiffTest extends BaseTest {

    @Test
    public void compareUiAndDbPhiRecordsAndPrintDifference() {
        String username = System.getProperty("ui.username", "admin");
        String password = System.getProperty("ui.password", "admin123");
        String dbQuery = System.getProperty("db.phi.query",
                "SELECT id, description, timestamp, severity FROM phi_records");

        HomePage homePage = new LoginPage(driver)
                .open()
                .loginAs(username, password);

        Set<PhiDto> uiSet = homePage.getPhiRecordsAsSet();
        Set<PhiDto> dbSet = new MySqlPhiRepository(DbConfig.fromSystemProperties()).fetchPhiRecords(dbQuery);

        Set<PhiDto> uiMinusDb = new HashSet<>(uiSet);
        uiMinusDb.removeAll(dbSet);

        Set<PhiDto> dbMinusUi = new HashSet<>(dbSet);
        dbMinusUi.removeAll(uiSet);

        printDifference("UI - DB (missing in DB)", uiMinusDb);
        printDifference("DB - UI (extra in DB)", dbMinusUi);
    }

    private void printDifference(String title, Set<PhiDto> difference) {
        System.out.println("========== " + title + " ==========");
        if (difference.isEmpty()) {
            System.out.println("No records in this difference set.");
            return;
        }
        for (PhiDto phiDto : difference) {
            System.out.println(phiDto);
        }
    }
}
