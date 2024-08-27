package ivan.makhorin.travelagency.place;

import ivan.makhorin.travelagency.AbstractCommonIntegrationTest;
import ivan.makhorin.travelagency.config.LiquibaseConfig;
import ivan.makhorin.travelagency.config.SpringConfig;
import ivan.makhorin.travelagency.place.model.PlaceDTO;
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

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class, LiquibaseConfig.class})
@WebAppConfiguration("")
class PlaceControllerTest extends AbstractCommonIntegrationTest {
    @Autowired
    private PlaceService placeService;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        placeRepository.deleteById(newPlaceDTO2.id());
    }
    // servlet context and controller exist
    @Test
    public void controllerExists() {
        var servletContext = webApplicationContext.getServletContext();
        assertNotNull(servletContext);
        assertInstanceOf(MockServletContext.class, servletContext);
        assertNotNull(webApplicationContext.getBean("placeController"));
    }

    // place to update
    @Test
    public void updatePlace() throws Exception {
        var body = gson.toJson(newPlaceDTO);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/place/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }


    // place to save
    @Test
    public void savePlace() throws Exception {
        var body = gson.toJson(newPlaceDTO2);
        var response = mockMvc.perform(MockMvcRequestBuilders.post("/place/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        var result = gson.fromJson(response.getResponse().getContentAsString(), PlaceDTO.class);
        assertEquals(newPlaceDTO2, result);
    }
}
