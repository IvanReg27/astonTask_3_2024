package ivan.makhorin.touristagency.place.impl;

import ivan.makhorin.touristagency.place.PlaceMapper;
import ivan.makhorin.touristagency.place.PlaceRepository;
import ivan.makhorin.touristagency.place.PlaceService;
import ivan.makhorin.touristagency.place.model.Place;
import ivan.makhorin.touristagency.place.model.PlaceDTO;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper = Mappers.getMapper(PlaceMapper.class);

    @Override
    public PlaceDTO save(PlaceDTO placeDTO) {
        var place = placeMapper.toPlace(placeDTO);
        place.getSights().forEach(sight -> sight.setPlace(place));
        return placeMapper.toPlaceDTO(placeRepository.save(place));
    }

    @Override
    public void updatePlace(PlaceDTO placeDTO) {
        var placeName = placeDTO.placeName();
        var population = placeDTO.population();
        var metroAvailability = placeDTO.metroAvailable();
        if (population != null && metroAvailability != null) {
            placeRepository.updatePlacePopulationAndMetroByPlaceName(population, metroAvailability, placeName);
            return;
        }
        if (population != null) {
            placeRepository.updatePlacePopulationByPlaceName(population, placeName);
            return;
        }
        placeRepository.updatePlaceMetroAvailabilityByPlaceName(metroAvailability, placeName);
    }

    @Override
    public Place findById(Long id) {
        return placeRepository.findById(id).orElseThrow();
    }
}
