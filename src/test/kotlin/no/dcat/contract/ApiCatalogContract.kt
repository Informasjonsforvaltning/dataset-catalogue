package no.dcat.contract

import com.jayway.jsonpath.matchers.JsonPathMatchers.isJson
import com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath
import no.dcat.utils.ApiTestContainer
import no.dcat.utils.apiAuthorizedRequest
import no.dcat.utils.jwk.Access
import no.dcat.utils.jwk.JwtToken
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.http.HttpStatus
import kotlin.test.Ignore

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("contract")
@Ignore
class CatalogContract : ApiTestContainer() {

    @Test
    fun createCatalog() {
        val orgNo = "910244132"

        val response = apiAuthorizedRequest(
            "/catalogs",
            "{\"id\": \"$orgNo\"}",
            JwtToken(Access.ORG_WRITE).toString(),
            "POST")

        assertEquals(HttpStatus.OK.value(), response["status"])
        MatcherAssert.assertThat(
            response["body"],
            isJson(
                CoreMatchers.allOf(
                    withJsonPath("$.id", CoreMatchers.equalTo(orgNo)),
                    withJsonPath("$.publisher.id", CoreMatchers.equalTo(orgNo)),
                    withJsonPath("$.uri", CoreMatchers.equalTo("http://dataset-catalogue:8080/catalogs/$orgNo")),
                    withJsonPath("$.publisher.uri", CoreMatchers.equalTo("https://data.brreg.no/enhetsregisteret/api/enheter/$orgNo")))))
    }

}