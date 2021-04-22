package com.example.demo.Trainee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TraineeService {

    private final TraineeRepository traineeRepository;

    @Autowired
    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public void addNewTrainee(Trainee trainee) {
        Optional<Trainee> traineeOptional = traineeRepository.findTraineesByEmail(trainee.getEmail());

        if (traineeOptional.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }

        traineeRepository.save(trainee);
    }

    public List<Trainee> getTrainees() {
        return traineeRepository.findAll();
    }

    public void deleteTrainee(Long traineeId) {
        boolean exists = traineeRepository.existsById(traineeId);

        if (!exists) {
            throw new IllegalStateException("Trainee with id " + traineeId + " doesn't exist.");
        }

        traineeRepository.deleteById(traineeId);
    }

    @Transactional
    public void updateTrainee(Long traineeId, String name, String email) {
        Trainee trainee = traineeRepository.findById(traineeId)
                .orElseThrow(() -> new IllegalStateException("Trainee with id " + traineeId + " doesn't exist."));

        // checking for a valid name
        if (name != null && name.length() > 0 && !Objects.equals(trainee.getName(), name)) {
            trainee.setName(name);
        }

        // checking for a valid email
        if (email != null && email.length() > 0 && !Objects.equals(trainee.getEmail(), email)) {
            // checking if the email is already taken
            Optional<Trainee> traineeOptional = traineeRepository.findTraineesByEmail(email);

            if (traineeOptional.isPresent()) {
                throw new IllegalStateException("Email already taken.");
            }

            trainee.setEmail(email);
        }

        traineeRepository.save(trainee);
    }
}
