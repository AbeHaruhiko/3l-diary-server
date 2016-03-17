package jp.caliconography;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
    private RESTAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
    private RESTAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private RESTAuthenticationSuccessHandler authenticationSuccessHandler;
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers("/", "/static/**").permitAll();
//			.antMatchers("/admin").hasRole("ADMIN")
//			.antMatchers("/api").authenticated()
//			.anyRequest().authenticated();
//			.and().httpBasic();
//		http.formLogin()
//			.loginPage("/login").permitAll()
////			.usernameParameter("user")
////			.passwordParameter("password")
//			.and().logout().permitAll();
//		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
//		http.formLogin().successHandler(authenticationSuccessHandler);
//        http.formLogin().failureHandler(authenticationFailureHandler);
//        http.csrf().disable();

	
		http.authorizeRequests()
		.antMatchers("/", "/static/**").permitAll();
//		.antMatchers("/api/**").authenticated()
//		.and().httpBasic();
		http.csrf().disable();
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		http.formLogin()
		.loginPage("/").permitAll()
		.defaultSuccessUrl("/", true)
		.failureUrl("/");
//		.successHandler(authenticationSuccessHandler);
//		http.formLogin().failureHandler(authenticationFailureHandler);
		
		
//		http.authorizeRequests()
//		.antMatchers("/", "/static/**").permitAll()
//		.anyRequest().authenticated();
////        http.httpBasic();
//        http.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			// インメモリで認証するパターン		
//			.inMemoryAuthentication()
//			.withUser("user").password("password").roles("USER").and()
//			.withUser("admin").password("password").roles("USER", "ADMIN");
			// userテーブルで認証
			.jdbcAuthentication()
			.dataSource(dataSource);
	}
}