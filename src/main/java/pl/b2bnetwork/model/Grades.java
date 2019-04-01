package pl.b2bnetwork.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Grades implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int history;
    private int math;
    private int physics;
    private int english;
    private int biology;
    private int chemistry;
    private int polish;
    private int IT;
    private int german;
    private int geography;
    private int sport;



    public Grades() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getPhysics() {
        return physics;
    }

    public void setPhysics(int physics) {
        this.physics = physics;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getBiology() {
        return biology;
    }

    public void setBiology(int biology) {
        this.biology = biology;
    }

    public int getChemistry() {
        return chemistry;
    }

    public void setChemistry(int chemistry) {
        this.chemistry = chemistry;
    }

    public int getPolish() {
        return polish;
    }

    public void setPolish(int polish) {
        this.polish = polish;
    }

    public int getIT() {
        return IT;
    }

    public void setIT(int IT) {
        this.IT = IT;
    }

    public int getGerman() {
        return german;
    }

    public void setGerman(int german) {
        this.german = german;
    }

    public int getGeography() {
        return geography;
    }

    public void setGeography(int geography) {
        this.geography = geography;
    }

    public int getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = sport;
    }

    public Double averageGrades(Grades grades) {
        double average = 0.0;
        average = (grades.getHistory() + grades.getMath() + grades.getPhysics() + grades.getEnglish()
                + grades.getBiology() + grades.getChemistry() + grades.getPolish() + grades.getIT() + grades.getGerman()
                + grades.getGeography() + grades.getSport());
        return average / 11;
    }

    @Override
    public String toString() {
        return "Grades{" +
                "id=" + id +
                ", history=" + history +
                ", math=" + math +
                ", physics=" + physics +
                ", english=" + english +
                ", biology=" + biology +
                ", chemistry=" + chemistry +
                ", polish=" + polish +
                ", IT=" + IT +
                ", german=" + german +
                ", geography=" + geography +
                ", sport=" + sport +
                '}';
    }
}