package org.springframework.samples.petclinic.tablero;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.springframework.samples.petclinic.casilla.Casilla;
import org.springframework.samples.petclinic.model.BaseEntity;


import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tableros")
public class Tablero extends BaseEntity{
    

    
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

	@OneToMany
    List<Casilla> casilla;
	


}
