package ivan.makhorin.travelagency.touristservice;

import ivan.makhorin.travelagency.touristservice.model.TouristService;
import ivan.makhorin.travelagency.touristservice.model.TouristServiceDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TouristServiceMapper {

    TouristService toTouristService(TouristServiceDTO touristServiceDTO);

    TouristServiceDTO toTouristServiceDTO(TouristService touristServiceDTO);

    List<TouristService> toTouristServices(List<TouristServiceDTO> touristServiceDTOs);

    List<TouristServiceDTO> toTouristServiceDTOs(List<TouristService> touristServiceDTOs);
}
