package fatela.database.backend.controllers;

import fatela.database.backend.dto.request.UpdateUserRequest;
import fatela.database.backend.dto.response.EditUsersDTO;
import fatela.database.backend.dto.response.ShowUsersDTO;
import fatela.database.backend.models.UserModel;
import fatela.database.backend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    @RequestMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ShowUsersDTO> getAllUsers() {
        return usersService.getAllUsers();
    }

    @PutMapping
    @RequestMapping("/updateUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UpdateUserRequest> updateUser(@RequestBody UpdateUserRequest userRequest) {
        UserModel user = usersService.updateUser(userRequest.getUserId(), userRequest.getUserDto());
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userRequest);
    }

    @DeleteMapping
    @RequestMapping("/deleteUser/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        boolean isDeleted = usersService.deleteUser(userId);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Usuario eliminado exitosamente");
    }



}
