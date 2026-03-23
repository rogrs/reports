package br.com.rogrs.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.rogrs.security.ApiTokenFilter;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ApiTokenFilter apiTokenFilter;

    public SecurityConfiguration(ApiTokenFilter apiTokenFilter) {
        this.apiTokenFilter = apiTokenFilter;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/", "/index.html", "/css/**", "/img/**", "/console/**", "/error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/auth/token").permitAll()
            .antMatchers("/engine/**").permitAll()
            .antMatchers("/api/**").authenticated()
            .anyRequest().permitAll()
            .and()
            .addFilterBefore(apiTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
