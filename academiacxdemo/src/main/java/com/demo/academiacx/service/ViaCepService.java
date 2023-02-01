package com.demo.academiacx.service;
import com.demo.academiacx.model.ViaCepModel;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ViaCepService {

    private RestTemplate restTemplate = new RestTemplate();

    public ViaCepModel getViaCep(String cep) {
        String url = "http://viacep.com.br/ws/" + cep + "/json/";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<ViaCepModel> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, ViaCepModel.class);

        return responseEntity.getBody();
    }

}