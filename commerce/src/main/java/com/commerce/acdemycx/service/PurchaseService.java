package com.commerce.acdemycx.service;


import com.commerce.acdemycx.handler.exceptions.ErroDeFormatoException;
import com.commerce.acdemycx.handler.exceptions.ParametroInvalidoException;
import com.commerce.acdemycx.handler.exceptions.RecursoNaoEncontradoExeception;
import com.commerce.acdemycx.handler.exceptions.SemConteudoException;
import com.commerce.acdemycx.model.AddressModel;
import com.commerce.acdemycx.model.CartModel;
import com.commerce.acdemycx.model.PurchaseModel;
import com.commerce.acdemycx.repository.AddressRepository;
import com.commerce.acdemycx.repository.CartRepository;
import com.commerce.acdemycx.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    private final CartRepository cartRepository;

    private final AddressRepository addressRepository;


    public PurchaseService(PurchaseRepository purchaseRepository, CartRepository cartRepository, AddressRepository addressRepository) {
        this.purchaseRepository = purchaseRepository;
        this.cartRepository = cartRepository;
        this.addressRepository = addressRepository;
    }


    public PurchaseModel buy(Long cart_id, Long address_id, String paymentMethod){
        if (cartRepository.findById(cart_id).isEmpty()){
            throw new ParametroInvalidoException("Carrinho selecionado inválido");
        }
        if (addressRepository.findById(address_id).isEmpty()){
            throw new ParametroInvalidoException("Endereço selecionado inválido");
        }
        CartModel cartModel = cartRepository.findById(cart_id).get();
        AddressModel addressModel = addressRepository.findById(address_id).get();

        if (cartModel.getClientModel() != addressModel.getClientModel()){
            throw new ParametroInvalidoException("O endereço e o carrinho devem ser do mesmo cliente");
        }

        PurchaseModel purchaseModel = new PurchaseModel(
                null,
                cartModel,
                addressModel,
                LocalDateTime.now(),
                "completed",
                paymentMethod
        );


        return purchaseRepository.save(purchaseModel);
    }

    public List<PurchaseModel> findAll() {
        try {
            List<PurchaseModel> purchaseList = purchaseRepository.findAll();
            if(purchaseList.isEmpty()) {
                throw new SemConteudoException("Não há conteúdo no endpoint de compras");
            }
            return purchaseList;
        } catch (Exception e){
            throw new ErroDeFormatoException("Erro ao buscar compras");
        }
    }

    public PurchaseModel findById(PurchaseModel purchaseModel) {
        if (purchaseModel == null) {
            throw new ParametroInvalidoException("A Purchase Model deve ser informada");
        }

        if (purchaseModel.getId() == null) {
            throw new ParametroInvalidoException("A Purchase Model deve conter um id");
        }

        try {
            return purchaseRepository.findById(purchaseModel.getId()).orElseThrow(() -> new RecursoNaoEncontradoExeception("Compra não encontrada com o id informado"));
        } catch (Exception e) {
            throw new ErroDeFormatoException("Erro ao buscar compra pelo id");
        }
    }

    public PurchaseModel findById(Long id) {
        if (id == null) {
            throw new ParametroInvalidoException("Id informado inválido");
        }

        try {
            return purchaseRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoExeception("Compra não encontrada com o id informado"));
        } catch (Exception e) {
            throw new ErroDeFormatoException("Erro ao buscar compra pelo id");
        }
    }


}
