package no.dcat.model;

import no.dcat.factory.DatasetFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by bjg on 02.05.2018.
 */
@Tag("unit")
public class DatasetFactoryTest {

    @Test
    public void datasetCreatedWithCorrectUri() {
        String catalogId = "12345";
        Catalog catalog = new Catalog();
        catalog.setId(catalogId);
        Dataset data = new Dataset();
        Dataset result = DatasetFactory.create(catalog, data);

        assertThat(result.getUri(), containsString("http://brreg.no/catalogs/" + catalogId + "/datasets/"));
    }
}
