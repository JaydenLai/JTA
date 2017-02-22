package com.hz.syxx.repository;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hz.syxx.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired 
	UserRepository repository;
	
	User user;

	@Before
	public void setUp() {
		user = new User();
		user.setName("Jayden");
	}

	@Test
	public void findSavedUserById() {

		user = repository.save(user);

		assertThat(repository.findOne(user.getId()), is(user));
	}

}
