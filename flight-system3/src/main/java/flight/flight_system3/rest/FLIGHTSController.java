package flight.flight_system3.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import flight.flight_system3.model.FLIGHTSDTO;
import flight.flight_system3.service.FLIGHTSService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api/fLIGHTSs", produces = MediaType.APPLICATION_JSON_VALUE)
public class FLIGHTSController {

    private final FLIGHTSService fLIGHTSService;

    @Autowired
    public FLIGHTSController(final FLIGHTSService fLIGHTSService) {
        this.fLIGHTSService = fLIGHTSService;
    }

    @GetMapping
    public List<FLIGHTSDTO> getAllFLIGHTSs() {
        return fLIGHTSService.findAll();
    }

    @GetMapping("/{fid}")
    public FLIGHTSDTO getFLIGHTS(@PathVariable final Integer fid) {
        return fLIGHTSService.get(fid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createFLIGHTS(@RequestBody @Valid final FLIGHTSDTO fLIGHTSDTO) {
        return fLIGHTSService.create(fLIGHTSDTO);
    }

    @PutMapping("/{fid}")
    public void updateFLIGHTS(@PathVariable final Integer fid, @RequestBody @Valid final FLIGHTSDTO fLIGHTSDTO) {
        fLIGHTSService.update(fid, fLIGHTSDTO);
    }

    @DeleteMapping("/{fid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFLIGHTS(@PathVariable final Integer fid) {
        fLIGHTSService.delete(fid);
    }

}
