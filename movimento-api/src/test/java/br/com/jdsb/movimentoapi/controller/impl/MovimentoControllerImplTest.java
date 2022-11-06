package br.com.jdsb.movimentoapi.controller.impl;

import br.com.jdsb.movimentoapi.model.dto.MovimentoDTO;
import br.com.jdsb.movimentoapi.model.entity.Movimento;
import br.com.jdsb.movimentoapi.service.MovimentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
class MovimentoControllerImplTest {

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
    private MovimentoControllerImpl controller;

    @Mock
    private MovimentoService service;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initMocks();
    }

    @Test
    void create() {
        when(service.create(any())).thenReturn(movimentoDTO);
        var mov = controller.create(movimentoDTO);
        assertNotNull(mov);
    }

    @Test
    void findAll() {
        when(service.findAll()).thenReturn(List.of(movimentoDTO));
        var movs = controller.findAll();
        assertNotNull(movs);
        assertEquals(1,movs.getBody().size());

    }

    @Test
    void findById() {
        when(service.findById(anyLong())).thenReturn(movimentoDTO);
        var mov = controller.findById(CD_SEQUENCIAL_MOVIMENTO);
        assertNotNull(mov);
    }

    @Test
    void update() {
        when(service.update(anyLong(),any())).thenReturn(movimentoDTO);
        var mov = controller.update(CD_SEQUENCIAL_MOVIMENTO,movimentoDTO);
        assertNotNull(mov);
    }

    @Test
    void delete() {
        doNothing().when(service).delete(anyLong());
        controller.delete(CD_SEQUENCIAL_MOVIMENTO);
        verify(service, times(1)).delete(anyLong());
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