package com.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// ===== THÊM =====
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
// ===== HẾT PHẦN THÊM =====

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    // mỗi thread có driver riêng
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static void initDriver(String browser) {

        WebDriver driver;
        boolean isCI = System.getenv("CI") != null;

        switch (browser.toLowerCase()) {

            // ===== THÊM =====
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();

                if (isCI) {
                    edgeOptions.addArguments("--headless=new");
                    edgeOptions.addArguments("--no-sandbox");
                    edgeOptions.addArguments("--disable-dev-shm-usage");
                    edgeOptions.addArguments("--window-size=1920,1080");
                    System.out.println("Chạy Edge ở chế độ headless trên CI");
                } else {
                    edgeOptions.addArguments("--start-maximized");
                    System.out.println("Chạy Edge ở chế độ local");
                }

                driver = new EdgeDriver(edgeOptions);
                break;
            // ===== HẾT PHẦN THÊM =====

            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();

                if (isCI) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    System.out.println("Chạy Chrome ở chế độ headless trên CI");
                } else {
                    chromeOptions.addArguments("--start-maximized");
                    System.out.println("Chạy Chrome ở chế độ local");
                }

                driver = new ChromeDriver(chromeOptions);
                break;
        }

        tlDriver.set(driver);
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}