package com.ankur.keepurl.service.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ankur.keepurl.dataaccess.document.UserData;
import com.ankur.keepurl.dto.UserDataDTO;
import com.ankur.keepurl.exception.KeepUrlServiceException;

@SuppressWarnings("serial")
@Service
public class UserDetailMapper {

    @Autowired
    private PasswordEncoder encoder;

    public UserDetails mapEntityToUserDetail(UserData userData) {
	if (userData == null) {
	    throw new KeepUrlServiceException("User Data has not been received");
	}
	return new UserDetails() {

	    @Override
	    public boolean isEnabled() {
		return userData.getIsEnabled();
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
		return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
		return true;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
		return true;
	    }

	    @Override
	    public String getUsername() {
		return userData.getUsername();
	    }

	    @Override
	    public String getPassword() {
		return userData.getPassword();
	    }

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
		return userData.getRoles().stream()
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());
	    }
	};
    }

    public UserData mapDtoToEntity(UserDataDTO dto) {
	if (dto == null) {
	    throw new KeepUrlServiceException("User Data has not been received");
	}
	UserData entity = new UserData();
	entity.setUsername(dto.getEmail());
	entity.setPassword(encoder.encode(dto.getPassword()));
	entity.setIsEnabled(true);
	entity.addRole("USER");
	return entity;
    }

}
