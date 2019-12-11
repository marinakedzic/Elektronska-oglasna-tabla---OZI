
package com.proba;



import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigurationAdm  {

    
  
   
  @Configuration
  @Order(1)
    public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {

        public App1ConfigurationAdapter() {
            super();
          
        }
    

    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;
    
    
     @Autowired 
    private CustomAuthenticationSuccessHandler successHandler;

    
     @Override
      protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery("select email, password, active from admin where email=?")
                .authoritiesByUsernameQuery("select a.email, u.role from admin a inner join user_role u on(a.status_id_status = u.id_status)  where a.email=?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
  
	

        auth.
                jdbcAuthentication()           
                .usersByUsernameQuery("select email, password, active from parents where email=?")
                .authoritiesByUsernameQuery("select p.email, u.role from parents p inner join user_role u on(p.status_id_status = u.id_status)  where p.email=?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
        
        
        
         auth.
                jdbcAuthentication()           
                .usersByUsernameQuery("select email, password, active from teachers where email=?")
                .authoritiesByUsernameQuery("select t.email, u.role from teachers t inner join user_role u on(t.status_id_status = u.id_status)  where t.email=?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

      
      
     
    
        @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll() 
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/parent/**").hasAnyAuthority("PARENT","ADMIN") 
                .antMatchers("/teachers/**").hasAnyAuthority("TEACHER","ADMIN")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest()          
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .successHandler(successHandler).permitAll()
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll().and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").permitAll().and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }
    
 

  
       
        }
}

    


        

