package com.nesea.exercise.carservicesrl.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.util.ISO8601Utils;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import javafx.util.converter.LocalDateTimeStringConverter;
import lombok.*;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Automobile  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAR_SEQ_GEN")
    @SequenceGenerator(name = "CAR_SEQ_GEN", sequenceName = "car_id_seq", allocationSize = 1)
    private Integer id;
    private String modello;
    @Column(length =20)
    private String targa;
    //Il numero di telaio si presenta come una sequenza di 17 caratteri alfanumerici, suddivisibili in 3 parti denominate WMI, VDS, VIS
    //http://assicurazioneauto.soswiki.com/numero-telaio-codice-vin.php
    //https://rgxdb.com/r/61PVCR9B VALIDAZIONE
     /*/^(?<wmi>[A-HJ-NPR-Z\d]{3})(?<vds>[A-HJ-NPR-Z\d]{5})(?<check>[\dX])(?<vis>(?<year>[A-HJ-NPR-Z\d])(?<plant>[A-HJ-NPR-Z\d])(?<seq>[A-HJ-NPR-Z\d]{6}))$/*/
    @Column(length =17)
    private String numeroTelaio;
    @Column(length =50)
    private String colore;
    //@JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm[XXX][X]")
    private LocalDateTime dataVendita;
    private Double prezzoVendita;

    @OneToOne
    @JoinColumn(name = "proprietario_id", referencedColumnName = "id")
    private Proprietario owner;

    //https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    //Fixing the entity identifier equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Automobile p = (Automobile) o;
        return Objects.equals(id, p.id);
    }
    public int hashCode() {
        return 296989308;
    }
}