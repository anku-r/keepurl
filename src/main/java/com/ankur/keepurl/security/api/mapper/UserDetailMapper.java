package com.ankur.keepurl.security.api.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ankur.keepurl.dataaccess.document.UserAccess;
import com.ankur.keepurl.dataaccess.document.UserRole;

@SuppressWarnings("serial")
@Service
public class UserDetailMapper {

	public UserDetails mapEntityToUserDetail(UserAccess userAccess) {

		return new UserDetails() {

			@Override
			public boolean isEnabled() {
				return userAccess.getIsEnabled();
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
				return userAccess.getUsername();
			}

			@Override
			public String getPassword() {
				return userAccess.getPassword();
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return getUserAuthorities(userAccess.getUserRoles());
			}
		};
	}

	private Collection<? extends GrantedAuthority> getUserAuthorities(List<UserRole> roles) {
	 
		Collection<GrantedAuthority> userAuthorities = new ArrayList<>();
		for (UserRole role : roles) {

			GrantedAuthority authority = new GrantedAuthority() {
				@Override
				public String getAuthority() {
					return role.getRole();
				}
			};
			userAuthorities.add(authority);
		}
		return userAuthorities;
	}

}
