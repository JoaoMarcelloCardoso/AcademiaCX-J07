package com.example.academiacx2.service;

import com.example.academiacx2.handler.exceptions.RecursoNaoEncontradoException;
import com.example.academiacx2.handler.exceptions.ParametroInvalidoException;
import com.example.academiacx2.model.ProdutoModel;
import com.example.academiacx2.model.dto.ProdutoDto;
import com.example.academiacx2.repository.ProdutoRepository;
import com.example.academiacx2.repository.VendaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ModelMapper modelMapper;
    private final VendaRepository vendaRepository;

    public ProdutoService(ProdutoRepository produtoRepository,ModelMapper modelMapper,VendaRepository vendaRepository) {
        this.produtoRepository = produtoRepository;
        this.modelMapper = modelMapper;
        this.vendaRepository = vendaRepository;
    }

    public List<ProdutoDto> findall(){
        List<ProdutoModel> produtoModels = produtoRepository.findAll();

        return modelMapper.map(produtoModels, new TypeToken<List<ProdutoDto>>(){
        }.getType());
    }

    public ProdutoDto findById(ProdutoModel produtoModel) {

        if (produtoModel == null) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");

        }

        if (produtoModel.getId() == null) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");

        }

        try {
            produtoModel = produtoRepository.findById(produtoModel.getId()).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        return modelMapper.map(produtoModel, ProdutoDto.class);
    }

    public ProdutoDto findById(Long id) {

        if(id == null){
            throw  new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        ProdutoModel produtoModel = new ProdutoModel();

        try {
            produtoModel = produtoRepository.findById(id).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }

        return modelMapper.map(produtoModel, ProdutoDto.class);
    }

    public ProdutoDto insert(ProdutoDto produtoDto) {
        produtoDto.setId(null);

        validarInsert(produtoDto);

        ProdutoModel produtoModel = produtoRepository.save(modelMapper.map(produtoDto, ProdutoModel.class));

        return modelMapper.map(produtoModel, ProdutoDto.class);
    }

    public ProdutoModel update(ProdutoModel produtoModel) {

        if(!produtoRepository.existsById(produtoModel.getId())){
            throw new RecursoNaoEncontradoException("Id não encontrado");
        }

        return produtoRepository.save(produtoModel);
    }

    public boolean delete(Long id){

        ProdutoModel produtoModel = new ProdutoModel();

        if(!produtoRepository.existsById(id) || vendaRepository.existsById(produtoModel.getId())){
            throw new RecursoNaoEncontradoException("Id não encontrado");
        }

        produtoRepository.deleteById(id);

        return true;
    }

    public List<ProdutoModel> findBySkuOrNome(String sku, String nome) {

        Optional<List<ProdutoModel>> listUserModel = produtoRepository.findBySkuOrNome(sku, nome);

        if (listUserModel.isPresent()) {


            return listUserModel.stream().findFirst().get();
        } else {
            throw new RecursoNaoEncontradoException("Conteúdo não encontrado");
        }
    }

    public void validarInsert(ProdutoDto produtoDto){

        if(produtoDto.getId() == null){
            throw new ParametroInvalidoException("Id inválido, digite outro");
        }

        if(produtoDto.getNome() == null){
            throw new ParametroInvalidoException("Nome inválido, digite outro");
        }

        if(produtoDto.getSku() == null){
            throw new ParametroInvalidoException("SKU inválido, digite outro");
        }
    }
}