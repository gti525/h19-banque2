package com.ets.gti525;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ets.gti525.helper.CardNumberHelper;
import com.ets.gti525.service.UserService;

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
	
	@Test
	public void testPasswordComplexity() {
		String validPassword1 = "Qwertyqwerty1"; // 3/4
		String validPassword2 = "Qwertyqwerty!"; // 3/4
		String validPassword3 = "qwertyqwerty1!"; // 3/4
		String validPassword4 = "QWERTYQWERTY1%"; // 3/4
		String validPassword5 = "Qwertyqwerty1!"; // 4/4
		String invalidPassword1 = "qwerty"; // length
		String invalidPassword2 = "Qwertyqwerty"; // 2/4
		String invalidPassword3 = "qwertyqwerty"; // 1/4
		String invalidPassword4 = "QWERTYQWERTY!"; // 2/4
		String invalidPassword5 = "1111111111!"; // 2/4
		
		UserService service = new UserService(null, null, null, null);
		
		Assert.assertTrue(service.isPasswordComplex(validPassword1));
		Assert.assertTrue(service.isPasswordComplex(validPassword2));
		Assert.assertTrue(service.isPasswordComplex(validPassword3));
		Assert.assertTrue(service.isPasswordComplex(validPassword4));
		Assert.assertTrue(service.isPasswordComplex(validPassword5));
		Assert.assertFalse(service.isPasswordComplex(invalidPassword1));
		Assert.assertFalse(service.isPasswordComplex(invalidPassword2));
		Assert.assertFalse(service.isPasswordComplex(invalidPassword3));
		Assert.assertFalse(service.isPasswordComplex(invalidPassword4));
		Assert.assertFalse(service.isPasswordComplex(invalidPassword5));
	}
}
