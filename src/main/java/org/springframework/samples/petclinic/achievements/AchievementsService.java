package org.springframework.samples.petclinic.achievements;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AchievementsService {
    
	private AchievementsRepository achievementsRepository;	

	@Autowired
	public AchievementsService(AchievementsRepository achievementsRepository) {
		this.achievementsRepository = achievementsRepository;
	}	

	@Transactional(readOnly = true)
	public List<Achievement> findAllAchievementsPageable(Integer page, Pageable pageable) {
		return this.achievementsRepository.findAllAchievementsPageable(pageable);
	}
	
	@Transactional(readOnly = true)
	public List<Achievement> findAllAchievements() {
		return this.achievementsRepository.findAllAchievements();
	}

	@Transactional(readOnly = true)
	public Integer countAllAchievements() throws DataAccessException {
		return achievementsRepository.countAllAchievements();
	}
	
	@Transactional(readOnly = true)
	public Optional<Achievement> getAchievementById(int id) throws DataAccessException {
		return achievementsRepository.findById(id);
	}

	@Transactional
	public void saveAchievement(Achievement achievement) throws DataAccessException {
		//creating achievement
		achievementsRepository.save(achievement);
	}	

	@Transactional(readOnly = true)
    public Collection<AchievementType> findAllAchievementTypes() throws DataAccessException {
        return achievementsRepository.findAllAchievementTypes();
    }
	
	@Transactional
	public void deleteAchievement(Integer id) {
		achievementsRepository.deleteById(id); 
	}
	
}
