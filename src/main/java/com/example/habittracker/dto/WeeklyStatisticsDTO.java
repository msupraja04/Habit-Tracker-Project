package com.example.habittracker.dto;

public class WeeklyStatisticsDTO
{
    private int completed;
    private int pending;
    private int points;
    private double averagePerDay;
    private double progress;
    private int[] dailyCounts;


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

    public double getAveragePerDay()
    {
        return averagePerDay;
    }

    public void setAveragePerDay(double averagePerDay)
    {
        this.averagePerDay = averagePerDay;
    }

    public double getProgress()
    {
        return progress;
    }

    public void setProgress(double progress)
    {
        this.progress = progress;
    }
    
    public int[] getDailyCounts()
    {
        return dailyCounts;
    }

    public void setDailyCounts(int[] dailyCounts)
    {
        this.dailyCounts = dailyCounts;
    }
}
