package no.dcat.repository;

import no.dcat.model.Dataset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface DatasetRepository extends ElasticsearchRepository<Dataset, String> {

    List<Dataset> removeByIdAndCatalogId(String id, String catalogId);

    Page<Dataset> findByCatalogId(String catalogId, Pageable pageable);

    Page<Dataset> findByCatalogIdAndRegistrationStatus(String catalogId, Dataset.REGISTRATION_STATUS status, Pageable pageable);

}
