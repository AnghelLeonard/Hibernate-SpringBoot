package com.app.service;

import com.app.entity.UserBad;
import com.app.entity.UserGood;
import com.app.repository.UserBadRepository;
import com.app.repository.UserGoodRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserBadRepository userBadRepository;
    private final UserGoodRepository userGoodRepository;

    public UserService(UserBadRepository userBadRepository, UserGoodRepository userGoodRepository) {
        this.userBadRepository = userBadRepository;
        this.userGoodRepository = userGoodRepository;
    }

    public void persistBadUser() {
        UserBad userBad = new UserBad();
        userBad.setName("Bad User");
        userBad.setCity("Bad City");
        userBad.setAge(20);

        userBadRepository.save(userBad);
    }

    public void persistGoodUser() {
        UserGood userGood = new UserGood();
        userGood.setName("Good User");
        userGood.setCity("Good City");
        userGood.setAge(20);

        userGoodRepository.save(userGood);
    }
}
