package com.ets.gti525.domain.entity;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ets.gti525.domain.constant.Role;

@Entity
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	private int enabled;
	
	private String firstName;
	private String lastName;
	private boolean isCompany;
	private String companyName;
	private String email;
	
	public User() {
		
	}
	
	public User(String username, String password, Role role, int enabled, String firstName,
			String lastName, boolean isCompany, String companyName, String email) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isCompany = isCompany;
		this.companyName = companyName;
		this.email = email;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		GrantedAuthority ga = new GrantedAuthority() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				return role.toString();
			}
		};
		
		return Arrays.asList(ga);
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return (enabled == 1) ? true : false;
	}
	
	@Override
	public String toString() {
		return username + ";" + role + ";" + enabled;
	}

	public boolean equals(User u) {
		return this.id == u.getId();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isCompany() {
		return isCompany;
	}

	public void setCompany(boolean isCompany) {
		this.isCompany = isCompany;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCardholderName() {
		if(isCompany)
			return companyName;
		return firstName + " " + lastName;
	}
	
	
}
