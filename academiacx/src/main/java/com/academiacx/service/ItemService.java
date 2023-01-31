package com.academiacx.service;

import com.academiacx.handler.exceptions.ParametroInvalidoException;
import com.academiacx.handler.exceptions.ParametroNullException;
import com.academiacx.handler.exceptions.RecursoNaoEncontradoException;
import com.academiacx.model.CarrinhoModel;
import com.academiacx.model.ItemModel;
import com.academiacx.model.ProdutoModel;
import com.academiacx.model.dto.CarrinhoDto;
import com.academiacx.model.dto.ItemDto;
import com.academiacx.repository.ItemRepository;
import com.academiacx.utils.ValidacaoUtils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private final ModelMapper modelMapper;

    private final CarrinhoService carrinhoService;

    private final ProdutoService produtoService;


    public ItemService(ItemRepository itemRepository, ModelMapper modelMapper, CarrinhoService carrinhoService, ProdutoService produtoService) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.carrinhoService = carrinhoService;
        this.produtoService = produtoService;
    }

    public List<ItemDto> findAll() {

        List<ItemModel> itemModels = itemRepository.findAll();

        return modelMapper.map(itemModels, new TypeToken<List<ItemDto>>() {
        }.getType());
    }


    public ItemDto findById(Long id) {

        ValidacaoUtils.validarId(id, "Id de item informado é inválido!");

        Optional<ItemModel> itemModel = itemRepository.findById(id);

        if (!itemModel.isPresent() || itemModel.isEmpty()) {
            throw new RecursoNaoEncontradoException("Id de item informado não encontrado!");
        }

        return modelMapper.map(itemModel.get(), ItemDto.class);
    }


    public List<ItemDto> findByCarrinhoId(Long id) {

        ValidacaoUtils.validarId(id, "Id de carrinho informado é inválido!");

        Optional<List<ItemModel>> itemModels = itemRepository.findByCarrinhoId(id);

        if (!itemModels.isPresent() || itemModels.get().isEmpty()) {
            throw new RecursoNaoEncontradoException("Não foram encontrados itens com o id de carrinho informado!");
        }

        return modelMapper.map(itemModels.get(), new TypeToken<List<ItemDto>>() {
        }.getType());
    }


    public ItemDto insert(ItemModel itemModel) {

        itemModel.setId(null);

        validarItem(itemModel);

        itemRepository.save(itemModel);

        return atualizarTotal(itemModel);
    }


    public ItemDto update(ItemModel itemModel) {

        if (itemModel == null) {
            throw new ParametroNullException("O Item Model deve ser informado!");
        }

        ValidacaoUtils.validarId(itemModel.getId(), "Id de item informado é inválido!");

        Optional<ItemModel> item = itemRepository.findById(itemModel.getId());

        if (!item.isPresent() || item.isEmpty()) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado!");
        }

        validarItem(itemModel);

        return atualizarTotal(itemModel);
    }


    public boolean delete(Long id) {

        ItemDto itemDto = findById(id);

        itemRepository.deleteById(id);

        removerDoTotal(new ItemModel(itemDto));

        return true;
    }


    @Transactional
    public boolean deleteByCarrinhoId(Long id) {

        ValidacaoUtils.validarId(id, "Id de carrinho informado é inválido!");

        Optional<List<ItemModel>> itemModels = itemRepository.findByCarrinhoId(id);

        if (!itemModels.isPresent() || itemModels.get().isEmpty()) {
            throw new RecursoNaoEncontradoException("Não foram encontrados itens com o id de carrinho informado!");
        }

        itemRepository.deleteByCarrinhoId(id);

        itemModels = itemRepository.findByCarrinhoId(id);

        for (ItemModel item : itemModels.get()) {
            removerDoTotal(item);
        }

        return true;
    }


    private void validarItem(ItemModel itemModel) {
        if (itemModel.getQuantidade() <= 0) {
            throw new ParametroNullException("A quantidade deve ser informada!");
        }

        if (itemModel.getCarrinho() == null) {
            throw new ParametroNullException("O carrinho deve ser informado!");
        }

        ValidacaoUtils.validarId(itemModel.getCarrinho().getId(), "O id de carrinho não foi informado ou é inválido!");

        if (itemModel.getProduto() == null) {
            throw new ParametroNullException("O produto deve ser informado!");
        }

        ValidacaoUtils.validarId(itemModel.getProduto().getId(), "O id de produto não foi informado ou é inválido!");

        itemModel.setCarrinho(new CarrinhoModel(carrinhoService.findById(itemModel.getCarrinho().getId())));
        itemModel.setProduto(new ProdutoModel(produtoService.findById(itemModel.getProduto().getId())));

        itemModel.setTotal(new BigDecimal(0.00));
    }


    private ItemDto atualizarTotal(ItemModel itemModel) {
        Optional<BigDecimal> total = itemRepository.calcularTotal(itemModel.getId());

        if (total.isPresent() && !total.isEmpty()) {
            itemModel.setTotal(total.get());
        }

        ItemDto itemDto = new ItemDto(itemRepository.save(itemModel));

        CarrinhoDto carrinhoDto = carrinhoService.update(itemModel.getCarrinho());

        itemDto.setCarrinho(carrinhoDto);

        return itemDto;
    }


    private void removerDoTotal(ItemModel itemModel) {
        Optional<BigDecimal> total = itemRepository.calcularTotal(itemModel.getId());

        if (total.isPresent() && !total.isEmpty()) {
            itemModel.setTotal(total.get());
        }

        carrinhoService.update(itemModel.getCarrinho());
    }
}
