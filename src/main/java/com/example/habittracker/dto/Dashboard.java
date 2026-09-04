package com.example.habittracker.dto;

public class Dashboard {

    private int totalHabits;

    private int completedHabits;

    private int pendingHabits;

    private double progressPercentage;

    private int pointsToday;
    
    private int currentStreak;

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

    public double getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(double progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public int getPointsToday() {
        return pointsToday;
    }

    public void setPointsToday(int pointsToday) {
        this.pointsToday = pointsToday;
    }
    
    public int getCurrentStreak()
    {
        return currentStreak;
    }

    public void setCurrentStreak(int currentStreak)
    {
        this.currentStreak = currentStreak;
    }
}