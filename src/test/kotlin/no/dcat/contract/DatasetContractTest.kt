package no.dcat.contract

import no.dcat.utils.ApiTestContainer
import no.dcat.utils.apiAuthorizedRequest
import no.dcat.utils.datasetWithOriginalUri
import no.dcat.utils.jwk.Access
import no.dcat.utils.jwk.JwtToken
import no.dcat.utils.turtleApiAuthorizedRequest
import org.junit.jupiter.api.*
import org.springframework.http.HttpStatus
import kotlin.test.Ignore
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("contract")
@Ignore
class DatasetContractTest : ApiTestContainer() {

    val orgNo = "910244132"
    @BeforeAll
    fun setup() {
        val response = apiAuthorizedRequest(
                "/catalogs",
                "{\"id\": \"$orgNo\"}",
                JwtToken(Access.ORG_WRITE).toString(),
                "POST")

        Assumptions.assumeTrue(HttpStatus.OK.value() == response["status"])
    }

    @Test
    fun `should create dataset with original uri`() {
        val expectedUri = "https://the.og.uri.no/node/1237"

        val createResponse = apiAuthorizedRequest(
                "/catalogs/$orgNo/datasets",
                datasetWithOriginalUri,
                JwtToken(Access.ORG_WRITE).toString(),
                "POST")

        var originalUriIndex = (createResponse["body"] as CharSequence).indexOf("originalUri") + 14
        val originalUriResult = (createResponse["body"] as String).substring(originalUriIndex,originalUriIndex+31)
        Assertions.assertEquals(expectedUri,originalUriResult)

        val turtleResponse = turtleApiAuthorizedRequest(
                endpoint = "/catalogs/$orgNo",
                token = JwtToken(Access.ORG_WRITE).toString(),
                method = "GET"
        )
        Assertions.assertTrue((turtleResponse["body"] as CharSequence).indexOf(expectedUri) != -1)


    }

}