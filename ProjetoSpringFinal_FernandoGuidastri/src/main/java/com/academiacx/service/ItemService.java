package com.academiacx.service;

import com.academiacx.handler.exceptions.ForeignKeyException;
import com.academiacx.handler.exceptions.UsuarioNaoEncontradoException;
import com.academiacx.models.ItemModel;
import com.academiacx.models.dto.ItemDto;
import com.academiacx.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    public ItemService(ItemRepository itemRepository, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }

    public List<ItemDto> findAll(){
        return itemRepository
                .findAll()
                .stream()
                .map(ItemDto::new)
                .toList();
    }

    public ItemDto findById(Long id) {
        return itemRepository
                .findById(id)
                .map(ItemDto::new)
                .orElse(null);
    }
    public List<ItemDto> findItemByCarrinhoId(Long id) {
        List<ItemModel> items = itemRepository.findItemModelsByCarrinhoId(id).get();
        return items
                .stream()
                .map(ItemDto::new)
                .toList();
    }

    public ItemDto save(ItemModel itemModel) {
        itemRepository.save(itemModel);
        return new ItemDto(itemModel);
    }

    public ItemDto update(ItemDto itemDto){
        Optional<ItemModel> item = itemRepository.findById(itemDto.getId());
        if (item.isPresent()){
            ItemModel itemModel = item.get();
            itemModel.setQuantidade(itemDto.getQuantidade());
            itemModel.setTotal(itemDto.getTotal());
            itemRepository.save(itemModel);
            return new ItemDto(itemModel);
        }else{
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }

    }

    public List<ItemDto> deleteByCarrinhoId(Long id) {
        List<ItemModel> itemsToDelete = itemRepository.findItemModelsByCarrinhoId(id).get();
        itemRepository.deleteAll(itemsToDelete);
        return itemsToDelete
                .stream()
                .map(ItemDto::new)
                .toList();
    }

    public ItemDto delete(ItemDto itemDto){
        try {
            Optional<ItemModel> item = itemRepository.findById(itemDto.getId());
            if (item.isPresent()) {
                ItemModel itemModel = item.get();
                itemRepository.delete(itemModel);
                return new ItemDto(itemModel);
            }
        } catch (Exception e) {
            throw new ForeignKeyException("Entidade presente em varias tabelas");

        }
        return null;

    }
}
