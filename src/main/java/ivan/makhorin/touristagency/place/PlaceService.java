package ivan.makhorin.touristagency.place;

import ivan.makhorin.touristagency.place.model.Place;
import ivan.makhorin.touristagency.place.model.PlaceDTO;

public interface PlaceService {
    PlaceDTO save(PlaceDTO place);

    void updatePlace(PlaceDTO placeDTO);

    Place findById(Long id);
}
