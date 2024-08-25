package ivan.makhorin.touristagency.touristservice;

import ivan.makhorin.touristagency.touristservice.model.TouristService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TouristServiceRepository extends JpaRepository<TouristService, Long> {
}
