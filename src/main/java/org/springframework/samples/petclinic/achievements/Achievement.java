package org.springframework.samples.petclinic.achievements;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import org.springframework.samples.petclinic.model.AuditableEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "achievements")
public class Achievement extends AuditableEntity implements Serializable {
   	
	@NotEmpty
	@Column(name = "title")
	private String title;

	@NotNull
	@Positive
	@Column(name= "number")
	private Integer number;
	
	@ManyToOne
	@NotNull
    @JoinColumn(name = "achievementtypes_id")
    private AchievementType achievementType;
}
