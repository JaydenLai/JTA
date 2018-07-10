package com.hz.syxx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.hz.syxx.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
