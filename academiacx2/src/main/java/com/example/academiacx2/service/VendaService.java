package com.example.academiacx2.service;

import com.example.academiacx2.handler.exceptions.ParametroInvalidoException;
import com.example.academiacx2.handler.exceptions.RecursoNaoEncontradoException;
import com.example.academiacx2.model.VendaModel;
import com.example.academiacx2.model.dto.VendaDto;
import com.example.academiacx2.repository.VendaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ModelMapper modelMapper;

    public VendaService(VendaRepository vendaRepository, ModelMapper modelMapper) {
        this.vendaRepository = vendaRepository;
        this.modelMapper = modelMapper;
    }

    public List<VendaDto> findAll(){
        List<VendaModel> vendaModel = vendaRepository.findAll();

        return modelMapper.map(vendaModel, new TypeToken<List<VendaDto>>(){
        }.getType());
    }

    public VendaDto findById(VendaModel vendaModel){

        if (vendaModel == null) {
            throw  new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        if (vendaModel.getId() == null){
            throw  new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        try {
            vendaModel =vendaRepository.findById(vendaModel.getId()).get();
        } catch (Exception e){
            throw  new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        return modelMapper.map(vendaModel, VendaDto.class);
    }

    public VendaDto findById(Long id) {

        if(id == null){
            throw  new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        VendaModel vendaModel = new VendaModel();

        try {
            vendaModel = vendaRepository.findById(id).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        return modelMapper.map(vendaModel, VendaDto.class);
    }

    public VendaDto insert(VendaDto vendaDto) {
        vendaDto.setId(null);

        validarInsert(vendaDto);

        VendaModel vendaModel = vendaRepository.save(modelMapper.map(vendaDto, VendaModel.class));

        return modelMapper.map(vendaModel,VendaDto.class);
    }

    public VendaModel update(VendaModel vendaModel) {

        if(!vendaRepository.existsById(vendaModel.getId())){
            throw new RecursoNaoEncontradoException("Id não encontrado");
        }

        return vendaRepository.save(vendaModel);
    }

    public boolean delete(Long id){

        if(!vendaRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Id não encontrado");
        }

        vendaRepository.deleteById(id);

        return true;
    }

    public List<VendaModel> findByCarrinhoModelOrClienteModelOrEnderecoModelOrItemModel(Long id_carrinho, Long id_cliente, Long id_endereco, Long id_item) {

        Optional<List<VendaModel>> listVendaModel = vendaRepository.findByCarrinhoModelOrClienteModelOrEnderecoModelOrItemModel(id_carrinho,id_cliente,id_endereco,id_item);

        if (listVendaModel.isPresent()) {
            return listVendaModel.stream().findFirst().get();
        } else {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }
    }

    public void validarInsert(VendaDto vendaDto){

        if(vendaDto.getId() == null){
            throw new ParametroInvalidoException("Id inválido, digite outro");
        }

        if(vendaDto.getClienteModel() == null){
            throw new ParametroInvalidoException("Id do cliente inválido, digite outro");
        }

        if(vendaDto.getCarrinhoModel() == null){
            throw new ParametroInvalidoException("Id do carrinho inválido, digite outro");
        }

        if(vendaDto.getEnderecoModel() == null){
            throw new ParametroInvalidoException("Id do endereco inválido, digite outro");
        }

        if(vendaDto.getItemModel() == null){
            throw new ParametroInvalidoException("Id do item inválido, digite outro");
        }
    }
}
