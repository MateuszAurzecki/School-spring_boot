package pl.b2bnetwork.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.b2bnetwork.model.Grades;
import pl.b2bnetwork.repository.GradesRepository;


import java.util.List;
import java.util.Optional;

@Service
public class GradesService {

    @Autowired
    private GradesRepository gradesRepository;


    public String delete(Long id) {

        gradesRepository.deleteById(id);

        Optional<Grades> grades = gradesRepository.findById(id);
        if (grades.isPresent()) {
            gradesRepository.deleteById(id);
            return "Grades removed";
        } else return "Grades not removed";
    }


    public Grades add(Grades student) {
        return gradesRepository.save(student);
    }


    public List<Grades> findAll() {
        return gradesRepository.findAll();
    }


    public Grades findById(Long id) throws NotFoundException {
        Optional<Grades> grades = gradesRepository.findById(id);

        if (grades.isPresent()) {
            return grades.get();
        } else throw new NotFoundException("Grades not found");
    }

    public int scholarshipList(List<Grades> gradesList) {
        int result = 0;

        for (Grades g : gradesList) {
            if (g.averageGrades(g) >= 4.5) {
                result++;
            }
        }
        return result;
    }

}
