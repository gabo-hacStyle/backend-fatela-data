package fatela.database.backend.repository;

import fatela.database.backend.models.CountryModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends CrudRepository<CountryModel, Integer> {
    Optional<CountryModel> findByCountryName(String countryName);

}
