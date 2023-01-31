package com.example.academiacx2.service;

import com.example.academiacx2.handler.exceptions.RecursoNaoEncontradoException;
import com.example.academiacx2.handler.exceptions.ParametroInvalidoException;
import com.example.academiacx2.model.ItemModel;
import com.example.academiacx2.model.dto.ItemDto;
import com.example.academiacx2.repository.ItemRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

    public List<ItemDto> findall(){
        List<ItemModel> itemModel = itemRepository.findAll();

        return modelMapper.map(itemModel, new TypeToken<List<ItemDto>>(){
        }.getType());
    }

    public ItemDto findById(ItemModel itemModel) {

        if (itemModel == null) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");

        }

        if (itemModel.getId() == null) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");

        }

        try {
            itemModel = itemRepository.findById(itemModel.getId()).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        return modelMapper.map(itemModel, ItemDto.class);
    }

    public ItemDto findById(Long id) {

        if(id == null){
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        ItemModel itemModel = new ItemModel();

        try {
            itemModel = itemRepository.findById(id).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        return modelMapper.map(itemModel, ItemDto.class);
    }

    public ItemDto insert(ItemDto itemDto) {
        itemDto.setId(null);

        validarInsert(itemDto);

        ItemModel itemModel = itemRepository.save(modelMapper.map(itemDto, ItemModel.class));

        return modelMapper.map(itemModel, ItemDto.class);
    }

    public ItemModel update(ItemModel itemModel) {

        if(!itemRepository.existsById(itemModel.getId())){
            throw new RecursoNaoEncontradoException("Id não encontrado");
        }

        return itemRepository.save(itemModel);
    }

    public boolean delete(Long id){

        if(!itemRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Id não encontrado");
        }

        itemRepository.deleteById(id);

        return true;
    }

    public List<ItemModel> findByQuantidadeOrTotal(Integer quantidade, Double total) {

        Optional<List<ItemModel>> listItemModel = itemRepository.findByQuantidadeOrTotal(quantidade, total);

        if (listItemModel.isPresent()) {
            return listItemModel.stream().findFirst().get();
        } else {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }
    }

    public List<ItemModel> findByCarrinhoModel_Id(Long id) {

        Optional<List<ItemModel>> listItemModel = itemRepository.findByCarrinhoModel_Id(id);

        if (listItemModel.isPresent()) {
            return listItemModel.stream().findAny().get();

        } else {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }
    }

    public void validarInsert(ItemDto itemDto){

        if(itemDto.getId() == null){
            throw new ParametroInvalidoException("Id inválido, digite outro");
        }

        if(itemDto.getQuantidade() == null){
            throw new ParametroInvalidoException("Quantidade inválida, digite outro");
        }

        if(itemDto.getTotal() == null){
            throw new ParametroInvalidoException("Total inválido, digite outro");
        }

        if(itemDto.getCarrinhoModel() == null){
            throw new ParametroInvalidoException("Id do carrinho inválido, digite outro");
        }

        if(itemDto.getPrecoModel() == null){
            throw new ParametroInvalidoException("Id do preço inválida, digite outro");
        }
        if(itemDto.getProdutoModel() == null){
            throw new ParametroInvalidoException("Id do produto inválida, digite outro");
        }
    }
}
