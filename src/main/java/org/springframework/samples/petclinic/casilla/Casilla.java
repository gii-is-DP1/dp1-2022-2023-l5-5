package org.springframework.samples.petclinic.casilla;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.tablero.Tablero;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "casillas")
public class Casilla extends BaseEntity{
    

    @Column(name = "coordenada_x")
    @NotEmpty
    @Range(min=0,max=15)
    private Integer coordenadaX;

    @Column(name = "coordenada_y")
    @NotEmpty
    @Range(min=0,max=15)
    private Integer coordenadaY;


    @Column(name= " num_minas_adyacentes")
    @Range(min=0,max=8)
    @NotEmpty
    private Integer numMinasAdyacentes;

    @Column(name = "esta_cubierta")
    @NotEmpty
    private Boolean estaCubierta;

    @Column(name = "contenido")
    @NotEmpty
    private String contenido;

   // @ManyToOne
    //Tablero tablero;

}
