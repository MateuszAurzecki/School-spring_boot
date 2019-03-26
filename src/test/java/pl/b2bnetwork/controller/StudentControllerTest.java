package pl.b2bnetwork.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.b2bnetwork.model.Student;
import pl.b2bnetwork.service.StudentService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;


    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }


    @Test
    public void add() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Jan");
        student.setLastName("Kowalski");
        student.setGender("Male");
        student.setAge(25);
        student.setAttendance(75.5);
        student.setClassid("B");

//        when(studentService.add(student)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.post("/student/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(student)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void findById() throws Exception {

        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Jan");
        student.setLastName("Kowalski");
        student.setGender("Male");
        student.setAge(25);
        student.setAttendance(75.5);
        student.setClassid("B");

        when(studentService.find(1L)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/find/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".age").value(25));

    }

    @Test
    public void findAll() throws Exception {

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

        when(studentService.findAll()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/findAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(25));

    }

    @Test
    public void findByLastName() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Jan");
        student.setLastName("Kowalski");
        student.setGender("Male");
        student.setAge(25);
        student.setAttendance(75.5);
        student.setClassid("B");

        when(studentService.findByLastName("Kowalski")).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/name/Kowalski"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".firstName").value("Jan"));
    }


    @Test
    public void countByClassid() throws Exception {

        Student student = new Student();
        student.setId(777L);
        student.setFirstName("Jan");
        student.setLastName("Kowalski");
        student.setGender("Male");
        student.setAge(25);
        student.setAttendance(75.5);
        student.setClassid("B");

        when(studentService.countByClassid("B")).thenReturn(1L);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/count/B"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


    private String toJson(Student student) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(student);
    }

    private String toJson1(List<Student> student) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(student);
    }
}