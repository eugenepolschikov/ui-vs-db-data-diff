package com.abbydemo.pages;

import com.abbydemo.core.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By loginForm = By.cssSelector("form#login-form");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        open(Config.baseUrl() + "/login");
        waitForVisible(loginForm);
        return this;
    }

    public HomePage loginAs(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
        return new HomePage(driver);
    }
}
