package flight.flight_system3.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import flight.flight_system3.domain.TICKETS;


public interface TICKETSRepository extends JpaRepository<TICKETS, Integer> {
    // add custom queries here
}
