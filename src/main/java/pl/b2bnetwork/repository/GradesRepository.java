package pl.b2bnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.b2bnetwork.model.Grades;

import java.util.List;
import java.util.Optional;

public interface GradesRepository extends JpaRepository<Grades, Long> {

    void deleteById(Long id);

    Grades save(Grades student);

    List<Grades> findAll();

    Optional<Grades> findById(Long id);


}
