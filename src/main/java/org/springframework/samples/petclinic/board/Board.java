package org.springframework.samples.petclinic.board;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.AuditableEntity;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.square.Square;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "boards")
public class Board extends AuditableEntity {
    

	@Column(name = "rows_num")
	public int rowsNumber;
	
	@Column(name = "columns_num")
	public int columnsNumber;
	
	@Column(name = "mines_num")
	public int minesNumber;
	
	@Column(name = "flags_num")
	public int flagsNumber;
	
	@Column(name = "game_status")
	public GameStatus gameStatus;
	
	@Column(name = "start_time")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	public LocalDateTime startTime;
	
	@Column(name = "finish_time")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	public LocalDateTime finishTime;
	
	@Column(name = "duration")
	@DateTimeFormat(pattern="mm:ss")
	public Duration duration;
	
	@ManyToOne
	@JoinColumn(name="username")
	private Player player;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "board", fetch = FetchType.EAGER)
	public List<Square> squares;
	
	@JsonIgnore
	public long getDurationGame() {
		Duration durationGame = Duration.between(startTime, finishTime);
//		long difHours = durationGame.toHours();
//		long difMinuts = durationGame.toMinutes();
		long difSeconds = durationGame.toSeconds();
		//return ChronoUnit.SECONDS.between(startTime, finishTime);
		return difSeconds;
	}
	
	@JsonIgnore
	public String durationString() {
		if(finishTime == null) {
			long diffInSeconds = ChronoUnit.SECONDS.between(startTime, LocalDateTime.now());
			long minutos = diffInSeconds/60;
			long segundos = diffInSeconds%60;
			return String.valueOf(minutos) + " minuts and " + String.valueOf(segundos) + " seconds";
		}else {
			long diffInSeconds = ChronoUnit.SECONDS.between(startTime, finishTime);
			long minutos = diffInSeconds/60;
			long segundos = diffInSeconds%60;
			return String.valueOf(minutos) + " minuts and " + String.valueOf(segundos) + " seconds";
		}
	}
	
	@JsonIgnore
	public String startTimeString() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm, dd'/'MM'/'yyyy");
		return formato.format(startTime);
	}	
	
	@JsonIgnore
	public String finishTimeString() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm, dd'/'MM'/'yyyy");
		if(finishTime == null) {
			return formato.format(LocalDateTime.now());
		}
		return formato.format(finishTime);
	}
	
	public Board() {
		
	}

	public Board(int rowsNumber, int columnsNumber, int minesNumber, int flagsNumber, Player player) {
	    this.rowsNumber = rowsNumber;
	    this.columnsNumber = columnsNumber;
	    this.minesNumber = minesNumber;
	    this.flagsNumber = flagsNumber;
	    this.gameStatus = GameStatus.NONE;
	    this.startTime = LocalDateTime.now();
	    this.finishTime = null;
	    this.duration = null;
	    this.player = player;
	    this.createSquares();
	}

	public Board(int rowsNumber, int columnsNumber, int minesNumber) {
	    this.rowsNumber = rowsNumber;
	    this.columnsNumber = columnsNumber;
	    this.minesNumber = minesNumber;
	    this.gameStatus = GameStatus.NONE;
	    this.startTime = LocalDateTime.now();
	    this.finishTime = null;
	    this.duration = null;
	    this.createSquares1();
	}

	  public void createSquares(){
	        this.squares = new ArrayList<>();
	        for (int i = 0; i < this.rowsNumber; i++) {
	            for (int j = 0; j < this.columnsNumber; j++) {
	                Square square = new Square(i, j, this);
	                squares.add(square);
	            }
	        }
	        
	    }
	  

	  public void createSquares1(){
		    this.squares = new ArrayList<>();
		    for (int i = 0; i < this.rowsNumber; i++) {
		    for (int j = 0; j < this.columnsNumber; j++) {
		        Square square = new Square(i, j, this);
		        squares.add(square);
		            }
		        }
		        putMines1();
	  }

	    public void putMines(int row, int column){
	        int generatedMines = 0;
	        while(generatedMines < this.minesNumber){
	            int rowPos = (int)(Math.random()*this.rowsNumber);
	            int columnPos = (int)(Math.random()*this.columnsNumber);
		        if (!this.squares.get(columnPos+this.columnsNumber*rowPos).isMine() && rowPos != row && columnPos != column){
		            this.squares.get(columnPos+this.columnsNumber*rowPos).setMine(true);
		            generatedMines++;
	            	
	            }
	        }
	        increaseValueAround();
	    }
	    
	    public void putMines1(){
	        int generatedMines = 0;
	        while(generatedMines < this.minesNumber){
	        	List<Integer> x = List.of(0,2,3,4,4);
	        	List<Integer> y = List.of(0,1,3,1,4);
	            for(int i=0; i<x.size(); i++) {
		        	int rowPos = x.get(i);
		            int columnPos = y.get(i);
		            if (!this.squares.get(columnPos+this.columnsNumber*rowPos).isMine()){
		            	this.squares.get(columnPos+this.columnsNumber*rowPos).setMine(true);;
		                generatedMines++;
		            }
	            }
	        }
	        increaseValueAround();
	    }
	    
	    private void increaseValueAround(){
	        for (int i = 0; i < this.rowsNumber; i++) {
	            for (int j = 0; j < this.columnsNumber; j++) {
	                if (squares.get(j+i*this.columnsNumber).isMine()){
	                    List<Square> squaresAround = surroundingSquares(i, j);
	                    for(Square s : squaresAround) {
	                    	s.increaseValue();
	                    }
	                }
	            }
	        }
	    }
	 
	    public List<Square> surroundingSquares(int row, int column){
	        List<Square> res=new ArrayList<>();
	        for (int i = 0; i < 8; i++) {
	            int r = row;
	            int c = column;
	            switch(i){
	                case 0: 
	                	r--;
	                	break;
	                case 1: 
	                	r++;
	                	break;
	                case 2: 
	                	c++;
	                	break; 
	                case 3: 
	                	c--;
	                	break;
	                case 4: //Esquina inferior derecha
	                	r--;
	                	c++;
	                	break;
	                case 5: //Esquina superior izquierda
	                	r++;
	                	c--;
	                	break;
	                case 6: //Esquina superior derecha
	                	r++;
	                	c++;
	                	break; 
	                case 7: //Esquina inferior inzquierda
	                	r--; 
	                	c--;
	                	break;
	            }
	            
	            if (r>=0 && r<this.rowsNumber
	                    && c>=0 && c<this.columnsNumber){
	                res.add(this.squares.get(c+r*this.columnsNumber));
	            }
	            
	        }
	        return res;
	    }


	 public String toString() {
	    String res = "";
	    for (int i = 0; i < this.rowsNumber; i++) {
	        for (int j = 0; j < this.columnsNumber; j++) {
	        	if(this.squares.get(j+i*this.columnsNumber).isMine()) {
	            	res+="*";
	            }else {
	            	Integer v = this.squares.get(j+i*this.columnsNumber).getValue();
	            	res= res + v.toString();
	            }
	        }
	        res+="\n";
	    }
	    return res;
	}
	 

	public String toString2() {
		String res = "";
	    for (int i = 0; i < this.rowsNumber; i++) {
	        for (int j = 0; j < this.columnsNumber; j++) {
	        	if(this.squares.get(j+i*this.columnsNumber).isCovered()) {
	            	res+="T";
	            }else {
	            	res= res + "F";
	            }
	        }
	        res+="\n";
		        }
		        return res;
	}
		  
	public Square getSquare(int row, int column) {
	   	return this.squares.get(column+column*row);
	}
	  

}
