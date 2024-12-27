package nu.westlin.springhttpinterfacesrestclient

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.service.annotation.GetExchange
import java.util.*

interface HttpStatusService {

    // TODO pwestlin: Use HttpStatus instead of Int.
    //  Do I need to use a converter for HttpStatus -> Int?
    @GetExchange("/status/{status}")
    fun status(@PathVariable status: Int): ResponseEntity<Unit>

    @GetExchange("/status/notfound")
    fun notFound(): String?

    // Note! String is null at runtime!
    @GetExchange("/status/notfound")
    fun found(): String

    @GetExchange("/status/find/{toBeFound}")
    fun find(@PathVariable toBeFound: Boolean): Optional<String>

    @GetExchange("/status/find/{toBeFound}")
    fun find2(@PathVariable toBeFound: Boolean): String?
}
