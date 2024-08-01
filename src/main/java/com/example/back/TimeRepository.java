package com.example.back;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {
    List<Time> findByDate(LocalDate date);
    List<Time> findByGoalName(String goalName);

    List<Time> findByDateBetween(LocalDate startDate, LocalDate endDate);
}