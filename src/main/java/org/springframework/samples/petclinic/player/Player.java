package org.springframework.samples.petclinic.player;

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

import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.model.AuditableEntity;
import org.springframework.samples.petclinic.user.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "players")
public class Player extends AuditableEntity {
	
	@Column(name = "first_name")
	@NotEmpty
	protected String firstName;

	@Column(name = "last_name")
	@NotEmpty
	protected String lastName;
    
	@NotEmpty
	@Email
	@Column(name = "mail")
	private String mail;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	@Valid
	@JsonIgnore
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
	@JsonIgnore
	private List<Board> board;

	
}
