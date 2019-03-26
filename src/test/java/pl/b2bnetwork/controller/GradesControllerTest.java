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
import pl.b2bnetwork.model.Grades;
import pl.b2bnetwork.service.GradesService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(MockitoJUnitRunner.class)
public class GradesControllerTest {

    @InjectMocks
    private GradesController gradesController;

    @Mock
    private GradesService gradesService;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(gradesController).build();
    }


    @Test
    public void addNew() throws Exception {
        Grades grade = new Grades();
        grade.setId(1L);
        grade.setHistory(2);
        grade.setMath(3);
        grade.setPolish(5);
        grade.setIT(5);


//        when(gradesService.add(grade)).thenReturn(grade);

        mockMvc.perform(MockMvcRequestBuilders.post("/grades/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(grade)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    public void find() throws Exception {
        Grades grade = new Grades();
        grade.setId(1L);
        grade.setHistory(2);
        grade.setMath(3);
        grade.setPolish(5);
        grade.setIT(5);

        when(gradesService.findById(1L)).thenReturn(grade);

        mockMvc.perform(MockMvcRequestBuilders.get("/grades/find/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".math").value(3));
    }


    @Test
    public void findAll() throws Exception {
        Grades grade = new Grades();
        grade.setId(1L);
        grade.setHistory(2);
        grade.setMath(3);
        grade.setPolish(5);
        grade.setIT(5);
        List<Grades> gradesList = new ArrayList<>();
        gradesList.add(grade);

        when(gradesService.findAll()).thenReturn(gradesList);

        mockMvc.perform(MockMvcRequestBuilders.get("/grades/findAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].polish").value(5));
    }

    private String toJson(Grades grade) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(grade);
    }
}
