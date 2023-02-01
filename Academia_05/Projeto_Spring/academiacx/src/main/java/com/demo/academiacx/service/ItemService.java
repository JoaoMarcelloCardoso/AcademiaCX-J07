package com.demo.academiacx.service;

import com.demo.academiacx.handler.exceptions.ParametroInvalidoException;
import com.demo.academiacx.handler.exceptions.ParametroNullException;
import com.demo.academiacx.handler.exceptions.RecursoNaoEncontradoException;
import com.demo.academiacx.model.CarrinhoModel;
import com.demo.academiacx.model.ItemModel;
import com.demo.academiacx.model.ProdutoModel;
import com.demo.academiacx.model.UserModel;
import com.demo.academiacx.model.dto.compra.ItemDto;
import com.demo.academiacx.model.dto.produto.ProdutoDto;
import com.demo.academiacx.repository.CarrinhoRepository;
import com.demo.academiacx.repository.ItemRepository;
import com.demo.academiacx.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ProdutoRepository produtoRepository;
    private final CarrinhoService carrinhoService;

    private final CarrinhoRepository carrinhoRepository;
    private final ModelMapper modelMapper;


    public List<ItemModel> findAll() {
        try {
            List<ItemModel> itemList = itemRepository.findAll();
            if(itemList.isEmpty()) {
                throw new NoSuchFieldException("Não há conteúdo no endpoint item");
            }
            return itemList;
        } catch (Exception e){
            throw new ParametroNullException("Erro ao buscar items");
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
            return itemRepository.findById(itemModel.getId()).orElseThrow(() -> new RecursoNaoEncontradoException("Item não encontrado com o id informado"));
        } catch (Exception e) {
            throw new ParametroInvalidoException("Erro ao buscar item pelo id");
        }
    }

    public ItemModel findById(Long id) {
        if (id == null) {
            throw new ParametroInvalidoException("Id informado inválido");
        }

        try {
            return itemRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Item não encontrado com o id informado"));
        } catch (Exception e) {
            throw new ParametroInvalidoException("Erro ao buscar item pelo id");
        }
    }


    public ItemModel insert(ItemDto ItemDto) {
        if (produtoRepository.findById(ItemDto.getProduto_id()).isEmpty()){
            throw new ParametroInvalidoException("O id do produto solicitado não existe");
        }
        if (carrinhoRepository.findById(ItemDto.getCarrinho_id()).isEmpty()){
            throw new ParametroInvalidoException("O id do carrinho selecionado não existe");
        }
        ProdutoModel produtoModel = produtoRepository.findById(ItemDto.getProduto_id()).get();

        CarrinhoModel carrinhoModel = carrinhoRepository.findById(ItemDto.getCarrinho_id()).get();

        ItemModel itemModel = new ItemModel(ItemDto.getId(), ItemDto.getQuantidade(), produtoModel.getPreco().multiply(new BigDecimal(ItemDto.getQuantidade())), produtoModel, carrinhoModel);

        carrinhoModel.setTotal(carrinhoModel.getTotal().add(itemModel.getTotal()));

        return itemRepository.save(itemModel);
    }


    public ItemModel update(ItemDto ItemDto) {
        if (produtoRepository.findById(ItemDto.getProduto_id()).isEmpty()){
            throw new ParametroInvalidoException("O id do produto solicitado não existe");
        }
        if (carrinhoService.findById(ItemDto.getCarrinho_id()) == null){
            throw new ParametroInvalidoException("O id do carrinho selecionado não existe");
        }
        ProdutoModel produtoModel = produtoRepository.findById(ItemDto.getProduto_id()).get();

        CarrinhoModel carrinhoModel = carrinhoRepository.findById(ItemDto.getCarrinho_id()).get();

        ItemModel itemModel = new ItemModel(ItemDto.getId(), ItemDto.getQuantidade(), produtoModel.getPreco().multiply(new BigDecimal(ItemDto.getQuantidade())), produtoModel, carrinhoModel);

        carrinhoModel.setTotal(carrinhoModel.getTotal().add(itemModel.getTotal()));

        return itemRepository.save(itemModel);
    }

    public boolean delete(Long id) {

        try {
            if (itemRepository.findById(id).isEmpty()){
                throw new RecursoNaoEncontradoException("Não existe item com o id informado");
            }
            CarrinhoModel carrinhoModel = itemRepository.findById(id).get().getCarrinho();
            carrinhoModel.setTotal(carrinhoModel.getTotal().subtract (itemRepository.findById(id).get().getTotal()));

            itemRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ParametroInvalidoException("Erro ao deletar item");
        }
    }

    public boolean deleteByCarrinho(Long id) {
        try {
            if (itemRepository.findByCarrinhoId(id) == null){
                throw new RecursoNaoEncontradoException("Não existe item com o id informado");
            }
            CarrinhoModel carrinhoModel = itemRepository.findById(id).get().getCarrinho();
            carrinhoModel.setTotal(carrinhoModel.getTotal().subtract (itemRepository.findById(id).get().getTotal()));

            itemRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ParametroInvalidoException("Erro ao deletar item");
        }
    }

    public ItemModel findByCarrinhoId(Long id) {
        if (id == null) {
            throw new ParametroInvalidoException("Id informado inválido");
        }

        try {
            return itemRepository.findByCarrinhoId(id).orElseThrow(() -> new RecursoNaoEncontradoException("Item não encontrado com o id informado"));
        } catch (Exception e) {
            throw new ParametroInvalidoException("Erro ao buscar item pelo id");
        }
    }

    public ItemModel findByProdutoIdOrCarrinhoIdOrItemId(Long id, Long produtoId, Long carrinhoId) {
        Optional<List<ItemModel>> itemModels = itemRepository.findByProdutoIdOrCarrinhoIdOrItemId(id, produtoId, carrinhoId);

        if (itemModels.isPresent()) {
            return itemModels.stream().findFirst().get().get(0);
        } else {
            throw new RecursoNaoEncontradoException("Usuário não encontrado");
        }
    }
}
