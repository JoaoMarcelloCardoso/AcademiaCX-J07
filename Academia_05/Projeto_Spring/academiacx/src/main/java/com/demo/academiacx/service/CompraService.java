package com.demo.academiacx.service;

import com.demo.academiacx.handler.exceptions.ParametroInvalidoException;
import com.demo.academiacx.handler.exceptions.RecursoNaoEncontradoException;
import com.demo.academiacx.model.*;
import com.demo.academiacx.model.dto.checkout.CheckoutItemDto;
import com.demo.academiacx.model.dto.compra.CompraDto;
import com.demo.academiacx.repository.CarrinhoRepository;
import com.demo.academiacx.repository.ClienteRepository;
import com.demo.academiacx.repository.CompraRepository;
import com.demo.academiacx.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompraService {
    private final CompraRepository compraRepository;
    private final ModelMapper modelMapper;
    private final CarrinhoRepository carrinhoRepository;
    private final EnderecoRepository enderecoRepository;

    private final ClienteRepository clienteRepository;


    public List<CompraDto> findAll(){

        List<CompraModel> CompraModel = compraRepository.findAll();

        return modelMapper.map(CompraModel, new TypeToken<List<CompraDto>>(){
        }.getType());
    }

    public CheckoutItemDto findById(CompraModel CompraModel){

        if (CompraModel == null) {
            throw  new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        if (CompraModel.getId() == null){
            throw  new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        try {
            CompraModel = compraRepository.findById(CompraModel.getId()).get();
        } catch (Exception e){
            throw  new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        return modelMapper.map(CompraModel, CheckoutItemDto.class);
    }

    public CheckoutItemDto findById(Long id) {

        if(id == null){
            throw  new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        CompraModel CompraModel;

        try {
            CompraModel = compraRepository.findById(id).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        return modelMapper.map(CompraModel, CheckoutItemDto.class);
    }

    public List<CompraModel> findComprasByClienteId(Long id){
        if (id == null) {
            throw new ParametroInvalidoException("Id informado inválido");
        }

        ClienteModel clienteModel;

        try {
            clienteModel = clienteRepository.findById(id).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado!");
        }
        List<CompraModel> compras = clienteModel.getCompras();

        return compras;
    }


    public CompraModel comprar(CheckoutItemDto checkoutItemDto) {

        validarInsert(checkoutItemDto);

        CarrinhoModel carrinho = carrinhoRepository.findById(checkoutItemDto.getCarrinho_id()).get();
        EnderecoModel endereco = enderecoRepository.findById(checkoutItemDto.getEndereco_id()).get();

        if (carrinho.getCliente() != endereco.getCliente()){
            throw new ParametroInvalidoException("O endereço e o carrinho devem ser do mesmo cliente");
        }

        ClienteModel cliente = carrinho.getCliente();


        CompraModel compra = new CompraModel(endereco,cliente, carrinho, LocalDateTime.now());

        return compraRepository.save(compra);
    }

    public CompraModel update(CompraModel CompraModel) {

        if(!compraRepository.existsById(CompraModel.getId())){
            throw new RecursoNaoEncontradoException("Id não encontrado");
        }

        return compraRepository.save(CompraModel);
    }


    public List<CompraModel> findByCarrinhoModelOrClienteModelOrEnderecoModel(Long carrinho_id, Long cliente_id, Long endereco_id) {

        List<CompraModel> listCompraModel = compraRepository.findByCarrinhoOrClienteOrEndereco(carrinho_id,cliente_id, endereco_id);

        if (!listCompraModel.isEmpty()) {
            return listCompraModel;
        } else {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }
    }

    public void validarInsert(CheckoutItemDto CheckoutItemDto){
        if(CheckoutItemDto.getCarrinho_id() == null){
            throw new ParametroInvalidoException("Id do carrinho inválido, digite outro");
        }

        if(CheckoutItemDto.getEndereco_id() == null){
            throw new ParametroInvalidoException("Id do endereco inválido, digite outro");
        }

        if(CheckoutItemDto.getPagamento_id() == null){
            throw new ParametroInvalidoException("Id da forma de pagamento é inválido, digite outro");
        }
    }
}
