package pl.b2bnetwork.service;

import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.b2bnetwork.model.Grades;
import pl.b2bnetwork.model.Student;
import pl.b2bnetwork.repository.StudentRepository;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentService).build();
    }

    @Test
    public void delete() {
        // given
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Jan");
        student.setLastName("Kowalski");
        student.setGender("Male");
        student.setAge(25);
        student.setAttendance(75.5);
        student.setClassid("B");
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Assert.assertEquals("Student removed", studentService.delete(1L));
    }

    @Test
    public void add() {

        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Jan");
        student.setLastName("Kowalski");
        student.setGender("Male");
        student.setAge(25);
        student.setAttendance(75.5);
        student.setClassid("B");

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        Assert.assertEquals(student.getLastName(), studentService.add(student).getLastName());
    }

    @Test
    public void find() throws NotFoundException {
        Student student = new Student();
        student.setId(777L);
        student.setFirstName("Jan");
        student.setLastName("Kowalski");
        student.setGender("Male");
        student.setAge(25);
        student.setAttendance(75.5);
        student.setClassid("B");

        when(studentRepository.findById(777L)).thenReturn(Optional.of(student));
        Assert.assertEquals(student, studentService.find(777L));
    }

    @Test
    public void findAll() {
        List<Student> students = new ArrayList<>();
        Student student = new Student();
        student.setId(777L);
        student.setFirstName("Jan");
        student.setLastName("Kowalski");
        student.setGender("Male");
        student.setAge(25);
        student.setAttendance(75.5);
        student.setClassid("B");
        students.add(student);

        when(studentRepository.findAll()).thenReturn(students);
        Assert.assertEquals(1, studentService.findAll().size());
    }

    @Test
    public void findByLastName() {

        Student student = new Student();
        student.setId(777L);
        student.setFirstName("Jan");
        student.setLastName("Kowalski");
        student.setGender("Male");
        student.setAge(25);
        student.setAttendance(75.5);
        student.setClassid("B");

        when(studentRepository.findByLastName("Kowalski")).thenReturn(student);
        Assert.assertEquals(student, studentService.findByLastName("Kowalski"));

    }

    @Test
    public void findByClassid() {

        List<Student> students = new ArrayList<>();
        Student student = new Student();
        student.setId(777L);
        student.setFirstName("Jan");
        student.setLastName("Kowalski");
        student.setGender("Male");
        student.setAge(25);
        student.setAttendance(75.5);
        student.setClassid("B");
        students.add(student);

        when(studentRepository.findByClassid("B")).thenReturn(students);
        Assert.assertEquals(1, studentService.finddByClassid("B").size());

    }

    @Test
    public void countByClassid() {

        Student student = new Student();
        student.setId(777L);
        student.setFirstName("Jan");
        student.setLastName("Kowalski");
        student.setGender("Male");
        student.setAge(25);
        student.setAttendance(75.5);
        student.setClassid("B");

        when(studentRepository.countByClassid("B")).thenReturn(1L);

        Assert.assertEquals(1, studentService.countByClassid("B").byteValue());
    }


    @Test
    public void averageAttendanceInClass() {
        Student student1 = new Student();
        student1.setAttendance(56.9);
        student1.setClassid("B");

        Student student2 = new Student();
        student2.setAttendance(75.5);
        student2.setClassid("B");
        List<Student> students = new ArrayList<>();
        Map<String, Double> average = new HashMap<>();
        average.put(student1.getClassid(), student1.getAttendance());
        average.put(student2.getClassid(), student2.getAttendance());
        students.add(student1);
        students.add(student2);

        // doReturn(average).when(studentRepository).averageAttendanceInClass();

        Assert.assertEquals(true, studentService.averageAttendanceInClass(students).containsValue(66.2));
    }

    @Test
    public void averageGradesForStudentById() throws Exception {
        Grades grade = new Grades();
        grade.setHistory(3);
        grade.setMath(3);
        grade.setPolish(5);
        grade.setIT(5);
        grade.setEnglish(3);
        grade.setBiology(3);
        grade.setChemistry(4);
        grade.setPhysics(4);
        grade.setGeography(4);
        grade.setGerman(4);
        grade.setSport(4);

        Student student = new Student();
        student.setId(777L);
        student.setFirstName("Jan");
        student.setLastName("Kowalski");
        student.setGender("Male");
        student.setAge(25);
        student.setAttendance(75.5);
        student.setClassid("B");
        student.setGrades(grade);

        Assert.assertEquals(3.81, studentService.averageGradesForStudentById(student), 0.1);

    }
}