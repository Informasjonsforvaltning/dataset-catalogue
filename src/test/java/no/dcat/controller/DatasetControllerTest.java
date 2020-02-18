package no.dcat.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import no.dcat.datastore.domain.dcat.smoke.TestCompleteCatalog;
import no.dcat.factory.DatasetFactory;
import no.dcat.model.Catalog;
import no.dcat.model.Dataset;
import no.dcat.service.DatasetService;
import no.dcat.webutils.exceptions.FDKException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by dask on 21.04.2017.
 */

@Tag("unit")
@ExtendWith(MockitoExtension.class)
public class DatasetControllerTest {

    @InjectMocks
    private DatasetController datasetController;

    @Mock
    private DatasetService mockDatasetService;

    @Test
    public void createDatasetOK() throws FDKException {
        String catalogId = "1234";
        Catalog catalog = new Catalog(catalogId);
        Dataset data = new Dataset();

        when(mockDatasetService.createDataset(catalogId, data)).thenReturn(DatasetFactory.create(catalog, data));

        Dataset saveDataset = datasetController.createDataset(catalogId, data);

        assertThat(saveDataset.getCatalogId(), is(catalogId));
    }

    @Test
    public void serializationOK() throws Throwable {
        no.dcat.shared.Catalog completeCatalog = TestCompleteCatalog.getCompleteCatalog();

        no.dcat.shared.Dataset completeDataset = completeCatalog.getDataset().get(0);

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



