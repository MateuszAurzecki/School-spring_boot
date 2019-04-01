package pl.b2bnetwork.controller;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.b2bnetwork.TestContext;
import pl.b2bnetwork.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebAppConfiguration
@SpringBootTest(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:insertStudents.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:delete.sql")})
public class StudentControllerTestContext {
    @Autowired
    private StudentController studentController;

    @Test(expected = NotFoundException.class)
    public void deleteOneTest() throws NotFoundException {
        studentController.deleteById(5465L);
        studentController.findById(5465L);
    }

    @Test
    public void findVenusBedlingtonById() throws NotFoundException {
        Student student = new Student();
        student.setId(7103L);
        student.setFirstName("Vénus");
        student.setLastName("Bedlington");
        student.setGender("Male");
        student.setAge(14);
        student.setAttendance(73.4);
        student.setClassid("A");
        Student studentFromDB = studentController.findById(7103L);
        Assert.assertEquals(student.toString(), studentFromDB.toString());
    }

    @Test(expected = NotFoundException.class)
    public void notFoundTest_ID_10000() throws NotFoundException {
        studentController.findById(1000L);
    }

    @Test
    public void findAllStudents() {
        List<Student> students = studentController.findAll();
        Assert.assertEquals(150, students.size());
    }

    @Test
    public void findALlStudentOfEClass() {
        List<Student> students = studentController.findByClass("E");
        Assert.assertEquals(30, students.size());
    }

    @Test
    public void findVenusBedlingtonByLastName() {
        Student student = new Student();
        student.setId(7103L);
        student.setFirstName("Vénus");
        student.setLastName("Bedlington");
        student.setGender("Male");
        student.setAge(14);
        student.setAttendance(73.4);
        student.setClassid("A");
        Student studentFromDB = studentController.findByLastName("Bedlington");
        Assert.assertEquals(student.toString(), studentFromDB.toString());
    }

    @Test
    public void countStudentsOfAClass() {
        Assert.assertEquals(30, studentController.countByClassid("A").intValue());
    }

    @Test
    public void averageAttendanceInClass() {
        Student student1 = new Student();
        student1.setAttendance(73.4);
        student1.setClassid("A");
        Student student2 = new Student();
        student2.setAttendance(79.3);
        student2.setClassid("A");
        Student student3 = new Student();
        student3.setAttendance(89.7);
        student3.setClassid("C");
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        Map<String, Double> mapToTest = new HashMap<>();
        mapToTest.put("A", 76.35);
        mapToTest.put("B", 0.0);
        mapToTest.put("C", 89.7);
        mapToTest.put("D", 0.0);
        mapToTest.put("E", 0.0);
        Assert.assertEquals(mapToTest, studentController.averageAttendanceInClass(students));
    }
}
