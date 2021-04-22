package com.example.demo.Trainee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/trainees")
public class TraineeController {

    private final TraineeService traineeService;

    // dependency injection via spring bean
    @Autowired
    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping
    public List<Trainee> getTrainees() {
        return traineeService.getTrainees();
    }

    // using mapping via RequestBody
    @PostMapping
    public void registerNewTrainee(@RequestBody Trainee trainee) {
        traineeService.addNewTrainee(trainee);
    }

    @DeleteMapping(path = "{traineeId}")
    public void deleteTrainee(@PathVariable("traineeId") Long traineeId) {
        traineeService.deleteTrainee(traineeId);
    }

    @PutMapping(path = "{traineeId}")
    public void updateTrainee(
            @PathVariable("traineeId") Long traineeId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        traineeService.updateTrainee(traineeId, name, email);
    }
}
