package org.springframework.samples.petclinic.jugador;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "jugadores")
public class Jugador extends Person{
    

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jugador")
    private List<Game> game;





//    @Override
//	public String toString() {
//		return new ToStringCreator(this)
//
//				.append("id", this.getId()).append("new", this.isNew()).append("nombreUsuario", this.getNombreUsuario())
//				.append("correoElectronico", this.getCorreoElectronico()).toString();
//	}
}
