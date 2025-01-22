package fatela.database.backend.repository;

import fatela.database.backend.models.MenuCoursesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MenuCoursesRepository extends JpaRepository<MenuCoursesModel, String> {

}
