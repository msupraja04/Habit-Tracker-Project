package com.example.habittracker.repository;
import com.example.habittracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import com.example.habittracker.model.HabitTracking;
import java.time.LocalDate;
import java.util.List;
public interface HabitTrackingRepository
       extends JpaRepository<HabitTracking, Integer>{
	List<HabitTracking> findByDate(LocalDate date);
	List<HabitTracking> findByDateBetween
	(LocalDate startDate, LocalDate endDate);
	HabitTracking findByHabitIdAndDate(
	        int habitId,
	        LocalDate date);
	List<HabitTracking> findByHabitUser(User user);

	List<HabitTracking> findByHabitUserAndDate(
	        User user,
	        LocalDate date);

	List<HabitTracking> findByHabitUserAndDateBetween(
	        User user,
	        LocalDate startDate,
	        LocalDate endDate);
	@Modifying
	@Transactional
	void deleteByHabitId(int habitId);
	}