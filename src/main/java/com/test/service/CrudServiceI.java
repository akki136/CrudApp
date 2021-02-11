package com.test.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.test.BO.DemoTest;

public interface CrudServiceI {

	List<DemoTest> getDemoDataAccountList();
	 DemoTest getDemoDataAccount(Long id);
	 DemoTest getAccountByAadhar(Long aadhar);
	 ResponseEntity<String> addDemoAccount(DemoTest demoTest);
	 ResponseEntity<String>  deleteAccount(Long id);
	  ResponseEntity<String>  addAmount(Long id,DemoTest demoTest,String type);
	  ResponseEntity<String> withdrawAmount(Long id,Double Amount);
}
