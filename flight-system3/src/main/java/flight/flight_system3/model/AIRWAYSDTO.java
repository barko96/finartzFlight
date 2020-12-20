package flight.flight_system3.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;


@Getter
@Setter
public class AIRWAYSDTO {

    private Integer airwayID;

    @Size(max = 255)
    private String airwayName;

    private Integer airtoport;

}
