package com.example.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    public Time saveTime(Time time) {
        return timeRepository.save(time);
    }

    public List<Time> getAllTimes(String userId) {
        return timeRepository.findByUserId(userId);
    }

    public void deleteTime(Long id) {
        timeRepository.deleteById(id);
    }

    public Map<String, Long> getWeeklySummary(int weeksAgo, String userId) {
        LocalDate endDate = LocalDate.now().minusWeeks(weeksAgo);
        LocalDate startDate = endDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        endDate = endDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        List<Time> times = timeRepository.findByDateBetweenAndUserId(startDate, endDate, userId);

        Map<String, Long> summary = new HashMap<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            summary.put(date.getDayOfWeek().toString(), 0L);
        }

        for (Time time : times) {
            LocalDate date = time.getDate();
            long elapsedTime = parseElapsedTime(time.getElapsedTime());
            summary.put(date.getDayOfWeek().toString(), summary.get(date.getDayOfWeek().toString()) + elapsedTime);
        }

        return summary;
    }

    public Map<String, Long> getMonthlySummary(int monthsAgo, String userId) {
        LocalDate startDate = LocalDate.now().minusMonths(monthsAgo).withDayOfMonth(1);
        LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
        List<Time> times = timeRepository.findByDateBetweenAndUserId(startDate, endDate, userId);

        Map<String, Long> summary = new HashMap<>();
        summary.put("1-8", 0L);
        summary.put("9-16", 0L);
        summary.put("17-23", 0L);
        summary.put("24-31", 0L);

        for (Time time : times) {
            LocalDate date = time.getDate();
            long elapsedTime = parseElapsedTime(time.getElapsedTime());
            if (date.getDayOfMonth() <= 8) {
                summary.put("1-8", summary.get("1-8") + elapsedTime);
            } else if (date.getDayOfMonth() <= 16) {
                summary.put("9-16", summary.get("9-16") + elapsedTime);
            } else if (date.getDayOfMonth() <= 23) {
                summary.put("17-23", summary.get("17-23") + elapsedTime);
            } else {
                summary.put("24-31", summary.get("24-31") + elapsedTime);
            }
        }

        return summary;
    }

    private long parseElapsedTime(String elapsedTime) {
        String[] parts = elapsedTime.split("m ");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1].replace("s", ""));
        return minutes * 60 + seconds;
    }

    public Map<String, Long> getYearlySummary(int yearsAgo, String userId) {
        LocalDate startDate = LocalDate.now().minusYears(yearsAgo).withDayOfYear(1);
        LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfYear());
        List<Time> times = timeRepository.findByDateBetweenAndUserId(startDate, endDate, userId);
    
        Map<String, Long> summary = new HashMap<>();
        for (int i = 1; i <= 12; i++) {
            summary.put(String.format("%02d", i), 0L);
        }
    
        for (Time time : times) {
            LocalDate date = time.getDate();
            long elapsedTime = parseElapsedTime(time.getElapsedTime());
            int month = date.getMonthValue();
            summary.put(String.format("%02d", month), summary.get(String.format("%02d", month)) + elapsedTime);
        }
    
        return summary;
    }
}
