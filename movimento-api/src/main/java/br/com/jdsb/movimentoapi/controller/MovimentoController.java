package br.com.jdsb.movimentoapi.controller;

import br.com.jdsb.movimentoapi.model.dto.MovimentoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/movimento")
public interface MovimentoController {

    @PostMapping
    ResponseEntity<MovimentoDTO> create(@RequestBody MovimentoDTO dto);

    @GetMapping
    ResponseEntity<List<MovimentoDTO>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<MovimentoDTO> findById(@PathVariable Long id);

    @PutMapping(value = "/{id}")
    ResponseEntity<MovimentoDTO> update(@PathVariable Long id, @RequestBody MovimentoDTO dto);

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MovimentoDTO> delete(@PathVariable Long id);

}
