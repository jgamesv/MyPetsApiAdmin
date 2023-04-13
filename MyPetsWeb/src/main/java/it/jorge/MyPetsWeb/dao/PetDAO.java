package it.jorge.MyPetsWeb.dao;

import it.jorge.MyPetsWeb.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetDAO extends JpaRepository<Pet, Integer>{

}
