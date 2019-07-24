package com.bookstore.auditor;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // use Spring Security to retrive the current logged-in user(s)
        return Optional.of(Arrays.asList("mark1990", "adrianm", "dan555")
                .get(new Random().nextInt(3)));
    }

}
