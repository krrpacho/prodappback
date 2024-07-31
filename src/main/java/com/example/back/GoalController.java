package com.example.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin(origins = "https://krrpacho.github.io")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @PostMapping
    public ResponseEntity<Goal> createGoal(@RequestBody Goal goal) {
        Goal savedGoal = goalService.saveGoal(goal);
        return ResponseEntity.ok(savedGoal);
    }

    @GetMapping
    public ResponseEntity<List<Goal>> getGoals(@RequestParam String userId) {
        List<Goal> goals = goalService.getAllGoals(userId);
        return ResponseEntity.ok(goals);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Goal> updateGoal(@PathVariable Long id, @RequestBody Goal goal) {
        Goal updatedGoal = goalService.updateGoal(id, goal);
        return updatedGoal != null ? ResponseEntity.ok(updatedGoal) : ResponseEntity.notFound().build();
    }
}