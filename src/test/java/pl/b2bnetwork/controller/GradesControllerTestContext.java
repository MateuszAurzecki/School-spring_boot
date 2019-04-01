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
import pl.b2bnetwork.model.Grades;

import java.util.List;
@WebAppConfiguration
@SpringBootTest(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:insertGrades.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:delete.sql")})
public class GradesControllerTestContext {
    @Autowired
    private GradesController gradesController;

    @Test
    public void findGradesById() throws NotFoundException {
        Grades grade = new Grades();
        grade.setId(7103L);
        grade.setHistory(6);
        grade.setMath(4);
        grade.setPhysics(1);
        grade.setEnglish(3);
        grade.setBiology(1);
        grade.setChemistry(5);
        grade.setPolish(2);
        grade.setIT(5);
        grade.setGerman(3);
        grade.setGeography(2);
        grade.setSport(5);
        Grades gradesFromDB = gradesController.find(7103L);
        Assert.assertEquals(grade.toString(), gradesFromDB.toString());
    }

    @Test(expected = NotFoundException.class)
    public void deleteOneTest() throws NotFoundException {
        gradesController.deleteById(5465L);
        gradesController.find(5465L);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundTest_ID_10000() throws NotFoundException {
        gradesController.find(1000L);
    }

    @Test
    public void findAllStudents() {
        List<Grades> grades = gradesController.findAll();
        Assert.assertEquals(150, grades.size());
    }

    @Test
    public void scholarshipTest() {
        List<Grades> grades = gradesController.findAll();
        int result = gradesController.scholarshipList(grades);
        Assert.assertEquals(4, result);
    }
}
