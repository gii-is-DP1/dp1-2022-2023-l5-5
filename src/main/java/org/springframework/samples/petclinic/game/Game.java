package org.springframework.samples.petclinic.game;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "games")
public class Game extends BaseEntity{
	
	//dificultad
	@Column(name = "difficulty")
	@NotEmpty
	private Difficulty difficulty;
	
	//tiempo
	//Hay que cambiar el tipo
	@Column(name = "time")
    private Integer time;
    
	//numeroDePulsaciones
	@Column(name = "numClicks")
    private Integer numClicks;
	
	//enProgreso
    @NotEmpty
    private Boolean inProgress;
    
    
    //partidaPerdida
    @Column(name = "lostGame")
    private Boolean lostGame;
    
    //Relación Juego:Jugador
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private Player player;
    
    
	

}
