package com.example.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin(origins = "http://localhost:3000")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @PostMapping
    public Goal saveGoal(@RequestBody Goal goal) {
        return goalService.saveGoal(goal);
    }

    @GetMapping
    public List<Goal> getAllGoals() {
        return goalService.getAllGoals();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Goal> updateGoal(@PathVariable Long id, @RequestBody Goal updatedGoal) {
    Goal goal = goalService.updateGoal(id, updatedGoal);
    if (goal != null) {
        return ResponseEntity.ok(goal);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    }
}

