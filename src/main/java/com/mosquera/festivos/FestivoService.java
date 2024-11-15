package com.mosquera.festivos;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FestivoService {
    private final FestivoRepository festivoRepository;

    public FestivoService(final FestivoRepository festivoRepository) {
        this.festivoRepository = festivoRepository;
    }

    public boolean esFestivo(final LocalDate fecha) {
        final Optional<Festivo> festivo = festivoRepository.findByDiaAndMes(fecha.getDayOfMonth(), fecha.getMonthValue());
        if (festivo.isPresent()) {
            return true;
        }

        // Manejar festivos basados en Pascua
        final List<Festivo> festivosPascua = festivoRepository.findByTipoIn(Arrays.asList(3, 4));
        for (Festivo f : festivosPascua) {
            final LocalDate pascua = calcularFechaPascua(fecha.getYear());
            LocalDate fechaFestivo = pascua.plusDays(f.getDiasPascua());
            if (f.getTipo() == 4) {
                fechaFestivo = fechaFestivo.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
            }
            if (fecha.equals(fechaFestivo)) {
                return true;
            }
        }

        // Manejar festivos que se trasladan al lunes
        final List<Festivo> festivosPuente = festivoRepository.findByTipo(2);
        for (Festivo f : festivosPuente) {
            final LocalDate fechaOriginal = LocalDate.of(fecha.getYear(), f.getMes(), f.getDia());
            final LocalDate fechaTrasladada = fechaOriginal.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
            if (fecha.equals(fechaTrasladada)) {
                return true;
            }
        }

        return false;
    }

    public String validarFecha(final String textoFecha) {
        try {
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            final LocalDate fecha = LocalDate.parse(textoFecha, formatter);

            // Validar si la fecha es válida en el calendario
            if (fecha.getDayOfMonth() != Integer.parseInt(textoFecha.substring(0, 2)) ||
                    fecha.getMonthValue() != Integer.parseInt(textoFecha.substring(3, 5))) {
                throw new DateTimeParseException("Fecha no válida", textoFecha, 0);
            }

            if (esFestivo(fecha)) {
                return "true";
            } else {
                return "false";
            }
        } catch (DateTimeParseException e) {
            return "{\"Message\": \"Fecha no válida\"}";
        }
    }

    private LocalDate calcularFechaPascua(int year) {
        final int a = year % 19;
        final int b = year % 4;
        final int c = year % 7;
        final int d = (19 * a + 24) % 30;
        final int e = (2 * b + 4 * c + 6 * d + 5) % 7;
        int day = 22 + d + e;
        int month = 3;

        if (day > 31) {
            day -= 31;
            month = 4;
        }

        return LocalDate.of(year, month, day);
    }
}