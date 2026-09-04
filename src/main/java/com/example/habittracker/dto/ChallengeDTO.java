package com.example.habittracker.dto;

public class ChallengeDTO
{
    private int dailyCompleted;
    private int dailyTarget;

    private int weeklyCompleted;
    private int weeklyTarget;

    private int monthlyCompleted;
    private int monthlyTarget;

    public int getDailyCompleted() {
        return dailyCompleted;
    }

    public void setDailyCompleted(int dailyCompleted) {
        this.dailyCompleted = dailyCompleted;
    }

    public int getDailyTarget() {
        return dailyTarget;
    }

    public void setDailyTarget(int dailyTarget) {
        this.dailyTarget = dailyTarget;
    }

    public int getWeeklyCompleted() {
        return weeklyCompleted;
    }

    public void setWeeklyCompleted(int weeklyCompleted) {
        this.weeklyCompleted = weeklyCompleted;
    }

    public int getWeeklyTarget() {
        return weeklyTarget;
    }

    public void setWeeklyTarget(int weeklyTarget) {
        this.weeklyTarget = weeklyTarget;
    }

    public int getMonthlyCompleted() {
        return monthlyCompleted;
    }

    public void setMonthlyCompleted(int monthlyCompleted) {
        this.monthlyCompleted = monthlyCompleted;
    }

    public int getMonthlyTarget() {
        return monthlyTarget;
    }

    public void setMonthlyTarget(int monthlyTarget) {
        this.monthlyTarget = monthlyTarget;
    }
}
