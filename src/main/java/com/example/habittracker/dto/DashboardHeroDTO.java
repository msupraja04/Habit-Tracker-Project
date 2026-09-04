package com.example.habittracker.dto;

	public class DashboardHeroDTO
	{
	    private int currentStreak;
	    private int totalPoints;
	    private int pendingHabits;
	    private int badgesEarned;
	    private String streakMessage;
	    private String pendingMessage;
	    private String nextBadgeMessage;

	    public int getCurrentStreak()
	    {
	        return currentStreak;
	    }

	    public void setCurrentStreak(int currentStreak)
	    {
	        this.currentStreak = currentStreak;
	    }

	    public int getTotalPoints()
	    {
	        return totalPoints;
	    }

	    public void setTotalPoints(int totalPoints)
	    {
	        this.totalPoints = totalPoints;
	    }

	    public int getPendingHabits()
	    {
	        return pendingHabits;
	    }

	    public void setPendingHabits(int pendingHabits)
	    {
	        this.pendingHabits = pendingHabits;
	    }

	    public int getBadgesEarned()
	    {
	        return badgesEarned;
	    }

	    public void setBadgesEarned(int badgesEarned)
	    {
	        this.badgesEarned = badgesEarned;
	    }
	    
	    public String getStreakMessage()
	    {
	    	return streakMessage;
	    }
	    public void setStreakMessage(String streakMessage)
	    {
	    	this.streakMessage= streakMessage;
	    }
	    
	    public String getPendingMessage()
	    {
	    	return pendingMessage;
	    }
	    public void setPendingMessage(String pendingMessage)
	    {
	    	this.pendingMessage= pendingMessage;
	    }
	    
	    public String getNextBadgeMessage()
	    {
	    	return nextBadgeMessage;
	    }
	    public void setNextBadgeMessage(String nextBadgeMessage)
	    {
	    	this.nextBadgeMessage=nextBadgeMessage;
	    }
	    
	    
	}
