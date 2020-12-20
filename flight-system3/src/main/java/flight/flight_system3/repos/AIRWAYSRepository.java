package flight.flight_system3.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import flight.flight_system3.domain.AIRWAYS;


public interface AIRWAYSRepository extends JpaRepository<AIRWAYS, Integer> {
    // add custom queries here
}
