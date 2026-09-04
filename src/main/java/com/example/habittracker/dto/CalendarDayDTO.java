package com.example.habittracker.dto;
import java.util.List;
public class CalendarDayDTO
{
    private String date;

    private int totalHabits;

    private int completedHabits;

    private int pointsEarned;

    private double completionRate;
    
    private List<String> completedHabitNames;

    private List<String> pendingHabitNames;

    private boolean streakMaintained;

    public String getDate()
    {
    	return date;
    }
    public void setDate(String date)
    {
    	this.date=date;
    }
    
    public int getTotalHabits()
    {
    	return totalHabits;
    }
    public void setTotalHabits(int totalHabits)
    {
    	this.totalHabits=totalHabits;
    }
    
    public int getCompletedHabits()
    {
    	return completedHabits;
    }
    public void setCompletedHabits(int completedHabits)
    {
    	this.completedHabits=completedHabits;
    }
    
    public int getPointsEarned()
    {
    	return pointsEarned;
    }
    public void setPointsEarned(int pointsEarned)
    {
    	this.pointsEarned=pointsEarned;
    }
    
    public double getCompletionRate()
    {
    	return completionRate;
    }
    public void setCompletionRate(double completionRate)
    {
    	this.completionRate=completionRate;
    }
    
    public List<String> getCompletedHabitNames()
    {
        return completedHabitNames;
    }

    public void setCompletedHabitNames(
    List<String> completedHabitNames)
    {
        this.completedHabitNames =
        completedHabitNames;
    }

    public List<String> getPendingHabitNames()
    {
        return pendingHabitNames;
    }

    public void setPendingHabitNames(
    List<String> pendingHabitNames)
    {
        this.pendingHabitNames =
        pendingHabitNames;
    }

    public boolean isStreakMaintained()
    {
        return streakMaintained;
    }

    public void setStreakMaintained(
    boolean streakMaintained)
    {
        this.streakMaintained =
        streakMaintained;
    }
    
    
}
