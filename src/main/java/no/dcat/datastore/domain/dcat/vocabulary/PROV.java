package no.dcat.datastore.domain.dcat.vocabulary;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;

public class PROV {
    public static final String NS = "http://www.w3.org/ns/prov#";

    public static final Property hasBody = model.createProperty(NS, "hasBody");

}

