package org.springframework.samples.petclinic.board;

import java.beans.Transient;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.square.Square;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "boards")
public class Board extends BaseEntity{

    String background;
    
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

    public Board(){
        this.background="resources/images/tablero-buscaminas.jpg";
        this.filas=800;
        this.columnas=800;
    }

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "board",fetch = FetchType.EAGER)
    private List<Square> casilla;
	
	//Relación Juego:Tablero
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id")
    private Game game;


     @Transient
     public int getAnchuraTotal() {
        return 900;        
     }

     @Transient
     public int getAlturaTotal() {
        return 840;        
     }

     @Transient
     public int getHeightTotal() {
        return this.filas*(100/2)-((28*this.filas)/9);        
     }

     @Transient
     public int getWidthTotal() {
        return this.columnas*(100/2);        
     }


     

}
