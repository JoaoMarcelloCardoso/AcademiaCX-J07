package com.demo.academiacx.service;

import com.demo.academiacx.handler.ConflitoRecursoException;
import com.demo.academiacx.handler.ParametroInvalidoException;
import com.demo.academiacx.handler.RecursoNaoEncontradoException;
import com.demo.academiacx.model.PedidoModel;
import com.demo.academiacx.model.dto.PedidoDto;
import com.demo.academiacx.repository.PedidoRepository;
import com.demo.academiacx.utils.ValidacaoUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final ModelMapper modelMapper;

    public PedidoService(PedidoRepository pedidoRepository, ModelMapper modelMapper) {
        this.pedidoRepository = pedidoRepository;
        this.modelMapper = modelMapper;
    }

    public List<PedidoDto> findAll() {
        List<PedidoModel> pedidos = pedidoRepository.findAll();
        return modelMapper.map(pedidos, new TypeToken<List<PedidoDto>>(){
        }.getType());
    }

    public PedidoDto findById(Long id) {
        if(id == null){
            throw new ParametroInvalidoException("Id informado é inválido");
        }

        PedidoModel pedidoModel = new PedidoModel();

        try {
            pedidoModel = pedidoRepository.findById(id).get();

        } catch (Exception e){
            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }

        return modelMapper.map(pedidoModel, PedidoDto.class);
    }

    public PedidoDto findById(PedidoModel pedidoModel){

        if(pedidoModel == null){
            throw new ParametroInvalidoException("A Cliente Model precisa ser informada");
        }

        if(pedidoModel.getId() == null){
            throw new ParametroInvalidoException("A Cliente Model deve conter um id");
        }

        try {
            pedidoModel = pedidoRepository.findById(pedidoModel.getId()).get();

        } catch (Exception e){
            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }

        return modelMapper.map(pedidoModel, PedidoDto.class);
    }


    public PedidoDto save(PedidoDto pedidoDto) {

        pedidoDto.setId(null);

        PedidoModel pedidoModel = pedidoRepository.save(modelMapper.map(pedidoDto, PedidoModel.class));

        return modelMapper.map(pedidoModel, PedidoDto.class);
    }

    public PedidoDto update(@RequestBody PedidoDto pedidoDto) {

        findById(pedidoDto.getId());

        PedidoModel pedidoModel = pedidoRepository.save(modelMapper.map(pedidoDto, PedidoModel.class));

        return modelMapper.map(pedidoModel, PedidoDto.class);

    }


    public boolean deleteById(Long id){
        findById(id);

        try{
            pedidoRepository.deleteById(id);
        }catch (Exception e){
            throw new ConflitoRecursoException("Solicitação atual conflitou com o recurso que está no servidor");
        }

        return true;
    }

//    public List<PedidoDto> findHistory(Long clienteId, Long enderecoId, Long itemId){
//
//
//        List<PedidoModel> historico = pedidoRepository.findHistory(clienteId, enderecoId, itemId);
//
//        if (!historico.isEmpty()) {
//            return modelMapper.map(historico, new TypeToken<List<PedidoDto>>(){
//        }.getType());
//
//        } else {
//            throw new RecursoNaoEncontradoException("Pedido não encontrado");
//        }
//    }


    public void validateSave(PedidoDto pedidoDto){

        ValidacaoUtils.idNaoNula(pedidoDto.getId(), "Id informada não é válida");

        ValidacaoUtils.idNaoNula(pedidoDto.getCliente().getId(), "Id informada não é válida" );

        ValidacaoUtils.idNaoNula(pedidoDto.getEndereco().getId(), "Id informada não é válida");

        ValidacaoUtils.idNaoNula(pedidoDto.getItem().getId(), "Id informada não é válida");

        ValidacaoUtils.idNaoNula(pedidoDto.getCarrinho().getId(), "Id informada não é válida");
    }
}