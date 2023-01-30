package com.commerce.acdemycx.service;

import com.commerce.acdemycx.handler.exceptions.ErroDeFormatoException;
import com.commerce.acdemycx.handler.exceptions.ParametroInvalidoException;
import com.commerce.acdemycx.handler.exceptions.RecursoNaoEncontradoExeception;
import com.commerce.acdemycx.handler.exceptions.SemConteudoException;
import com.commerce.acdemycx.model.CartModel;
import com.commerce.acdemycx.model.ItemModel;
import com.commerce.acdemycx.model.ProductModel;
import com.commerce.acdemycx.model.dto.request.ItemDtoRequest;
import com.commerce.acdemycx.repository.CartRepository;
import com.commerce.acdemycx.repository.ItemRepository;
import com.commerce.acdemycx.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    public List<ItemModel> findAll() {
        try {
            List<ItemModel> itemList = itemRepository.findAll();
            if(itemList.isEmpty()) {
                throw new SemConteudoException("Não há conteúdo no endpoint item");
            }
            return itemList;
        } catch (Exception e){
            throw new ErroDeFormatoException("Erro ao buscar items");
        }
    }

    public ItemModel findById(ItemModel itemModel) {
        if (itemModel == null) {
            throw new ParametroInvalidoException("A Item Model deve ser informada");
        }

        if (itemModel.getId() == null) {
            throw new ParametroInvalidoException("A Item Model deve conter um id");
        }

        try {
            return itemRepository.findById(itemModel.getId()).orElseThrow(() -> new RecursoNaoEncontradoExeception("Item não encontrado com o id informado"));
        } catch (Exception e) {
            throw new ErroDeFormatoException("Erro ao buscar item pelo id");
        }
    }

    public ItemModel findById(Long id) {
        if (id == null) {
            throw new ParametroInvalidoException("Id informado inválido");
        }

        try {
            return itemRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoExeception("Item não encontrado com o id informado"));
        } catch (Exception e) {
            throw new ErroDeFormatoException("Erro ao buscar item pelo id");
        }
    }


    public ItemModel insert(ItemDtoRequest itemDtoRequest) {
        if (productRepository.findById(itemDtoRequest.getProduct_id()).isEmpty()){
            throw new ParametroInvalidoException("O id do produto solicitado não existe");
        }
        if (cartRepository.findById(itemDtoRequest.getCart_id()).isEmpty()){
            throw new ParametroInvalidoException("O id do carrinho selecionado não existe");
        }
        ProductModel productModel = productRepository.findById(itemDtoRequest.getProduct_id()).get();



        CartModel cartModel = cartRepository.findById(itemDtoRequest.getCart_id()).get();

        ItemModel itemModel = new ItemModel(null,
                itemDtoRequest.getQuantity(),
                itemDtoRequest.getQuantity() * productModel.getPrice(),
                productModel,
                cartModel



        );
        cartModel.setTotal(cartModel.getTotal() + itemModel.getTotal());

        return itemRepository.save(itemModel);
    }


    public ItemModel update(ItemDtoRequest itemDtoRequest) {
        if (productRepository.findById(itemDtoRequest.getProduct_id()).isEmpty()){
            throw new ParametroInvalidoException("O id do produto solicitado não existe");
        }
        if (cartRepository.findById(itemDtoRequest.getCart_id()).isEmpty()){
            throw new ParametroInvalidoException("O id do carrinho selecionado não existe");
        }
        ProductModel productModel = productRepository.findById(itemDtoRequest.getProduct_id()).get();



        CartModel cartModel = cartRepository.findById(itemDtoRequest.getCart_id()).get();

        ItemModel itemModel = new ItemModel(itemDtoRequest.getId(),
                itemDtoRequest.getQuantity(),
                itemDtoRequest.getQuantity() * productModel.getPrice(),
                productModel,
                cartModel



        );
        cartModel.setTotal(cartModel.getTotal() + itemModel.getTotal());

        return itemRepository.save(itemModel);
    }

    public boolean delete(Long id) {

        try {
            if (itemRepository.findById(id).isEmpty()){
                throw new RecursoNaoEncontradoExeception("Não existe item com o id informado");
            }
            CartModel cartModel = itemRepository.findById(id).get().getCart();
            cartModel.setTotal(cartModel.getTotal() - (itemRepository.findById(id).get().getTotal()));

            itemRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ErroDeFormatoException("Erro ao deletar item");
        }
    }
}
