package pl.b2bnetwork.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.b2bnetwork.model.Grades;
import pl.b2bnetwork.model.Student;
import pl.b2bnetwork.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String delete(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return "Student removed";
        } else return "Student not removed";
    }


    public Student add(Student student) {
        return studentRepository.save(student);
    }


    public Student find(Long id) throws NotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new NotFoundException("Student not found");
        }
    }


    public List<Student> findAll() {
        return studentRepository.findAll();
    }


    public List<Student> finddByClassid(String letter) {
        return studentRepository.findByClassid(letter);

    }

    public Student findByLastName(String name) {
        return studentRepository.findByLastName(name);
    }


    public Long countByClassid(String classid) {
        return studentRepository.countByClassid(classid);
    }

    public Map<String, Double> averageAttendanceInClass(List<Student> listStudent) {
        List<Student> students = new ArrayList<>();
        Map<String, Double> averageAttendanceMap = new HashMap<>();
        double a = 0, b = 0, c = 0, d = 0, e = 0;
        int aSize = 0;
        int bSize = 0;
        int cSize = 0;
        int dSize = 0;
        int eSize = 0;

        if (listStudent.size() == 0) {
            students = findAll();
        } else students = listStudent;

        for (Student s : students) {
            switch (s.getClassid()) {
                case "A":
                    a += s.getAttendance();
                    aSize++;
                    break;
                case "B":
                    b += s.getAttendance();
                    bSize++;
                    break;
                case "C":
                    c += s.getAttendance();
                    cSize++;
                    break;
                case "D":
                    d += s.getAttendance();
                    dSize++;
                    break;
                default:
                    e += s.getAttendance();
                    eSize++;
                    break;
            }
        }
        a = a / (double) aSize;
        b = b / (double) bSize;
        c = c / (double) cSize;
        d = d / (double) dSize;
        e = e / (double) eSize;
        averageAttendanceMap.put("A", Math.round(a * 100) / 100D);
        averageAttendanceMap.put("B", Math.round(b * 100) / 100D);
        averageAttendanceMap.put("C", Math.round(c * 100) / 100D);
        averageAttendanceMap.put("D", Math.round(d * 100) / 100D);
        averageAttendanceMap.put("E", Math.round(e * 100) / 100D);

        return averageAttendanceMap;
    }


    public Double averageGradesForStudentById(Student student) {
        Double result = 0.0;

        Grades grades = student.getGrades();
        result = (double) ((grades.getHistory() + grades.getMath() + grades.getPhysics() + grades.getEnglish()
                + grades.getBiology() + grades.getChemistry() + grades.getPolish() + grades.getIT() + grades.getGerman()
                + grades.getGeography() + grades.getSport()));

        return result / 11;
    }


}
