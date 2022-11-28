package org.springframework.samples.petclinic.square;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "squares")
public class Square extends BaseEntity{
    
    String type;
    
    @Range(min=0,max=15)
    private Integer coordinateX;

    
    @Range(min=0,max=15)
    private Integer coordinateY;


    @Range(min=0,max=8)
    private Integer minesAdjacentNumber;

    
    private Boolean isMine;
    
    @ManyToOne
    Board board;


    public Integer getPositionXInPixels(Integer size){
        return coordinateX*size;
    }
    public Integer getPositionYInPixels(Integer size){
        return coordinateY*size;
    }

//    private Boolean isCovered;
    
    public Square() {
		this.type = "COVERED";
	}



}
