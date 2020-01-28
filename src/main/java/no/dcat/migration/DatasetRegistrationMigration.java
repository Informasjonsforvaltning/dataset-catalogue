package no.dcat.migration;

import lombok.RequiredArgsConstructor;
import no.dcat.model.Dataset;
import no.dcat.repository.DatasetRepository;
import no.dcat.shared.DataTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Configuration
@RequiredArgsConstructor
public class DatasetRegistrationMigration {
    private final DatasetRepository datasetRepository;

    private static Logger logger = LoggerFactory.getLogger(DatasetRegistrationMigration.class);

    private static String convertHttpUriToHttps(String oldUri) {
        if (oldUri == null || !oldUri.contains("http://psi.norge.no/los/")) {
            return oldUri;
        }

        return oldUri.replace("http://psi.norge.no/los/", "https://psi.norge.no/los/");
    }

    private static DataTheme convertHttpThemeToHttps(DataTheme theme) {
        String convertedUri = convertHttpUriToHttps(theme.getUri());
        theme.setUri(convertedUri);
        return theme;
    }

    @PostConstruct
    public void migrate() {
        Iterable<Dataset> datasetRegistrations = datasetRepository.findAll();

        long migratedDatasetCount = StreamSupport.stream(datasetRegistrations.spliterator(), false)
            // filter datasets that have some of the theme uris changed.
            .filter(dataset -> dataset.getTheme() != null)
            .peek(dataset -> {
                List<DataTheme> newThemes = dataset.getTheme().stream()
                    //replace http with https
                    .map(DatasetRegistrationMigration::convertHttpThemeToHttps)
                    .collect(Collectors.toList());

                dataset.setTheme(newThemes);

                datasetRepository.save(dataset);
            })
            .count();

        logger.info("Total datasets migrated {}", migratedDatasetCount);
    }
}