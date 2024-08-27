package ivan.makhorin.travelagency.place;

import ivan.makhorin.travelagency.place.model.Place;
import ivan.makhorin.travelagency.place.model.PlaceDTO;

public interface PlaceService {
    PlaceDTO save(PlaceDTO place);

    void updatePlace(PlaceDTO placeDTO);

    Place findById(Long id);
}
