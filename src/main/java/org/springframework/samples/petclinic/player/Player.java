package org.springframework.samples.petclinic.player;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.achievements.Achievement;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "players")
public class Player extends Person implements Serializable{
    
	@NotEmpty
	@Email
	@Column(name = "mail")
	private String mail;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	@Valid
	private User user;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    private List<Game> game;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    private List<Achievement> achievements;
	
}
