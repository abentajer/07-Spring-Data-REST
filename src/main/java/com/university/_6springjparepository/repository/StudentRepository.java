package com.university._6springjparepository.repository;

import com.university._6springjparepository.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

// 1. ANNOTATION: customize the path.
// Default would be "/students", but here we can be explicit.
// collectionResourceRel = key in JSON ("students": [...])
// path = URL path segment (localhost:8080/api/members)
@RepositoryRestResource(collectionResourceRel = "students", path = "students")
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // 2. EXPOSING QUERY METHODS
    //fg
    // Spring Data REST automatically finds this and exposes it at:
    // URL: /students/search/findByEmail?email=...
    // We use @Param to name the query parameter in the URL
    Student findByEmail(@Param("email") String email);

    // 3. HIDING METHODS
    // If you want to prevent DELETE operations via the API, you can override and hide them.
    @Override
    @RestResource(exported = false) // This method will NOT be exposed via REST
    void deleteById(Integer id);
}