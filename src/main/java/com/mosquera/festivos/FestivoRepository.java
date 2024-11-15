package com.mosquera.festivos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FestivoRepository extends JpaRepository<Festivo, Long> {
    Optional<Festivo> findByDiaAndMes(final int dia, final int mes);
    List<Festivo> findByTipoIn(final List<Integer> tipos);
    List<Festivo> findByTipo(final int tipo);
}