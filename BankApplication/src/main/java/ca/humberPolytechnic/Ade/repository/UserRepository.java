package ca.humberPolytechnic.Ade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.humberPolytechnic.Ade.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long>{
	AppUser findByUsername(String username);
}
