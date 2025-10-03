package com.rafael.register.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.rafael.register.entity.User;
import com.rafael.register.repository.UserRepository;
import com.rafael.register.service.impl.UserServiceImpl;

@SpringBootTest
class UserServiceTest {

	private final static String id = "1";

	@Mock
	private UserRepository repository;

	@InjectMocks
	private UserServiceImpl service;

	@Test
	void should_register_user_successfully() {
		User userBefore = new User();
		userBefore.setUsername("Rafael");
		userBefore.setPassword(1234);
		userBefore.setBirthDate(LocalDate.of(2002, 7, 25));

		User userAfter = userBefore;
		userAfter.setId(id);

		when(repository.save(userBefore)).thenReturn(userAfter);

		User actual = service.register(userBefore);

		verify(repository).save(userBefore);
		assertEquals(userAfter, actual);
	}

}
