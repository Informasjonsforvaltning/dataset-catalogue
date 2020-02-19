package no.dcat.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Tag("unit")
public class DatasetTest {

    @Test
    public void sameId_isEqual() {
        Dataset dataset = new Dataset("1");
        Dataset dataset1 = new Dataset("1");
        assertThat(dataset, is(dataset1));
    }

    @Test
    public void toString_makesSense() {
        Dataset dataset = new Dataset("1");
        Map languangeDescription = new HashMap();
        languangeDescription.put("no", "test");
        dataset.setDescription(languangeDescription);
        assertThat(dataset.toString(), is("Dataset(1, null, null, null, null, {no=test}, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)"));
    }

}
