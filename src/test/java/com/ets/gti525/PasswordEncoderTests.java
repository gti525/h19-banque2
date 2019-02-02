package com.ets.gti525;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ets.gti525.helper.CardNumberHelper;

public class PasswordEncoderTests {

	@Test
	public void test() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String pass = encoder.encode("qwerty");
		
		System.out.println(pass);
	}
	
	@Test
	public void validatePassword() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();

		Assert.assertTrue(encoder.matches("qwerty", "$2a$10$vsMf.RQM/cg3nUjoYU8WH.bB9abGYVeE/rmSPLZ3UAR6/WksudUUu"));
	}
	
	@Test
	public void validateCreditCardNumber(){
		System.out.println(CardNumberHelper.getInstace().generateCreditCardNbr());
		
	}
}
