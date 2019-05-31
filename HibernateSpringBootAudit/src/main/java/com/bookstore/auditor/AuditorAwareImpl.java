package com.bookstore.auditor;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Leonard"); // use Spring Security to return the currently logged-in user
    }

}
