package com.dh.webtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dh.web.OnlineShoppingApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OnlineShoppingApplication.class)
@WebAppConfiguration
public class OnlineShoppingApplicationTests {

	@Test
	public void contextLoads() {
	}

}
