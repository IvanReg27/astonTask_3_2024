package ivan.makhorin.travelagency.sight.model;

import ivan.makhorin.travelagency.place.model.PlaceDTO;
import ivan.makhorin.travelagency.touristservice.model.TouristServiceDTO;

import java.util.List;

public record SightDTO(Long id, String sightName, String type, String creationDate, String description, PlaceDTO place,
                       List<TouristServiceDTO> touristServiceDTOs) {
}
