package com.demo.academiacx.service;

import com.demo.academiacx.handler.exceptions.ParametroInvalidoException;
import com.demo.academiacx.handler.exceptions.RecursoNaoEncontradoException;
import com.demo.academiacx.model.CarrinhoModel;
import com.demo.academiacx.model.ClienteModel;
import com.demo.academiacx.model.UserModel;
import com.demo.academiacx.model.dto.user.ClienteDto;
import com.demo.academiacx.model.dto.user.UserDto;
import com.demo.academiacx.model.dto.user.UserResponseDto;
import com.demo.academiacx.repository.CarrinhoRepository;
import com.demo.academiacx.repository.ClienteRepository;
import com.demo.academiacx.repository.UserRepository;
import com.demo.academiacx.utils.ValidacaoUtils;
import lombok.AllArgsConstructor;
import org.apache.catalina.Store;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.demo.academiacx.config.security.WebSecurityConfig.passwordEncoder;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final ClienteRepository clienteRepository;

    private final CarrinhoRepository carrinhoRepository;

    public List<UserDto> findAll() {
        List<UserModel> userModels = userRepository.findAll();


        return modelMapper.map(userModels, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    public UserDto findById(UserModel userModel) {

        if (userModel == null) {
            throw new ParametroInvalidoException("A User Model deve informada");

        }

        if (userModel.getId() == null) {
            throw new ParametroInvalidoException("A User Model deve conter um id");

        }

        try {
            userModel = userRepository.findById(userModel.getId()).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }


        return modelMapper.map(userModel, UserDto.class);
    }


    public UserDto findById(Long id) {

        if (id == null) {
            throw new ParametroInvalidoException("Id informado inválido");

        }

        UserModel userModel = new UserModel();
        try {
            userModel = userRepository.findById(id).get();

        } catch (Exception e) {
            throw new RecursoNaoEncontradoException("Id informado não encontrado");
        }

        return modelMapper.map(userModel, UserDto.class);
    }

    public UserResponseDto insert(UserDto userDto) {

        ValidarUser(userDto);

        //Gerando o cliente
        ClienteModel clienteModel = new ClienteModel(new ClienteDto(userDto));
        clienteModel.setId(10l);
        clienteModel = clienteRepository.save(clienteModel);

        UserModel userModel = new UserModel(userDto);
        //Vinculando o usuario ao cliente
        userModel.setCliente(clienteModel);
        userModel.setPassword(passwordEncoder().encode(userModel.getPassword()));


        userModel = userRepository.save(userModel);


        UserResponseDto result = new UserResponseDto(userModel);

        //Gerando o carrinho do cliente
        CarrinhoModel carrinhoModel = new CarrinhoModel();
        carrinhoModel.setCliente(userModel.getCliente());
        carrinhoModel.setTotal(new BigDecimal(0));

        carrinhoModel = carrinhoRepository.save(carrinhoModel);

        return result;
    }

    public UserModel update(UserModel userModel) {

            findById(userModel);

            ValidarUser(new UserDto(userModel));

        return userRepository.save(userModel);
    }

    public boolean delete(Long id) {

        findById(id);

        userRepository.deleteById(id);

        return true;
    }

    public UserDto findByNameOrEmailOrId(String name, String email, Long id, String username) {

        Optional<UserModel> userModel = userRepository.findByNameOrEmailOrIdOrUsername(id, name, email, username);

        if (userModel.isPresent()) {
            return modelMapper.map(userModel, UserDto.class);
            //return userModel.stream().findFirst().get();
        } else {
            throw new RecursoNaoEncontradoException("Usuário não encontrado");
        }
    }

    public UserDto buscarPorId(Long id) {

        Optional<UserModel> userModel = userRepository.buscaPorId(id);

        if (userModel.isPresent())
        {
            return new UserDto(userModel.get());
        }else {
            throw new RecursoNaoEncontradoException("Id não encontrado");
        }
    }

    public void ValidarUser(UserDto userDto){
        //ValidacaoUtils.validarVazio(userDto.getNome(), "O nome é obrigatório");
        //ValidacaoUtils.validarEmail(userDto.getEmail(), "O email inserido é inválido");
    }
}
