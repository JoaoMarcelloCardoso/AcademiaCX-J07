package br.com.commerce.academiacx.service;

import java.nio.charset.Charset;
import java.util.Optional;

import br.com.commerce.academiacx.handler.exceptions.ParametroInvalidoException;
import br.com.commerce.academiacx.model.ClienteLogin;
import br.com.commerce.academiacx.model.Pedido;
import br.com.commerce.academiacx.utils.ValidacaoUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.commerce.academiacx.model.Cliente;
import br.com.commerce.academiacx.model.ListaDeDesejos;
import br.com.commerce.academiacx.repository.ClienteRepository;
import br.com.commerce.academiacx.repository.ListaDeDesejosRepository;
import br.com.commerce.academiacx.repository.PedidoRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ListaDeDesejosRepository listaDeDesejosRepository;

    public Optional<Cliente> CadastrarCliente(Cliente cliente) {

        validateSave(cliente);

        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent() && cliente.getId() == 0) {
            return null;

        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String senhaEncoder = encoder.encode(cliente.getSenha());
        cliente.setSenha(senhaEncoder);

        Pedido pedido = new Pedido();

        ListaDeDesejos listaDeDesejos = new ListaDeDesejos();

        clienteRepository.save(cliente);

        pedido.setCliente(cliente);

        listaDeDesejos.setCliente(cliente);

        pedidoRepository.save(pedido);

        listaDeDesejosRepository.save(listaDeDesejos);

        return Optional.of(clienteRepository.save(cliente));

    }

    public Optional<ClienteLogin> Logar(Optional<ClienteLogin> clienteLogin) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<Cliente> cliente = clienteRepository.findByEmail(clienteLogin.get().getEmail());


        if (cliente.isPresent()) {
            if (encoder.matches(clienteLogin.get().getSenha(), cliente.get().getSenha())) {

                String auth = clienteLogin.get().getEmail() + ":" + clienteLogin.get().getSenha();

                byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));

                String authHeader = "Basic " + new String(encodedAuth);

                clienteLogin.get().setToken(authHeader);
                clienteLogin.get().setUsuario(cliente.get().getUsuario());
                clienteLogin.get().setEmail(cliente.get().getEmail());
                clienteLogin.get().setSenha(cliente.get().getSenha());
                clienteLogin.get().setTipo(cliente.get().getTipo());
                clienteLogin.get().setNome(cliente.get().getNome());
                clienteLogin.get().setBairro(cliente.get().getBairro());
                clienteLogin.get().setCep(cliente.get().getCep());
                clienteLogin.get().setCidade(cliente.get().getCidade());
                clienteLogin.get().setCpf(cliente.get().getCpf());
                clienteLogin.get().setEndereco(cliente.get().getEndereco());
                clienteLogin.get().setId(cliente.get().getId());
                clienteLogin.get().setNumero(cliente.get().getNumero());
                clienteLogin.get().setPedidos(cliente.get().getPedidos());
                clienteLogin.get().setListaDeDesejos(cliente.get().getListaDeDesejos());

                return clienteLogin;

            }
        }

        return null;
    }

    private void validateSave(Cliente cliente) {
        ValidacaoUtils.validarTamanhoMinimo(cliente.getNome(), 3, "Nome deve conter 3 caracteres");

        ValidacaoUtils.validarTamanhoMinimo(cliente.getUsuario(), 5, "O username deve conter 5 caracteres no minimo");

        ValidacaoUtils.PasswordValidator(cliente.getSenha(), "minimo de 8 caracteres, incluir ao menos uma letra maiuscula, minuscula, digito e caractere especial");

        ValidacaoUtils.EmailValidator(cliente.getEmail(), "exemplo email valido: hello@example.com");

        ValidacaoUtils.cpfFormater(cliente.getCpf(), "exemplo cpf valido: 526.458.456-85");

        if (cliente.getNome() == null) {
            throw new ParametroInvalidoException("O nome deve ser informado");
        }
        if (cliente.getUsuario() == null) {
            throw new ParametroInvalidoException("O username deve ser informado");
        }
        if (cliente.getSenha() == null) {
            throw new ParametroInvalidoException("A senha deve ser informada");
        }
        if (cliente.getEmail() == null) {
            throw new ParametroInvalidoException("O email deve ser informado");
        }


    }


}
