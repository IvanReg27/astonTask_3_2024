package ivan.makhorin.travelagency.sight.model;

import ivan.makhorin.travelagency.place.model.Place;
import ivan.makhorin.travelagency.touristservice.model.TouristService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sight")
public class Sight {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sight_name")
    private String sightName;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "description")
    private String description;

    @Enumerated
    @Column(name = "sight_type")
    private SightType sightType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false, updatable = false)
    private Place place;

    @ManyToMany(mappedBy = "sights")
    private List<TouristService> services = new ArrayList<>();
}
