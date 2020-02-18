package no.dcat.controller;

import no.dcat.datastore.domain.dcat.smoke.TestCompleteCatalog;
import no.dcat.model.Catalog;
import no.dcat.model.Dataset;
import no.dcat.repository.DatasetRepository;
import no.dcat.repository.CatalogRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by dask on 21.04.2017.
 */

@Tag("unit")
@ExtendWith(MockitoExtension.class)
public class RdfCatalogControllerTest {

    @InjectMocks
    private RdfCatalogController controller;

    @Mock
    private DatasetRepository mockDR;

    @Mock
    private CatalogRepository mockCR;

    @Test
    public void getDcatRepOK() throws Throwable {
        Catalog catalog = new Catalog();
        BeanUtils.copyProperties(TestCompleteCatalog.getCompleteCatalog(), catalog);

        when(mockCR.findById(anyString())).thenReturn(Optional.of(catalog));
        String catalogId = catalog.getId();

        Page<Dataset> pagedDataset = new Page<Dataset>() {
            @Override
            public Iterator<Dataset> iterator() {
                return null;
            }

            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<Dataset> getContent() {
                List<Dataset> ds = new ArrayList<>();
                catalog.getDataset().forEach(d -> {
                    ds.add((Dataset) d);
                });
                return ds;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public <U> Page<U> map(Function<? super Dataset, ? extends U> function) {
                return null;
            }
        };

        HttpEntity<Catalog> actualEntity = controller.getCatalog(catalogId);

        Catalog actual = actualEntity.getBody();
        assertThat(actual.getId(), is(catalogId));

    }
}
