package org.springframework.samples.petclinic.game;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.player.Player;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "games")
public class Game extends BaseEntity{
	
	//dificultad
//	@Column(name = "difficulty")
//	@NotEmpty
//	private Difficulty difficulty;
    @NotEmpty
    @Enumerated(EnumType.STRING)
	@Column(name = "difficulty")
	private Difficulty difficulty;
	
	//tiempo
	//Hay que cambiar el tipo
//	@Column(name = "time")
//    private Integer time;
	
	@NotNull
	@Column(name="start_time")
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.s")
	private LocalDateTime startTime;
	
	@NotNull
	@Column(name="finish_time")
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.s")
	private LocalDateTime finishTime;
    
	//numeroDePulsaciones
	@Column(name = "num_clicks")
    private Integer numClicks;
	
	//enProgreso
    @NotEmpty
    private Boolean inProgress;
    
    
    //partidaPerdida
    @Column(name = "lost_game")
    private Boolean lostGame;
    
    public String duration() {
    	long diferencia = ChronoUnit.SECONDS.between(startTime, finishTime);
    	long min=diferencia/60;
    	long seg=diferencia%60;
    	
    	return String.valueOf(min) + " min" + String.valueOf(seg) + " seg ";
    }
    
    //Relación Juego:Jugador
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Player player;
    
    //Relación Juego:Tablero
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_tablero", referencedColumnName = "id")
    private Tablero tablero;
    
	

}
