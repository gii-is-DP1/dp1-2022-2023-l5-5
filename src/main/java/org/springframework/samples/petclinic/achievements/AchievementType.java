package org.springframework.samples.petclinic.achievements;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.NamedEntity;


@Entity
@Table(name = "achievementtypes")
public class AchievementType extends NamedEntity{


}