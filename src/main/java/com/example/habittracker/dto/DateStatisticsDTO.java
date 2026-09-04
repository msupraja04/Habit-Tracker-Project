package com.example.habittracker.dto;

public class DateStatisticsDTO
{
    private int completed;
    private int pending;
    private int points;
    private double averagePoints;
    private double progress;

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

    public double getAveragePoints()
    {
        return averagePoints;
    }

    public void setAveragePoints(double averagePoints)
    {
        this.averagePoints = averagePoints;
    }

    public double getProgress()
    {
        return progress;
    }

    public void setProgress(double progress)
    {
        this.progress = progress;
    }
}
