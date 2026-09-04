package com.example.habittracker.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.habittracker.model.Habit;
import java.util.List;
import com.example.habittracker.model.User;
import org.springframework.data.domain.Sort;
public interface HabitRepository extends JpaRepository<Habit, Integer>
{
	List<Habit> findByUser(User user);
	List<Habit> findByNameContaining(String name);
	List<Habit> findByCategory(String category);
	List<Habit> findByFrequency(String frequency);
}