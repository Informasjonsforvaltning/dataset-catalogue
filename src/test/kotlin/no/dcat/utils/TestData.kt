package no.dcat.utils

import no.dcat.utils.ApiTestContainer.Companion.TEST_API
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap

const val API_PORT = 8080
const val LOCAL_SERVER_PORT = 5000

const val ELASTIC_PORT = 9200
const val ELASTIC_TCP_PORT = 9300
const val ELASTIC_NETWORK_NAME = "elasticsearch5"
const val ELASTIC_CLUSERNAME = "elasticsearch"
const val ELASTIC_CLUSTERNODES = "$ELASTIC_NETWORK_NAME:$ELASTIC_TCP_PORT"

const val WIREMOCK_TEST_HOST = "http://host.testcontainers.internal:$LOCAL_SERVER_PORT"

val API_ENV_VALUES : Map<String,String> = ImmutableMap.of(
    "SPRING_PROFILES_ACTIVE" , "contract-test",
    "WIREMOCK_TEST_HOST" , WIREMOCK_TEST_HOST,
    "FDK_ES_CLUSTERNODES" , ELASTIC_CLUSTERNODES,
    "FDK_ES_CLUSTERNAME" , ELASTIC_CLUSERNAME
)
val ELASTIC_ENV_VALUES : Map<String,String> = ImmutableMap.of(
    "cluster.name" , ELASTIC_CLUSERNAME,
    "xpack.security.enabled", "false",
    "xpack.monitoring.enabled", "false"
)

fun getApiAddress( endpoint: String ): String{
    return "http://${TEST_API.getContainerIpAddress()}:${TEST_API.getMappedPort(API_PORT)}$endpoint"
}

val datasetWithOriginalUri="{\n" +
        "  \"originalUri\": \"https://the.og.uri.no/node/1237\",\n" +
        "  \"title\": {\n" +
        "    \"nb\": \"TIL EN ANNEN ORGANISASJON 3\"\n" +
        "  },\n" +
        "  \"description\": {\n" +
        "    \"nb\": \"Testdatasett med all obligatorisk info\"\n" +
        "  },\n" +
        "  \"objective\": {\n" +
        "    \"nb\": \"For å sjekke at ting er som det skal og sjekke at ting blir sendt skikkelig og at uri opplegget ordner seg\"\n" +
        "  },\n" +
        "  \"contactPoint\": [\n" +
        "    {\n" +
        "      \"email\": \"fellesdatakatalog@digdir.no\",\n" +
        "      \"organizationUnit\": \"Felles Datakatalog\",\n" +
        "      \"hasURL\": \"https://fellesdatakatalog.digdir.no/kontaktskjema\",\n" +
        "      \"hasTelephone\": \"23456789\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"keyword\": [\n" +
        "    {\n" +
        "      \"nb\": \"test\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"issued\": \"2020-04-27T00:00:00.000+0000\",\n" +
        "  \"modified\": \"2020-04-27T00:00:00.000+0000\",\n" +
        "  \"language\": [\n" +
        "    {\n" +
        "      \"uri\": \"http://publications.europa.eu/resource/authority/language/NOR\",\n" +
        "      \"code\": \"NOR\",\n" +
        "      \"prefLabel\": {\n" +
        "        \"nb\": \"Norsk\"\n" +
        "      }\n" +
        "    }\n" +
        "  ],\n" +
        "  \"landingPage\": [\n" +
        "    \"https://fellesdatakatalog.digdir.no\"\n" +
        "  ],\n" +
        "  \"theme\": [\n" +
        "    {\n" +
        "      \"uri\": \"https://psi.norge.no/los/tema/arbeid\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"uri\": \"https://psi.norge.no/los/tema/arbeidssoking\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"uri\": \"https://psi.norge.no/los/ord/arbeidsmarkedstiltak\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"distribution\": [\n" +
        "    {\n" +
        "      \"title\": {\n" +
        "        \"nb\": \"Test distribusjon\"\n" +
        "      },\n" +
        "      \"description\": {\n" +
        "        \"nb\": \"test\"\n" +
        "      },\n" +
        "      \"downloadURL\": [\n" +
        "        \"https://demo.fellesdatakatalog.digdir.no\"\n" +
        "      ],\n" +
        "      \"accessURL\": [\n" +
        "        \"https://demo.fellesdatakatalog.digdir.no\"\n" +
        "      ],\n" +
        "      \"license\": {\n" +
        "        \"uri\": \"http://creativecommons.org/licenses/by/4.0/\",\n" +
        "        \"prefLabel\": {\n" +
        "          \"en\": \"Creative Commons Attribution 4.0 International\",\n" +
        "          \"no\": \"Creative Commons Navngivelse 4.0 Internasjonal\"\n" +
        "        }\n" +
        "      },\n" +
        "      \"conformsTo\": [\n" +
        "        {\n" +
        "          \"uri\": \"https://demo.fellesdatakatalog.digdir.no\",\n" +
        "          \"prefLabel\": {\n" +
        "            \"nb\": \"Test\"\n" +
        "          }\n" +
        "        }\n" +
        "      ],\n" +
        "      \"page\": [\n" +
        "        {\n" +
        "          \"uri\": \"https://demo.fellesdatakatalog.digdir.no\",\n" +
        "          \"prefLabel\": {}\n" +
        "        }\n" +
        "      ],\n" +
        "      \"format\": [\n" +
        "        \"text/csv\"\n" +
        "      ],\n" +
        "      \"accessService\": [\n" +
        "        {\n" +
        "          \"description\": {\n" +
        "            \"nb\": \"Nøkkeltall fra årsregnskapet Brasker kommune\"\n" +
        "          },\n" +
        "          \"endpointDescription\": [\n" +
        "            {\n" +
        "              \"uri\": \"6a507471-2245-46f0-9def-ecb42decd12b\"\n" +
        "            }\n" +
        "          ]\n" +
        "        }\n" +
        "      ]\n" +
        "    }\n" +
        "  ],\n" +
        "  \"sample\": [\n" +
        "    {\n" +
        "      \"description\": {\n" +
        "        \"nb\": \"test\"\n" +
        "      },\n" +
        "      \"accessURL\": [\n" +
        "        \"https://demo.fellesdatakatalog.digdir.no\"\n" +
        "      ],\n" +
        "      \"license\": {\n" +
        "        \"uri\": \"\",\n" +
        "        \"prefLabel\": {}\n" +
        "      },\n" +
        "      \"conformsTo\": [],\n" +
        "      \"page\": [\n" +
        "        {\n" +
        "          \"uri\": \"\",\n" +
        "          \"prefLabel\": {}\n" +
        "        }\n" +
        "      ],\n" +
        "      \"format\": [\n" +
        "        \"A2L\"\n" +
        "      ]\n" +
        "    }\n" +
        "  ],\n" +
        "  \"temporal\": [\n" +
        "    {\n" +
        "      \"startDate\": \"2020-04-27T00:00:00.000+0000\",\n" +
        "      \"endDate\": \"2020-08-15T00:00:00.000+0000\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"spatial\": [\n" +
        "    {\n" +
        "      \"uri\": \"Oslo\",\n" +
        "      \"prefLabel\": {}\n" +
        "    }\n" +
        "  ],\n" +
        "  \"accessRights\": {\n" +
        "    \"uri\": \"http://publications.europa.eu/resource/authority/access-right/PUBLIC\",\n" +
        "    \"prefLabel\": {}\n" +
        "  },\n" +
        "  \"legalBasisForRestriction\": [\n" +
        "    {\n" +
        "      \"uri\": \"\",\n" +
        "      \"prefLabel\": {}\n" +
        "    }\n" +
        "  ],\n" +
        "  \"legalBasisForProcessing\": [\n" +
        "    {\n" +
        "      \"uri\": \"\",\n" +
        "      \"prefLabel\": {}\n" +
        "    }\n" +
        "  ],\n" +
        "  \"legalBasisForAccess\": [\n" +
        "    {\n" +
        "      \"uri\": \"\",\n" +
        "      \"prefLabel\": {}\n" +
        "    }\n" +
        "  ],\n" +
        "  \"hasAccuracyAnnotation\": {\n" +
        "    \"inDimension\": \"iso:Accuracy\",\n" +
        "    \"motivatedBy\": \"dqv:qualityAssessment\",\n" +
        "    \"hasBody\": {\n" +
        "      \"nb\": \"test\"\n" +
        "    }\n" +
        "  },\n" +
        "  \"hasCompletenessAnnotation\": {\n" +
        "    \"inDimension\": \"iso:Completeness\",\n" +
        "    \"motivatedBy\": \"dqv:qualityAssessment\",\n" +
        "    \"hasBody\": {\n" +
        "      \"nb\": \"test\"\n" +
        "    }\n" +
        "  },\n" +
        "  \"hasCurrentnessAnnotation\": {\n" +
        "    \"inDimension\": \"iso:Currentness\",\n" +
        "    \"motivatedBy\": \"dqv:qualityAssessment\",\n" +
        "    \"hasBody\": {\n" +
        "      \"nb\": \"test\"\n" +
        "    }\n" +
        "  },\n" +
        "  \"hasAvailabilityAnnotation\": {\n" +
        "    \"inDimension\": \"iso:Availability\",\n" +
        "    \"motivatedBy\": \"dqv:qualityAssessment\",\n" +
        "    \"hasBody\": {\n" +
        "      \"nb\": \"test\"\n" +
        "    }\n" +
        "  },\n" +
        "  \"hasRelevanceAnnotation\": {\n" +
        "    \"inDimension\": \"iso:Relevance\",\n" +
        "    \"motivatedBy\": \"dqv:qualityAssessment\",\n" +
        "    \"hasBody\": {\n" +
        "      \"nb\": \"test\"\n" +
        "    }\n" +
        "  },\n" +
        "  \"references\": [\n" +
        "    {\n" +
        "      \"referenceType\": {\n" +
        "        \"uri\": \"http://purl.org/dc/terms/isReferencedBy\",\n" +
        "        \"code\": \"isReferencedBy\",\n" +
        "        \"prefLabel\": {\n" +
        "          \"en\": \"Is Referenced By\",\n" +
        "          \"nn\": \"Er referert av\",\n" +
        "          \"nb\": \"Er referert av\"\n" +
        "        }\n" +
        "      },\n" +
        "      \"source\": {\n" +
        "        \"uri\": \"http://brreg.no/catalogs/555111020/datasets/2d106704-87ff-4c86-863f-007a56afa89a\"\n" +
        "      }\n" +
        "    }\n" +
        "  ],\n" +
        "  \"relations\": [\n" +
        "    {\n" +
        "      \"uri\": \"https://demo.fellesdatakatalog.digdir.no\",\n" +
        "      \"prefLabel\": {\n" +
        "        \"nb\": \"Test\"\n" +
        "      }\n" +
        "    }\n" +
        "  ],\n" +
        "  \"provenance\": {\n" +
        "    \"uri\": \"http://data.brreg.no/datakatalog/provinens/bruker\",\n" +
        "    \"code\": \"BRUKER\",\n" +
        "    \"prefLabel\": {\n" +
        "      \"nb\": \"Brukerinnsamlede data\",\n" +
        "      \"nn\": \"Brukerinnsamlede data\",\n" +
        "      \"en\": \"User collection\"\n" +
        "    }\n" +
        "  },\n" +
        "  \"accrualPeriodicity\": {\n" +
        "    \"uri\": \"http://publications.europa.eu/resource/authority/frequency/ANNUAL\",\n" +
        "    \"code\": \"ANNUAL\",\n" +
        "    \"prefLabel\": {\n" +
        "      \"de\": \"jährlich\",\n" +
        "      \"lv\": \"reizi gadā\",\n" +
        "      \"bg\": \"годишен\",\n" +
        "      \"nl\": \"jaarlijks\",\n" +
        "      \"sk\": \"ročný\",\n" +
        "      \"no\": \"årlig\",\n" +
        "      \"sv\": \"årlig\",\n" +
        "      \"pl\": \"roczny\",\n" +
        "      \"hr\": \"godišnje\",\n" +
        "      \"sl\": \"letni\",\n" +
        "      \"fi\": \"vuotuinen\",\n" +
        "      \"mt\": \"annwali\",\n" +
        "      \"lt\": \"kasmetinis\",\n" +
        "      \"fr\": \"annuel\",\n" +
        "      \"ga\": \"bliantúil\",\n" +
        "      \"da\": \"årligt\",\n" +
        "      \"et\": \"aastane\",\n" +
        "      \"pt\": \"anual\",\n" +
        "      \"ro\": \"anual\",\n" +
        "      \"es\": \"anual\",\n" +
        "      \"el\": \"ετήσιος\",\n" +
        "      \"en\": \"annual\",\n" +
        "      \"cs\": \"roční\",\n" +
        "      \"it\": \"annuale\",\n" +
        "      \"hu\": \"évenkénti\"\n" +
        "    }\n" +
        "  },\n" +
        "  \"subject\": [\n" +
        "    {\n" +
        "      \"uri\": \"https://fellesdatakatalog.brreg.no/api/concepts/5fd83ce0-95a3-45b9-9525-ba7b1bd8b025\",\n" +
        "      \"identifier\": \"https://registrering-begrep-api.demo.fellesdatakatalog.brreg.no/555111006/bdb97fc2-c0e4-4b9d-b669-c5c5c0bdf52b\",\n" +
        "      \"prefLabel\": {\n" +
        "        \"nb\": \"test\"\n" +
        "      },\n" +
        "      \"altLabel\": [],\n" +
        "      \"definition\": {\n" +
        "        \"nb\": \"test\"\n" +
        "      }\n" +
        "    }\n" +
        "  ],\n" +
        "  \"concepts\": [\n" +
        "    {\n" +
        "      \"id\": \"5fd83ce0-95a3-45b9-9525-ba7b1bd8b025\",\n" +
        "      \"uri\": \"https://fellesdatakatalog.brreg.no/api/concepts/5fd83ce0-95a3-45b9-9525-ba7b1bd8b025\",\n" +
        "      \"identifier\": \"https://registrering-begrep-api.demo.fellesdatakatalog.brreg.no/555111006/bdb97fc2-c0e4-4b9d-b669-c5c5c0bdf52b\",\n" +
        "      \"application\": [],\n" +
        "      \"definition\": {\n" +
        "        \"text\": {\n" +
        "          \"nb\": \"test\"\n" +
        "        },\n" +
        "        \"sources\": []\n" +
        "      },\n" +
        "      \"prefLabel\": {\n" +
        "        \"nb\": \"test\"\n" +
        "      },\n" +
        "      \"altLabel\": [],\n" +
        "      \"hiddenLabel\": []\n" +
        "    }\n" +
        "  ],\n" +
        "  \"conformsTo\": [\n" +
        "    {\n" +
        "      \"uri\": \"https://demo.fellesdatakatalog.digdir.no\",\n" +
        "      \"prefLabel\": {\n" +
        "        \"nb\": \"test\"\n" +
        "      }\n" +
        "    }\n" +
        "  ],\n" +
        "  \"informationModel\": [\n" +
        "    {\n" +
        "      \"uri\": \"https://uio.no\",\n" +
        "      \"prefLabel\": {\n" +
        "        \"nb\": \"test\"\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"uri\": \"https://www.demo.fellesdatakatalog.digdir.no/informationmodels/5a636bad-a733-4629-8db3-c19caee5a13d\",\n" +
        "      \"prefLabel\": {\n" +
        "        \"nb\": \"Innkjøpsordningar\"\n" +
        "      }\n" +
        "    }\n" +
        "  ],\n" +
        "  \"type\": \"Data\",\n" +
        "  \"catalogId\": \"555111020\",\n" +
        "  \"_lastModified\": \"2020-05-03T09:17:48.574+0000\",\n" +
        "  \"registrationStatus\": \"PUBLISH\"\n" +
        "}"