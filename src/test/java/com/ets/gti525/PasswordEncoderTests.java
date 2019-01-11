package com.ets.gti525;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTests {

	@Test
	public void test() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String pass = encoder.encode("qwerty");
		
		System.out.println(pass);
	}
}
