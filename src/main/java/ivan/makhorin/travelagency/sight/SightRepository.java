package ivan.makhorin.travelagency.sight;

import ivan.makhorin.travelagency.sight.model.Sight;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface SightRepository extends JpaRepository<Sight, Long> {
    @Query("SELECT s FROM Sight s WHERE s.place.placeName = :placeName")
    List<Sight> findByPlaceName(@Param("placeName") String placeName);

    @Modifying
    @Query("UPDATE Sight s SET s.description = :description WHERE s.sightName = :sightName")
    void updateSightDescription(@Param("sightName") String sightName, @Param("description") String description);

    @Modifying
    @Query("DELETE FROM Sight s WHERE s.sightName = :sightName")
    void deleteBySightName(@Param("sightName") String sightName);

    List<Sight> findAllBySightNameOrderBySightTypeAsc(String sightName);

    List<Sight> findAllByOrderBySightTypeAsc();

    List<Sight> findAllBySightName(String sightName);
}
