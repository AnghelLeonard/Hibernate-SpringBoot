package com.jpa.ds2;

import org.springframework.stereotype.Service;

@Service
public class CoachService {
    
    private final CoachRepository coachRepository;

    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public Coach saveOneCoach() {
        Coach coach = new Coach();

        coach.setId(1L);
        coach.setName("Toni Nadal");

        return coachRepository.save(coach);        
    }
}
