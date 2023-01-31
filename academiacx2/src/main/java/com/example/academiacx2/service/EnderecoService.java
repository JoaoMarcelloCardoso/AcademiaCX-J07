package com.example.academiacx2.service;

import com.example.academiacx2.handler.exceptions.RecursoNaoEncontradoException;
import com.example.academiacx2.handler.exceptions.ParametroInvalidoException;
import com.example.academiacx2.model.CarrinhoModel;
import com.example.academiacx2.model.EnderecoModel;
import com.example.academiacx2.model.dto.EnderecoDto;
import com.example.academiacx2.repository.EnderecoRepository;
import com.example.academiacx2.utils.Validation;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ModelMapper modelMapper;

    public EnderecoService(EnderecoRepository enderecoRepository, ModelMapper modelMapper) {
        this.enderecoRepository = enderecoRepository;
        this.modelMapper = modelMapper;
    }

    public List<EnderecoDto> findall(){
        List<EnderecoModel> enderecoModels = enderecoRepository.findAll();

        return modelMapper.map(enderecoModels, new TypeToken<List<EnderecoDto>>(){
        }.getType());
    }

    public EnderecoDto findById(EnderecoModel enderecoModel) {

        if (enderecoModel == null) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");

        }

        if (enderecoModel.getId() == null) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");

        }

        try {
            enderecoModel = enderecoRepository.findById(enderecoModel.getId()).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        return modelMapper.map(enderecoModel, EnderecoDto.class);
    }

    public EnderecoDto findById(Long id) {

        if(id == null){
            throw  new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        EnderecoModel enderecoModel = new EnderecoModel();

        try {
            enderecoModel = enderecoRepository.findById(id).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        return modelMapper.map(enderecoModel, EnderecoDto.class);
    }

    public EnderecoDto insert(EnderecoDto enderecoDto) {
        enderecoDto.setId(null);

        validarInsert(enderecoDto);

        EnderecoModel enderecoModel = enderecoRepository.save(modelMapper.map(enderecoDto, EnderecoModel.class));

        return modelMapper.map(enderecoModel,EnderecoDto.class);
    }

    public EnderecoModel update(EnderecoModel enderecoModel) {

        if(!enderecoRepository.existsById(enderecoModel.getId())){
            throw new RecursoNaoEncontradoException("Id não encontrado");
        }

        return enderecoRepository.save(enderecoModel);
    }

    public boolean delete(Long id){

        if(!enderecoRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Id não encontrado");
        }

        enderecoRepository.deleteById(id);

        return true;
    }

    public List<EnderecoModel> findByCepOrLogradouroOrNumeroOrBairroOrCidadeOrUf(String cep, String logradouro, String numero, String bairro, String cidade, String uf) {

        Optional<List<EnderecoModel>> listEnderecoModel = enderecoRepository.findByCepOrLogradouroOrNumeroOrBairroOrCidadeOrUf(cep, logradouro, numero, bairro, cidade, uf);

        if (listEnderecoModel.isPresent()) {


            return listEnderecoModel.stream().findAny().get();
        } else {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }
    }

    public List<EnderecoModel> findByClienteModel_Id(Long id) {

        Optional<List<EnderecoModel>> listEnderecoModel = enderecoRepository.findByClienteModel_Id(id);

        if (listEnderecoModel.isPresent()) {
            return listEnderecoModel.stream().findAny().get();

        } else {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }
    }

    public void validarInsert(EnderecoDto enderecoDto){

        if(enderecoDto.getId() == null){
            throw new ParametroInvalidoException("Id inválido, digite outro");
        }

        if(enderecoDto.getLogradouro() == null){
            throw new ParametroInvalidoException("Logradouro inválido, digite outro");
        }

        if(enderecoDto.getNumero() == null){
            throw new ParametroInvalidoException("Numero inválido, digite outro");
        }

        if(enderecoDto.getBairro() == null){
            throw new ParametroInvalidoException("Bairro inválido, digite outro");
        }

        if(enderecoDto.getCidade() == null){
            throw new ParametroInvalidoException("Cidade inválida, digite outro");
        }

        Validation.ufValidation(enderecoDto.getUf(),"Uf inválida, digite outro");

        if(enderecoDto.getCep() == null){
            throw new ParametroInvalidoException("Cep inválido, digite outro");
        }

        if(enderecoDto.getClienteModel() == null){
            throw new ParametroInvalidoException("Id do Cliente inválido, digite outro");
        }
    }

}
