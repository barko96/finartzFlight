package flight.flight_system3.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Setter
@Getter
public class AIRWAYS {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "primary_sequence", sequenceName = "primary_sequence",
            allocationSize = 1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_sequence")
    private Integer airwayID;

    @Column
    private String airwayName;

    @OneToMany(mappedBy = "fly", targetEntity = FLIGHTS.class)
    private Set<FLIGHTS> fLIGHTSFlys;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airtoport_id")
    private AIRPORTS airtoport;


}
