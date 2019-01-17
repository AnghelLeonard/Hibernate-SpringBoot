package com.jpa;

import java.util.List;
import javax.persistence.QueryHint;
import static org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    
    @Transactional(readOnly=true)
    @QueryHints(value = @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false"))
    @Query(value = "SELECT DISTINCT t FROM Tournament t JOIN FETCH t.tennisPlayers p")
    List<Tournament> fetchAllTournamentsAndPlayers();
}
