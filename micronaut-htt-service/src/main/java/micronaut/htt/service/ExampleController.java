package micronaut.htt.service;

import java.io.IOException;

import antessio.Kanye;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micronaut.http.annotation.*;

@Controller("/")
public class ExampleController {
    private static final Logger LOG = LoggerFactory.getLogger(ExampleController.class);
    RxHttpClient httpClient;
    ObjectMapper objectMapper;

    public ExampleController(@Client("http://api.kanye.rest") RxHttpClient httpClient) {
        this.httpClient = httpClient;
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    }

    @Get("/ping")
    public String index() {
        return "{\"pong\":true, \"graal\": true}";
    }

    @Get("/kanye")
    public Kanye kanye() {

        HttpResponse<String> response = httpClient.exchange(HttpRequest.GET("/")
                        .contentType(MediaType.APPLICATION_JSON_TYPE),
                String.class)
                .blockingFirst();
        LOG.info("response status {} and  body {}", response.getStatus().getCode(), response.body());
        try {
            return objectMapper.readValue(response.body(), Kanye.class);
        } catch (IOException e) {
            return new Kanye("I'm a gay fish (South Park quote)");
        }
    }
}
