package br.com.jdsb.movimentoapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MOVIMENTO")
public class Movimento {

    @Id
    private Long cdSequencialMovimento;

    @Column
    private LocalDateTime dtMovimento;

    @Column
    private Integer qtMovimento;

    @Column
    private String dsTpMovimento;

    @Column
    private String nmResposavelRecebimento;
}
