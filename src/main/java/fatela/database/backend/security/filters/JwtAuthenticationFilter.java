package fatela.database.backend.security.filters;


import com.fasterxml.jackson.databind.ObjectMapper;
import fatela.database.backend.models.CountryModel;
import fatela.database.backend.models.UserModel;
import fatela.database.backend.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    //Inyectando
    private JwtUtils jwtUtils;
    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        UserModel userModel = null;
        String email = "";
        String password = "";

        try {
            userModel = new ObjectMapper().readValue(request.getInputStream(), UserModel.class);
            email = userModel.getEmail();
            password = userModel.getPassword();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                email, password
        );
        return getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();
        String token = jwtUtils.generateAccessToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("email", user.getUsername());
        httpResponse.put("roles", user.getAuthorities());
        httpResponse.put("token", token);
        httpResponse.put("authenticated", true);
        response.setContentType("application/json");
        response.setStatus(200);

        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }


}
