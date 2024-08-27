package ivan.makhorin.travelagency.sight.impl;

import ivan.makhorin.travelagency.sight.SightMapper;
import ivan.makhorin.travelagency.sight.SightRepository;
import ivan.makhorin.travelagency.sight.SightService;
import ivan.makhorin.travelagency.sight.model.Sight;
import ivan.makhorin.travelagency.sight.model.SightDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SightServiceImpl implements SightService {

    private final SightRepository sightRepository;
    private final SightMapper sightMapper = Mappers.getMapper(SightMapper.class);

    @Override
    public SightDTO save(SightDTO sightDTO) {
        var sight = sightMapper.toSight(sightDTO);
        return sightMapper.toSightDTO(sightRepository.save(sight));
    }

    @Override
    public List<SightDTO> getAll() {
        return sightRepository.findAll().stream()
                .map(sightMapper::toSightDTO)
                .toList();
    }

    @Override
    public List<SightDTO> getAllByPlaceName(String placeName) {
        var sights = sightRepository.findByPlaceName(placeName);
        if (!sights.isEmpty()) {
            return sights.stream()
                    .map(sightMapper::toSightDTO)

                    .toList();
        }
        return List.of();
    }

    @Override
    public void updateSightDescription(SightDTO sightDTO) {
        sightRepository.updateSightDescription(sightDTO.sightName(), sightDTO.description());
    }

    @Override
    public void deleteBySightName(String sightName) {
        sightRepository.deleteBySightName(sightName);
    }

    @Override
    public List<SightDTO> getWithOptions(String sightName, Boolean sorted) {
        var sightNameIsValid = sightName != null && !sightName.isEmpty() && !sightName.isBlank();
        List<Sight> sights;
        if (sorted != null && sorted) {
            if (sightNameIsValid) {
                sights = sightRepository.findAllBySightNameOrderBySightTypeAsc(sightName);
            } else {
                sights = sightRepository.findAllByOrderBySightTypeAsc();
            }
        } else {
            if (sightNameIsValid) {
                sights = sightRepository.findAllBySightName(sightName);
            } else {
                sights = sightRepository.findAll();
            }
        }
        return sights.stream()
                .map(sightMapper::toSightDTO)
                .toList();
    }
}
