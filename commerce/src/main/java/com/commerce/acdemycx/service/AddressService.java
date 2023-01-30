package com.commerce.acdemycx.service;

import com.commerce.acdemycx.handler.exceptions.ErroDeFormatoException;
import com.commerce.acdemycx.handler.exceptions.ParametroInvalidoException;
import com.commerce.acdemycx.handler.exceptions.RecursoNaoEncontradoExeception;
import com.commerce.acdemycx.handler.exceptions.SemConteudoException;
import com.commerce.acdemycx.model.AddressModel;
import com.commerce.acdemycx.model.dto.request.AddressDtoRequest;
import com.commerce.acdemycx.model.dto.response.AddressDtoResponse;
import com.commerce.acdemycx.repository.AddressRepository;
import com.commerce.acdemycx.repository.ClientRepository;
import com.commerce.acdemycx.utils.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    private final ModelMapper modelMapper;

    private final ClientRepository clientRepository;

    public AddressService(AddressRepository addressRepository, ModelMapper modelMapper, ClientRepository clientRepository) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
        this.clientRepository = clientRepository;
    }

    public List<AddressDtoResponse> findAll() {
        try {
            List<AddressModel> addressList = addressRepository.findAll();
            if(addressList.isEmpty()) {
                throw new SemConteudoException("Não há conteúdo no endpoint address");
            }
            return modelMapper.map(addressList, new TypeToken<List<AddressDtoResponse>>() {}.getType());
        } catch (Exception e){
            throw new ErroDeFormatoException("Erro ao buscar endereços");
        }
    }

    public AddressDtoResponse findById(Long id) {
        if (id == null) {
            throw new ParametroInvalidoException("Id informado inválido");
        }

        try {

            AddressModel addressModel = addressRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoExeception("Endereço não encontrado com o id informado"));
            return modelMapper.map(addressModel,AddressDtoResponse.class);
        } catch (Exception e) {
            throw new ErroDeFormatoException("Erro ao buscar endereço pelo id");
        }
    }

    public AddressDtoResponse findById(AddressModel addressModel) {
        if (addressModel == null) {
            throw new ParametroInvalidoException("A Address Model deve ser informada");
        }

        if (addressModel.getId() == null) {
            throw new ParametroInvalidoException("A Address Model deve conter um id");
        }

        try {
            AddressModel address=addressRepository.findById(addressModel.getId()).orElseThrow(() -> new RecursoNaoEncontradoExeception("Endereço não encontrado com o id informado"));
            return modelMapper.map(address,AddressDtoResponse.class);
        } catch (Exception e) {
            throw new ErroDeFormatoException("Erro ao buscar endereço pelo id");
        }
    }


    public AddressDtoResponse insert(AddressDtoRequest addressDtoRequest) {


        AddressModel addressModel = validateAndSetupInsert(addressDtoRequest, null);


        addressRepository.save(addressModel);






        return modelMapper.map(addressModel, AddressDtoResponse.class);
    }


    public AddressDtoResponse update(AddressDtoRequest addressDtoRequest) {
        if(addressRepository.findById(addressDtoRequest.getId()).isEmpty()) {
            throw new RecursoNaoEncontradoExeception("Não existe endereço com o id informado");
        }

        try {

            AddressModel addressModel = validateAndSetupInsert(addressDtoRequest, addressDtoRequest.getId());
            addressModel = validateUpdate(addressModel, addressDtoRequest.getId());
            addressRepository.save(addressModel);

            return modelMapper.map(addressModel,AddressDtoResponse.class);

        } catch (Exception e) {
            throw new ErroDeFormatoException("Erro ao atualizar endereço");
        }
    }

    public boolean delete(Long id) {
//        validateDelete(id);

        addressRepository.deleteById(id);
        return true;
    }

    private void validateDelete(Long id){
        if (addressRepository.findById(id).isEmpty()){
            throw new RecursoNaoEncontradoExeception("Não existe endereço com o id informado");
        }
        if (addressRepository.findById(id).get().getClientModel().getAddresses().size() <= 1){
            throw new ParametroInvalidoException("Não é permitido deletar todos os endereços do cliente");
        }
    }








    private AddressModel validateAndSetupInsert(AddressDtoRequest addressDtoRequest, Long id){
        if (clientRepository.findById(addressDtoRequest.getClient_id()).isEmpty()){
            throw new ParametroInvalidoException("Usuário não existe");
        }
        if (addressDtoRequest.getCep() == null){
            throw new ParametroInvalidoException("CEP deve ser preenchido");
        }
        ValidationUtils.validateCEP(addressDtoRequest.getCep());


        return new AddressModel(id,
                addressDtoRequest.getCep(),
                addressDtoRequest.getLogradouro(),
                addressDtoRequest.getNumero(),
                addressDtoRequest.getBairro(),
                addressDtoRequest.getCidade(),
                addressDtoRequest.getEstado(),
                addressDtoRequest.getComplemento(),
                clientRepository.findById(addressDtoRequest.getClient_id()).get()
        );
    }

    private AddressModel validateUpdate(AddressModel addressModel, Long id){
        if (addressModel.getCep() == null){
            addressModel.setCep(addressRepository.findById(id).get().getCep());
        }
        if (addressModel.getEstado() == null){
            addressModel.setEstado(addressRepository.findById(id).get().getEstado());
        }

        if (addressModel.getLogradouro() == null){
            addressModel.setLogradouro(addressRepository.findById(id).get().getLogradouro());
        }
        if (addressModel.getNumero() == null){
            addressModel.setNumero(addressRepository.findById(id).get().getNumero());
        }
        if (addressModel.getBairro() == null){
            addressModel.setBairro(addressRepository.findById(id).get().getBairro());
        }
        if (addressModel.getCidade() == null){
            addressModel.setCidade(addressRepository.findById(id).get().getCidade());
        }
        if (addressModel.getComplemento() == null){
            addressModel.setComplemento(addressRepository.findById(id).get().getComplemento());
        }
        if (addressModel.getClientModel() == null){
            addressModel.setClientModel(addressRepository.findById(id).get().getClientModel());
        }
        return addressModel;
    };

}
