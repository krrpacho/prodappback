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
    public ResponseEntity<Time> createTime(@RequestBody Time time) {
        Time savedTime = timeService.saveTime(time);
        return ResponseEntity.ok(savedTime);
    }

    @GetMapping
    public ResponseEntity<List<Time>> getTimes(@RequestParam String userId) {
        List<Time> times = timeService.getAllTimes(userId);
        return ResponseEntity.ok(times);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable Long id) {
        timeService.deleteTime(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/weekly-summary")
    public ResponseEntity<Map<String, Long>> getWeeklySummary(@RequestParam int weeksAgo, @RequestParam String userId) {
        Map<String, Long> summary = timeService.getWeeklySummary(weeksAgo, userId);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/monthly-summary")
    public ResponseEntity<Map<String, Long>> getMonthlySummary(@RequestParam int monthsAgo, @RequestParam String userId) {
        Map<String, Long> summary = timeService.getMonthlySummary(monthsAgo, userId);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/yearly-summary")
    public ResponseEntity<Map<String, Long>> getYearlySummary(@RequestParam int yearsAgo, @RequestParam String userId) {
        Map<String, Long> summary = timeService.getYearlySummary(yearsAgo, userId);
        return ResponseEntity.ok(summary);
    }
}