package flight.flight_system3.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;


@Getter
@Setter
public class TICKETSDTO {

    private Integer ticketid;

    @Size(max = 255)
    private String flightNumber;

    private Integer flys;

}
