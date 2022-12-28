package org.springframework.samples.petclinic.square;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.model.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="casillas")
@JsonIgnoreProperties(value="board")
public class Square extends BaseEntity{
	@Column(name = "fila")
	public int fila;
	@Column(name = "columna")
	public int columna;
	@Column(name = "valor")
	public int valor;
	@Column(name = "is_covered")
	public boolean isCovered;
	@Column(name = "is_mine")
	public boolean isMine;
	@Column(name = "isFlag")
	public boolean isFlag;
	@Column(name = "isWrong")
	public boolean isWrong;
	
	@JoinColumn(name = "tablero_id")
	@ManyToOne
	private Board board;

	public Square() {
		
	}

	public Square(int fila, int columna, Board tablero) {
		this.fila = fila;
		this.columna= columna;
		this.valor = 0;
		this.isCovered=true;
		this.isMine=false;
		this.isFlag=false;
		this.board=tablero;
	}
	
	public void increaseValue() {
		this.valor+=1;
	}
}
