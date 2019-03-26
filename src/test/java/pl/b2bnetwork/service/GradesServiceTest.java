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
import pl.b2bnetwork.repository.GradesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GradesServiceTest {

    @InjectMocks
    private GradesService gradesService;

    @Mock
    private GradesRepository gradesRepository;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(gradesService).build();
    }


    @Test
    public void delete() {
        Grades grade = new Grades();
        grade.setId(1L);
        grade.setHistory(2);
        grade.setMath(3);
        grade.setPolish(5);
        grade.setIT(5);

        when(gradesRepository.findById(1L)).thenReturn(Optional.of(grade));
        Assert.assertEquals("Grades removed", gradesService.delete(1L));
    }

    @Test
    public void add() {

        Grades grade = new Grades();
        grade.setId(1L);
        grade.setHistory(2);
        grade.setMath(3);
        grade.setPolish(5);
        grade.setIT(5);


        when(gradesRepository.save(any(Grades.class))).thenReturn(grade);
        Assert.assertEquals(grade.getHistory(), gradesService.add(grade).getHistory());
    }

    @Test
    public void findAll() {

        List<Grades> grades = new ArrayList<>();
        Grades grade = new Grades();
        grade.setHistory(2);
        grade.setMath(3);
        grade.setPolish(5);
        grade.setIT(5);
        grades.add(grade);

        when(gradesRepository.findAll()).thenReturn(grades);
        Assert.assertEquals(1, gradesService.findAll().size());
    }

    @Test
    public void findById() throws NotFoundException {
        Grades grade = new Grades();
        grade.setId(1L);
        grade.setHistory(2);
        grade.setMath(3);
        grade.setPolish(5);
        grade.setIT(5);

        when(gradesRepository.findById(1L)).thenReturn(Optional.of(grade));
        Assert.assertEquals(grade, gradesService.findById(1L));
    }


    @Test
    public void scholarshipList() {
        Grades grade = new Grades();
        grade.setHistory(6);
        grade.setMath(6);
        grade.setPolish(5);
        grade.setIT(5);
        grade.setEnglish(6);
        grade.setBiology(6);
        grade.setChemistry(4);
        grade.setPhysics(4);
        grade.setGeography(4);
        grade.setGerman(4);
        grade.setSport(4);
        List<Grades> gradesList = new ArrayList<>();
        gradesList.add(grade);

        Assert.assertEquals(1, gradesService.scholarshipList(gradesList));
    }
}