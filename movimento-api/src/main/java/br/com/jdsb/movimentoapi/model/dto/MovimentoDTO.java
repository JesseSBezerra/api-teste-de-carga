package br.com.jdsb.movimentoapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentoDTO {

    private Long cdSequencialMovimento;
    private LocalDateTime dtMovimento;
    private Integer qtMovimento;
    private String dsTpMovimento;
    private String nmResposavelRecebimento;

}
