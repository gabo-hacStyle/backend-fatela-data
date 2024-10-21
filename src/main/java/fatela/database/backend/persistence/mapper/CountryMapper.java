package fatela.database.backend.persistence.mapper;

import fatela.database.backend.domain.Country;
import fatela.database.backend.persistence.entity.Pais;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CountryMapper {


    @Mappings({
            @Mapping(source = "idPais", target = "idCountry"),
            @Mapping(source = "pais", target = "countryName")
    })
    Country toCountry(Pais pais);

    @InheritInverseConfiguration
    @Mapping(target = "usuarios", ignore = true)
    Pais toPais(Country country);

}
