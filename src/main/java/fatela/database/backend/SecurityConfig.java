package fatela.database.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/api/hello").permitAll() // Permitir acceso público a /api/hello
                    .anyRequest().authenticated() // Requerir autenticación para cualquier otra ruta
            )
            .formLogin(Customizer.withDefaults()); // Configurar el formulario de login por defecto

        return http.build();
    }
}