package org.springframework.samples.petclinic.tablero;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.springframework.samples.petclinic.model.BaseEntity;


import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tableros")
public class Tablero extends BaseEntity{
    

    

    //ARREGLAR FALLO ENUM DEL SQL
    // @NotEmpty
    // @Enumerated(EnumType.STRING)
    // @Column(columnDefinition = "dificultad")
    // private Dificultad dificultad;

    @Column(name = "filas")
    @NotEmpty
    @Positive
    private Integer filas;

    @Column(name = "columnas")
    @NotEmpty
    @Positive
    private Integer columnas;

    @Column(name = "numero_minas")
    @NotEmpty
    @Positive
    private Integer numeroMinas; 

	// //
	// @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "username", referencedColumnName = "username")
	// private User user;
	// //
	


}
