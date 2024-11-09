package fatela.database.backend.repository;

import fatela.database.backend.models.GradesModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradesRepository extends PagingAndSortingRepository<GradesModel, Integer> {


}
