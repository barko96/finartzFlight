package flight.flight_system3.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import flight.flight_system3.model.AIRWAYSDTO;
import flight.flight_system3.service.AIRWAYSService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api/aIRWAYSs", produces = MediaType.APPLICATION_JSON_VALUE)
public class AIRWAYSController {

    private final AIRWAYSService aIRWAYSService;

    @Autowired
    public AIRWAYSController(final AIRWAYSService aIRWAYSService) {
        this.aIRWAYSService = aIRWAYSService;
    }

    @GetMapping
    public List<AIRWAYSDTO> getAllAIRWAYSs() {
        return aIRWAYSService.findAll();
    }

    @GetMapping("/{airwayID}")
    public AIRWAYSDTO getAIRWAYS(@PathVariable final Integer airwayID) {
        return aIRWAYSService.get(airwayID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createAIRWAYS(@RequestBody @Valid final AIRWAYSDTO aIRWAYSDTO) {
        return aIRWAYSService.create(aIRWAYSDTO);
    }

    @PutMapping("/{airwayID}")
    public void updateAIRWAYS(@PathVariable final Integer airwayID, @RequestBody @Valid final AIRWAYSDTO aIRWAYSDTO) {
        aIRWAYSService.update(airwayID, aIRWAYSDTO);
    }

    @DeleteMapping("/{airwayID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAIRWAYS(@PathVariable final Integer airwayID) {
        aIRWAYSService.delete(airwayID);
    }

}
