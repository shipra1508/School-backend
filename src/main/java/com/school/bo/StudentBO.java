package com.school.bo;

// StudentBO represents the business model used in the service layer
// It can also contain business logic like grade calculation based on marks
public class StudentBO {

    private int id;
    private String name;
    private char gender;
    private String studentClass;
    private int marks; // out of 100

    // Default constructor
    public StudentBO() {}

    // All-args constructor
    public StudentBO(int id, String name, char gender, String studentClass, int marks) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.studentClass = studentClass;
        this.marks = marks;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
    
    // Business logic: Returns a textual remark based on the student's marks
    public String getRemarks() {
        if (marks >= 90) return "Excellent";
        else if (marks >= 75) return "Very Good";
        else if (marks >= 60) return "Good";
        else if (marks >= 35) return "Needs Improvement";
        else return "Fail";
    }
    
    // Business logic: Calculate grade based on student's marks
    public String getGrade() {
        if (marks >= 90) {
            return "A";
        } else if (marks >= 75) {
            return "B";
        } else if (marks >= 60) {
            return "C";
        } else {
            return "F";
        }
    }

}
