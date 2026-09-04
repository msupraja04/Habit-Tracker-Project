package com.example.habittracker.dto;

	public class BadgeDTO
	{
	    private String badgeName;
	    private String description;
	    private boolean unlocked;
	    private int progress;
	    private int target;

	    public String getBadgeName()
	    {
	        return badgeName;
	    }

	    public void setBadgeName(String badgeName)
	    {
	        this.badgeName = badgeName;
	    }

	    public String getDescription()
	    {
	        return description;
	    }

	    public void setDescription(String description)
	    {
	        this.description = description;
	    }

	    public boolean isUnlocked()
	    {
	        return unlocked;
	    }

	    public void setUnlocked(boolean unlocked)
	    {
	        this.unlocked = unlocked;
	    }

	    public int getProgress()
	    {
	        return progress;
	    }

	    public void setProgress(int progress)
	    {
	        this.progress = progress;
	    }

	    public int getTarget()
	    {
	        return target;
	    }

	    public void setTarget(int target)
	    {
	        this.target = target;
	    }
	}
