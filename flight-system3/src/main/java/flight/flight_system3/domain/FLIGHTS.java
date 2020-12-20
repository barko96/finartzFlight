package flight.flight_system3.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Setter
@Getter
public class FLIGHTS {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "primary_sequence", sequenceName = "primary_sequence",
            allocationSize = 1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_sequence")
    private Integer fid;

    @Column
    private String departure;

    @Column
    private String destination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fly_id")
    private AIRWAYS fly;

    @OneToMany(mappedBy = "flys", targetEntity = TICKETS.class)
    private Set<TICKETS> tICKETSFlyss;


}
