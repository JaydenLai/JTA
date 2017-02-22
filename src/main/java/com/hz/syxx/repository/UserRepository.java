package com.hz.syxx.repository;

import org.springframework.data.repository.CrudRepository;

import com.hz.syxx.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByName(String name);
	
	User findByAge(int age);
	
}
