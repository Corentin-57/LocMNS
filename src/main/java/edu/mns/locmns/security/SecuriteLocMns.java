package edu.mns.locmns.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.List;

@EnableWebSecurity
public class SecuriteLocMns extends WebSecurityConfigurerAdapter { //Configuration

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PersonneDetailsServiceLocMns personneDetailsServiceLocMns;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override //Configurer l'authentification
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(personneDetailsServiceLocMns).passwordEncoder(getPasswordEncoder());
    }

    @Override //Gérer l'authorisation
    protected void configure(HttpSecurity http) throws Exception{
        //cors -> autorise toutes les requêtes qui viennent de l'extérieur (par défaut donc autorise toutes les requêtes et entêtes
//        http.cors().configurationSource((request -> new CorsConfiguration().applyPermitDefaultValues()))
                http.cors().and()
                    .csrf().disable() //On n'a pas de token formulaire (check pas le token lors réception requête)
                    .authorizeRequests()// Verifie le droit sur le lien cliqué
                    .antMatchers("/", "/connexion", "/contact" ).permitAll()
                    .antMatchers("/gestionnaire/**").hasRole("GESTIONNAIRE")
                    .antMatchers("/**").hasAnyRole("UTILISATEUR", "GESTIONNAIRE")
                .and().exceptionHandling()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); //Filtre vérifie que le token est correct

    }

    @Bean
    public CorsConfigurationSource configurationCrossOrigin() { //Configurer les méthodes autorisés (permet autoriser le requête delete
        CorsConfiguration maConfiguration = new CorsConfiguration();

        maConfiguration.setAllowedOrigins(List.of("*"));
        maConfiguration.setAllowedMethods(List.of("HEAD","GET", "POST", "PUT", "DELETE", "PATCH"));
        maConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", maConfiguration);
        return source;
    }

    @Bean
    public AuthenticationManager getAuthentificationManager() throws Exception{
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
