package com.example.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    public Goal saveGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    public List<Goal> getAllGoals(String userId) {
        return goalRepository.findByUserId(userId);
    }

    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }

    public Goal updateGoal(Long id, Goal updatedGoal) {
        return goalRepository.findById(id).map(goal -> {
            goal.setName(updatedGoal.getName());
            goal.setTargetTime(updatedGoal.getTargetTime());
            goal.setColor(updatedGoal.getColor());
            return goalRepository.save(goal);
        }).orElse(null);
    }
}
