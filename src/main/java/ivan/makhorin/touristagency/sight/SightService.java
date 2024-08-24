package ivan.makhorin.touristagency.sight;

import ivan.makhorin.touristagency.sight.model.SightDTO;

import java.util.List;

public interface SightService {
    SightDTO save(SightDTO sightDTO);

    List<SightDTO> getAll();

    List<SightDTO> getAllByPlaceName(String placeName);

    void updateSightDescription(SightDTO sightDTO);

    void deleteBySightName(String sightName);

    List<SightDTO> getWithOptions(String type, Boolean sorted);
}
