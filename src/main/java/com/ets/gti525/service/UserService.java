package com.ets.gti525.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ets.gti525.domain.constant.Role;
import com.ets.gti525.domain.entity.Challenge;
import com.ets.gti525.domain.entity.CreditCard;
import com.ets.gti525.domain.entity.DebitCard;
import com.ets.gti525.domain.entity.User;
import com.ets.gti525.domain.repository.ChallengeRepository;
import com.ets.gti525.domain.repository.CreditCardRepository;
import com.ets.gti525.domain.repository.DebitCardRepository;
import com.ets.gti525.domain.repository.UsersRepository;
import com.ets.gti525.domain.request.CreateUserRequest;
import com.ets.gti525.domain.request.ResetPasswordRequest;
import com.ets.gti525.domain.response.CreateUserResponse;
import com.ets.gti525.domain.response.ResetPasswordResponse;
import com.ets.gti525.domain.response.SearchUsersResponse;
import com.ets.gti525.domain.response.SingleSearchUsers;
import com.ets.gti525.helper.CardNumberHelper;

/**
 * Description : Service containing operations related to users.
 * (Usually called by controllers)
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 19-01-2019
 */
@Service
public class UserService {

	private final UsersRepository usersRepository;
	private final DebitCardRepository debitRepository;
	private final CreditCardRepository creditRepository;
	private final ChallengeRepository challengeRepository;
	
	public UserService(final UsersRepository usersRepository,
			final DebitCardRepository debitCardRepository,
			final CreditCardRepository creditRepository,
			final ChallengeRepository challengeRepository) {
		this.usersRepository = usersRepository;
		this.debitRepository = debitCardRepository;
		this.creditRepository = creditRepository;
		this.challengeRepository = challengeRepository;
	}
	
	public List<User> getUsers() {
		return usersRepository.findAll();
	}
	
	public CreateUserResponse createUser(CreateUserRequest request) {
		
		String username = generateAccountNumber();
		String password = generatePassword();
		String message = "Account successfully created.";
		
		String encodedPassword = encodePassword(password);

		User user = new User(username, encodedPassword, Role.USER, 1,
				request.getFirstName(), request.getLastName(), request.isCompany(),
				request.getCompanyName(), request.getEmail());
		
		DebitCard dc = new DebitCard();
		CreditCard cc = new CreditCard(Long.parseLong(generateCreditCardNumber()));
		
		dc.setNbr(Long.parseLong(username));
		dc.setOwner(user);
		dc.setBalance(0);
		cc.setOwner(user);
		
		Challenge challenge = new Challenge();
		challenge.setQuestion(request.getSecretQuestion().toUpperCase());
		challenge.setResponse(request.getSecretAnswer().toUpperCase());
		challenge.setUsername(user.getUsername());
		
		usersRepository.save(user);
		debitRepository.save(dc);
		creditRepository.save(cc);
		challengeRepository.save(challenge);
		
		return new CreateUserResponse(HttpStatus.OK, message, username, password, String.valueOf(cc.getNbr()));
	}
	
	public SearchUsersResponse searchUsers(String keyword) {
		List<SingleSearchUsers> searchResult = new ArrayList<SingleSearchUsers>();
		List<User> users = new ArrayList<User>();
		
		if(keyword == null) {
			users = usersRepository.findAll();
		}else {
			users = usersRepository.findByKeyword(keyword.toUpperCase());	
		}
		
		for (User user : users) {
			SingleSearchUsers singleSearchUsers = new SingleSearchUsers(user.getFirstName(), user.getLastName());
			
			DebitCard debitCard = debitRepository.findByOwnerId(user.getId());
			CreditCard creditCard = creditRepository.findByOwnerId(user.getId());
			
			if(debitCard != null)
				singleSearchUsers.setDebitCardNumber(String.valueOf(debitCard.getNbr()));
			if(creditCard != null)
				singleSearchUsers.setCreditCardNumber(String.valueOf(creditCard.getNbr()));
			
			searchResult.add(singleSearchUsers);
		}
		
		if(searchResult.isEmpty()) {
			return new SearchUsersResponse(HttpStatus.NO_CONTENT, searchResult);
		}else {
			return new SearchUsersResponse(HttpStatus.OK, searchResult);
		}
	}
	
	private String generateAccountNumber() {
		String accountNumber = CardNumberHelper.getInstace().generateAccountNumber();
		while(debitRepository.findByNbr(Long.parseLong(accountNumber)) != null)
			accountNumber = CardNumberHelper.getInstace().generateAccountNumber();
		
		return accountNumber;
	}
	
public ResetPasswordResponse resetPassword (ResetPasswordRequest request) {
		
		String oldPassword = request.getOldPassword();
		String hashedPassword = encodePassword(oldPassword);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username;

		try {
			username = auth.getPrincipal().toString();
		} catch (Exception e) {
			return new ResetPasswordResponse(HttpStatus.UNAUTHORIZED, null);
		}

		User user = usersRepository.findOneByUsername(username);

		if (passwordMatches(oldPassword, user.getPassword())) {
			user.setPassword(encodePassword(request.getNewPassword())); // À modifier (Ajouter une validation de la complexité du mot de pass)
			usersRepository.save(user);
			
			return new ResetPasswordResponse(HttpStatus.OK, "Password successfully reset");
		}
		return new ResetPasswordResponse(HttpStatus.BAD_REQUEST, "Password entered is invalid!");
	}
	
	private String generateCreditCardNumber() {
		String accountNumber = CardNumberHelper.getInstace().generateCreditCardNbr();
		while(creditRepository.findByNbr(Long.parseLong(accountNumber)) != null)
			accountNumber = CardNumberHelper.getInstace().generateCreditCardNbr();
		
		return accountNumber;
	}
	
	private String generatePassword() {
		return "qwerty";
	}
	
	private String encodePassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
	
	private boolean passwordMatches(String rawPassword, String hashedPassword) {
		return new BCryptPasswordEncoder().matches(rawPassword, hashedPassword);
	}
}
