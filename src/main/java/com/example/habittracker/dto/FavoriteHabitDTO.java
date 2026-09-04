package com.example.habittracker.dto;

public class FavoriteHabitDTO
{
    private String habitName;
    private int completedCount;
    private double consistency;

    public String getHabitName()
    {
        return habitName;
    }

    public void setHabitName(String habitName)
    {
        this.habitName = habitName;
    }

    public int getCompletedCount()
    {
        return completedCount;
    }

    public void setCompletedCount(int completedCount)
    {
        this.completedCount = completedCount;
    }

    public double getConsistency()
    {
        return consistency;
    }

    public void setConsistency(double consistency)
    {
        this.consistency = consistency;
    }
}
