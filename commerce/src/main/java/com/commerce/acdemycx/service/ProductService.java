package com.commerce.acdemycx.service;


import com.commerce.acdemycx.model.ItemModel;
import com.commerce.acdemycx.model.PurchaseModel;
import com.commerce.acdemycx.model.dto.response.ProductDtoResponse;
import com.commerce.acdemycx.handler.exceptions.ErroDeFormatoException;
import com.commerce.acdemycx.handler.exceptions.ParametroInvalidoException;
import com.commerce.acdemycx.handler.exceptions.RecursoNaoEncontradoExeception;
import com.commerce.acdemycx.handler.exceptions.SemConteudoException;
import com.commerce.acdemycx.model.ProductModel;
import com.commerce.acdemycx.repository.ProductRepository;
import com.commerce.acdemycx.repository.PurchaseRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final PurchaseRepository purchaseRepository;
    private final ModelMapper modelMapper;

    public ProductService(ModelMapper modelMapper, ProductRepository productRepository, PurchaseRepository purchaseRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public List<ProductDtoResponse> findAll() {

        try {
            List<ProductModel> productList = productRepository.findAll();
            if(productList.isEmpty()) {
                throw new SemConteudoException("Não há conteúdo no endpoint product");
            }
            return modelMapper.map(productList, new TypeToken<List<ProductDtoResponse>>() {}.getType());
        } catch (Exception e){
            throw new ErroDeFormatoException("Erro ao buscar lista de produtos");
        }
    }


    public ProductDtoResponse findById(Long id) {
        if (id == null) {
            throw new ParametroInvalidoException("Id informado inválido");
        }

        try {
            ProductModel product = productRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoExeception("Item não encontrado com o id informado"));
            return modelMapper.map(product, ProductDtoResponse.class);
        } catch (Exception e) {
            throw new ErroDeFormatoException("Erro ao buscar produto pelo id");
        }
    }


    public ProductDtoResponse insert(ProductModel productModel) {
        productModel.setId(null);

        productRepository.save(productModel);
        return modelMapper.map(productModel, ProductDtoResponse.class);
    }


    public ProductDtoResponse update(ProductModel productModel) {
        if(!productRepository.existsById(productModel.getId())) {
            throw new RecursoNaoEncontradoExeception("Não existe produto com o id informado");
        }

        try {
            productRepository.save(productModel);
            return modelMapper.map(productModel, ProductDtoResponse.class);
        } catch (Exception e) {
            throw new ErroDeFormatoException("Erro ao atualizar produto");
        }
    }


    public boolean delete(Long id) {
        if(id == null || !productRepository.existsById(id)) {
            throw new RecursoNaoEncontradoExeception("Não existe produto com o id informado");
        }

        try {
            for (PurchaseModel purchaseModel: purchaseRepository.findAll()){
                for (ItemModel item : purchaseModel.getCart().getItems()){
                    if (item.getProduct() == productRepository.findById(id).get()){
                        throw new ParametroInvalidoException("Não é possivel deletar um produto que já foi vendido");
                    }
                }
            }
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ErroDeFormatoException("Erro ao deletar produto");
        }
    }

}
