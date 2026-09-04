package com.example.habittracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.habittracker.model.Habit;
import com.example.habittracker.service.HabitService;
import java.util.List;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/habit")
public class HabitController {

    @Autowired
    private HabitService habitService;

    @PostMapping("/add/{userId}")
    public Habit addHabit(@PathVariable int userId,
                          @RequestBody Habit habit)
    {
        return habitService.addHabit(userId, habit);
    }
    
    @GetMapping("/all")
    public List<Habit> getAllHabits()
    {
        return habitService.getAllHabits();
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteHabit(@PathVariable int id)
    {
        habitService.deleteHabit(id);

        return "Habit deleted successfully";
    }
    
    @PutMapping("/update/{id}")
    public Habit updateHabit(@PathVariable int id,
                             @RequestBody Habit updatedHabit)
    {
        return habitService.updateHabit(id, updatedHabit);
    }
    
    @GetMapping("/user/{userId}")
    public List<Habit> getUserHabits
    (@PathVariable int userId)
    {
        return habitService.getUserHabits(userId);
    }
    
    @GetMapping("/search/{name}")
    public List<Habit> searchHabit
    (@PathVariable String name)
    {
        return habitService.searchHabit(name);
    }
    
    @GetMapping("/category/{category}")
    public List<Habit> getHabitsByCategory
    (@PathVariable String category)
    {
        return habitService.getHabitsByCategory(category);
    }
    
    @GetMapping("/frequency/{frequency}")
    public List<Habit> getHabitsByFrequency
    (@PathVariable String frequency)
    {
        return habitService.getHabitsByFrequency(frequency);
    }
    
    @GetMapping("/sort/streak")
    public List<Habit> sortByStreak()
    {
        return habitService.sortByStreak();
    }
    
    @GetMapping("/sort/name")
    public List<Habit> sortByName()
    {
        return habitService.sortByName();
    }
    
}