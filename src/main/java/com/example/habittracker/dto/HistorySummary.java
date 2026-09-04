package com.example.habittracker.dto;

import java.util.List;

public class HistorySummary
{
    private int totalHabits;
    private int completedHabits;
    private int pendingHabits;
    private int totalPoints;

    private List<String> completedHabitNames;
    private List<String> pendingHabitNames;

    public int getTotalHabits() {
        return totalHabits;
    }

    public void setTotalHabits(int totalHabits) {
        this.totalHabits = totalHabits;
    }

    public int getCompletedHabits() {
        return completedHabits;
    }

    public void setCompletedHabits(int completedHabits) {
        this.completedHabits = completedHabits;
    }

    public int getPendingHabits() {
        return pendingHabits;
    }

    public void setPendingHabits(int pendingHabits) {
        this.pendingHabits = pendingHabits;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public List<String> getCompletedHabitNames() {
        return completedHabitNames;
    }

    public void setCompletedHabitNames(List<String> completedHabitNames) {
        this.completedHabitNames = completedHabitNames;
    }

    public List<String> getPendingHabitNames() {
        return pendingHabitNames;
    }

    public void setPendingHabitNames(List<String> pendingHabitNames) {
        this.pendingHabitNames = pendingHabitNames;
    }
}