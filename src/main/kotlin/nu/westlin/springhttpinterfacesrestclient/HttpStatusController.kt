package nu.westlin.springhttpinterfacesrestclient

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/status")
class HttpStatusController {

    @GetMapping("/{status}")
    fun status(@PathVariable status: Int): ResponseEntity<Unit> {
        return ResponseEntity.status(status).build()
    }

    @GetMapping("/notfound")
    fun notFound(): ResponseEntity<String> {
        return ResponseEntity.notFound().build()
    }

    @GetMapping("/find/{toBeFound}")
    fun find(@PathVariable toBeFound: Boolean): ResponseEntity<String> {
        return if(toBeFound) {
            ResponseEntity.ok("A String")
        } else {
            ResponseEntity.notFound().build()
        }
    }
}