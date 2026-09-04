package com.example.habittracker.model;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name="Habits")
public class Habit
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="category")
	private String category;
	@Column(name="frequency")
	private String frequency;
	@Column(name="last_completed_date")
	private LocalDate lastCompletedDate;
	@Column(name="streak")
	private int streak;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String getCategory()
	{
		return category;
	}
	public void setCategory(String category)
	{
		this.category=category;
	}
	
	public String getFrequency()
	{
	    return frequency;
	}
	public void setFrequency(String frequency)
	{
	    this.frequency = frequency;
	}
	
	public LocalDate getLastCompletedDate()
	{
	    return lastCompletedDate;
	}
	public void setLastCompletedDate
	(LocalDate lastCompletedDate)
	{
	    this.lastCompletedDate = lastCompletedDate;
	}
	
	public int getStreak()
	{
	    return streak;
	}
	public void setStreak(int streak)
	{
	    this.streak = streak;
	}
	
	public User getUser()
	{
	    return user;
	}
	public void setUser(User user)
	{
	    this.user = user;
	}
}