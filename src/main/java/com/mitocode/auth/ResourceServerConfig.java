package com.mitocode.auth;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.google.gson.Gson;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	String jsonUnauthorized = null;
	String jsonAccessDenied = null;
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public void configure(HttpSecurity http) throws Exception {

		Map<String, Object> objU = new HashMap<>();
		Map<String, Object> objA = new HashMap<>();

		http.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/utilitario/llenarBD").permitAll()
				.antMatchers(HttpMethod.GET,"/api/estudiante/conseguirFoto/*").permitAll()
				.antMatchers(HttpMethod.GET,"/api/apoderado/conseguirFoto/*").permitAll()
				.antMatchers(HttpMethod.GET,"/api/empleado/conseguirFoto/*").permitAll()
				.antMatchers(HttpMethod.POST,"/api/seccionEstudiante/*").permitAll()
				.antMatchers(HttpMethod.GET,"/api/seccionEstudiante/*").permitAll()
				.anyRequest().authenticated()
				.and().cors().configurationSource(corsConfigurationSource()).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.exceptionHandling().authenticationEntryPoint((request, response, e) -> {
			objU.put("Fecha de solicitud", String.valueOf(LocalDateTime.now()));
			if (request.getRequestURI().compareToIgnoreCase("/error") == 0) {
				objU.put("mensaje",
						"No cuenta con credenciales de inicio de la aplicacion correctas, revise sus datos");
			} else {
				objU.put("mensaje", "Usted no esta autorizado, registre un token valido");
				objU.put("Ruta solicitada", request.getRequestURI());
			}

			objU.put("error", e.getStackTrace()[0].getClassName());
			Gson gson = new Gson();
			jsonUnauthorized = gson.toJson(objU);
			LOG.error(jsonUnauthorized, new RuntimeException(e),
					"Acceso a la ruta " + request.getRequestURI() + " denegado");
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write(jsonUnauthorized);
		}).accessDeniedHandler((request, response, e) -> {
			objA.put("Fecha de solicitud", String.valueOf(LocalDateTime.now()));
			objA.put("mensaje", "Usted no tiene acceso a los recursos especificados");
			objA.put("Ruta solicitada", request.getRequestURI());
			objA.put("error", e.getStackTrace()[0].getClassName());
			Gson gson = new Gson();
			jsonAccessDenied = gson.toJson(objA);
			LOG.error(jsonAccessDenied, new RuntimeException(e),
					"Acceso a la ruta " + request.getRequestURI() + " prohibido");
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.getWriter().write(jsonAccessDenied);
		});
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization", "X-XSRF-TOKEN"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;

	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(
				new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
