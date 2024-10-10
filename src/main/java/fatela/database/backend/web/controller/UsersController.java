package fatela.database.backend.web.controller;


import fatela.database.backend.domain.User;
import fatela.database.backend.domain.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAll(){

        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user){
        user.setIdUser(null);
        user.setCreationDate(null);

        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity delete(@PathVariable Long idUser){
        if(userService.delete(idUser)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{idUser}")
    public ResponseEntity<User> getUser(@PathVariable("idUser") Long idUser){

        return userService.getUser(idUser).map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/country/{idCountry}")
    public ResponseEntity<List<User>> getByCountry(@PathVariable("idCountry") Long idCountry){
        return userService.getByCountry(idCountry).map(users -> new ResponseEntity<>(users, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
