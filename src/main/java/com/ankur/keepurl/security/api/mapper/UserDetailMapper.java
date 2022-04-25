package com.ankur.keepurl.security.api.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ankur.keepurl.app.exception.KeepUrlServiceException;
import com.ankur.keepurl.dataaccess.document.UserData;

@SuppressWarnings("serial")
@Service
public class UserDetailMapper {

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

}
