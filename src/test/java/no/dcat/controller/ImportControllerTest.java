package no.dcat.controller;

import no.dcat.datastore.domain.dcat.builders.DcatReader;
import no.dcat.model.Dataset;
import no.dcat.shared.SkosCode;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
public class ImportControllerTest {
    static Logger logger = LoggerFactory.getLogger(ImportControllerTest.class);

    @Test
    public void publisherContainsMultipleNamesWhenOnlyOneIsExpected() throws Throwable {
        Model model = FileManager.get().loadModel("ut1-export.ttl");

        ImportController impController = new ImportController(null, null);
        ImportController iController = Mockito.spy(impController);

        doReturn(new DcatReader(model)).when(iController).getDcatReader(any());

        List<Dataset> ds = iController.parseDatasets(model);

        assertThat(ds.size(), is(27));
    }

    @Test
    public void parseSetsPublisher() throws Throwable {
        Model model = FileManager.get().loadModel("ut1-export.ttl");

        ImportController importController = new ImportController(null, null);
        ImportController iController = Mockito.spy(importController);

        doReturn(new DcatReader(model)).when(iController).getDcatReader(any());

        List<Dataset> ds = iController.parseDatasets(model);

        ds.forEach(dataset -> {
            assertThat(String.format("dataset %s has null publisher", dataset.getId()), dataset.getPublisher(), is(not(nullValue())));
        });
    }

    @Test
    public void testLanguagePruning() {

        SkosCode skosCode1 = new SkosCode();

        skosCode1.getPrefLabel().put("en", "english");
        skosCode1.getPrefLabel().put("fi", "finish");


        SkosCode skosCode2 = new SkosCode();

        skosCode2.getPrefLabel().put("en", "english");
        skosCode2.getPrefLabel().put("no", "norwegian");

        List<SkosCode> skosCodes = Arrays.asList(skosCode1, skosCode2, skosCode1, skosCode2);

        new ImportController(null, null).pruneLanguages(skosCodes);

        assertNull(skosCode1.getPrefLabel().get("fi"));

        assertNotNull(skosCode1.getPrefLabel().get("en"));
        assertNotNull(skosCode2.getPrefLabel().get("en"));
        assertNotNull(skosCode2.getPrefLabel().get("no"));

    }


}
