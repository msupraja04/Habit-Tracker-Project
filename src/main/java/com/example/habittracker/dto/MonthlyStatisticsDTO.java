package com.example.habittracker.dto;

public class MonthlyStatisticsDTO
{
    private int completed;
    private int pending;
    private int points;
    private int highestStreak;
    private double averageDailyPoints;
    private double progress;
    private int[] weeklyPoints;

    public int getCompleted()
    {
        return completed;
    }

    public void setCompleted(int completed)
    {
        this.completed = completed;
    }

    public int getPending()
    {
        return pending;
    }

    public void setPending(int pending)
    {
        this.pending = pending;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public int getHighestStreak()
    {
        return highestStreak;
    }

    public void setHighestStreak(int highestStreak)
    {
        this.highestStreak = highestStreak;
    }

    public double getAverageDailyPoints()
    {
        return averageDailyPoints;
    }

    public void setAverageDailyPoints(double averageDailyPoints)
    {
        this.averageDailyPoints = averageDailyPoints;
    }

    public double getProgress()
    {
        return progress;
    }

    public void setProgress(double progress)
    {
        this.progress = progress;
    }
    
    public int[] getWeeklyPoints()
    {
        return weeklyPoints;
    }

    public void setWeeklyPoints(int[] weeklyPoints)
    {
        this.weeklyPoints = weeklyPoints;
    }
}
