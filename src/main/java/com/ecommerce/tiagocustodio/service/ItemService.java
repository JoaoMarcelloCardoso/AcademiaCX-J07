package com.ecommerce.tiagocustodio.service;

import com.ecommerce.tiagocustodio.handler.exceptions.ParametroInvalidoException;
import com.ecommerce.tiagocustodio.handler.exceptions.RecursoNaoEncontradoException;
import com.ecommerce.tiagocustodio.model.ItemModel;
import com.ecommerce.tiagocustodio.model.dto.ItemDto;
import com.ecommerce.tiagocustodio.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private final ModelMapper modelMapper;

    public ItemService(ItemRepository itemRepository, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }

    public ItemDto findById(Long id) {
        if (id == null) {
            throw new ParametroInvalidoException("Id informado inválido");
        }

        ItemModel itemModel = new ItemModel();
        try {
            itemModel = itemRepository.findById(id).get();
        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }

        return modelMapper.map(itemModel, ItemDto.class);
    }

    public List<ItemDto> findByCarrinhoId(Long carrinhoModel) {
        List<ItemModel> itemModels = itemRepository.findByCarrinhoModel(carrinhoModel);
        return itemModels.stream().map(itemModel -> new ItemDto(itemModel)).collect(Collectors.toList());
    }

    /*public ItemDto insert(ItemDto itemDto) {
        itemDto.setId(null);
        ItemModel itemModel = new ItemModel(itemDto);
        itemModel = itemRepository.save(itemModel);
        return new ItemDto(itemModel);
    }*/

    public ItemDto update(ItemModel itemDto) {
        ItemModel itemModel = itemRepository.findById(itemDto.getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Item não encontrado com id: " + itemDto.getId()));
        itemModel.setQuantidade(itemDto.getQuantidade());
        itemModel = itemRepository.save(itemModel);
        return new ItemDto(itemModel);
    }

    public boolean delete(Long id) {
        findById(id);
        itemRepository.deleteById(id);
        return true;
    }

    public List<ItemDto> findAll() {
        List<ItemModel> itemModels = itemRepository.findAll();
        return modelMapper.map(itemModels, new TypeToken<List<ItemDto>>() {
        }.getType());
    }

    public ItemDto insert(ItemDto itemDto) {
        itemDto.setId(null);

        ItemDto result = new ItemDto(itemRepository.save(new ItemModel(itemDto)));

        return result;
    }
}