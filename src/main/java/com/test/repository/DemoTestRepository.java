package com.test.repository;

import org.springframework.data.repository.CrudRepository;

import com.test.entity.DemoTestEntity;

public interface  DemoTestRepository extends CrudRepository<DemoTestEntity,Long>{
  DemoTestEntity findByAadharCard(Long aadharCard);
}
