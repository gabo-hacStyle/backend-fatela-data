package fatela.database.backend.persistence.mapper;

import fatela.database.backend.domain.User;
import fatela.database.backend.persistence.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "idUsuario", target = "idUser"),
            @Mapping(source = "nombreUsuario", target = "nameUser"),
            @Mapping(source = "fechaCreacion", target = "creationDate"),
            @Mapping(source = "paisUsuario", target = "idCountry"),
            @Mapping(source = "userPassword", target = "passwordUser"),
            @Mapping(source = "nombreUsuario", target = "userName"),
    })
    User toUser(Usuario usuario);
    List<User> toUsers(List<Usuario> usuarios);

    @InheritInverseConfiguration
    @Mapping(target = "fechaModificacion", ignore = true)
    Usuario toUsuario(User user);

}
