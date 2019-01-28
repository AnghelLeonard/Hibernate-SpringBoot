package com.jpa;

import java.util.stream.Stream;
import javax.persistence.QueryHint;
import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("select u from User u")
    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE))
    Stream<User> streamAll();
}
