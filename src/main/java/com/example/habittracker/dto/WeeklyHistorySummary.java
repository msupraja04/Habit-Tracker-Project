package com.example.habittracker.dto;

import java.util.List;

public class WeeklyHistorySummary
{
    private int totalHabits;
    private int completedHabits;
    private int pendingHabits;
    private int totalPoints;

    private List<String> dailySummary;

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

    public List<String> getDailySummary() {
        return dailySummary;
    }

    public void setDailySummary(List<String> dailySummary) {
        this.dailySummary = dailySummary;
    }
}