package ivan.makhorin.touristagency.sight;

import com.google.gson.reflect.TypeToken;
import ivan.makhorin.touristagency.AbstractCommonIntegrationTest;
import ivan.makhorin.touristagency.config.LiquibaseConfig;
import ivan.makhorin.touristagency.config.SpringConfig;
import ivan.makhorin.touristagency.place.PlaceService;
import ivan.makhorin.touristagency.sight.model.Sight;
import ivan.makhorin.touristagency.sight.model.SightDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class, LiquibaseConfig.class})
@WebAppConfiguration("")
class SightControllerTest extends AbstractCommonIntegrationTest {
    @Autowired
    private PlaceService placeService;
    @Autowired
    private SightRepository sightRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    // servlet context and controller exist
    @Test
    public void controllerExists() {
        var servletContext = webApplicationContext.getServletContext();
        assertNotNull(servletContext);
        assertInstanceOf(MockServletContext.class, servletContext);
        assertNotNull(webApplicationContext.getBean("sightController"));
    }

    // new sight / save / saved
    @Test
    void save() throws Exception {
        var body = gson.toJson(newSightDTO);
        var response = mockMvc.perform(MockMvcRequestBuilders.post("/sight/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        var result = gson.fromJson(response.getResponse().getContentAsString(), SightDTO.class);
        assertEquals(newSightDTO.id(), result.id());
    }

    // OK: sights / get all
    @Test
    void getAll() throws Exception {
        var sights = sightRepository.findAll();
        var existingIndexes = sights.stream().map(Sight::getId).toList();
        var response = mockMvc.perform(MockMvcRequestBuilders.get("/sight/get"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        var result = (List<SightDTO>) gson.fromJson(response.getResponse().getContentAsString(),
                new TypeToken<List<SightDTO>>() {
                }.getType());
        var resultIndexes = result.stream().map(SightDTO::id).toList();
        assertEquals(sights.size(), result.size());
        assertEquals(existingIndexes, resultIndexes);
    }

    // place name / get all place
    @Test
    void getSightsFromPlace() throws Exception {
        var place = placeService.findById(2L);
        var expected = 1;
        var response = mockMvc.perform(MockMvcRequestBuilders.get("/sight/get/place/" + place.getPlaceName()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        var result = (List<SightDTO>) gson.fromJson(response.getResponse().getContentAsString(),
                new TypeToken<List<SightDTO>>() {
                }.getType());
        assertEquals(expected, result.size());
    }

    // new sight / update sight description
    @Test
    void updateSightsDescription() throws Exception {
        var body = gson.toJson(newSightDTO);
        mockMvc.perform(MockMvcRequestBuilders.put("/sight/update/description")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        var result = sightRepository.findAllBySightName(newSightDTO.sightName()).get(0);
        assertEquals(newSightDTO.description(), result.getDescription());
    }

    // OK: sight / deleting sight / deleted
    @Test
    void deleteSight() throws Exception {
        var sightName = sightRepository.findAll().get(1).getSightName();
        mockMvc.perform(MockMvcRequestBuilders.delete("/sight/remove/" + sightName))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        assertTrue(sightRepository.findAll().stream().noneMatch(sight1 -> Objects.equals(sight1.getSightName(), sightName)));
    }
}
