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
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "games")
public class Game extends BaseEntity{
	
    @NotNull
    @Enumerated(EnumType.STRING)
	@Column(name = "difficulty")
	private Difficulty difficulty;
    
	
	@NotNull
	@Column(name="start_time")
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.s")
	private LocalDateTime startTime;


	@Column(name="finish_time")
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss.s")
	private LocalDateTime finishTime;
    
	//numeroDePulsaciones
	@Column(name = "num_clicks")
    private Integer numClicks;
	
	//enProgreso
    @NotNull
    private Boolean inProgress;
    
    
    //partidaPerdida
    @Column(name = "lost_game")
    private Boolean lostGame;
    
    public String duration() {
        if(finishTime == null) {
            long diffInSeconds = ChronoUnit.SECONDS.between(startTime, LocalDateTime.now());
            long minutos = diffInSeconds/60;
            long segundos = diffInSeconds%60;
            return String.valueOf(minutos) + " minutos y " + String.valueOf(segundos) + " segundos";
        }else {
        long diffInSeconds = ChronoUnit.SECONDS.between(startTime, finishTime);
        long minutos = diffInSeconds/60;
        long segundos = diffInSeconds%60;
        return String.valueOf(minutos) + " minutos y " + String.valueOf(segundos) + " segundos";
        }
    }
    
//    public Game() {
//    	
//        startTime = LocalDateTime.now();
//        numClicks = 0;
//        inProgress = true;
//        lostGame = false;
//		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		//Player currentUser = (Player) authentication.getPrincipal();
//        
//        this.player.setUser(null);       
//        this.tablero.setId(1);
//
//    }
    
    //Relación Juego:Jugador
    @ManyToOne()
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Player player;
    
    //Relación Juego:Tablero
    @OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="id_board", referencedColumnName = "id")
    @OnDelete( action = OnDeleteAction.CASCADE )
    private Board board;
    
	

}
