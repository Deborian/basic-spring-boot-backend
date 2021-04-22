package com.example.demo.Trainee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// trainee id is Long, that's why where are using <Trainee, Long> here
@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long> {
    // the query is optional and can be left out
    // @Query("SELECT t FROM Trainee t WHERE t.email = ?1")
    Optional<Trainee> findTraineesByEmail(String email);
}
