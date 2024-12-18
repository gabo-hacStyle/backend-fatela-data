package fatela.database.backend.repository;

import fatela.database.backend.dto.response.ShowStudentsByCountryDTO;
import fatela.database.backend.dto.response.ShowStudentsByYearDTO;
import fatela.database.backend.models.GradesModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;

@Repository
public interface GradesRepository extends JpaRepository<GradesModel, String> {

    //Para buscar los datos y renderizarlos
    @Query("SELECT g FROM GradesModel g WHERE " +
            "(:studentCode IS NULL OR g.studentCode = :studentCode) AND " +
            "(:approved IS NULL OR g.approved = :approved) AND " +
            "(:courseCode IS NULL OR g.courseCode = :courseCode) AND " +
            "(:courseProgram IS NULL OR g.courseProgram = :courseProgram) AND " +
            "(:year IS NULL OR g.year = :year) AND " +
            "(:countryId IS NULL OR g.studentCountryId = :countryId) AND " +
            "(:studentGender IS NULL OR g.studentGender = :studentGender) AND " +
            "(:courseCodeWithYear IS NULL OR g.courseCodeWithYear = :courseCodeWithYear)")
    Page<GradesModel> findGradesByFilters(
            @Param("studentCode") String studentCode,
            @Param("approved") String approved,
            @Param("courseCode") String courseCode,
            @Param("courseProgram") String courseProgram,
            @Param("year") Integer year,
            @Param("countryId") Integer countryId,
            @Param("studentGender") String studentGender,
            @Param("courseCodeWithYear") String courseCodeWithYear, Pageable pageable);

    //Para contar los datos de estudiantes hombres
    @Query("SELECT COUNT(distinct g.studentCode) FROM GradesModel g WHERE " +
            "g.studentGender = 'Masculino' AND " +
            "(:studentCode IS NULL OR g.studentCode = :studentCode) AND " +
            "(:approved IS NULL OR g.approved = :approved) AND " +
            "(:courseCode IS NULL OR g.courseCode = :courseCode) AND " +
            "(:courseProgram IS NULL OR g.courseProgram = :courseProgram) AND " +
            "(:year IS NULL OR g.year = :year) AND " +
            "(:countryId IS NULL OR g.studentCountryId = :countryId) AND " +
            "(:courseCodeWithYear IS NULL OR g.courseCodeWithYear = :courseCodeWithYear)")
    Integer countGradesByFiltersMale(
            @Param("studentCode") String studentCode,
            @Param("approved") String approved,
            @Param("courseCode") String courseCode,
            @Param("courseProgram") String courseProgram,
            @Param("year") Integer year,
            @Param("countryId") Integer countryId,

            @Param("courseCodeWithYear") String courseCodeWithYear);

    @Query("SELECT COUNT(distinct g.studentCode) FROM GradesModel g WHERE " +
            "g.studentGender = 'Femenino' AND " +
            "(:studentCode IS NULL OR g.studentCode = :studentCode) AND " +
            "(:approved IS NULL OR g.approved = :approved) AND " +
            "(:courseCode IS NULL OR g.courseCode = :courseCode) AND " +
            "(:courseProgram IS NULL OR g.courseProgram = :courseProgram) AND " +
            "(:year IS NULL OR g.year = :year) AND " +
            "(:countryId IS NULL OR g.studentCountryId = :countryId) AND " +

            "(:courseCodeWithYear IS NULL OR g.courseCodeWithYear = :courseCodeWithYear)")
    Integer countGradesByFiltersFemale(
            @Param("studentCode") String studentCode,
            @Param("approved") String approved,
            @Param("courseCode") String courseCode,
            @Param("courseProgram") String courseProgram,
            @Param("year") Integer year,
            @Param("countryId") Integer countryId,

            @Param("courseCodeWithYear") String courseCodeWithYear);

    //Todos los estudiantes
    @Query("SELECT COUNT(distinct g.studentCode) FROM GradesModel g WHERE " +
            "(:studentCode IS NULL OR g.studentCode = :studentCode) AND " +
            "(:approved IS NULL OR g.approved = :approved) AND " +
            "(:courseCode IS NULL OR g.courseCode = :courseCode) AND " +
            "(:courseProgram IS NULL OR g.courseProgram = :courseProgram) AND " +
            "(:year IS NULL OR g.year = :year) AND " +
            "(:countryId IS NULL OR g.studentCountryId = :countryId) AND " +
            "(:courseCodeWithYear IS NULL OR g.courseCodeWithYear = :courseCodeWithYear)")
    Integer countGradesByFilters(
            @Param("studentCode") String studentCode,
            @Param("approved") String approved,
            @Param("courseCode") String courseCode,
            @Param("courseProgram") String courseProgram,
            @Param("year") Integer year,
            @Param("countryId") Integer countryId,

            @Param("courseCodeWithYear") String courseCodeWithYear);


    @Query("SELECT new " +
            "fatela.database.backend.dto.response.ShowStudentsByCountryDTO(COUNT(distinct g.studentCode), g.studentCountryName) FROM GradesModel g WHERE " +
            "(:studentCode IS NULL OR g.studentCode = :studentCode) AND " +
            "(:approved IS NULL OR g.approved = :approved) AND " +
            "(:courseCode IS NULL OR g.courseCode = :courseCode) AND " +
            "(:courseProgram IS NULL OR g.courseProgram = :courseProgram) AND " +
            "(:year IS NULL OR g.year = :year) AND " +
            "(:studentGender IS NULL OR g.studentGender = :studentGender) AND " +
            "(:countryId IS NULL OR g.studentCountryId = :countryId) AND " +
            "(:courseCodeWithYear IS NULL OR g.courseCodeWithYear = :courseCodeWithYear)" +
            "GROUP BY g.studentCountryName")
    List<ShowStudentsByCountryDTO> studentsNumberByCountry(@Param("studentCode") String studentCode,
                                                           @Param("approved") String approved,
                                                           @Param("courseCode") String courseCode,
                                                           @Param("courseProgram") String courseProgram,
                                                           @Param("year") Integer year,
                                                           @Param("countryId") Integer countryId,
                                                           @Param("studentGender") String studentGender,
                                                           @Param("courseCodeWithYear") String courseCodeWithYear);





    List<GradesModel> findAllByStudentCountryId(Integer countryId);
    //Integer countAllByStudentCountryId(Integer countryId);

    @Query(
            value = "SELECT COUNT(DISTINCT studentCode) FROM GradesModel"
    )
    Integer studentsQuantity();

    @Query(
            value = "SELECT COUNT(studentGender) FROM GradesModel where studentGender = 'Femenino'"
    )
    Integer  femalesQuantity();

    @Query(
            value = "SELECT COUNT(studentGender) FROM GradesModel where studentGender = 'Masculino'"
    )
    Integer malesQuantity();

    @Query(
            value = "SELECT COUNT(DISTINCT g.courseCode) FROM GradesModel g where" +
                    "(:courseProgram IS NULL OR g.courseProgram = :courseProgram)"
    )
    Integer coursesQuantity(
            @Param("courseProgram") String courseProgram
    );

    @Query(
            value = "SELECT COUNT(DISTINCT studentCode) FROM GradesModel where studentCountryId = :countryId"
    )
    Integer countAllByStudentCountryId(
            @Param("countryId") Integer countryId
    );

    @Query(
            value = "SELECT new " +
                    "fatela.database.backend.dto.response.ShowStudentsByYearDTO(COUNT(distinct g.studentCode), g.year) FROM GradesModel g WHERE " +
                    " g.studentCountryId = :countryId group by g.year"
    )
    List<ShowStudentsByYearDTO> studentsNumberByYearAndCountry(
            @Param("countryId") Integer countryId
    );

    @Query(
            value = "SELECT new " +
                    "fatela.database.backend.dto.response.ShowStudentsByYearDTO(COUNT(distinct g.studentCode), g.year) FROM GradesModel g " +
                    " group by g.year"
    )
    List<ShowStudentsByYearDTO> studentsNumberByYear();


}
