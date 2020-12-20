package flight.flight_system3.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;


@Getter
@Setter
public class FLIGHTSDTO {

    private Integer fid;

    @Size(max = 255)
    private String departure;

    @Size(max = 255)
    private String destination;

    private Integer fly;

}
