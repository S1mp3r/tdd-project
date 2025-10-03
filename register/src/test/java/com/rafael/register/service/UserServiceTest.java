package com.rafael.register.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.rafael.register.entity.User;
import com.rafael.register.repository.UserRepository;
import com.rafael.register.service.impl.UserServiceImpl;

@SpringBootTest
class UserServiceTest {

	private final static String id = "1";
	private static final TemporalUnit year = ChronoUnit.YEARS;
	private static final int invalidAgeMinimum = 2015;
	private static final int invalidAgeMaximum = 1960;
	private static final String invalidPasswordMinimum = "123";
	private static final String invalidPasswordMaximum = "1234567";

	@Mock
	private UserRepository repository;

	@InjectMocks
	private UserServiceImpl service;

	@Test
	void should_register_user_successfully() {
		User userBefore = new User();
		userBefore.setUsername("Rafael");
		userBefore.setPassword("1234");
		userBefore.setBirthDate(LocalDate.of(2002, 7, 25));

		User userAfter = userBefore;
		userAfter.setId(id);

		when(repository.save(userBefore)).thenReturn(userAfter);

		User actual = service.register(userBefore);

		verify(repository).save(userBefore);
		assertEquals(userAfter, actual);
	}

	@ParameterizedTest
	@ValueSource(ints = {invalidAgeMinimum, invalidAgeMaximum})
	void should_return_error_when_age_is_invalid(int year) {
		User user = new User();
		user.setBirthDate(LocalDate.of(year, 1, 1));

		assertThrows(
			RuntimeException.class, 
			() -> service.register(user), 
			"Invalid age."
		);

		verifyNoInteractions(repository);
	}

	@ParameterizedTest
	@ValueSource(strings = {invalidPasswordMinimum, invalidPasswordMaximum})
	void should_return_error_when_password_is_invalid(String password) {
		User user = new User();
		user.setUsername("Rafael");
		user.setPassword(password);
		user.setBirthDate(LocalDate.now().minus(20, year));

		assertThrows(
			RuntimeException.class, 
			() -> service.register(user), 
			"Invalid password."
		);

		verifyNoInteractions(repository);
	}

}
