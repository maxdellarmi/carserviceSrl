package com.nesea.exercise.carservicesrl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "automobile")
public class Proprietario implements LookupEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROP_SEQ_GEN")
    @SequenceGenerator(name = "PROP_SEQ_GEN", sequenceName = "prop_id_seq", allocationSize = 1)
    private Integer id;
    @Column(name= "NOME", length =100)
    private String nome;
    @Column(name="COGNOME", length =100)
    private String cognome;
    @Column(name="CODICEFISCALE",length =16)
    private String codiceFiscale;
    @Column(length =100)
    private String indirizzo;
    @Column(length =50)
    private String telefono;
    @Column(length =100)
    private String email;
    @Formula("NOME||' '||COGNOME||' '||CODICEFISCALE")
    private String description;

    @JsonIgnore  //EVITA L'ERRORE DI STACKOVERFLOW NEI RIFERIMENTI CIRCOLARI
    @OneToOne(mappedBy = "owner")
    private Automobile automobile;



    //https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    //Fixing the entity identifier equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proprietario owner= (Proprietario) o;
        return Objects.equals(id, owner.id);
    }
    public int hashCode() {
        return 196989308;
    }
}