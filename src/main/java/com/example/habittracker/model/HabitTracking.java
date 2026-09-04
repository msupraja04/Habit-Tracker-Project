package com.example.habittracker.model;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "habit_tracking")
public class HabitTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate date;

    private boolean completed;

    private int pointsEarned;

    @ManyToOne
    @JoinColumn(name = "habit_id")
    private Habit habit;

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean getCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }
    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public Habit getHabit() {
        return habit;
    }
    public void setHabit(Habit habit) {
        this.habit = habit;
    }
}