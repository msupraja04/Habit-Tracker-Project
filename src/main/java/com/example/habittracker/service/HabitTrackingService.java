package com.example.habittracker.service;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.YearMonth;

import com.example.habittracker.dto.CalendarDayDTO;
import com.example.habittracker.model.HabitTracking;

import com.example.habittracker.dto.HeatmapDTO;
import com.example.habittracker.dto.MonthlyHistorySummary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.habittracker.model.Habit;
import com.example.habittracker.model.HabitTracking;
import com.example.habittracker.repository.HabitRepository;
import com.example.habittracker.repository.HabitTrackingRepository;
import java.util.List;
import com.example.habittracker.dto.Badge;

import com.example.habittracker.dto.Dashboard;
import java.time.YearMonth;
import com.example.habittracker.dto.HistorySummary;
import java.util.ArrayList;
import com.example.habittracker.dto.WeeklyHistorySummary;

import com.example.habittracker.dto.DateStatisticsDTO;
import com.example.habittracker.dto.WeeklyStatisticsDTO;
import com.example.habittracker.dto.MonthlyStatisticsDTO;
import com.example.habittracker.dto.OverallStatisticsDTO;
import com.example.habittracker.dto.MonthlyStats;
import com.example.habittracker.dto.BadgeDTO;
import com.example.habittracker.dto.DashboardHeroDTO;
import com.example.habittracker.model.Habit;
import com.example.habittracker.model.HabitTracking;
import com.example.habittracker.dto.ChallengeDTO;
import com.example.habittracker.dto.FavoriteHabitDTO;
import com.example.habittracker.model.Habit;
import java.util.HashMap;
import java.util.Map;
import java.time.DayOfWeek;
import com.example.habittracker.model.User;
import com.example.habittracker.repository.UserRepository;
@Service
public class HabitTrackingService {

    @Autowired
    private HabitTrackingRepository trackingRepository;

    @Autowired
    private HabitRepository habitRepository;
    
    @Autowired
    private UserRepository userRepository;

    public HabitTracking completeHabitToday(int habitId)
    {
        Habit habit =
            habitRepository.findById(habitId).orElse(null);

        if(habit != null)
        {
            LocalDate today = LocalDate.now();

            HabitTracking existingTracking =
                trackingRepository.findByHabitIdAndDate(
                    habitId,
                    today);

            if(existingTracking != null)
            {
                return existingTracking;
            }

            LocalDate yesterday =
                today.minusDays(1);

            if(habit.getLastCompletedDate() != null &&
               habit.getLastCompletedDate().equals(yesterday))
            {
                habit.setStreak(
                    habit.getStreak() + 1);
            }
            else
            {
                habit.setStreak(1);
            }

            habit.setLastCompletedDate(today);

            habitRepository.save(habit);

            HabitTracking tracking =
                new HabitTracking();

            tracking.setHabit(habit);

            tracking.setDate(today);

            tracking.setCompleted(true);

            tracking.setPointsEarned(10);

            return trackingRepository.save(tracking);
        }

        return null;
    }
    
    public Dashboard getDashboard(int userId) 
    { 
    	User user = userRepository.findById(userId);

    	if(user == null)
    	{
    	    return new Dashboard();
    	}

    	List<Habit> habits =
    	habitRepository.findByUser(user);
    	int currentStreak = 0;

    	for(Habit habit : habits)    	{
    	    if(habit.getStreak() > currentStreak)
    	    {
    	        currentStreak = habit.getStreak();
    	    }
    	}
    	List<HabitTracking> todayTracks = trackingRepository.findByHabitUserAndDate(
    	        user,
    	        LocalDate.now());
    	Dashboard dashboard = new Dashboard();
       //int totalHabits = habitRepository.findAll().size(); 
    	int totalHabits =
    			habits.size();
    	int completedHabits = todayTracks.size();
       int pendingHabits = totalHabits - completedHabits;
       int totalPoints = 0; 
       for(HabitTracking track : todayTracks)
      { 
	    totalPoints += track.getPointsEarned(); 
	  } 
      double progress = 0;
      if(totalHabits > 0)
      { 
    	 progress = ((double) completedHabits / totalHabits) * 100;
      }
       dashboard.setTotalHabits(totalHabits); 
       dashboard.setCompletedHabits(completedHabits);
       dashboard.setPendingHabits(pendingHabits);
       dashboard.setPointsToday(totalPoints); 
       dashboard.setProgressPercentage(progress);
       dashboard.setCurrentStreak(currentStreak);
       return dashboard; 
    }
    
    public List<HabitTracking> getTodayHistory(int userId)
    {
        User user = userRepository.findById(userId);

        return trackingRepository.findByHabitUserAndDate(
                user,
                LocalDate.now());
    }
    
    public MonthlyStats getMonthlyStats(int userId)
    {
    	User user =
    			userRepository.findById(userId);

    			if(user == null)
    			{
    			    return new MonthlyStats();
    			}
        YearMonth currentMonth = YearMonth.now();

        LocalDate startDate = currentMonth.atDay(1);

        LocalDate endDate = currentMonth.atEndOfMonth();

        List<HabitTracking> monthlyTracks =
        		trackingRepository.findByHabitUserAndDateBetween(
        		        user,
        		        startDate,
        		        endDate);

        MonthlyStats stats = new MonthlyStats();

        int completed = monthlyTracks.size();

        int totalPoints = 0;

        for(HabitTracking track : monthlyTracks)
        {
            totalPoints += track.getPointsEarned();
        }

        int totalHabits =
                habitRepository.findByUser(user).size();

        int pending =
                totalHabits - completed;

        double successRate = 0;

        if(totalHabits > 0)
        {
            successRate =
              ((double) completed / totalHabits) * 100;
        }

        stats.setCompletedHabits(completed);

        stats.setPendingHabits(pending);

        stats.setTotalPoints(totalPoints);

        stats.setSuccessRate(successRate);

        return stats;
    }
    
    public Badge getBadge(int habitId)
    {
        Habit habit =
          habitRepository.findById(habitId).orElse(null);

        Badge badge = new Badge();

        if(habit != null)
        {
            int streak = habit.getStreak();

            badge.setStreak(streak);

            if(streak >= 100)
            {
                badge.setBadgeName("Gold");
            }
            else if(streak >= 30)
            {
                badge.setBadgeName("Silver");
            }
            else if(streak >= 7)
            {
                badge.setBadgeName("Bronze");
            }
            else
            {
                badge.setBadgeName("No Badge");
            }
        }

        return badge;
    }
    
    public List<HabitTracking> getHistoryByDate
    (int userId,LocalDate date)
    {
    	User user =
    			userRepository.findById(userId);

    			if(user == null)
    			{
    			    return new ArrayList<>();
    			}
    	return trackingRepository.findByHabitUserAndDate(
    	        user,
    	        date);
    }
    
    public List<HabitTracking> getWeeklyHistory(int userId,
            LocalDate startDate,
            LocalDate endDate)
    {
    	User user = userRepository.findById(userId);

    	if(user == null)
    	{
    	    return new ArrayList<>();
    	}
    	return trackingRepository.findByHabitUserAndDateBetween(
    	        user,
    	        startDate,
    	        endDate);
    }

    public List<HabitTracking> getMonthlyHistory(int userId,
            int year,
            int month)
    {
    	User user = userRepository.findById(userId);

    	if(user == null)
    	{
    	    return new ArrayList<>();
    	}
        YearMonth yearMonth =
                YearMonth.of(year, month);

        LocalDate startDate =
                yearMonth.atDay(1);

        LocalDate endDate =
                yearMonth.atEndOfMonth();

        return trackingRepository.findByHabitUserAndDateBetween(
                user,
                startDate,
                endDate);
    }
    
    public HistorySummary getHistorySummary(int userId,LocalDate date)
    {
    	User user=userRepository.findById(userId);

    	List<Habit> allHabits=
    	habitRepository.findByUser(user);

        List<HabitTracking> completedTracks =
                trackingRepository.findByHabitUserAndDate(user,date);

        HistorySummary summary =
                new HistorySummary();

        summary.setTotalHabits(
                allHabits.size());

        summary.setCompletedHabits(
                completedTracks.size());

        summary.setPendingHabits(
                allHabits.size() -
                completedTracks.size());

        int totalPoints = 0;

        List<String> completedNames =
                new ArrayList<>();

        List<String> pendingNames =
                new ArrayList<>();

        for(HabitTracking track : completedTracks)
        {
            totalPoints +=
                    track.getPointsEarned();

            completedNames.add(
                    track.getHabit().getName());
        }

        for(Habit habit : allHabits)
        {
            boolean completed = false;

            for(HabitTracking track : completedTracks)
            {
                if(track.getHabit().getId()
                        == habit.getId())
                {
                    completed = true;
                    break;
                }
            }

            if(!completed)
            {
                pendingNames.add(
                        habit.getName());
            }
        }

        summary.setTotalPoints(
                totalPoints);

        summary.setCompletedHabitNames(
                completedNames);

        summary.setPendingHabitNames(
                pendingNames);

        return summary;
    }
    public WeeklyHistorySummary getWeeklyHistorySummary(int userId,
            LocalDate startDate,
            LocalDate endDate)
    {
    	User user=userRepository.findById(userId);
    	List<HabitTracking> tracks =
    	trackingRepository.findByHabitUserAndDateBetween(
    	user,
    	startDate,
    	endDate
    	); 

        WeeklyHistorySummary summary =
                new WeeklyHistorySummary();

        int totalPoints = 0;

        int totalHabitsPerDay =
                habitRepository.findByUser(user).size();

        List<String> dailySummary =
                new ArrayList<>();

        LocalDate current = startDate;

        while(!current.isAfter(endDate))
        {
            int completed = 0;
            int points = 0;

            for(HabitTracking track : tracks)
            {
                if(track.getDate().equals(current))
                {
                    completed++;
                    points += track.getPointsEarned();
                }
            }

            int pending =
                    totalHabitsPerDay - completed;

            dailySummary.add(
                current.getDayOfWeek() +
                " : " +
                completed + "/" +
                totalHabitsPerDay +
                " Completed | " +
                pending +
                " Pending | " +
                points +
                " Points"
            );

            totalPoints += points;

            current = current.plusDays(1);
        }

        int totalHabits =
                totalHabitsPerDay * 7;

        summary.setTotalHabits(
                totalHabits);

        summary.setCompletedHabits(
                tracks.size());

        summary.setPendingHabits(
                totalHabits -
                tracks.size());

        summary.setTotalPoints(
                totalPoints);

        summary.setDailySummary(
                dailySummary);

        return summary;
    }
    
    public MonthlyHistorySummary getMonthlyHistorySummary(int userId,
            int year,
            int month)
    {
    	User user=userRepository.findById(userId);
        YearMonth yearMonth =
                YearMonth.of(year, month);

        LocalDate startDate =
                yearMonth.atDay(1);

        LocalDate endDate =
                yearMonth.atEndOfMonth();

        List<HabitTracking> tracks =
                trackingRepository.findByHabitUserAndDateBetween(user,
                        startDate,
                        endDate);

        MonthlyHistorySummary summary =
                new MonthlyHistorySummary();

        int totalHabitsPerDay =
                habitRepository.findByUser(user).size();

        int daysInMonth =
                yearMonth.lengthOfMonth();

        int totalHabits =
                totalHabitsPerDay *
                daysInMonth;

        int totalPoints = 0;

        for(HabitTracking track : tracks)
        {
            totalPoints +=
                    track.getPointsEarned();
        }

        summary.setTotalHabits(
                totalHabits);

        summary.setCompletedHabits(
                tracks.size());

        summary.setPendingHabits(
                totalHabits -
                tracks.size());

        summary.setTotalPoints(
                totalPoints);

        return summary;
    }
    
    public DateStatisticsDTO getDateStatistics(int userId,LocalDate date)
    {
    	User user=userRepository.findById(userId);
        DateStatisticsDTO dto =
                new DateStatisticsDTO();

        int totalHabits =
                habitRepository.findByUser(user).size();

        List<HabitTracking> tracks =
                trackingRepository.findByHabitUserAndDate(user,date);

        int completed = tracks.size();

        int pending =
                totalHabits - completed;

        int points = 0;

        for(HabitTracking track : tracks)
        {
            points += track.getPointsEarned();
        }

        double averagePoints = 0;

        if(completed > 0)
        {
            averagePoints =
                    (double) points / completed;
        }

        double progress = 0;

        if(totalHabits > 0)
        {
            progress =
                    ((double) completed /
                    totalHabits) * 100;
        }

        dto.setCompleted(completed);
        dto.setPending(pending);
        dto.setPoints(points);
        dto.setAveragePoints(averagePoints);
        dto.setProgress(progress);

        return dto;
    }
    
    public WeeklyStatisticsDTO getWeeklyStatistics(int userId,
            LocalDate startDate,
            LocalDate endDate)
    {
    	User user=userRepository.findById(userId);
        WeeklyStatisticsDTO dto =
                new WeeklyStatisticsDTO();

        List<HabitTracking> tracks =
                trackingRepository.findByHabitUserAndDateBetween(user,
                        startDate,
                        endDate);
        
        int[] dailyCounts = new int[7];

        for(HabitTracking track : tracks)
        {
            int dayIndex =
                    track.getDate()
                    .getDayOfWeek()
                    .getValue() - 1;

            dailyCounts[dayIndex]++;
        }

        int completed = tracks.size();

        int points = 0;

        for(HabitTracking track : tracks)
        {
            points += track.getPointsEarned();
        }

        int totalHabits =
                habitRepository.findByUser(user).size();

        long days =
                java.time.temporal.ChronoUnit.DAYS
                .between(startDate, endDate) + 1;

        int expectedHabits =
                totalHabits * (int)days;

        int pending =
                expectedHabits - completed;

        double averagePerDay = 0;

        if(days > 0)
        {
            averagePerDay =
                    (double) points / days;
        }

        double progress = 0;

        if(expectedHabits > 0)
        {
            progress =
                    ((double) completed /
                    expectedHabits) * 100;
        }

        dto.setCompleted(completed);
        dto.setPending(pending);
        dto.setPoints(points);
        dto.setAveragePerDay(averagePerDay);
        dto.setProgress(progress);
        dto.setDailyCounts(dailyCounts);

        return dto;
    }
    
    public MonthlyStatisticsDTO getMonthlyStatistics(int userId,
            int year,
            int month)
    {
    	User user=userRepository.findById(userId);
        MonthlyStatisticsDTO dto =
                new MonthlyStatisticsDTO();

        YearMonth yearMonth =
                YearMonth.of(year, month);

        LocalDate startDate =
                yearMonth.atDay(1);

        LocalDate endDate =
                yearMonth.atEndOfMonth();

        List<HabitTracking> tracks =
                trackingRepository.findByHabitUserAndDateBetween(user,
                        startDate,
                        endDate);
        int[] weeklyPoints = new int[5];

        for(HabitTracking track : tracks)
        {
            int day =
                    track.getDate().getDayOfMonth();

            int weekIndex =
                    (day - 1) / 7;

            if(weekIndex > 4)
            {
                weekIndex = 4;
            }

            weeklyPoints[weekIndex] +=
                    track.getPointsEarned();
        }

        int completed = tracks.size();

        int points = 0;

        for(HabitTracking track : tracks)
        {
            points += track.getPointsEarned();
        }

        int totalHabits =
                habitRepository.findByUser(user).size();

        int daysInMonth =
                yearMonth.lengthOfMonth();

        int expectedHabits =
                totalHabits * daysInMonth;

        int pending =
                expectedHabits - completed;

        double averageDailyPoints = 0;

        if(daysInMonth > 0)
        {
            averageDailyPoints =
                    (double) points / daysInMonth;
        }

        double progress = 0;

        if(expectedHabits > 0)
        {
            progress =
                    ((double) completed /
                    expectedHabits) * 100;
        }

        int highestStreak = 0;

        for(Habit habit : habitRepository.findByUser(user))
        {
            if(habit.getStreak() > highestStreak)
            {
                highestStreak =
                        habit.getStreak();
            }
        }

        dto.setCompleted(completed);
        dto.setPending(pending);
        dto.setPoints(points);
        dto.setHighestStreak(highestStreak);
        dto.setAverageDailyPoints(
                averageDailyPoints);
        dto.setProgress(progress);
        dto.setWeeklyPoints(
                weeklyPoints);

        return dto;
    }
    
    public OverallStatisticsDTO getOverallStatistics(int userId)
    {
        OverallStatisticsDTO dto =
                new OverallStatisticsDTO();

        User user =
        		userRepository.findById(userId);
        if(user == null)
        {
            return new OverallStatisticsDTO();
        }

        		List<Habit> habits =
        		habitRepository.findByUser(user);
        		int totalHabits =
                		habits.size();

        List<HabitTracking> allTracks =
        		trackingRepository.findByHabitUser(user);
        int totalCompletions =
                allTracks.size();
        int pending = 0;

        if(totalCompletions < totalHabits)
        {
            pending =
                    totalHabits -
                    totalCompletions;
        }

        int totalPoints = 0;

        for(HabitTracking track : allTracks)
        {
            totalPoints += track.getPointsEarned();
        }

        int currentStreak = 0;
        int highestStreak = 0;

        for(Habit habit : habits)
        {
            if(habit.getStreak() > currentStreak)
            {
                currentStreak =
                        habit.getStreak();
            }

            if(habit.getStreak() > highestStreak)
            {
                highestStreak =
                        habit.getStreak();
            }
        }

        double averagePoints = 0;

        if(totalCompletions > 0)
        {
            averagePoints =
                    (double) totalPoints /
                    totalCompletions;
        }
        
        double completionRate = 0;

        int totalPossible =
                totalCompletions +
                totalHabits;

        if(totalPossible > 0)
        {
            completionRate =
                    ((double) totalCompletions /
                     totalPossible) * 100;
        }
        dto.setTotalHabits(totalHabits);
        dto.setTotalCompletions(totalCompletions);
        dto.setTotalPoints(totalPoints);
        dto.setCurrentStreak(currentStreak);
        dto.setHighestStreak(highestStreak);
        dto.setAveragePointsPerCompletion(
                averagePoints);
        dto.setPending(
                pending);
        dto.setCompletionRate(
                completionRate);

        return dto;
    }
    
    public List<BadgeDTO> getAllBadges(int userId)
    {
    	User user =
    			userRepository.findById(userId);

    			if(user == null)
    			{
    			    return new ArrayList<>();
    			}
        List<BadgeDTO> badges =
                new ArrayList<>();

        int highestStreak = 0;

        int totalPoints = 0;

        for(Habit habit :
        	habitRepository.findByUser(user))
        {
            if(habit.getStreak() >
                    highestStreak)
            {
                highestStreak =
                        habit.getStreak();
            }
        }

        for(HabitTracking track :
        	trackingRepository.findByHabitUser(user))
        {
            totalPoints +=
                    track.getPointsEarned();
        }

        BadgeDTO b1 =
                new BadgeDTO();

        b1.setBadgeName(
                "🏅 First Step");

        b1.setDescription(
                "Complete First Habit");

        b1.setTarget(1);

        b1.setProgress(
                trackingRepository.findByHabitUser(user)
                .size());

        b1.setUnlocked(
                b1.getProgress() >= 1);

        badges.add(b1);

        BadgeDTO b2 =
                new BadgeDTO();

        b2.setBadgeName(
                "🔥 3 Day Streak");

        b2.setDescription(
                "Maintain 3 Day Streak");

        b2.setTarget(3);

        b2.setProgress(
                highestStreak);

        b2.setUnlocked(
                highestStreak >= 3);

        badges.add(b2);

        BadgeDTO b3 =
                new BadgeDTO();

        b3.setBadgeName(
                "⚡ 7 Day Streak");

        b3.setDescription(
                "Maintain 7 Day Streak");

        b3.setTarget(7);

        b3.setProgress(
                highestStreak);

        b3.setUnlocked(
                highestStreak >= 7);

        badges.add(b3);

        BadgeDTO b4 =
                new BadgeDTO();

        b4.setBadgeName(
                "🏆 Consistency King");

        b4.setDescription(
                "Maintain 15 Day Streak");

        b4.setTarget(15);

        b4.setProgress(
                highestStreak);

        b4.setUnlocked(
                highestStreak >= 15);

        badges.add(b4);

        BadgeDTO b5 =
                new BadgeDTO();

        b5.setBadgeName(
                "⭐ Point Collector");

        b5.setDescription(
                "Earn 500 Points");

        b5.setTarget(500);

        b5.setProgress(
                totalPoints);

        b5.setUnlocked(
                totalPoints >= 500);

        badges.add(b5);

        return badges;
    }
    
    public DashboardHeroDTO getDashboardHero(int userId)
    {
    	User user =
    			userRepository.findById(userId);

    			if(user == null)
    			{
    			    return new DashboardHeroDTO();
    			}

    			List<Habit> habits =
    			habitRepository.findByUser(user);

    			List<HabitTracking> tracks =
    			trackingRepository.findByHabitUser(user);

    			List<HabitTracking> todayTracks =
    			trackingRepository.findByHabitUserAndDate(
    			        user,
    			        LocalDate.now());
        DashboardHeroDTO dto =
                new DashboardHeroDTO();

        int currentStreak = 0;

        for(Habit habit : habits)
        {
            if(habit.getStreak() > currentStreak)
            {
                currentStreak =
                        habit.getStreak();
            }
        }

        int totalPoints = 0;

        for(HabitTracking track :
                tracks)
        {
            totalPoints +=
                    track.getPointsEarned();
        }

        int totalHabits =
                habits.size();

        int completedToday =
                todayTracks.size();;

        int pendingHabits =
                totalHabits -
                completedToday;

        if(pendingHabits < 0)
        {
            pendingHabits = 0;
        }

        int badgesEarned = 0;

        if(currentStreak >= 1) badgesEarned++;
        if(currentStreak >= 3) badgesEarned++;
        if(currentStreak >= 7) badgesEarned++;
        if(totalPoints >= 500) badgesEarned++;

        dto.setCurrentStreak(currentStreak);
        dto.setTotalPoints(totalPoints);
        dto.setPendingHabits(pendingHabits);
        dto.setBadgesEarned(badgesEarned);
        if(currentStreak > 0)
        {
            dto.setStreakMessage(
            "🔥 You are on a "
            + currentStreak +
            " day streak");
        }

        if(pendingHabits > 0)
        {
            dto.setPendingMessage(
            "⚠️ " + pendingHabits +
            " habit(s) pending today");
        }

        dto.setNextBadgeMessage(
        "🎯 Only "
        + (7 - currentStreak)
        + " days left for 7 Day Badge"
        );

        return dto;
    }
    
    public List<HeatmapDTO> getHeatmap(int userId)
    {
    	User user=userRepository.findById(userId);
        List<HeatmapDTO> heatmap =
                new ArrayList<>();

        List<HabitTracking> history =
                trackingRepository.findByHabitUser(user);

        for(HabitTracking h : history)
        {
            HeatmapDTO dto =
                    new HeatmapDTO();

            dto.setDate(
                    h.getDate()
                     .toString()
            );

            dto.setCompleted(true);

            heatmap.add(dto);
        }

        return heatmap;
    }
    
    public List<CalendarDayDTO> getCalendarData(int userId,
            int year,
            int month)
    {
    	User user=userRepository.findById(userId);
        List<CalendarDayDTO> calendar =
                new ArrayList<>();

        YearMonth yearMonth =
                YearMonth.of(year, month);

        LocalDate startDate =
                yearMonth.atDay(1);

        LocalDate endDate =
                yearMonth.atEndOfMonth();

        List<HabitTracking> tracks =
                trackingRepository.findByHabitUserAndDateBetween(user,
                        startDate,
                        endDate);

        int totalHabits =
                habitRepository.findByUser(user).size();

        LocalDate current = startDate;

        while(!current.isAfter(endDate))
        {
            CalendarDayDTO dto =
                    new CalendarDayDTO();

            int completed = 0;
            int points = 0;
            
            List<String> completedHabitNames =
                    new ArrayList<>();

            List<String> pendingHabitNames =
                    new ArrayList<>();

            for(HabitTracking track : tracks)
            {
                if(track.getDate().equals(current))
                {
                    completed++;
                    points += track.getPointsEarned();
                    completedHabitNames.add(
                    		track.getHabit().getName());
                }
            }

            for(Habit habit :
            	habitRepository.findByUser(user))
            	{
            	    if(!completedHabitNames.contains(
            	    habit.getName()))
            	    {
            	        pendingHabitNames.add(
            	        habit.getName());
            	    }
            	}
            double completionRate = 0;

            if(totalHabits > 0)
            {
                completionRate =
                        ((double) completed /
                         totalHabits) * 100;
            }

            dto.setDate(current.toString());
            dto.setTotalHabits(totalHabits);
            dto.setCompletedHabits(completed);
            dto.setPointsEarned(points);
            dto.setCompletionRate((int) completionRate);
            dto.setCompletedHabitNames(
            		completedHabitNames);

            		dto.setPendingHabitNames(
            		pendingHabitNames);

            		dto.setStreakMaintained(
            		completed == totalHabits);
            calendar.add(dto);

            current = current.plusDays(1);
        }

        return calendar;
    }
    
    public ChallengeDTO getChallenges(int userId)
    {
        ChallengeDTO dto = new ChallengeDTO();

        Dashboard dashboard = new Dashboard();
        dashboard.setTotalHabits(
        		habitRepository.findByUser(
        		        userRepository.findById(userId)
        		).size()
        		);

        		dashboard.setCompletedHabits(
        				trackingRepository
        				.findByHabitUserAndDate(
        				userRepository.findById(userId),
        				LocalDate.now()
        				).size()
        		);

        dto.setDailyCompleted(
                dashboard.getCompletedHabits());

        dto.setDailyTarget(
                dashboard.getTotalHabits());

        LocalDate today = LocalDate.now();

        LocalDate weekStart =
                today.with(DayOfWeek.MONDAY);

        LocalDate weekEnd =
                today.with(DayOfWeek.SUNDAY);

        List<HabitTracking> weekly =
                getWeeklyHistory(userId,
                        weekStart,
                        weekEnd);

        dto.setWeeklyCompleted(
                weekly.size());

        dto.setWeeklyTarget(
                dashboard.getTotalHabits() * 7);

        List<HabitTracking> monthly =
                getMonthlyHistory(userId,
                        today.getYear(),
                        today.getMonthValue());

        dto.setMonthlyCompleted(
                monthly.size());

        int daysInMonth =
                today.lengthOfMonth();

        dto.setMonthlyTarget(
                dashboard.getTotalHabits()
                * daysInMonth);

        return dto;
    }
    
    public FavoriteHabitDTO getFavoriteHabit(int userId)
    {
    	User user=userRepository.findById(userId);
        FavoriteHabitDTO dto =
                new FavoriteHabitDTO();

        List<HabitTracking> trackingList =
                trackingRepository.findByHabitUser(user);

        Map<String,Integer> habitCount =
                new HashMap<>();

        for(HabitTracking tracking : trackingList)
        {
            if(tracking.getCompleted())
            {
                String habitName =
                        tracking.getHabit()
                        .getName();

                habitCount.put(
                        habitName,
                        habitCount.getOrDefault(
                                habitName,
                                0) + 1);
            }
        }

        String favoriteHabit = "-";
        int max = 0;

        for(Map.Entry<String,Integer> entry :
                habitCount.entrySet())
        {
            if(entry.getValue() > max)
            {
                max = entry.getValue();
                favoriteHabit =
                        entry.getKey();
            }
        }

        dto.setHabitName(
                favoriteHabit);

        dto.setCompletedCount(
                max);

        if(!trackingList.isEmpty())
        {
            dto.setConsistency(
                    (max * 100.0) /
                    trackingList.size());
        }
        else
        {
            dto.setConsistency(0);
        }

        return dto;
    }
}