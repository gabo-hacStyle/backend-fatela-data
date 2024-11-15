package fatela.database.backend.service;

import fatela.database.backend.dto.response.EditUsersDTO;
import fatela.database.backend.dto.response.ShowUsersDTO;
import fatela.database.backend.models.CountryModel;
import fatela.database.backend.models.RolModel;
import fatela.database.backend.models.UserModel;
import fatela.database.backend.repository.CountryRepository;
import fatela.database.backend.repository.RolRepository;
import fatela.database.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private CountryRepository countryRepository;

    public List<ShowUsersDTO> getAllUsers() {
        List<UserModel> users = usersRepository.findAll();
        return users.stream()
                .map(user -> new ShowUsersDTO(
                        user.getUserId(),
                        user.getName(),
                        user.getEmail(),
                        user.isEnabled(),
                        user.getCountry(),
                        user.getRoles().stream()
                                .map(RolModel::getName)
                                .collect(Collectors.toSet())
                ))
                .collect(Collectors.toList());
    }

    public ShowUsersDTO getUserByEmail(String email) {
        UserModel user = usersRepository.findByEmail(email).orElse(null);
        if(user == null){
            return null;
        }
        return new ShowUsersDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.isEnabled(),
                user.getCountry(),
                user.getRoles().stream()
                        .map(RolModel::getName)
                        .collect(Collectors.toSet())
        );
    }

    public UserModel updateUser(Long userId, EditUsersDTO userDto) {
        UserModel user = usersRepository.findByUserId(userId).orElse(null);

        if(user == null){
            return null;
        }
        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getEnabled() != null) {
            user.setEnabled(userDto.getEnabled());
        }
        if (userDto.getCountryId() != null) {
            CountryModel countryModel = countryRepository.findById(userDto.getCountryId()).orElseThrow();
            user.setCountry(countryModel);
        }
        if (userDto.getRoles() != null) {
            Set<RolModel> roles = userDto.getRoles()
                    .stream()
                    .map(roleName -> rolRepository.findByName(roleName)
                            .orElseGet(() -> rolRepository.save(RolModel.builder().name(roleName).build())))
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }

        return usersRepository.save(user);
    }

    public boolean deleteUser(Long userId) {
        return usersRepository.findByUserId(userId)
                .map(user -> {
                    usersRepository.delete(user);
                    return true;
                })
                .orElse(false);
    }

}
