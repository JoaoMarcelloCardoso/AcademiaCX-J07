package com.academiacx.service;

import com.academiacx.handler.exceptions.*;
import com.academiacx.model.CarrinhoModel;
import com.academiacx.model.EnderecoModel;
import com.academiacx.model.PedidoModel;
import com.academiacx.model.dto.CarrinhoDto;
import com.academiacx.model.dto.PedidoDto;
import com.academiacx.repository.PedidoRepository;
import com.academiacx.utils.ValidacaoUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final CarrinhoService carrinhoService;

    private final EnderecoService enderecoService;

    private final ModelMapper modelMapper;


    public PedidoService(PedidoRepository pedidoRepository, ModelMapper modelMapper, CarrinhoService carrinhoService, EnderecoService enderecoService) {
        this.pedidoRepository = pedidoRepository;
        this.modelMapper = modelMapper;
        this.carrinhoService = carrinhoService;
        this.enderecoService = enderecoService;
    }


    public List<PedidoDto> findAll() {

        List<PedidoModel> pedidoModels = pedidoRepository.findAll();

        return modelMapper.map(pedidoModels, new TypeToken<List<PedidoDto>>() {
        }.getType());
    }


    public PedidoDto findById(Long id) {

        ValidacaoUtils.validarId(id, "Id informado é inválido");

        Optional<PedidoModel> pedidoModel = pedidoRepository.findById(id);

        if (!pedidoModel.isPresent() || pedidoModel.isEmpty()) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado!");
        }

        return modelMapper.map(pedidoModel.get(), PedidoDto.class);
    }


    public List<PedidoDto> findByClienteId(Long id) {

        ValidacaoUtils.validarId(id, "Id de cliente informado é inválido");

        Optional<List<PedidoModel>> pedidoModels = pedidoRepository.findByCarrinhoClienteId(id);

        if (!pedidoModels.isPresent() || pedidoModels.get().isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum pedido foi encontrado com o id de cliente informado!");
        }

        return modelMapper.map(pedidoModels.get(), new TypeToken<List<PedidoDto>>() {
        }.getType());
    }


    public PedidoDto insert(PedidoModel pedidoModel, String cupom) {

        if (cupom != null && (!cupom.equals("academiacx10") && !cupom.equals("academiacx20") && !cupom.equals("academiacx30"))) {
            throw new ParametroInvalidoException("Cupom informado é inválido!");
        }

        validarPedido(pedidoModel);

        pedidoModel.setData(LocalDate.now());

        pedidoModel.getCarrinho().setAtivo(false);

        try {
            PedidoDto pedidoDto = new PedidoDto(pedidoRepository.save(pedidoModel));
            CarrinhoDto carrinhoDto = carrinhoService.update(pedidoModel.getCarrinho());

            if (cupom != null && (cupom.equals("academiacx10") || cupom.equals("academiacx20") || cupom.equals("academiacx30"))) {
                carrinhoDto = carrinhoService.atualizarTotalComCupom(pedidoModel.getCarrinho(), cupom);
            }

            pedidoDto.setCarrinho(carrinhoDto);
            return pedidoDto;

        } catch (Exception e) {
            throw new ErroAoConcluirPedidoException("Erro ao concluir pedido!");
        }

    }


    public PedidoDto insertFalha(PedidoModel pedidoModel) {
        throw new ErroAoConcluirPedidoException("Erro ao concluir pedido!");
    }


    private void validarPedido(PedidoModel pedidoModel) {
        pedidoModel.setId(null);
        pedidoModel.setData(null);

        if (pedidoModel.getCarrinho() == null) {
            throw new ParametroNullException("O carrinho deve ser informado!");
        }

        if (pedidoModel.getEndereco() == null) {
            throw new ParametroNullException("O endereço deve ser informado!");
        }

        ValidacaoUtils.validarVazio(pedidoModel.getMetodoPagamento(), "O método de pagamento é obrigatório!");
        ValidacaoUtils.validarId(pedidoModel.getCarrinho().getId(), "Id de carrinho não foi informado ou é inválido!");
        ValidacaoUtils.validarId(pedidoModel.getEndereco().getId(), "Id de endereço não foi informado ou é inválido!");

        pedidoModel.setCarrinho(new CarrinhoModel(carrinhoService.findById(pedidoModel.getCarrinho().getId())));

        if (!pedidoModel.getCarrinho().isAtivo()) {
            throw new ErroAoConcluirPedidoException("O carrinho informado não está ativo!");
        }

        pedidoModel.setEndereco(new EnderecoModel(enderecoService.findById(pedidoModel.getEndereco().getId())));

        if (!pedidoModel.getEndereco().getCliente().equals(pedidoModel.getCarrinho().getCliente())) {
            throw new ParametroInvalidoException("O endereço deve pertencer ao dono do carrinho!");
        }
    }

}
