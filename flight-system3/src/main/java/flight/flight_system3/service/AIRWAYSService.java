package flight.flight_system3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import flight.flight_system3.config.CustomNotFoundException;
import flight.flight_system3.domain.AIRPORTS;
import flight.flight_system3.domain.AIRWAYS;
import flight.flight_system3.model.AIRWAYSDTO;
import flight.flight_system3.repos.AIRPORTSRepository;
import flight.flight_system3.repos.AIRWAYSRepository;


@Service
public class AIRWAYSService {

    private final AIRWAYSRepository aIRWAYSRepository;
    private final AIRPORTSRepository aIRPORTSRepository;

    @Autowired
    public AIRWAYSService(final AIRWAYSRepository aIRWAYSRepository,
                          final AIRPORTSRepository aIRPORTSRepository) {
        this.aIRWAYSRepository = aIRWAYSRepository;
        this.aIRPORTSRepository = aIRPORTSRepository;
    }

    public List<AIRWAYSDTO> findAll() {
        return aIRWAYSRepository.findAll()
                .stream()
                .map(aIRWAYS -> mapToDTO(aIRWAYS, new AIRWAYSDTO()))
                .collect(Collectors.toList());
    }

    public AIRWAYSDTO get(final Integer airwayID) {
        return aIRWAYSRepository.findById(airwayID)
                .map(aIRWAYS -> mapToDTO(aIRWAYS, new AIRWAYSDTO()))
                .orElseThrow(CustomNotFoundException::new);
    }

    public Integer create(final AIRWAYSDTO aIRWAYSDTO) {
        final AIRWAYS aIRWAYS = new AIRWAYS();
        mapToEntity(aIRWAYSDTO, aIRWAYS);
        return aIRWAYSRepository.save(aIRWAYS).getAirwayID();
    }

    public void update(final Integer airwayID, final AIRWAYSDTO aIRWAYSDTO) {
        final AIRWAYS aIRWAYS = aIRWAYSRepository.findById(airwayID)
                .orElseThrow(CustomNotFoundException::new);
        mapToEntity(aIRWAYSDTO, aIRWAYS);
        aIRWAYSRepository.save(aIRWAYS);
    }

    public void delete(final Integer airwayID) {
        aIRWAYSRepository.deleteById(airwayID);
    }

    private AIRWAYSDTO mapToDTO(final AIRWAYS aIRWAYS, final AIRWAYSDTO aIRWAYSDTO) {
        aIRWAYSDTO.setAirwayID(aIRWAYS.getAirwayID());
        aIRWAYSDTO.setAirwayName(aIRWAYS.getAirwayName());
        aIRWAYSDTO.setAirtoport(aIRWAYS.getAirtoport() == null ? null : aIRWAYS.getAirtoport().getAirportId());
        return aIRWAYSDTO;
    }

    private AIRWAYS mapToEntity(final AIRWAYSDTO aIRWAYSDTO, final AIRWAYS aIRWAYS) {
        aIRWAYS.setAirwayName(aIRWAYSDTO.getAirwayName());
        if (aIRWAYSDTO.getAirtoport() != null &&
                (aIRWAYS.getAirtoport() == null || aIRWAYS.getAirtoport().getAirportId() != aIRWAYSDTO.getAirtoport())) {
            final AIRPORTS airtoport = aIRPORTSRepository.findById(aIRWAYSDTO.getAirtoport())
                    .orElseThrow(CustomNotFoundException::new);
            aIRWAYS.setAirtoport(airtoport);
        }
        return aIRWAYS;
    }

}
