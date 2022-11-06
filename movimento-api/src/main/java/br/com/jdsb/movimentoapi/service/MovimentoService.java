package br.com.jdsb.movimentoapi.service;

import br.com.jdsb.movimentoapi.model.dto.MovimentoDTO;

import java.util.List;

public interface MovimentoService {

    MovimentoDTO create(MovimentoDTO produtoDTO);

    List<MovimentoDTO> findAll();

    MovimentoDTO findById(Long id);

    MovimentoDTO update(Long id, MovimentoDTO dto);

    void delete(Long id);

}
