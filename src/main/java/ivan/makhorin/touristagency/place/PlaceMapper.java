package ivan.makhorin.touristagency.place;

import ivan.makhorin.touristagency.place.model.Place;
import ivan.makhorin.touristagency.place.model.PlaceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;


@Mapper(componentModel = "spring", nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public abstract class PlaceMapper {
    @Mapping(target = "sights", expression = "java(java.util.List.of())")
    public abstract Place toPlace(PlaceDTO placeDTO);

    public abstract PlaceDTO toPlaceDTO(Place place);

}
