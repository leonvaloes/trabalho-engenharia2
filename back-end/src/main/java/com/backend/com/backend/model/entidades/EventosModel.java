package com.backend.com.backend.model.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Table(name="Eventos", uniqueConstraints ={@UniqueConstraint(columnNames = "Evento_nome")})
@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EventosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="Evento_nome", columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    private String nome;

    @Column(name="Evento_data")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name="tipo_evento_id", nullable = false)
    private TipoEvento tipoEvento;
}

