package com.UsuariosCadastro.cadastro.intregacaoExterna.client;

import com.UsuariosCadastro.cadastro.model.dto.BeerRequest;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BeerClientTest {

    private static String BASE_URL = "https://api.punkapi.com/v2/beers";
    private static String RESPONSE_BEER = "[\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"name\": \"Buzz\",\n" +
            "        \"description\": \"A light, crisp and bitter IPA brewed with English and American hops. A small batch brewed only once.\",\n" +
            "        \"abv\": 4,\n" +
            "        \"ibu\": 60\n" +
            "    }\n" +
            "]";

    private BeerClient beerClient;

    @Test
    public void getRandomBeer() {
        this.builderFeignClient(new MockClient().ok(
                HttpMethod.GET,
                BASE_URL.concat("/random"),
                RESPONSE_BEER
        ));

        List<BeerRequest> randomBeer = beerClient.getRandomBeer();

        assertFalse(randomBeer.isEmpty());
        assertThat(randomBeer.get(0).getId(), equalTo(1L));
    }

    @Test
    public void getIdBeer() {
        this.builderFeignClient(new MockClient().ok(
                HttpMethod.GET,
                BASE_URL.concat("/{id}"),
                RESPONSE_BEER
        ));

        List<BeerRequest> randomBeer = beerClient.getBeerById(1L);

        assertFalse(randomBeer.isEmpty());
        assertThat(randomBeer.get(0).getId(), equalTo(1L));
    }

    private void builderFeignClient(MockClient client) {
        beerClient = Feign.builder()
                .client(client)
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new SpringMvcContract())
                .target(BeerClient.class, BASE_URL);
    }

    @Test
    void getBeerById() {
    }
}