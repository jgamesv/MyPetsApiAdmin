package it.jorge.MyPetsWeb.dao;

import it.jorge.MyPetsWeb.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeDAO extends JpaRepository<Employee, Integer>{

    @Query("SELECT u FROM Employee u WHERE u.email = ?1")
    Employee hashLogin (String email);
}
