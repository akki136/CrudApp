package com.test.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.BO.DemoTest;
import com.test.Dao.CrudRepositoryDao;
import com.test.Exception.DemoTestException;
import com.test.entity.DemoTestEntity;
import com.test.service.CrudServiceI;

@Service
public class CrudServiceImpl implements CrudServiceI{

	@Autowired
	CrudRepositoryDao  crudRepository;

	Logger logger = LogManager.getLogger(CrudServiceImpl.class);

	@Override
	public List<DemoTest> getDemoDataAccountList() {
		List<DemoTest> demoTests=crudRepository.findDemoDataList().parallelStream().map(demoTestEntity ->{
			DemoTest demoTest=new DemoTest();
			demoTest=new DemoTest();
			demoTest.setAadharCard(demoTestEntity.getAadharCard());
			demoTest.setDepositAmount(demoTestEntity.getDepositAmount());
			demoTest.setFirstName(demoTestEntity.getFirstName());
			demoTest.setLastName(demoTestEntity.getLastName());
			demoTest.setLastName(demoTestEntity.getLastName());
			return demoTest;
		}).collect(Collectors.toList());
		return demoTests;
	}

	@Override
	public DemoTest getDemoDataAccount(Long id) {
		DemoTest demoTest=new DemoTest();
		BeanUtils.copyProperties(demoTest, crudRepository.findById(id));
		// TODO Auto-generated method stub
		return demoTest;
	}

	@Override
	public  ResponseEntity<String> addDemoAccount(DemoTest demoTest) {

		String response="";
		try {
			return crudRepository.addDemoAccount(demoTest);
		}  catch (Exception e) {
			logger.error("AddDemoAccount Error::{}",e.getMessage());
			response=e.getMessage();
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}



	}

	@Override
	public ResponseEntity<String> deleteAccount(Long id) {
		String response="";
		try {
			return	crudRepository.DeleteDemoAccount(id);

		} catch (DemoTestException e) {
			logger.error("AddDemoAccount Error::{}",e.getMessage());
			response=e.getMessage();
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}

	@Override
	public ResponseEntity<String>  addAmount(Long aadhar, DemoTest demoTest,String type) {
		String response="";
		try {

			return crudRepository.addAmount(aadhar,demoTest.getDepositAmount(),type);
		} catch (DemoTestException e) {
			logger.error("addAmount Error::{}",e.getMessage());
			response=e.getMessage();
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}



	}

	@Override
	public ResponseEntity<String> withdrawAmount(Long aadhar, Double amount) {
		String response="";
		try {
			return crudRepository.withdrawAmount(aadhar,amount);


		} catch (DemoTestException e) {
			logger.error("withdrawAmount Error::{}",e.getMessage());
			response=e.getMessage();
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}

	@Override
	public DemoTest getAccountByAadhar(Long aadhar)  {
		DemoTest demoTest=null;

		
		try {
			DemoTestEntity demoTestEntity=crudRepository.findDemoDataByAadhar(aadhar);
			if(demoTestEntity!=null)
			{
			demoTest=new DemoTest();
			demoTest.setAadharCard(demoTestEntity.getAadharCard());
			demoTest.setDepositAmount(demoTestEntity.getDepositAmount());
			demoTest.setFirstName(demoTestEntity.getFirstName());
			demoTest.setLastName(demoTestEntity.getLastName());
			demoTest.setLastName(demoTestEntity.getLastName());
			}
		}  catch (Exception e) {
			logger.error("getAccountByAadhar Error::{}",e.getMessage());
		}
		//response="Ammount "+demoTest.getId()+" Credited to Account :: "+demoTest.getDepositAmount();


		return demoTest;
	}

}
