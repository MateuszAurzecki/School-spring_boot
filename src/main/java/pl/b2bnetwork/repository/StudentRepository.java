package pl.b2bnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.b2bnetwork.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    void deleteById(Long id);

    Optional<Student> findById(Long id);

    List<Student> findAll();

    Student save(Student student);

    List<Student> findByClassid(String classid);

    Long countByClassid(String classid);

    Student findByLastName(String lastName);

}
