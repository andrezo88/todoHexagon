package com.abreu.todoHexagonal.business.model;

public enum Priority {
    HIGH,
    MEDIUM,
    LOW;

    public static boolean isValid(String priority) {
        return priority.equals(Priority.HIGH.toString()) ||
                priority.equals(Priority.MEDIUM.toString()) ||
                priority.equals(Priority.LOW.toString());
    }
}
