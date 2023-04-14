package it.jorge.MyPetsWeb.controller;


import io.jsonwebtoken.Claims;
import it.jorge.MyPetsWeb.model.Employee;
import it.jorge.MyPetsWeb.model.Pet;
import it.jorge.MyPetsWeb.service.EmployeeService;
import it.jorge.MyPetsWeb.service.PetService;
import it.jorge.MyPetsWeb.util.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static it.jorge.MyPetsWeb.util.Functions.ENCODER;
import static it.jorge.MyPetsWeb.util.Functions.getJWTToken;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    PetService petService;
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Employee employee){
        Employee login = employeeService.hashLogin(employee.getEmail());

        if(login!= null){
            if (ENCODER.matches(employee.getPass(), login.getPass())){
                return ResponseEntity.ok(getJWTToken(login));
            }
        }
        return new ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/pet")
    public List<Pet> findAllPet (){
        return petService.findAll();
    }

    @PostMapping("/pet")
    public Pet addPet(@RequestBody Pet pet){
        petService.save(pet);
        return pet;
    }

    @PutMapping("/pet")
    public Pet modifierPet(@RequestBody Pet pet){
        petService.save(pet);
        return pet;
    }

    @DeleteMapping("/pet")
    public Pet deletePet(@RequestBody Pet pet){
        petService.delete(pet);
        return pet;
    }
    @GetMapping("/admin")
    public List<Employee> findAllEmployees(@RequestHeader("Authorization") String aut){
        Claims claim = new Functions().getClaims(aut);
        if (claim.get("rol")==2){
            return employeeService.findAll();
        }
        return null;
    }
    @PostMapping("/admin")
    public Employee AddEmployee(@RequestHeader("Authorization") String aut, @RequestBody Employee employee){
        Claims claim = new Functions().getClaims(aut);
        if (claim.get("rol")==2){
            return employeeService.save(employee);
        }
        return null;
    }
    @DeleteMapping("/admin")
    public Employee deleteEmployee(@RequestHeader("Authorization") String aut, @RequestBody Employee employee){
        Claims claim = new Functions().getClaims(aut);
        if (claim.get("rol")==2){
            employeeService.delete(employee);
            return employee;
        }
        return null;
    }

}
