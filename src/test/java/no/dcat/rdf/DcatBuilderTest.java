package no.dcat.rdf;

import com.google.gson.*;
import no.dcat.datastore.domain.dcat.builders.DcatBuilder;
import no.dcat.datastore.domain.dcat.smoke.TestCompleteCatalog;
import no.dcat.model.Catalog;
import no.dcat.model.Dataset;
import no.dcat.shared.SkosConcept;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.ActiveProfiles;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by dask on 12.04.2017.
 */
@Tag("unit")
@ActiveProfiles(value = "develop")
public class DcatBuilderTest {
    static Logger logger = LoggerFactory.getLogger(DcatBuilderTest.class);

    DcatBuilder builder = new DcatBuilder();

    @Test
    public void convertCompleteCatalogToTurtleOK() throws Throwable {
        builder = new DcatBuilder();
        Catalog catalog = new Catalog();
        BeanUtils.copyProperties(TestCompleteCatalog.getCompleteCatalog(), catalog);

        String actual = builder.transform(catalog, "TURTLE");

        assertThat(actual, is(notNullValue()));
        logger.debug("actual DCAT \n{}", actual);

    }

    @Test
    public void mustCorrectlySerialiseDatasetRelations() {
        Catalog catalog = new Catalog();
        Dataset dataset = new Dataset();

        dataset.setId("http://catalog/1/dataset/1");
        dataset.setUri("http://catalog/1/dataset/1");
        dataset.setRelations(
                Arrays.asList(
                        SkosConcept.getInstance(
                                "http://uri-1",
                                new HashMap<String, String>() {{
                                    put("nb", "label-1-nb");
                                    put("en", "label-1-en");
                                }}),
                        SkosConcept.getInstance(
                                "http://uri-2",
                                new HashMap<String, String>() {{
                                    put("nb", "label-2-nb");
                                    put("en", "label-2-en");
                                }})
                ))
        ;

        catalog.setId("http://catalog/1");
        catalog.setUri("http://catalog/1");
        catalog.setDataset(Collections.singletonList(dataset));

        Model model = ModelFactory.createDefaultModel().read(new StringReader(DcatBuilder.transform(catalog, "TURTLE")), null, "TURTLE");
        Model expectedModel = RDFDataMgr.loadModel("catalog-with-dataset-with-relations.ttl");

        assertTrue(model.isIsomorphicWith(expectedModel));
    }

    @Test
    public void mustCorrectlySerialiseDatasetQualifiedAttributions() {
        Catalog catalog = new Catalog();
        Dataset dataset = new Dataset();

        dataset.setId("http://catalog/1/dataset/1");
        dataset.setUri("http://catalog/1/dataset/1");
        dataset.setQualifiedAttributions(Stream.of("123456789", "987654321").collect(Collectors.toSet()));

        catalog.setId("http://catalog/1");
        catalog.setUri("http://catalog/1");
        catalog.setDataset(Collections.singletonList(dataset));

        Model model = ModelFactory.createDefaultModel().read(new StringReader(DcatBuilder.transform(catalog, "TURTLE")), null, "TURTLE");
        Model expectedModel = RDFDataMgr.loadModel("catalog-with-dataset-with-qualified-attributions.ttl");

        assertTrue(model.isIsomorphicWith(expectedModel));
    }


    @Test
    public void convertCompleteCatalogToJsonOK() throws Throwable {
        builder = new DcatBuilder();
        Catalog catalog = new Catalog();
        BeanUtils.copyProperties(TestCompleteCatalog.getCompleteCatalog(), catalog);

        JsonSerializer<Date> ser = new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext
                    context) {
                return src == null ? null : new JsonPrimitive(src.getTime());
            }
        };
        //Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, ser).setPrettyPrinting().create();

        String actual = gson.toJson(catalog);

        assertThat(actual, is(notNullValue()));
        logger.debug("{}", actual);
    }


    @Test
    public void convertMinimumCatalogOK() throws Throwable {
        builder = new DcatBuilder();
        Catalog catalog = new Catalog();

        String actual = builder.transform(catalog, "TURTLE");

        assertThat(actual, is(notNullValue()));
        logger.debug("mini catalog\n{}", actual);
    }

    @Test
    public void convertMinimumCatalogToTurtleOK() throws Throwable {
        builder = new DcatBuilder();
        Catalog catalog = new Catalog();

        Dataset dataset = new Dataset();
        Map<String, String> title = new HashMap<>();
        title.put("nb", "minimum dataset");
        dataset.setTitle(title);
        dataset.setUri("http://uri/12345");

        catalog.setDataset(Arrays.asList(dataset));

        String actual = builder.transform(catalog, "TURTLE");

        assertThat(actual, is(notNullValue()));
        logger.debug("miniimum catalog\n{}", actual);

    }

}
