package br.com.jdsb.movimentoapi.repository;

import br.com.jdsb.movimentoapi.model.entity.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentoRepository extends JpaRepository<Movimento,Long> {
}
