package com.example.habittracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import com.example.habittracker.model.Habit;
import com.example.habittracker.repository.HabitRepository;
import java.util.List;
import com.example.habittracker.model.User;
import com.example.habittracker.repository.UserRepository;
import com.example.habittracker.repository.HabitTrackingRepository;
import org.springframework.transaction.annotation.Transactional;
@Service
public class HabitService {

    @Autowired
    private HabitRepository habitRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
private HabitTrackingRepository trackingRepository;

    public Habit addHabit(int userId, Habit habit)
{
    User user =
userRepository.findById(userId);

    if(user != null)
    {
        if(habit.getFrequency() == null)
        {
            habit.setFrequency("DAILY");
        }

        habit.setUser(user);

        return habitRepository.save(habit);
    }

    return null;
}
    
    public List<Habit> getAllHabits()
    {
      return habitRepository.findAll();
    } 
    @Transactional
    public void deleteHabit(int id)
    {
      trackingRepository.deleteByHabitId(id);
       habitRepository.deleteById(id);
     }
     
     public Habit updateHabit(int id, Habit updatedHabit)
     {
       Habit habit = habitRepository.findById(id).orElse(null);

       if(habit != null)
       {
          habit.setName(updatedHabit.getName());
          habit.setCategory(updatedHabit.getCategory());
          habit.setFrequency(updatedHabit.getFrequency());
          return habitRepository.save(habit);
        }
           return null;
      }
      
      public List<Habit> getUserHabits(int userId)
{
 User user =
userRepository.findById(userId);

    if(user != null)
    {
        return habitRepository.findByUser(user);
    }

    return null;
}
 public List<Habit> searchHabit(String name)
{
    return habitRepository.findByNameContaining(name);
}

public List<Habit> getHabitsByCategory(String category)
{
    return habitRepository.findByCategory(category);
}

public List<Habit> getHabitsByFrequency(String frequency)
{
    return habitRepository.findByFrequency(frequency);
}

public List<Habit> sortByStreak()
{
    return habitRepository.findAll(
            Sort.by(Sort.Direction.DESC, "streak"));
}

public List<Habit> sortByName()
{
    return habitRepository.findAll(
            Sort.by(Sort.Direction.ASC, "name"));
}
      
}