package com.demo.academiacx.service;

import com.demo.academiacx.handler.ConflitoRecursoException;
import com.demo.academiacx.handler.ParametroInvalidoException;
import com.demo.academiacx.handler.RecursoNaoEncontradoException;
import com.demo.academiacx.model.CarrinhoModel;
import com.demo.academiacx.model.ClienteModel;
import com.demo.academiacx.model.dto.CarrinhoDto;
import com.demo.academiacx.model.dto.ClienteDto;
import com.demo.academiacx.repository.CarrinhoRepository;
import com.demo.academiacx.utils.ValidacaoUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

    private final ModelMapper modelMapper;

    private final CarrinhoRepository carrinhoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, ModelMapper modelMapper) {
        this.carrinhoRepository = carrinhoRepository;
        this.modelMapper = modelMapper;
    }

    public List<CarrinhoDto> findAll(){
        List<CarrinhoModel> carrinhoModel = carrinhoRepository.findAll();
        return modelMapper.map(carrinhoModel, new TypeToken<List<CarrinhoDto>>(){
        }.getType());
    }

    public CarrinhoDto findById(Long id){

        if(id == null){
            throw new ParametroInvalidoException("Id informada é inválida");
        }

        CarrinhoModel carrinhoModel = new CarrinhoModel();
        try{
            carrinhoRepository.findById(id).get();

        }catch (Exception e){
            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }
        return modelMapper.map(carrinhoModel, CarrinhoDto.class);
    }

    public CarrinhoDto findById(CarrinhoModel carrinhoModel){

        if(carrinhoModel == null){
            throw new ParametroInvalidoException("O Carrinho Model precisa ser informado");
        }

        if(carrinhoModel.getId() == null){

            throw new ParametroInvalidoException("O Carrinho Model precisa ter um id");
        }

        try{
            carrinhoRepository.findById(carrinhoModel.getId()).get();

        }catch (Exception e){
            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }
        return modelMapper.map(carrinhoModel, CarrinhoDto.class);
    }

    public CarrinhoDto save(CarrinhoDto carrinhoDto){

        validateSave(carrinhoDto);

        CarrinhoModel carrinhoModel = carrinhoRepository.save(modelMapper.map(carrinhoDto, CarrinhoModel.class));

        return modelMapper.map(carrinhoModel, CarrinhoDto.class);
    }

    public CarrinhoDto update(CarrinhoDto carrinhoDto){

        findById(carrinhoDto.getId());

        CarrinhoModel carrinhoModel = carrinhoRepository.save(modelMapper.map(carrinhoDto, CarrinhoModel.class));

        return modelMapper.map(carrinhoModel, CarrinhoDto.class);
    }



    public boolean delete(Long id){

        try {
            carrinhoRepository.deleteById(id);

        } catch (Exception e){
            throw new ConflitoRecursoException("Solicitação atual conflitou com o recurso que está no servidor");
        }

        return true;
    }

    public List<CarrinhoDto> findByClienteId(Long id) {


        Optional<List<CarrinhoModel>> carrinhoModels = carrinhoRepository.findByClienteId(id);

        return modelMapper.map(carrinhoModels.get(), new TypeToken<List<CarrinhoDto>>() {
        }.getType());
    }


    private void validateSave(CarrinhoDto carrinhoDto) {

        ValidacaoUtils.idNaoNula(carrinhoDto.getId(), "Id inválida! Tente novamente!");

        ValidacaoUtils.dataNaoVazia(carrinhoDto.getDatahora(), "Data informada inválida");

        ValidacaoUtils.totalPositivo(carrinhoDto.getTotal(), "Total inválido! ");

        if(carrinhoDto.getCliente() == null){
            throw new ParametroInvalidoException("Id do Cliente inválido, digite outro");
        }
    }
}
