package flight.flight_system3.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import flight.flight_system3.domain.FLIGHTS;


public interface FLIGHTSRepository extends JpaRepository<FLIGHTS, Integer> {
    // add custom queries here
}
