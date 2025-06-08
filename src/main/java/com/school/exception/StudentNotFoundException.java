package com.school.exception;

public class StudentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(int id) {
        super("No student record found for ID: " + id);
    }

    public static StudentNotFoundException forId(int id) {
        return new StudentNotFoundException(id);
    }
}
