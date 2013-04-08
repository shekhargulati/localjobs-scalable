package com.localjobs.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableJdbcConnectionRepository;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.linkedin.config.annotation.EnableLinkedIn;

import com.localjobs.controllers.SimpleSignInAdapter;

@Configuration
@EnableJdbcConnectionRepository
@EnableLinkedIn(appId = "${linkedin.consumerKey}", appSecret = "${linkedin.consumerSecret}")
public class SocialConfig {

	@Inject
	private Environment environment;

	@Inject
	private ConnectionFactoryLocator connectionFactoryLocator;

	@Inject
	private ConnectionRepository connectionRepository;

	@Inject
	private UsersConnectionRepository usersConnectionRepository;

	@Bean
	public ConnectController connectController() {
		ConnectController connectController = new ConnectController(
				connectionFactoryLocator, connectionRepository);
		return connectController;
	}

	@Bean
	public ProviderSignInController providerSignInController(
			RequestCache requestCache) {
		ProviderSignInController providerSignInController = new ProviderSignInController(connectionFactoryLocator,
						usersConnectionRepository,
						new SimpleSignInAdapter(requestCache));
		providerSignInController.setPostSignInUrl("/home");
		return providerSignInController;
	}

	@Bean
	public UserIdSource userIdSource() {
		return new UserIdSource() {
			@Override
			public String getUserId() {
				Authentication authentication = SecurityContextHolder
						.getContext().getAuthentication();
				if (authentication == null) {
					throw new IllegalStateException(
							"Unable to get a ConnectionRepository: no user signed in");
				}
				return authentication.getName();
			}
		};
	}

}