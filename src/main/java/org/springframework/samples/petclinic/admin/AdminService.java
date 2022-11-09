package org.springframework.samples.petclinic.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {
	
	private AdminRepository adminRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	
	@Transactional(readOnly = true)
	public Admin findAdmin() {
		return this.adminRepository.findAdmin();
	}
	
	@Transactional
	public void saveAdmin(Admin admin) throws DataAccessException {
		adminRepository.save(admin);
		userService.saveUser(admin.getUser());
		authoritiesService.saveAuthorities(admin.getUser().getUsername(), "admin");
	}
	
	@Transactional
	public Boolean validator(Admin admin) throws DataAccessException {
		Boolean res = null;
		Iterable<Admin> lista = this.adminRepository.findAll(); //El findAll devuelve un Iterable
		List<String> lis = new ArrayList<>();
		for (Admin a : lista) {
			lis.add(a.getUser().getUsername());
		}
		if (lis.contains(admin.getUser().getUsername())) {
			res = true;
		} else {
			res = false;
		}
		
		return res;
	}
	
	
	
}
