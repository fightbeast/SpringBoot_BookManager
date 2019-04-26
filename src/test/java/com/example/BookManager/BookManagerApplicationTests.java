package com.example.BookManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.BookManager.biz.LoginBiz;
import com.example.BookManager.model.constants.User;
import com.example.BookManager.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BookManagerApplication.class)
public class BookManagerApplicationTests {
	@Autowired
	private LoginBiz loginBiz;
	

	@Test
	public void loginTest() {

}}
	
