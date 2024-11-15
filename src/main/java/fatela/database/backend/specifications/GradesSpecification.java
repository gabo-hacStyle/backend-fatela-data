package fatela.database.backend.specifications;

import fatela.database.backend.models.GradesModel;
import org.springframework.data.jpa.domain.Specification;

public class GradesSpecification {

    public static Specification<GradesModel> hasStudentCode(String studentCode) {
        return (root, query, criteriaBuilder) ->
                studentCode == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("studentCode"), studentCode);
    }


    public static Specification<GradesModel> hasApproved(String approved) {
        return (root, query, criteriaBuilder) ->
                approved == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("approved"), approved);
    }

    public static Specification<GradesModel> hasCourseCode(String courseCode) {
        return (root, query, criteriaBuilder) ->
                courseCode == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("courseCode"), courseCode);
    }

    public static Specification<GradesModel> hasCourseProgram(String courseProgram) {
        return (root, query, criteriaBuilder) ->
                courseProgram == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("courseProgram"), courseProgram);
    }

    public static Specification<GradesModel> hasYear(Integer year) {
        return (root, query, criteriaBuilder) ->
                year == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("year"), year);
    }

    public static Specification<GradesModel> hasStudentCountryName(String studentCountryName) {
        return (root, query, criteriaBuilder) ->
                studentCountryName == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("studentCountryName"), studentCountryName);
    }

    public static Specification<GradesModel> hasStudentGender(String studentGender) {
        return (root, query, criteriaBuilder) ->
                studentGender == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("studentGender"), studentGender);
    }

    public static Specification<GradesModel> hasCourseCodeWithYear(String courseCodeWithYear) {
        return (root, query, criteriaBuilder) ->
                courseCodeWithYear == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("courseCodeWithYear"), courseCodeWithYear);
    }


}
