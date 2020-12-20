package flight.flight_system3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import flight.flight_system3.config.CustomNotFoundException;
import flight.flight_system3.domain.AIRPORTS;
import flight.flight_system3.model.AIRPORTSDTO;
import flight.flight_system3.repos.AIRPORTSRepository;


@Service
public class AIRPORTSService {

    private final AIRPORTSRepository aIRPORTSRepository;

    @Autowired
    public AIRPORTSService(final AIRPORTSRepository aIRPORTSRepository) {
        this.aIRPORTSRepository = aIRPORTSRepository;
    }

    public List<AIRPORTSDTO> findAll() {
        return aIRPORTSRepository.findAll()
                .stream()
                .map(aIRPORTS -> mapToDTO(aIRPORTS, new AIRPORTSDTO()))
                .collect(Collectors.toList());
    }

    public AIRPORTSDTO get(final Integer airportId) {
        return aIRPORTSRepository.findById(airportId)
                .map(aIRPORTS -> mapToDTO(aIRPORTS, new AIRPORTSDTO()))
                .orElseThrow(CustomNotFoundException::new);
    }

    public Integer create(final AIRPORTSDTO aIRPORTSDTO) {
        final AIRPORTS aIRPORTS = new AIRPORTS();
        mapToEntity(aIRPORTSDTO, aIRPORTS);
        return aIRPORTSRepository.save(aIRPORTS).getAirportId();
    }

    public void update(final Integer airportId, final AIRPORTSDTO aIRPORTSDTO) {
        final AIRPORTS aIRPORTS = aIRPORTSRepository.findById(airportId)
                .orElseThrow(CustomNotFoundException::new);
        mapToEntity(aIRPORTSDTO, aIRPORTS);
        aIRPORTSRepository.save(aIRPORTS);
    }

    public void delete(final Integer airportId) {
        aIRPORTSRepository.deleteById(airportId);
    }

    private AIRPORTSDTO mapToDTO(final AIRPORTS aIRPORTS, final AIRPORTSDTO aIRPORTSDTO) {
        aIRPORTSDTO.setAirportId(aIRPORTS.getAirportId());
        aIRPORTSDTO.setName(aIRPORTS.getName());
        return aIRPORTSDTO;
    }

    private AIRPORTS mapToEntity(final AIRPORTSDTO aIRPORTSDTO, final AIRPORTS aIRPORTS) {
        aIRPORTS.setName(aIRPORTSDTO.getName());
        return aIRPORTS;
    }

}
