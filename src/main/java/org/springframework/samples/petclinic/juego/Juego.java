package org.springframework.samples.petclinic.juego;

import java.util.Timer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Table;
import org.springframework.core.style.ToStringCreator;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Persona;
import org.springframework.samples.petclinic.user.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "juegos")
public class Juego extends BaseEntity{
	
	//dificultad
	@Column(name = "dificultad")
	@NotEmpty
	private Dificultad dificultad;
	
	//tiempo
	@Column(name = "tiempo")
    private Integer tiempo;
    
	//numeroDePulsaciones
	@Column(name = "numero_pulsaciones")
    private Integer numeroDePulsaciones;
	
	//enProgreso
    @NotEmpty
    private Boolean enProgreso;
    
    //partidaPerdida
    @Column(name = "partida_perdida")
    private Boolean partidaPerdida;
    
    //Relación Juego:Jugador
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Jugador jugador;
    
	

}
