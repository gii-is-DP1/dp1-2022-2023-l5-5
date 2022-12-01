package org.springframework.samples.petclinic.achievements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AchievementsService {
    
	@Autowired
    private AchievementsRepository achievementsRepository;	

	@Autowired
	public AchievementsService(AchievementsRepository achievementsRepository) {
		this.achievementsRepository = achievementsRepository;
	}	

	@Transactional(readOnly = true)
	public List<Achievement> findAllAchievements(Integer page, Pageable pageable) {
		return this.achievementsRepository.findAllAchievements(pageable);
	}

	@Transactional(readOnly = true)
	public Integer countAllAchievements() throws DataAccessException {
		return achievementsRepository.countAllAchievements();
	}

	
	@Transactional(readOnly = true)
	public Optional<Achievement> getAchievementById(int id) throws DataAccessException {
		return achievementsRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Achievement> getAchievementsByUsername(String username, Pageable pageable, Integer page) throws DataAccessException {
		return achievementsRepository.findAchievementsByUsername(username,pageable);
	}

}
