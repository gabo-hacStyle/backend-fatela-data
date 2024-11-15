package fatela.database.backend.service;

import fatela.database.backend.dto.registerUsers.CreateAdminRrequest;
import fatela.database.backend.dto.registerUsers.CreateUserRequest;
import fatela.database.backend.models.CountryModel;
import fatela.database.backend.models.RolModel;
import fatela.database.backend.models.UserModel;
import fatela.database.backend.repository.CountryRepository;
import fatela.database.backend.repository.RolRepository;
import fatela.database.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private CountryRepository countryRepository;

    public UserModel registerAdmin(CreateAdminRrequest adminModelRequest) {

        CountryModel countryModel = countryRepository.findById(adminModelRequest.getCountryId()).orElseThrow();

        Set<RolModel> rol = Collections.singleton(rolRepository.findByName("ADMIN")
                .orElseGet(() -> rolRepository.save(RolModel.builder().name("ADMIN").build())));


        UserModel userModel = UserModel.builder()
                .email(adminModelRequest.getEmail())
                .name(adminModelRequest.getName())
                .roles(rol)
                .enabled(adminModelRequest.isEnabled())
                .password(passwordEncoder.encode(adminModelRequest.getPassword()))
                .country(countryModel)
                .build();

        return userRepository.save(userModel);
    }

    public UserModel registerUser(CreateUserRequest user) {

        CountryModel countryModel = countryRepository.findById(user.getCountryId()).orElseThrow();

        Set<RolModel> roles = user.getRoles()
                .stream()
                .map(roleName ->
                        rolRepository.findByName(roleName)
                                .orElseGet(() -> rolRepository.save(RolModel.builder().name(roleName).build())))
                .collect(Collectors.toSet());


        UserModel userModel = UserModel.builder()
                .email(user.getEmail())
                .name(user.getName())
                .roles(roles)
                .enabled(user.isEnabled())
                .password(passwordEncoder.encode(user.getPassword()))
                .country(countryModel)
                .build();

        return userRepository.save(userModel);
    }
}
