package com.abbydemo.core;

public final class Config {

    private Config() {
    }

    public static String baseUrl() {
        return System.getProperty("base.url");
    }
}
