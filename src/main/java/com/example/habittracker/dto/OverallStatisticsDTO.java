package com.example.habittracker.dto;

public class OverallStatisticsDTO
{
    private int totalHabits;
    private int totalCompletions;
    private int totalPoints;
    private int currentStreak;
    private int highestStreak;
    private double completionRate;
    private double averagePointsPerCompletion;
    private int pending;
   

    public int getTotalHabits()
    {
        return totalHabits;
    }

    public void setTotalHabits(int totalHabits)
    {
        this.totalHabits = totalHabits;
    }

    public int getTotalCompletions()
    {
        return totalCompletions;
    }

    public void setTotalCompletions(int totalCompletions)
    {
        this.totalCompletions = totalCompletions;
    }

    public int getTotalPoints()
    {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints)
    {
        this.totalPoints = totalPoints;
    }

    public int getCurrentStreak()
    {
        return currentStreak;
    }

    public void setCurrentStreak(int currentStreak)
    {
        this.currentStreak = currentStreak;
    }

    public int getHighestStreak()
    {
        return highestStreak;
    }

    public void setHighestStreak(int highestStreak)
    {
        this.highestStreak = highestStreak;
    }

    public double getCompletionRate()
    {
        return completionRate;
    }

    public void setCompletionRate(double completionRate)
    {
        this.completionRate = completionRate;
    }

    public double getAveragePointsPerCompletion()
    {
        return averagePointsPerCompletion;
    }

    public void setAveragePointsPerCompletion(double averagePointsPerCompletion)
    {
        this.averagePointsPerCompletion =
        averagePointsPerCompletion;
    }
    
    public int getPending()
    {
        return pending;
    }

    public void setPending(int pending)
    {
        this.pending = pending;
    }
}
