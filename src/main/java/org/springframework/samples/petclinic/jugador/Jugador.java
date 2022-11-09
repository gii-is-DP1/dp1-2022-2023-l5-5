package org.springframework.samples.petclinic.jugador;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Table;
import org.springframework.core.style.ToStringCreator;
import org.springframework.samples.petclinic.model.Persona;
import org.springframework.samples.petclinic.user.User;

@Entity
@Table(name = "jugadores")
public class Jugador extends Persona{
    
    @Column(name = "correo_electronico")
	@NotEmpty
	private String correoElectronico;

	//
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	//
	

    public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



    @Override
	public String toString() {
		return new ToStringCreator(this)

				.append("id", this.getId()).append("new", this.isNew()).append("nombreUsuario", this.getNombreUsuario())
				.append("correoElectronico", this.getCorreoElectronico()).toString();
	}
}
