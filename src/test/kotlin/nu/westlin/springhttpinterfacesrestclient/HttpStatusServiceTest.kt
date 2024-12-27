package nu.westlin.springhttpinterfacesrestclient

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class HttpStatusServiceTest(
    @Autowired private val service: HttpStatusService
) {

    @Test
    fun `found should, according to the compiler, be not null but it is null`() {
        assertThat(service.found()).isNull()
    }

    @Test
    fun `find - found`() {
        val found = service.find(toBeFound = true)
        assertThat(found.orElse(null)).isEqualTo("A String")
    }

    @Test
    fun `find - not found`() {
        val found = service.find(toBeFound = false)
        assertThat(found.orElse(null)).isNull()
    }

    @Test
    fun `find2 - found`() {
        assertThat(service.find2(toBeFound = true)).isEqualTo("A String")
    }

    @Test
    fun `find2 - not found`() {
        assertThat(service.find2(toBeFound = false)).isNull()
    }

    @Test
    fun `notFound should return null`() {
        assertThat(service.notFound()).isNull()
    }
}