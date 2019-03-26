package pl.b2bnetwork.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.b2bnetwork.model.Student;
import pl.b2bnetwork.service.StudentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    //        {
//                "Student":{
//            "firstName":"Jan",
//            "lastName": "Kowalski",
//            "gender": "Male",
//            "age": 25,
//            "attendance": 89.9,
//            "classid": "E",
//            "grades": {   "history": 2,
//            "math": 5,
//            "physics": 5,
//            "english": 3,
//            "biology": 2,
//            "chemistry": 5,
//            "polish": 5,
//            "german": 2,
//            "geography": 1,
//            "sport": 6,
//            "student": null,
//            "it": 6}
//    }
    @Autowired
    private StudentService studentService;

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {

        studentService.delete(id);
        return "Student removed";
    }

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student) {

        studentService.add(student);
        return "Student added";
    }

    @GetMapping("/find/{id}")
    Student findById(@PathVariable Long id) throws NotFoundException {
        return studentService.find(id);
    }

    @GetMapping("/findAll")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/name/{name}")
    public Student findByLastName(@PathVariable String name) {
        return studentService.findByLastName(name);
    }

    @GetMapping("/findByClass/{classId}")
    public List<Student> findByClass(@PathVariable String classId) {
        return studentService.finddByClassid(classId);
    }

    @GetMapping("/count/{classId}")
    public Long countByClassid(@PathVariable String classId) {
        return studentService.countByClassid(classId);

    }

    @GetMapping("/attendance/{studentList}")
    public Map<String, Double> averageAttendanceInClass(List<Student> studentList) {
        return studentService.averageAttendanceInClass(studentList);
    }

    @GetMapping("/studentAverage")
    public Double averageGradesForStudentById(@RequestBody Student student) {
        return studentService.averageGradesForStudentById(student);
    }

}