package org.springframework.samples.petclinic.achievements;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.user.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "achievements")
public class Achievement extends BaseEntity implements Serializable {
    
	
	@NotEmpty
	@Column(name = "title")
	private String title;

	@ManyToOne
    @JoinColumn(name = "achievementtypes_id")
    private AchievementType achievementType;

	@NotNull
	@Positive
	@Column(name= "number")
	private Integer number;

	// @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "username", referencedColumnName = "username")
	// @Valid
	// @JsonIgnore
	// private User user;

}
