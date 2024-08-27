package ivan.makhorin.travelagency.place;

import ivan.makhorin.travelagency.place.model.Place;
import ivan.makhorin.travelagency.place.model.PlaceDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaceMapperTest {
    private final PlaceMapper placeMapper = Mappers.getMapper(PlaceMapper.class);


    // valid place dto
    @Test
    void toPlace() {
        var placeDTO = new PlaceDTO(1L, "Washington", 123123L, true);
        var expected = new Place(placeDTO.id(), placeDTO.placeName(), placeDTO.population(), List.of(), placeDTO.metroAvailable(), null, null);
        var result = placeMapper.toPlace(placeDTO);
        assertEquals(expected, result);
    }

    @Test
    void toPlaceDTO() {
        var expected = new PlaceDTO(1L, "Washington", 123123L, true);
        var place = new Place(expected.id(), expected.placeName(), expected.population(), List.of(), expected.metroAvailable(), null, null);
        var result = placeMapper.toPlaceDTO(place);
        assertEquals(expected, result);
    }
}
