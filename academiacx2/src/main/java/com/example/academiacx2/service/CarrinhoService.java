package com.example.academiacx2.service;

import com.example.academiacx2.handler.exceptions.RecursoNaoEncontradoException;
import com.example.academiacx2.handler.exceptions.ParametroInvalidoException;
import com.example.academiacx2.model.CarrinhoModel;
import com.example.academiacx2.model.ClienteModel;
import com.example.academiacx2.model.dto.CarrinhoDto;
import com.example.academiacx2.repository.CarrinhoRepository;
import com.example.academiacx2.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ModelMapper modelMapper;
    public CarrinhoService(CarrinhoRepository carrinhoRepository, ModelMapper modelMapper) {
        this.carrinhoRepository = carrinhoRepository;
        this.modelMapper = modelMapper;
    }

    public List<CarrinhoModel> findall(){
        List<CarrinhoModel> carrinhoModels = carrinhoRepository.findAll();

        return modelMapper.map(carrinhoModels, new TypeToken<List<CarrinhoDto>>(){
        }.getType());
    }

    public CarrinhoDto findById(CarrinhoModel carrinhoModel) {

        if (carrinhoModel == null) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");

        }

        if (carrinhoModel.getId() == null) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");

        }

        try {
            carrinhoModel = carrinhoRepository.findById(carrinhoModel.getId()).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        return modelMapper.map(carrinhoModel, CarrinhoDto.class);
    }

    public CarrinhoDto findById(Long id) {

        if(id == null){
            throw  new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        CarrinhoModel carrinhoModel;

        try {
            carrinhoModel = carrinhoRepository.findById(id).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        return modelMapper.map(carrinhoModel, CarrinhoDto.class);
    }


    public CarrinhoDto insert(CarrinhoDto carrinhoDto) {
        carrinhoDto.setId(null);

        validarInsert(carrinhoDto);

        CarrinhoModel carrinhoModel = carrinhoRepository.save(modelMapper.map(carrinhoDto, CarrinhoModel.class));

        return modelMapper.map(carrinhoModel,CarrinhoDto.class);
    }

    public CarrinhoModel update(CarrinhoModel carrinhoModel) {

        if(!carrinhoRepository.existsById(carrinhoModel.getId())){
            throw new RecursoNaoEncontradoException("Id não encontrado");
        }

        return carrinhoRepository.save(carrinhoModel);
    }

    public boolean delete(Long id){

        if(!carrinhoRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Id não encontrado");
        }

        carrinhoRepository.deleteById(id);

        return true;
    }

    public List<CarrinhoModel> findByDatahoraOrTotalOrId(LocalDateTime datahora, Double total,Long id) {

        Optional<List<CarrinhoModel>> listCarrinhoModel = carrinhoRepository.findByDatahoraOrTotalOrId(datahora, total, id);

        if (listCarrinhoModel.isPresent()) {
            return listCarrinhoModel.stream().findAny().get();

        } else {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }
    }

    public List<CarrinhoModel> findByClienteModel_Id(Long id) {

        Optional<List<CarrinhoModel>> listCarrinhoModel = carrinhoRepository.findByClienteModel_Id(id);

        if (listCarrinhoModel.isPresent()) {
            return listCarrinhoModel.stream().findAny().get();

        } else {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }
    }

    public void validarInsert(CarrinhoDto carrinhoDto){

        if(carrinhoDto.getId() == null){
            throw new ParametroInvalidoException("Id inválido, digite outro");
        }

        if(carrinhoDto.getDatahora() == null){
            throw new ParametroInvalidoException("Horário e Data inválidos, digite outro");
        }

        if(carrinhoDto.getTotal() == null){
            throw new ParametroInvalidoException("Valor Total inválido, digite outro");
        }

        if(carrinhoDto.getClienteModel() == null){
            throw new ParametroInvalidoException("Id do Cliente inválido, digite outro");
        }
    }
}
