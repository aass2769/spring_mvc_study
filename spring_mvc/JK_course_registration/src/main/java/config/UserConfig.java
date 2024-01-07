package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import beans.UserBean;

@Configuration
public class UserConfig {

	@Bean("userSession")
	@SessionScope
	public UserBean userSession() {
		return new UserBean();
	}
}
