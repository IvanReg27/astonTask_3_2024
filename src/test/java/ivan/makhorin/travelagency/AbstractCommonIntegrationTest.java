package ivan.makhorin.travelagency;

import ivan.makhorin.travelagency.place.model.Place;
import ivan.makhorin.travelagency.place.model.PlaceDTO;
import ivan.makhorin.travelagency.sight.model.Sight;
import ivan.makhorin.travelagency.sight.model.SightDTO;
import ivan.makhorin.travelagency.sight.model.SightType;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import com.google.gson.Gson;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDateTime;
import java.util.List;

public abstract class AbstractCommonIntegrationTest {

    protected final Sight sight = Sight.builder()
            .id(1L)
            .sightName("Tokarevsky lighthouse")
            .sightType(SightType.LIGHTHOUSE)
            .description("Have a description")
            .creationDate(LocalDateTime.of(1910, 1, 1, 0, 0))
            .build();
    protected final Sight newSight = Sight.builder()
            .id(5L)
            .sightName("Statue of Liberty")
            .sightType(SightType.STATUE)
            .description("Cтатуя Cвободы.")
            .creationDate(LocalDateTime.of(1886, 10, 28, 0, 0))
            .build();

    protected final Place place = Place.builder()
            .id(1L)
            .placeName("Vladivostok")
            .population(123123123L)
            .metroAvailable(true)
            .longitude("131.9110")
            .latitude("43.1332")
            .sights(List.of(sight))
            .build();

    protected final Gson gson = new Gson();
    protected final PlaceDTO placeDTO = new PlaceDTO(
            place.getId(), place.getPlaceName(), place.getPopulation(), place.getMetroAvailable());
    protected final PlaceDTO newPlaceDTO = new PlaceDTO(
            place.getId(), place.getPlaceName(), place.getPopulation() + 100, place.getMetroAvailable());
    protected final PlaceDTO newPlaceDTO2 = new PlaceDTO(
            1L, "Moscow", place.getPopulation() + 123, place.getMetroAvailable());
    protected final SightDTO newSightDTO = new SightDTO(sight.getId(), sight.getSightName(), SightType.STATUE.name(), "1147-01-01T00:00:00", sight.getDescription(), placeDTO , null);

    @Container
    public static PostgreSQLContainer<?> postgreDBContainer;

    static {
        int containerPort = 5432;
        int localPort = 5432;
        DockerImageName postgres = DockerImageName.parse("postgres:latest");
        postgreDBContainer = new PostgreSQLContainer<>(postgres)
                .withDatabaseName("postgres")
                .withUsername("postgres")
                .withPassword("admin")
                .withReuse(true)
                .withExposedPorts(containerPort)
                .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                        new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(localPort), new ExposedPort(containerPort)))
                ));
        postgreDBContainer.start();
    }
}
