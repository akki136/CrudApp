package com.test.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.test.BO.DemoTest;
import com.test.BO.DemoWithdrawBO;
import com.test.serviceImpl.CrudServiceImpl;

import io.swagger.annotations.Api;

@Api(value = "CrudController", description = "REST Apis related to Crud Operation!!!!")
@RestController
@Validated
public class CrudController {
	
	@Autowired
	CrudServiceImpl curCrudServiceImpl;

	@ApiOperation(value = "Get list of Demo Accounts  ", response = Iterable.class, tags = "getAllAccount")
	@RequestMapping(method = RequestMethod.GET, value = "/getAllAccount")
	public ResponseEntity<List<DemoTest>> getAllDemoAccounts() {
		try {

			return new ResponseEntity<>(curCrudServiceImpl.getDemoDataAccountList(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Get Account By Aadhar", response = Iterable.class, tags = "getDemoAccountByAadhar")
	@GetMapping("/getAccountByAadhar/{aadhar}")
	public ResponseEntity getDemoAccountByAadhar(@PathVariable("aadhar") @Min(value = 12, message = "Aadhar card length  should be of minimum 12 digit") long aadhar) {
		    DemoTest demoTest=curCrudServiceImpl.getAccountByAadhar(aadhar);

		if (demoTest!=null)
		{
			return new ResponseEntity<>(demoTest, HttpStatus.OK);
		} else {
			return new ResponseEntity("Account not Found",HttpStatus.NOT_FOUND);
		}
	}
//	@ApiOperation(value = "add Account", response = Iterable.class, tags = "addDemoAccount")
//	@PostMapping("/AddAccount")
//	public ResponseEntity<String> addDemoAccount(@Valid @RequestBody DemoTest demoTest) {
//		try {
//			//return  curCrudServiceImpl.addDemoAccount(demoTest);
//			
//		} catch (Exception e) {
//			return new ResponseEntity<>("Failed to add Account", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		return null;
//	}
	
	@ApiOperation(value = "add Test Account", response = Iterable.class, tags = "addTestDemoAccount")
	@PostMapping("/AddAccountInfo")
	public ResponseEntity<String> addTestDemoAccount(@Valid @RequestBody DemoTest demoTest) {
		try {
			return  curCrudServiceImpl.addDemoAccount(demoTest);
			
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to add Account", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value = "add Amount to account", response = Iterable.class, tags = "addAmountToAccount")
	@PutMapping("/AddAmountToAccount/{aadhar}")
	public ResponseEntity<String> addAmountToAccount(@PathVariable("aadhar") long aadhar, @Valid @RequestBody DemoTest demoTest) {
	return  curCrudServiceImpl.addAmount(aadhar, demoTest,"credit");
	}
	
	@ApiOperation(value = "withdraw Amount from account", response = Iterable.class, tags = "withDrawAmount")
	@PutMapping("/WithdrawAmountFromAccount/{aadhar}")
	public ResponseEntity<String> withDrawAmount(@PathVariable("aadhar") long aadhar, @Valid @RequestBody DemoWithdrawBO demoWithdrawBO) {
	return  curCrudServiceImpl.withdrawAmount(aadhar, demoWithdrawBO.getWithdrawAmount());
	}
	
	@ApiOperation(value = "delete account", response = Iterable.class, tags = "deleteAccount")
	@DeleteMapping("/DeleteAccount/{aadhar}")
	public ResponseEntity deleteAccount(@PathVariable("aadhar") long aadhar) {
		try {
			return curCrudServiceImpl.deleteAccount(aadhar);
		
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

}
