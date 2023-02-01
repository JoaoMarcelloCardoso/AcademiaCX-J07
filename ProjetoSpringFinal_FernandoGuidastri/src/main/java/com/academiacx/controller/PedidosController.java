package com.academiacx.controller;

import com.academiacx.models.*;
import com.academiacx.models.dto.ClienteDto;
import com.academiacx.models.dto.EnderecoDto;
import com.academiacx.models.dto.ItemDto;
import com.academiacx.repository.CarrinhoRepository;
import com.academiacx.repository.EnderecoRepository;
import com.academiacx.repository.ItemRepository;
import com.academiacx.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {
    private final ItemRepository itemRepository;
    private final EnderecoRepository enderecoRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final PedidoRepository pedidoRepository;

    public PedidosController(
            ItemRepository itemRepository,
            EnderecoRepository enderecoRepository,
            CarrinhoRepository carrinhoRepository,
            PedidoRepository pedidoRepository
    ){
        this.itemRepository=itemRepository;
        this.enderecoRepository=enderecoRepository;
        this.carrinhoRepository = carrinhoRepository;
        this.pedidoRepository=pedidoRepository;
    }

    @GetMapping("/listarPedidos")
    public ResponseEntity<List<?>> listarPedidos(){
        return ResponseEntity.ok(pedidoRepository.findAll());
    }

    @PostMapping("/criarPedido/{carrinhoId}/{enderecoId}")
    public ResponseEntity<CompraModel> criarPedido(@PathVariable Long carrinhoId,@PathVariable Long enderecoId){
        PedidoModel pedido = new PedidoModel();
        CompraModel compra = new CompraModel();

        EnderecoModel endereco = enderecoRepository
                .findById(enderecoId)
                .get();
        CarrinhoModel carrinho = carrinhoRepository
                .findById(carrinhoId)
                .get();

        ClienteModel cliente = endereco.getCliente();

        List<ItemDto> items = itemRepository
                .findItemModelsByCarrinhoId(carrinhoId)
                .get()
                .stream()
                .map(ItemDto::new)
                .toList();

        compra.setEnderecoModel(new EnderecoDto(endereco));
        compra.setItens(items);
        compra.setClienteDto(new ClienteDto(cliente));

        pedido.setCarrinhoModel(carrinho);
        pedido.setEnderecoModel(endereco);

        Boolean pedidoRealizado = pedido.getCarrinhoModel()!=null && pedido.getEnderecoModel()!=null;
        Boolean compraSucesso = compra.getEnderecoModel()!=null && compra.getItens()!=null && compra.getClienteDto()!=null;

        if(pedidoRealizado&&compraSucesso){
            pedidoRepository.save(pedido);
            compra.setMensagem("Operacao relizada com sucesso");
            return ResponseEntity.ok(compra);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
