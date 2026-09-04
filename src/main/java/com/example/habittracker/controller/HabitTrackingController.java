package com.example.habittracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.habittracker.dto.HeatmapDTO;
import com.example.habittracker.dto.MonthlyHistorySummary;
import org.springframework.web.bind.annotation.*;
import com.example.habittracker.dto.CalendarDayDTO;

import com.example.habittracker.dto.Dashboard;
import com.example.habittracker.model.HabitTracking;
import com.example.habittracker.service.HabitTrackingService;
import com.example.habittracker.dto.Badge;
import java.time.LocalDate;
import com.example.habittracker.dto.WeeklyHistorySummary;
import com.example.habittracker.dto.HistorySummary;
import com.example.habittracker.dto.MonthlyStats;
import com.example.habittracker.dto.DateStatisticsDTO;
import com.example.habittracker.dto.WeeklyStatisticsDTO;
import com.example.habittracker.dto.MonthlyStatisticsDTO;
import com.example.habittracker.dto.OverallStatisticsDTO;
import com.example.habittracker.dto.BadgeDTO;
import com.example.habittracker.dto.DashboardHeroDTO;
import com.example.habittracker.dto.ChallengeDTO;
import com.example.habittracker.dto.FavoriteHabitDTO;
@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/tracking")
public class HabitTrackingController {

    @Autowired
    private HabitTrackingService trackingService;

    @PostMapping("/complete/{habitId}/{userId}")
    public HabitTracking completeHabitToday(@PathVariable int habitId,@PathVariable int userId)
    {
        return trackingService.completeHabitToday(habitId);
    }
    
    @GetMapping("/dashboard/{userId}")
    public Dashboard getDashboard(
    @PathVariable int userId)
    {
        return trackingService.getDashboard(userId);
    }
    
    @GetMapping("/today/{userId}")
    public List<HabitTracking> getTodayHistory(@PathVariable int userId)
    {
        return trackingService.getTodayHistory(userId);
    }
    
    @GetMapping("/monthly/{userId}")
    public MonthlyStats getMonthlyStats(
    @PathVariable int userId)
    {
        return trackingService.getMonthlyStats(userId);
    }
    
    @GetMapping("/badge/{habitId}")
    public Badge getBadge(@PathVariable int habitId)
    {
        return trackingService.getBadge(habitId);
    }
    
    @GetMapping("/history/{userId}/{date}")
    public List<HabitTracking> getHistoryByDate(
    @PathVariable int userId,
    @PathVariable String date)
    {
        return trackingService.getHistoryByDate(
                userId,
                LocalDate.parse(date));
    }
    
    @GetMapping("/weekly-history/{userId}")
    public List<HabitTracking> getWeeklyHistory(
    @PathVariable int userId,
    @RequestParam String start,
    @RequestParam String end)
    {
        return trackingService.getWeeklyHistory(
                userId,
                LocalDate.parse(start),
                LocalDate.parse(end));
    }

    @GetMapping("/monthly-history/{userId}")
    public List<HabitTracking> getMonthlyHistory(@PathVariable int userId,
            @RequestParam int year,
            @RequestParam int month)
    {
        return trackingService.getMonthlyHistory(userId,
                year,
                month);
    }
    
    @GetMapping("/history-summary/{userId}/{date}")
    public HistorySummary getHistorySummary(@PathVariable int userId,

@PathVariable String date)
    {
        return trackingService
                .getHistorySummary(userId,
                        LocalDate.parse(date));
    }
    
    @GetMapping("/weekly-summary/{userId}")
    public WeeklyHistorySummary getWeeklySummary(@PathVariable int userId,
            @RequestParam String start,
            @RequestParam String end)
    {
        return trackingService
                .getWeeklyHistorySummary(userId,
                        LocalDate.parse(start),
                        LocalDate.parse(end));
    }
    
    @GetMapping("/monthly-summary/{userId}")
    public MonthlyHistorySummary getMonthlySummary(@PathVariable int userId,
            @RequestParam int year,
            @RequestParam int month)
    {
        return trackingService
                .getMonthlyHistorySummary(userId,
                        year,
                        month);
    }
    
    @GetMapping("/statistics/date/{userId}/{date}")
    public DateStatisticsDTO getDateStatistics(@PathVariable int userId,
            @PathVariable String date)
    {
        return trackingService
                .getDateStatistics(userId,
                        LocalDate.parse(date));
    }
    
    @GetMapping("/statistics/weekly/{userId}")
    public WeeklyStatisticsDTO getWeeklyStatistics(@PathVariable int userId,
            @RequestParam String start,
            @RequestParam String end)
    {
        return trackingService
                .getWeeklyStatistics(userId,
                        LocalDate.parse(start),
                        LocalDate.parse(end));
    }
    
    @GetMapping("/statistics/monthly/{userId}")
    public MonthlyStatisticsDTO getMonthlyStatistics(@PathVariable int userId,
            @RequestParam int year,
            @RequestParam int month)
    {
        return trackingService
                .getMonthlyStatistics(userId,
                        year,
                        month);
    }
    
    @GetMapping("/statistics/overall/{userId}")
    public OverallStatisticsDTO getOverallStatistics(
    @PathVariable int userId)
    {
        return trackingService.getOverallStatistics(userId);
    }
    
    @GetMapping("/badges/all/{userId}")
    public List<BadgeDTO> getAllBadges(
    @PathVariable int userId)
    {
        return trackingService.getAllBadges(userId);
    }
    
    @GetMapping("/dashboard-hero/{userId}")
    public DashboardHeroDTO getDashboardHero(@PathVariable int userId)
    {
        return trackingService
                .getDashboardHero(userId);
    }
    
    @GetMapping("/heatmap/{userId}")
    public List<HeatmapDTO> getHeatmap(@PathVariable int userId)
    {
        return trackingService.getHeatmap(userId);
    }
    
    @GetMapping("/calendar/{userId}/{year}/{month}")
    public List<CalendarDayDTO>
    getCalendarData(@PathVariable int userId,
    @PathVariable int year,
    @PathVariable int month)
    {
        return trackingService
        .getCalendarData(userId,
        year,
        month);
    }
    
    @GetMapping("/challenges/{userId}")
    public ChallengeDTO getChallenges(@PathVariable int userId)
    {
        return trackingService.getChallenges(userId);
    }
    
    @GetMapping("/favorite-habit/{userId}")
    public FavoriteHabitDTO getFavoriteHabit(@PathVariable int userId)
    {
        return trackingService.getFavoriteHabit(userId);
    }
}