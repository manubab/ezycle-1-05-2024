package ai.acintyo.ezykle.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import ai.acintyo.ezykle.exception.CustomAccessDeniedHandler;
import ai.acintyo.ezykle.exception.CustomAuthenticationEntryPoint;
import ai.acintyo.ezykle.filter.JwtAuthenticationFilter;
import ai.acintyo.ezykle.services.impl.UserDetailsServiceImp;
import ai.acintyo.ezykle.util.FormatterDateTime;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsServiceImp userDetailsServiceImp;
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private CustomLogoutHandler logoutHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(req -> req
						.requestMatchers("/ezykle-user/register","/ezykle-user/login", "/ezycle/forgotPassword/verifyMail/{email}",
								"/ezycle/forgotPassword/verifyOtp/{otp}/{email}",
								"/ezycle/forgotPassword/changePassword/{email}",
								"/save-trip-detials",
								"/get-all-trip-details",
								"/get-trip-details/userIdBy/{userId}/vehicleIdBy/{vehicleId},",
								"/save-trip-aggregator",
								"/get-trip-aggregator/userIdBy/{userId}/vehicleIdBy/{vehicleId}",
								"/get-all-trip-aggregator",
								"/save-vehicle-data",
								"/get-vehicle-data/userIdBy/{userId}",
								"/get-vehicle-data/vehicleDataIdBy/{vehicleDataId}",
								"/get-vehicle-data-all/vehicleDataIdBy/{vehicleDataId}/userIdBy/{userId}",
								
								"/ezycle-account/add",
								"/ezycle-account/getAccount/{userId}",
								"/ezycle-account/updateAccount",
								"/ezycle-account/deleteAccount/{userId}",
								"/ezycle-account/deleteBy",
								"/ezycle-account/deleteBy/{deleteBy}",
								
								"/ezycle-account/get/AllAccounts",
								
								
								
								"/trip/save-trip-details",
								"/trip/get-trip-details/userIdBy/{userId}/vehicleIdBy/{vehicleId}",
								"/trip/get-all-trip-details",
								"/trip/get-trip-details/userIdBy/{userId}",
								"/trip/update-trip-details/tripIdBy/{tripIdBy}",
								"/trip/save-trip-aggregator",
								"/trip/get-trip-aggregator/userIdBy/{userId}/vehicleIdBy/{vehicleId}",
								"/trip/get-all-trip-aggregator",
								"/trip/update-trip-aggregator/aggregateIdBy/{aggregateIdBy}",
							
							// 29/04/2024
								
								"/trip/get-trip-details/tripIdBy/{tripId}",
								"/get-vehicle-data/vehicleDataIdBy/{vehicleDataId}",
								
								"/get-vehicle-data-all/vehicleNoBy/{vehicleNo}/userIdBy/{userId}"
											
								).permitAll()
					
						
						.requestMatchers("/ezykle-service/book-appointment").hasAuthority("USER")
						.requestMatchers("/ezykle-service/get-appointment/{id}").hasAuthority("USER")
						.requestMatchers("/ezycle-vehicleprofile/byVehicle/{vehicleId}").hasAuthority("USER")
						.requestMatchers("/ezycle-user/**").hasAuthority("USER")
						
						
						.requestMatchers("/ezykle-admin/get-all-service-centers").hasAnyAuthority("USER", "ADMIN")
						.requestMatchers("/ezykle-admin/get-service-center/{id}").hasAnyAuthority("USER", "ADMIN")
						.requestMatchers("/ezykle-admin/get-service/{id}").hasAnyAuthority("USER", "ADMIN")
						.requestMatchers("/ezykle-admin/get-all-services").hasAnyAuthority("USER", "ADMIN")
						.requestMatchers("/ezykle-user/get-user/{id}").hasAnyAuthority("USER", "ADMIN")
						.requestMatchers("/ezykle-user/update-user/{id}").hasAnyAuthority("USER", "ADMIN")
						.requestMatchers("/ezycle-vehicleprofile/register/vehicle").hasAnyAuthority("USER", "ADMIN")
						.requestMatchers("/ezycle-vehicleprofile/getVehicle/{userId}").hasAnyAuthority("USER", "ADMIN")
						.requestMatchers("/ezykle-user/logout").hasAnyAuthority("ADMIN","USER")

						
						.requestMatchers("/ezykle-admin/update-service-center/{id}").hasAuthority("ADMIN")
						.requestMatchers("/ezykle-admin/update-service/{id}").hasAuthority("ADMIN")
						.requestMatchers("/ezykle-admin/add-service-center").hasAuthority("ADMIN")
						.requestMatchers("/ezykle-admin/get-all-services/serviceCentre/{serviceCenterId}").hasAuthority("ADMIN")
						.requestMatchers("/ezykle-user/get-all-users").hasAuthority("ADMIN")
						.requestMatchers("/ezykle-service/get-all-appointments").hasAuthority("ADMIN")
						
						.requestMatchers("/ezykle-admin/***").hasAuthority("ADMIN"))
				.userDetailsService(userDetailsServiceImp)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.cors(customizer -> {
					CorsConfiguration config = new CorsConfiguration();
					config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
					config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
					config.setAllowCredentials(true);
					config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
					customizer.configurationSource(request -> config);
				}).cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.exceptionHandling(accessDeniedHandler->accessDeniedHandler.accessDeniedHandler(accessDeniedHandler())
						.authenticationEntryPoint(authenticationEntryPoint()))
				  .logout(l->l
	                        .logoutUrl("/ezykle-user/logout")
	                        .addLogoutHandler(logoutHandler)
	                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()
	                        ))
	                .build();
		}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	private CorsConfigurationSource corsConfigurationSource() {
		return new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration ccfg = new CorsConfiguration();
				ccfg.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
				ccfg.setAllowedMethods(Collections.singletonList("*"));
				ccfg.setAllowCredentials(true);
				ccfg.setAllowedHeaders(Collections.singletonList("*"));
				ccfg.setExposedHeaders(Arrays.asList("Authorization"));
				ccfg.setMaxAge(3600L);
				return ccfg;
			}
		};

	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource1() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "DELETE"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint();
	}
	
	@Bean
	public FormatterDateTime dateTimeBean() {
		return new FormatterDateTime();
	}
}