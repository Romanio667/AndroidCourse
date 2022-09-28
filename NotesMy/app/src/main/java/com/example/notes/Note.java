package com.example.notes;

public class Note {
    private String tittle;
    private String description;
    private String dayOfWeek;
    private int priority;

    public Note(String tittle, String description, String dayOfWeek, int priority) {
        this.tittle = tittle;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
        this.priority = priority;
    }

    public String getTittle() {
        return tittle;
    }

    public String getDescription() {
        return description;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getPriority() {
        return priority;
    }
}

