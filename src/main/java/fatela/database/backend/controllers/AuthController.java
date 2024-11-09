package fatela.database.backend.controllers;

import fatela.database.backend.dto.registerUsers.CreateAdminRrequest;
import fatela.database.backend.dto.registerUsers.CreateUserRequest;
import fatela.database.backend.models.UserModel;
import fatela.database.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/registerAdmin")
    public ResponseEntity<?> registerAdmin(@RequestBody CreateAdminRrequest createAdminRrequest){


        UserModel userModel = authService.registerAdmin(createAdminRrequest);

        return ResponseEntity.ok(userModel);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody CreateUserRequest createUserRequest){
        UserModel userModel = authService.registerUser(createUserRequest);
        return ResponseEntity.ok(userModel);
    }



}
