package fatela.database.backend.repository;

import fatela.database.backend.models.CountryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<CountryModel, Integer> {
    Optional<CountryModel> findByCountryName(String countryName);

}
