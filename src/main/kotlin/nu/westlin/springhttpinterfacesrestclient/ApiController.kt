package nu.westlin.springhttpinterfacesrestclient

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api")
class ApiController(
    private val httpStatusService: HttpStatusService
) {

    @GetMapping("/status/{status}")
    fun status(@PathVariable status: Int): ResponseEntity<Unit> = httpStatusService.status(status)

    @GetMapping("/notfound")
    fun notFound(): String? = httpStatusService.notFound()

    @GetMapping("/found")
    fun found(): String {
        return httpStatusService.found()
    }
}
