package flight.flight_system3.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import flight.flight_system3.model.AIRPORTSDTO;
import flight.flight_system3.service.AIRPORTSService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api/aIRPORTSs", produces = MediaType.APPLICATION_JSON_VALUE)
public class AIRPORTSController {

    private final AIRPORTSService aIRPORTSService;

    @Autowired
    public AIRPORTSController(final AIRPORTSService aIRPORTSService) {
        this.aIRPORTSService = aIRPORTSService;
    }

    @GetMapping
    public List<AIRPORTSDTO> getAllAIRPORTSs() {
        return aIRPORTSService.findAll();
    }

    @GetMapping("/{airportId}")
    public AIRPORTSDTO getAIRPORTS(@PathVariable final Integer airportId) {
        return aIRPORTSService.get(airportId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createAIRPORTS(@RequestBody @Valid final AIRPORTSDTO aIRPORTSDTO) {
        return aIRPORTSService.create(aIRPORTSDTO);
    }

   

}
