package nu.westlin.springhttpinterfacesrestclient

import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.ConversionService
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import org.springframework.web.service.invoker.createClient


@Configuration
class RestClientConfiguration {

    @Bean
    fun httpStatusService(
        restClientBuilder: RestClient.Builder,
        conversionService: ConversionService,
        serverProperties: ServerProperties
    ): HttpStatusService {

        // TODO pwestlin: Use server.port
        val restClient = restClientBuilder
            .baseUrl("http://localhost:${serverProperties.port}")
            /*
                        .defaultStatusHandler(HttpStatusCode::is5xxServerError) { _, response ->
                            throw HttpServerErrorException(
                                response.statusCode,
                                response.statusText,
                                response.headers,
                                response.body.readAllBytes(),
                                Charset.defaultCharset()
                            )
                        }
            */
            .defaultStatusHandler({ it == HttpStatus.NOT_FOUND }) { _, _ ->
                // Do nothing for 404, i.e. let the client handle the status
            }
            /*
                        .defaultStatusHandler(HttpStatusCode::isError) {_,_ ->
                            // Do nothing, i.e. let the client handle the status
                        }
            */
            .build()
        val adapter = RestClientAdapter.create(restClient)
        val factory = HttpServiceProxyFactory.builderFor(adapter)
            .conversionService(conversionService)
            .build()

        return factory.createClient<HttpStatusService>()
    }
}
