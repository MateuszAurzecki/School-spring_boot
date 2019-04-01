package pl.b2bnetwork.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Student implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private Double attendance;
    private String classid;


    @OneToOne
    @JoinColumn
    Grades grades;


    public Student() {
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getAttendance() {
        return attendance;
    }

    public void setAttendance(Double attendance) {
        this.attendance = attendance;
    }

    public Grades getGrades() {
        return grades;
    }

    public void setGrades(Grades grades) {
        this.grades = grades;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        Student xxx = (Student) obj;
        if (xxx.lastName.equals(lastName) && xxx.firstName.equals(firstName) && xxx.age == age && xxx.attendance == attendance) {
            result = true;
        }

        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", attendance=" + attendance +
                ", classid='" + classid + '\'' +
                ", grades=" + grades +
                '}';
    }
}
