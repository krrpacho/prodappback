package com.example.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/times")
@CrossOrigin(origins = "https://krrpacho.github.io")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @PostMapping
    public Time saveTime(@RequestBody Time time) {
        return timeService.saveTime(time);
    }

    @GetMapping
    public List<Time> getAllTimes() {
        return timeService.getAllTimes();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable Long id) {
        timeService.deleteTime(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/weekly-summary")
    public Map<String, Long> getWeeklySummary(@RequestParam(defaultValue = "0") int weeksAgo) {
        return timeService.getWeeklySummary(weeksAgo);
    }

    @GetMapping("/monthly-summary")
    public Map<String, Long> getMonthlySummary(@RequestParam(defaultValue = "0") int monthsAgo) {
        return timeService.getMonthlySummary(monthsAgo);
    }

    @GetMapping("/yearly-summary")
    public Map<String, Long> getYearlySummary(@RequestParam(defaultValue = "0") int yearsAgo) {
    return timeService.getYearlySummary(yearsAgo);
    }
}