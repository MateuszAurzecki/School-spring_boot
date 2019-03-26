package pl.b2bnetwork.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.b2bnetwork.model.Grades;
import pl.b2bnetwork.service.GradesService;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradesController {

//    {
//            "history": 2,
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
//            "it": 6
//    }


    @Autowired
    private GradesService gradesService;


    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        gradesService.delete(id);
        return "grades was succesfully removed";
    }

    @PostMapping("/add")
    public Grades addNew(@RequestBody Grades student) {
        return gradesService.add(student);

    }

    @GetMapping("/find/{id}")
    public Grades find(@PathVariable Long id) throws NotFoundException {
        return gradesService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Grades> findAll() {
        return gradesService.findAll();
    }

    @GetMapping("/scholarship")
    public int scholarshipList(@RequestBody List<Grades> gradesList) {
        return gradesService.scholarshipList(gradesList);

        //JSON do testowania metody w postamnie
//            [  {
//            "history": 5,
//                    "math": 5,
//                    "physics": 5,
//                    "english": 5,
//                    "biology": 5,
//                    "chemistry": 5,
//                    "polish": 5,
//                    "german": 5,
//                    "geography": 5,
//                    "sport": 6,
//                    "it": 6
//        },
//        {
//            "history": 2,
//                "math": 5,
//                "physics": 5,
//                "english": 3,
//                "biology": 2,
//                "chemistry": 5,
//                "polish": 5,
//                "german": 2,
//                "geography": 1,
//                "sport": 6,
//                "it": 6
//        }
//    ]
    }
}