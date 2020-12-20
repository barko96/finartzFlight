package flight.flight_system3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import flight.flight_system3.config.CustomNotFoundException;
import flight.flight_system3.domain.AIRWAYS;
import flight.flight_system3.domain.FLIGHTS;
import flight.flight_system3.model.FLIGHTSDTO;
import flight.flight_system3.repos.AIRWAYSRepository;
import flight.flight_system3.repos.FLIGHTSRepository;


@Service
public class FLIGHTSService {

    private final FLIGHTSRepository fLIGHTSRepository;
    private final AIRWAYSRepository aIRWAYSRepository;

    @Autowired
    public FLIGHTSService(final FLIGHTSRepository fLIGHTSRepository,
                          final AIRWAYSRepository aIRWAYSRepository) {
        this.fLIGHTSRepository = fLIGHTSRepository;
        this.aIRWAYSRepository = aIRWAYSRepository;
    }

    public List<FLIGHTSDTO> findAll() {
        return fLIGHTSRepository.findAll()
                .stream()
                .map(fLIGHTS -> mapToDTO(fLIGHTS, new FLIGHTSDTO()))
                .collect(Collectors.toList());
    }

    public FLIGHTSDTO get(final Integer fid) {
        return fLIGHTSRepository.findById(fid)
                .map(fLIGHTS -> mapToDTO(fLIGHTS, new FLIGHTSDTO()))
                .orElseThrow(CustomNotFoundException::new);
    }

    public Integer create(final FLIGHTSDTO fLIGHTSDTO) {
        final FLIGHTS fLIGHTS = new FLIGHTS();
        mapToEntity(fLIGHTSDTO, fLIGHTS);
        return fLIGHTSRepository.save(fLIGHTS).getFid();
    }

    public void update(final Integer fid, final FLIGHTSDTO fLIGHTSDTO) {
        final FLIGHTS fLIGHTS = fLIGHTSRepository.findById(fid)
                .orElseThrow(CustomNotFoundException::new);
        mapToEntity(fLIGHTSDTO, fLIGHTS);
        fLIGHTSRepository.save(fLIGHTS);
    }

    public void delete(final Integer fid) {
        fLIGHTSRepository.deleteById(fid);
    }

    private FLIGHTSDTO mapToDTO(final FLIGHTS fLIGHTS, final FLIGHTSDTO fLIGHTSDTO) {
        fLIGHTSDTO.setFid(fLIGHTS.getFid());
        fLIGHTSDTO.setDeparture(fLIGHTS.getDeparture());
        fLIGHTSDTO.setDestination(fLIGHTS.getDestination());
        fLIGHTSDTO.setFly(fLIGHTS.getFly() == null ? null : fLIGHTS.getFly().getAirwayID());
        return fLIGHTSDTO;
    }

    private FLIGHTS mapToEntity(final FLIGHTSDTO fLIGHTSDTO, final FLIGHTS fLIGHTS) {
        fLIGHTS.setDeparture(fLIGHTSDTO.getDeparture());
        fLIGHTS.setDestination(fLIGHTSDTO.getDestination());
        if (fLIGHTSDTO.getFly() != null &&
                (fLIGHTS.getFly() == null || fLIGHTS.getFly().getAirwayID() != fLIGHTSDTO.getFly())) {
            final AIRWAYS fly = aIRWAYSRepository.findById(fLIGHTSDTO.getFly())
                    .orElseThrow(CustomNotFoundException::new);
            fLIGHTS.setFly(fly);
        }
        return fLIGHTS;
    }

}
