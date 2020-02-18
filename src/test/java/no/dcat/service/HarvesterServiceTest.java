package no.dcat.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import no.dcat.model.Catalog;
import no.dcat.shared.admin.DcatSourceDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by bjg on 20.02.2018.
 */
@Tag("unit")
public class HarvesterServiceTest {

    private WireMockServer server = new WireMockServer(8080);

    @AfterEach
    void stopServer() {
        server.stop();
    }

    @Test
    public void getHarvestEntriesReturnsCorrectNumberOFEntries() {
        harvesterResponseGetDcatSourcesWireMockStub();

        HarvesterService hs = new HarvesterService();
        hs.harvesterUrl = "http://localhost:" + server.port();
        hs.harvesterUsername = "testuser";
        hs.harvesterPassword = "testpasword";

        List<DcatSourceDto> result = hs.getHarvestEntries();
        assertTrue(result.size() == 2);

    }


    @Test
    public void harvestEntryContainsCorrectUri() {
        harvesterResponseGetDcatSourcesWireMockStub();

        HarvesterService hs = new HarvesterService();
        hs.harvesterUrl = "http://localhost:" + server.port();
        hs.harvesterUsername = "testuser";
        hs.harvesterPassword = "testpasword";

        List<DcatSourceDto> result = hs.getHarvestEntries();
        assertEquals(result.get(1).getUrl(), "http://test.test.no");

    }


    @Test
    public void createHarvestEntryWorks() {
        harvesterResponsePostDcatSourceWireMockStub();

        HarvesterService hs = new HarvesterService();
        hs.harvesterUrl = "http://localhost:" + server.port();
        hs.harvesterUsername = "testuser";
        hs.harvesterPassword = "testpasword";

        Catalog catalog = new Catalog();
        catalog.setId("123455");
        catalog.setUri("http://brreg.no/43434343434");
        Map<String, String> title = new HashMap<>();
        title.put("nb", "Description");
        catalog.setTitle(title);

        boolean success = hs.createHarvestEntry(catalog, "http://harvest.here.no");
        assertTrue(success);

    }


    /**
     * Simulate response from harvester
     */
    private void harvesterResponseGetDcatSourcesWireMockStub() {
        server.stubFor(get(urlEqualTo("/api/admin/dcat-sources"))
            .withHeader("Accept", containing("application/json"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("[\n" +
                    "    {\n" +
                    "        \"id\": \"http://dcat.difi.no/dcatSource_91413042-6549-4e11-8821-686f35954301\",\n" +
                    "        \"description\": \"Datakatalog for REINLI OG BERLEVÅG REGNSKAP\",\n" +
                    "        \"user\": \"test_admin\",\n" +
                    "        \"orgnumber\": \"910888447\",\n" +
                    "        \"url\": \"https://localhost:8099/catalogs/910888447\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": \"http://dcat.difi.no/dcatSource_e64c0b66-162a-4c5a-8ed0-01260d397d8c\",\n" +
                    "        \"description\": \"Datakatalog for Testformål\",\n" +
                    "        \"user\": \"test_admin\",\n" +
                    "        \"orgnumber\": \"787867677\",\n" +
                    "        \"url\": \"http://test.test.no\"\n" +
                    "    }\n" +
                    "]")));

        server.start();
    }


    /**
     * Simulate response from harvester
     */
    private void harvesterResponsePostDcatSourceWireMockStub() {
        server.stubFor(post(urlEqualTo("/api/admin/dcat-source"))
            .willReturn(aResponse()
                .withStatus(202)));

        server.start();
    }
}
