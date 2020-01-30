package no.dcat.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import no.dcat.datastore.domain.dcat.smoke.TestCompleteCatalog;
import no.dcat.factory.DatasetFactory;
import no.dcat.model.Catalog;
import no.dcat.model.Dataset;
import no.dcat.repository.DatasetRepository;
import no.dcat.repository.CatalogRepository;
import no.dcat.service.DatasetService;
import no.dcat.testcategories.UnitTest;
import no.dcat.webutils.exceptions.FDKException;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by dask on 21.04.2017.
 */

@Category(UnitTest.class)
public class DatasetControllerTest {

    private DatasetController datasetController;

    @Mock
    private DatasetService mockDatasetService;

    @Mock
    private DatasetRepository mockDatasetRepository;

    @Mock
    private CatalogRepository mockCatalogRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        String catalogId = "1234";
        Catalog catalog = new Catalog();
        catalog.setId(catalogId);
        when(mockCatalogRepository.findById(anyString())).thenReturn(Optional.of(catalog));

        datasetController = new DatasetController(mockDatasetService);
    }

    @Test
    public void createDatasetOK() throws FDKException {
        String catalogId = "1234";
        Catalog catalog = new Catalog();
        catalog.setId(catalogId);

        Dataset data = new Dataset();

        when(mockDatasetService.createDataset(catalogId, data)).thenReturn(DatasetFactory.create(catalog, data));
        when(mockDatasetRepository.save(any(Dataset.class))).thenAnswer((invocation) -> invocation.getArguments()[0]);

        Dataset saveDataset = datasetController.createDataset(catalogId, data);

        assertThat(saveDataset.getCatalogId(), is(catalogId));
    }

    @Test
    public void serializationOK() throws Throwable {
        no.dcat.shared.Catalog catalog = TestCompleteCatalog.getCompleteCatalog();

        no.dcat.shared.Dataset completeDataset = catalog.getDataset().get(0);

        Dataset expected = new Dataset();
        expected.setId("1");
        BeanUtils.copyProperties(completeDataset, expected);

        Gson gson = new Gson();

        String json = gson.toJson(expected);

        Dataset actual = new GsonBuilder().create().fromJson(json, Dataset.class);

        assertThat(actual, is(expected));
        assertThat(actual.getReferences(), is(expected.getReferences()));

    }
}



