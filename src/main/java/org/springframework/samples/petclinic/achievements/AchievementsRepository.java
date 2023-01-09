package org.springframework.samples.petclinic.achievements;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementsRepository extends CrudRepository<Achievement, Integer> {

	@Query("SELECT achievement FROM Achievement achievement")
	Achievement findAchievements();

	@Query("SELECT achievement FROM Achievement achievement ORDER BY achievement.id")
	List<Achievement> findAllAchievements(Pageable pageable);

	@Query("SELECT achievement FROM Achievement achievement WHERE achievement.id =:id")
	public Optional<Achievement> findById(@Param("id") int id);

	@Query("SELECT COUNT(ID) FROM Achievement achievement")
	Integer countAllAchievements();

	//@Query("SELECT achievement FROM Achievement achievement WHERE achievement.user.username=:username")
	//public List<Achievement> findAchievementsByUsername(@Param("username") String username,Pageable pageable);
	
	@Query("SELECT atype FROM AchievementType atype ORDER BY atype.name")
    List<AchievementType> findAllAchievementTypes() throws DataAccessException;
}


