package br.com.jdsb.movimentoapi.controller.impl;

import br.com.jdsb.movimentoapi.controller.MovimentoController;
import br.com.jdsb.movimentoapi.model.dto.MovimentoDTO;
import br.com.jdsb.movimentoapi.service.MovimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class MovimentoControllerImpl implements MovimentoController {

    @Autowired
    private MovimentoService movimentoService;

    @Override
    public ResponseEntity<MovimentoDTO> create(MovimentoDTO dto) {
        var produto = movimentoService.create(dto);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequestUri().path("/{id}").buildAndExpand(produto.getCdSequencialMovimento()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @Override
    public ResponseEntity<List<MovimentoDTO>> findAll() {
        return ResponseEntity.ok().body(movimentoService.findAll());
    }

    @Override
    public ResponseEntity<MovimentoDTO> findById(Long id) {
        return ResponseEntity.ok().body(movimentoService.findById(id));
    }

    @Override
    public ResponseEntity<MovimentoDTO> update(Long id, MovimentoDTO dto) {
        return ResponseEntity.ok().body(movimentoService.update(id,dto));
    }

    @Override
    public ResponseEntity<MovimentoDTO> delete(Long id) {
        movimentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
