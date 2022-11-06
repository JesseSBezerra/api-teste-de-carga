package br.com.jdsb.movimentoapi.service.impl;

import br.com.jdsb.movimentoapi.model.dto.MovimentoDTO;
import br.com.jdsb.movimentoapi.model.entity.Movimento;
import br.com.jdsb.movimentoapi.repository.MovimentoRepository;
import br.com.jdsb.movimentoapi.service.exception.MovimentoNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class MovimentoServiceImplTest {

    public static final long CD_SEQUENCIAL_MOVIMENTO = 1L;
    public static final LocalDateTime DT_MOVIMENTO = LocalDateTime.of(2022, 11, 6, 13, 54, 0);
    public static final int QT_MOVIMENTO = 10;
    public static final String TP_MOVIMENTO = "P";
    public static final String NM_RESPOSAVEL_RECEBIMENTO = "João Gomes da Cunha";
    public static final String MOVIMENTO_NAO_ENCONTRADO = "Movimento não encontrado";
    private Movimento movimento;
    private Optional<Movimento> movimentoOpt;
    private MovimentoDTO movimentoDTO;

    @InjectMocks
    private MovimentoServiceImpl service;

    @Mock
    private MovimentoRepository repository;

    @Spy
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initMocks();
    }

    @Test
    void create() {
        when(repository.save(any())).thenReturn(movimento);
        var mov = service.create(movimentoDTO);
        assertNotNull(mov);
        assertEquals(CD_SEQUENCIAL_MOVIMENTO, mov.getCdSequencialMovimento());
        assertEquals(DT_MOVIMENTO, mov.getDtMovimento());
        assertEquals(TP_MOVIMENTO, mov.getDsTpMovimento());
        assertEquals(NM_RESPOSAVEL_RECEBIMENTO, mov.getNmResposavelRecebimento());
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(List.of(movimento));
        var mov = service.findAll();
        assertNotNull(mov);
        assertEquals(1,mov.size());
        assertEquals(CD_SEQUENCIAL_MOVIMENTO, mov.get(0).getCdSequencialMovimento());
        assertEquals(DT_MOVIMENTO, mov.get(0).getDtMovimento());
        assertEquals(TP_MOVIMENTO, mov.get(0).getDsTpMovimento());
        assertEquals(NM_RESPOSAVEL_RECEBIMENTO, mov.get(0).getNmResposavelRecebimento());

    }

    @Test
    void findById() {
        when(repository.findById(anyLong())).thenReturn(movimentoOpt);
        var mov = service.findById(CD_SEQUENCIAL_MOVIMENTO);
        assertNotNull(mov);
    }

    @Test
    void findByIdThenMovimentoNaoEncontradoException() {
        when(repository.findById(anyLong())).thenThrow(new MovimentoNaoEncontradoException(MOVIMENTO_NAO_ENCONTRADO));
        try {
            var mov = service.findById(CD_SEQUENCIAL_MOVIMENTO);
        }catch (Exception e){
            assertEquals(MovimentoNaoEncontradoException.class,e.getClass());
            assertEquals(e.getMessage(), MOVIMENTO_NAO_ENCONTRADO);
        }
    }

    @Test
    void update() {
        when(repository.save(any())).thenReturn(movimento);
        var mov = service.update(CD_SEQUENCIAL_MOVIMENTO,movimentoDTO);
        assertNotNull(mov);
    }

    @Test
    void delete() {
        doNothing().when(repository).deleteById(anyLong());
        service.delete(CD_SEQUENCIAL_MOVIMENTO);
        verify(repository, times(1)).deleteById(anyLong());
    }

    private void initMocks(){
        movimento = new Movimento(CD_SEQUENCIAL_MOVIMENTO,
                                  DT_MOVIMENTO,
                                  QT_MOVIMENTO,
                                  TP_MOVIMENTO,
                                  NM_RESPOSAVEL_RECEBIMENTO);
        movimentoDTO = new MovimentoDTO(
                                        CD_SEQUENCIAL_MOVIMENTO,
                                        DT_MOVIMENTO,
                                        QT_MOVIMENTO,
                                        TP_MOVIMENTO,
                                        NM_RESPOSAVEL_RECEBIMENTO);
        movimentoOpt = Optional.of(movimento);
    }
}