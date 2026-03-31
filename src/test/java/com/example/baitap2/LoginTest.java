package com.example.baitap2;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.Assert;

import com.example.utils.DriverFactory;

public class LoginTest {

    @BeforeMethod
    public void setup() {

        DriverFactory.initDriver("chrome");

        DriverFactory.getDriver().get("https://www.saucedemo.com/");

        System.out.println("Setup LoginTest - Thread ID: " + Thread.currentThread().getId());
    }
    //Include với Tesng-smoke 
    @Test(groups = {"smoke","regression"})
    public void testLoginSuccess() throws InterruptedException {

        System.out.println("Login success - Thread ID: " + Thread.currentThread().getId());

        Thread.sleep(5000); // giữ browser 5 giây để thấy rõ

        Assert.assertTrue(true);
    }

    @Test(groups = {"regression"})
    public void testLoginFail() throws InterruptedException {

        System.out.println("Login fail - Thread ID: " + Thread.currentThread().getId());

        Thread.sleep(5000);

        Assert.assertTrue(true);
    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();

    }
}