package com.academiacx.service;

import com.academiacx.handler.exceptions.RecursoNaoEncontradoException;
import com.academiacx.model.ViaCepModel;
import com.academiacx.utils.ValidacaoUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ViaCepService {

    private RestTemplate restTemplate = new RestTemplate();

    public ViaCepModel getViaCep(String cep) {

        ValidacaoUtils.validarCep(cep, "CEP informado é inválido!");

        String url = "http://viacep.com.br/ws/" + cep + "/json/";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<ViaCepModel> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, ViaCepModel.class);

        if (responseEntity.getBody().getCep() == null) {
            throw new RecursoNaoEncontradoException("CEP inserido não encontrado!");
        }

        return responseEntity.getBody();
    }

}
