package ivan.makhorin.touristagency.sight.model;

import ivan.makhorin.touristagency.place.model.PlaceDTO;
import ivan.makhorin.touristagency.touristservice.model.TouristServiceDTO;

import java.util.List;

public record SightDTO(Long id, String sightName, String type, String creationDate, String description, PlaceDTO place,
                       List<TouristServiceDTO> touristServiceDTOs) {
}
