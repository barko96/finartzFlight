package flight.flight_system3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import flight.flight_system3.config.CustomNotFoundException;
import flight.flight_system3.domain.FLIGHTS;
import flight.flight_system3.domain.TICKETS;
import flight.flight_system3.model.TICKETSDTO;
import flight.flight_system3.repos.FLIGHTSRepository;
import flight.flight_system3.repos.TICKETSRepository;


@Service
public class TICKETSService {

    private final TICKETSRepository tICKETSRepository;
    private final FLIGHTSRepository fLIGHTSRepository;

    @Autowired
    public TICKETSService(final TICKETSRepository tICKETSRepository,
                          final FLIGHTSRepository fLIGHTSRepository) {
        this.tICKETSRepository = tICKETSRepository;
        this.fLIGHTSRepository = fLIGHTSRepository;
    }

    public List<TICKETSDTO> findAll() {
        return tICKETSRepository.findAll()
                .stream()
                .map(tICKETS -> mapToDTO(tICKETS, new TICKETSDTO()))
                .collect(Collectors.toList());
    }

    public TICKETSDTO get(final Integer ticketid) {
        return tICKETSRepository.findById(ticketid)
                .map(tICKETS -> mapToDTO(tICKETS, new TICKETSDTO()))
                .orElseThrow(CustomNotFoundException::new);
    }

    public Integer create(final TICKETSDTO tICKETSDTO) {
        final TICKETS tICKETS = new TICKETS();
        mapToEntity(tICKETSDTO, tICKETS);
        return tICKETSRepository.save(tICKETS).getTicketid();
    }

    public void update(final Integer ticketid, final TICKETSDTO tICKETSDTO) {
        final TICKETS tICKETS = tICKETSRepository.findById(ticketid)
                .orElseThrow(CustomNotFoundException::new);
        mapToEntity(tICKETSDTO, tICKETS);
        tICKETSRepository.save(tICKETS);
    }

    public void delete(final Integer ticketid) {
        tICKETSRepository.deleteById(ticketid);
    }

    private TICKETSDTO mapToDTO(final TICKETS tICKETS, final TICKETSDTO tICKETSDTO) {
        tICKETSDTO.setTicketid(tICKETS.getTicketid());
        tICKETSDTO.setFlightNumber(tICKETS.getFlightNumber());
        tICKETSDTO.setFlys(tICKETS.getFlys() == null ? null : tICKETS.getFlys().getFid());
        return tICKETSDTO;
    }

    private TICKETS mapToEntity(final TICKETSDTO tICKETSDTO, final TICKETS tICKETS) {
        tICKETS.setFlightNumber(tICKETSDTO.getFlightNumber());
        if (tICKETSDTO.getFlys() != null &&
                (tICKETS.getFlys() == null || tICKETS.getFlys().getFid() != tICKETSDTO.getFlys())) {
            final FLIGHTS flys = fLIGHTSRepository.findById(tICKETSDTO.getFlys())
                    .orElseThrow(CustomNotFoundException::new);
            tICKETS.setFlys(flys);
        }
        return tICKETS;
    }

}
