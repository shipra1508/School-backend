
package com.school.exception;

public class TeacherNotFoundException extends RuntimeException {

    public TeacherNotFoundException(String message) {
        super(message);
    }

    public static TeacherNotFoundException forId(int id) {
        return new TeacherNotFoundException("Teacher not found with id: " + id);
    }
}
