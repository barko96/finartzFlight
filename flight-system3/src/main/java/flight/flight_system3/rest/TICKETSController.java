package flight.flight_system3.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import flight.flight_system3.model.TICKETSDTO;
import flight.flight_system3.service.TICKETSService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api/tICKETSs", produces = MediaType.APPLICATION_JSON_VALUE)
public class TICKETSController {

    private final TICKETSService tICKETSService;

    @Autowired
    public TICKETSController(final TICKETSService tICKETSService) {
        this.tICKETSService = tICKETSService;
    }

    @GetMapping
    public List<TICKETSDTO> getAllTICKETSs() {
        return tICKETSService.findAll();
    }

    @GetMapping("/{ticketid}")
    public TICKETSDTO getTICKETS(@PathVariable final Integer ticketid) {
        return tICKETSService.get(ticketid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createTICKETS(@RequestBody @Valid final TICKETSDTO tICKETSDTO) {
        return tICKETSService.create(tICKETSDTO);
    }

    @PutMapping("/{ticketid}")
    public void updateTICKETS(@PathVariable final Integer ticketid, @RequestBody @Valid final TICKETSDTO tICKETSDTO) {
        tICKETSService.update(ticketid, tICKETSDTO);
    }

    @DeleteMapping("/{ticketid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTICKETS(@PathVariable final Integer ticketid) {
        tICKETSService.delete(ticketid);
    }

}
