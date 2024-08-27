package ivan.makhorin.travelagency.sight;

import ivan.makhorin.travelagency.place.PlaceMapper;
import ivan.makhorin.travelagency.sight.model.Sight;
import ivan.makhorin.travelagency.sight.model.SightDTO;
import ivan.makhorin.travelagency.sight.model.SightType;
import ivan.makhorin.travelagency.touristservice.TouristServiceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class SightMapper {

    protected PlaceMapper placeMapper = Mappers.getMapper(PlaceMapper.class);
    protected TouristServiceMapper touristServiceMapper = Mappers.getMapper(TouristServiceMapper.class);

    @Mapping(target = "sightType", expression = "java(toSightType(sightDTO))")
    @Mapping(target = "place", expression = "java(placeMapper.toPlace(sightDTO.place()))")
    @Mapping(target = "services", expression = "java(touristServiceMapper.toTouristServices(sightDTO.touristServiceDTOs()))")
    public abstract Sight toSight(SightDTO sightDTO);

    @Mapping(target = "type", expression = "java(sight.getSightType().name())")
    @Mapping(source = "creationDate", target = "creationDate", dateFormat = "dd.MM.yyyy")
    @Mapping(target = "touristServiceDTOs", expression = "java(touristServiceMapper.toTouristServiceDTOs(sight.getServices()))")
    public abstract SightDTO toSightDTO(Sight sight);

    protected SightType toSightType(SightDTO sightDTO) {
        return SightType.valueOf(sightDTO.type());
    }
}
