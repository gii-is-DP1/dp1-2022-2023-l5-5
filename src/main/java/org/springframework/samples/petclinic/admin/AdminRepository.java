package org.springframework.samples.petclinic.admin;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {

	@Query("SELECT admin FROM Admin admin")
	Admin findAdmin();
	
	
	

}
