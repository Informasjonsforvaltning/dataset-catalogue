package no.dcat.datastore.domain.dcat.vocabulary;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class PROV {
    public static final String NS = "http://www.w3.org/ns/prov#";
    private static final Model model = ModelFactory.createDefaultModel();
    public static final Resource Attribution = model.createResource(NS + "Attribution");

    // TODO: PROV vocabulary does not have "hasBody" attribute
    @Deprecated
    public static final Property hasBody = model.createProperty(NS, "hasBody");

    public static final Property agent = model.createProperty(NS, "agent");
    public static final Property qualifiedAttribution = model.createProperty(NS, "qualifiedAttribution");
}
