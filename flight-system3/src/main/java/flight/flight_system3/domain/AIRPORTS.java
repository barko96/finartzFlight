package flight.flight_system3.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity

@Getter
@Setter
public class AIRPORTS {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "primary_sequence", sequenceName = "primary_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_sequence")
    private Integer airportId;

    @Column
    private String name;

    @OneToMany(mappedBy = "airtoport", targetEntity = AIRWAYS.class)
    private Set<AIRWAYS> aIRWAYSAirtoports;


}
