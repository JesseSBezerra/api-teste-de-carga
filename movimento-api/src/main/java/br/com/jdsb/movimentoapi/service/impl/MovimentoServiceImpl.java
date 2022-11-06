package br.com.jdsb.movimentoapi.service.impl;

import br.com.jdsb.movimentoapi.model.dto.MovimentoDTO;
import br.com.jdsb.movimentoapi.model.entity.Movimento;
import br.com.jdsb.movimentoapi.repository.MovimentoRepository;
import br.com.jdsb.movimentoapi.service.MovimentoService;
import br.com.jdsb.movimentoapi.service.exception.MovimentoNaoEncontradoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimentoServiceImpl implements MovimentoService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private MovimentoRepository movimentoRepository;

    @Override
    public MovimentoDTO create(MovimentoDTO dto) {
        return mapper.map(movimentoRepository.save(mapper.map(dto, Movimento.class)),MovimentoDTO.class);
    }

    @Override
    public List<MovimentoDTO> findAll() {
        return movimentoRepository.findAll()
                .stream().map(x -> mapper.map(x,MovimentoDTO.class)).collect(Collectors.toList());
    }

    @Override
    public MovimentoDTO findById(Long id) {
        return mapper.map(movimentoRepository
                .findById(id).orElseThrow(
                        () -> new MovimentoNaoEncontradoException("Movimento Não Encontrado")),MovimentoDTO.class);
    }

    @Override
    public MovimentoDTO update(Long id, MovimentoDTO dto) {
        dto.setCdSequencialMovimento(id);
        return mapper.map(movimentoRepository.save(mapper.map(dto,Movimento.class)),MovimentoDTO.class);
    }

    @Override
    public void delete(Long id) {
        movimentoRepository.deleteById(id);
    }
}
