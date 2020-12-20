package flight.flight_system3.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import flight.flight_system3.domain.AIRPORTS;


public interface AIRPORTSRepository extends JpaRepository<AIRPORTS, Integer> {
    // add custom queries here
}
