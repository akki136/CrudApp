package com.test.Dao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.BO.DemoTest;
import com.test.Exception.DemoTestException;
import com.test.entity.DemoTestEntity;
import com.test.repository.DemoTestRepository;
import com.test.serviceImpl.CrudServiceImpl;

@Repository
public class CrudRepositoryDao {

	Logger logger = LogManager.getLogger(CrudRepositoryDao.class);
	

	
	@Autowired
	DemoTestRepository demoTestRepository;
	
	
	String className = "DemoTestEntity";
	public DemoTestEntity findById(Long id)
	{
		return this.demoTestRepository.findOne(id);
	}

	public List<DemoTestEntity> findDemoDataList()
	{
		
		
		List<DemoTestEntity> result = 
				  StreamSupport.stream(this.demoTestRepository.findAll().spliterator(), false)
				    .collect(Collectors.toList());
	return result;
	
	}
	
	public DemoTestEntity findDemoData(DemoTest demoTest) throws Exception
	{
		try
		{
		
	   return  this.demoTestRepository.findByAadharCard(demoTest.getAadharCard());
		}catch(NoResultException ex )
		{
			return null;
		}
		catch(Exception ex )
		{
			throw new Exception(ex.getMessage());
		}
	}
	
	public DemoTestEntity findDemoDataByAadhar(Long aadhar) throws Exception
	{
		try
		{
			 return  this.demoTestRepository.findByAadharCard(aadhar);
	
		}catch(NoResultException ex )
		{
			return null;
		}
		catch(Exception ex )
		{
			throw new Exception(ex.getMessage());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseEntity<String> addDemoAccount(DemoTest demoTest)  {
		DemoTestEntity deEntity=null;
		try {
			deEntity = this.findDemoData(demoTest);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (deEntity != null) {
			return new ResponseEntity<>("Account already exist for aadhar "+demoTest.getAadharCard(), HttpStatus.NOT_ACCEPTABLE);
		}
		deEntity=new DemoTestEntity();
		BeanUtils.copyProperties(demoTest, deEntity);
     	this.demoTestRepository.save(deEntity);
		DemoTestEntity demoTestEntity;
		try {
			demoTestEntity = this.findDemoData(demoTest);
			return new ResponseEntity<>("Account for aadhar ::"+demoTestEntity.getAadharCard()+" created ", HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	
		

	}
	
	@Transactional
	public ResponseEntity<String> DeleteDemoAccount(Long aadhar) throws DemoTestException {
		DemoTestEntity deEntity=null;
		try {
			deEntity = this.findDemoDataByAadhar(aadhar);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (deEntity == null) {
			return new ResponseEntity<>("Test Account do not exist for aadhar "+aadhar, HttpStatus.NOT_ACCEPTABLE);
			
		}
		this.demoTestRepository.delete(deEntity);
		return new ResponseEntity<>("Test Account Deleted with id:: "+deEntity.getId(), HttpStatus.ACCEPTED);

	}

	@Transactional(propagation = Propagation.REQUIRED )
	public ResponseEntity<String> addAmount(Long aadhar, double amount,String type) throws DemoTestException {
		DemoTestEntity deEntity=null;
		try {
			deEntity = this.findDemoDataByAadhar(aadhar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (deEntity == null) {
			return new ResponseEntity<>("No Demo Accound Found for "+aadhar, HttpStatus.NOT_ACCEPTABLE);
		}
		double depositedAmount=deEntity.getDepositAmount();
		double newBalance = depositedAmount+ amount;
		if ((depositedAmount + amount) < 0) {
			return new ResponseEntity<>("The money in the account '" + aadhar + "' is not enough (" + depositedAmount + ")", HttpStatus.NOT_ACCEPTABLE);
		}
		deEntity.setDepositAmount(newBalance);
		this.demoTestRepository.save(deEntity);
	
		return new ResponseEntity<>("Ammount "+amount+" "+type+" from  Account :: "+aadhar, HttpStatus.ACCEPTED);
	}


	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE,
			rollbackFor = DemoTestException.class)
	public ResponseEntity<String>   withdrawAmount( Long aadhar, double amount) throws DemoTestException {

		return addAmount(aadhar, -amount, "debited");

	}
}
