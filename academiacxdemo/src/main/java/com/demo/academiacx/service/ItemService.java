package com.demo.academiacx.service;

import com.demo.academiacx.handler.ConflitoRecursoException;
import com.demo.academiacx.handler.ParametroInvalidoException;
import com.demo.academiacx.handler.RecursoNaoEncontradoException;
import com.demo.academiacx.model.CarrinhoModel;
import com.demo.academiacx.model.ItemModel;
import com.demo.academiacx.model.dto.ItemDto;
import com.demo.academiacx.repository.ItemRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ModelMapper modelMapper;

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }

    public List<ItemDto> findAll() {

        List<ItemModel> itemModel = itemRepository.findAll();
        return modelMapper.map(itemModel, new TypeToken<List<ItemDto>>(){
        }.getType());
    }

    public ItemDto findById(Long id){

        if(id == null){

            throw new ParametroInvalidoException("Id informado inválido");
        }

        ItemModel itemModel = new ItemModel();

        try {

            itemModel = itemRepository.findById(id).get();

        } catch (Exception e){

            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }

        return modelMapper.map(itemModel, ItemDto.class);
    }

    public ItemDto findById(ItemModel itemModel){

        if(itemModel == null){

            throw new ParametroInvalidoException("O Item Model precisa ser informado");
        }

        if(itemModel.getId() == null){

            throw new ParametroInvalidoException("O Item Model precisa ter um id");

        }

        try {

            itemModel = itemRepository.findById(itemModel.getId()).get();

        } catch (Exception e){

            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }

        return modelMapper.map(itemModel, ItemDto.class);
    }

    public ItemDto save(ItemDto itemDto){

        itemDto.setId(null);

        ItemModel itemModel = itemRepository.save(modelMapper.map(itemDto, ItemModel.class));

        return modelMapper.map(itemModel, ItemDto.class);
    }

    public ItemDto update(ItemDto itemDto){

        findById(itemDto.getId());

        ItemModel itemModel = itemRepository.save(modelMapper.map(itemDto, ItemModel.class));

        return modelMapper.map(itemModel, ItemDto.class);

    }

    public boolean delete(Long id){

        try{

            itemRepository.deleteById(id);

        } catch (Exception e){

            throw new ConflitoRecursoException("Solicitação atual conflitou com o recurso que está no servidor");
        }

        return true;
    }

    public List<ItemDto> findByCarrinhoId(Long id){

       Optional<List<ItemModel>> items = itemRepository.findByCarrinhoId(id);

        return modelMapper.map(items.get(), new TypeToken<List<ItemDto>>(){
        }.getType());

    }

    public boolean deleteByCarrinhoId(Long id){

        Optional<List<ItemModel>> items = itemRepository.findByCarrinhoId(id);

        if(items.isEmpty() || items == null){
            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }

        CarrinhoModel carrinhoModel = itemRepository.findById(id).get().getCarrinho();
        itemRepository.deleteById(id);

        return true;


    }


}
