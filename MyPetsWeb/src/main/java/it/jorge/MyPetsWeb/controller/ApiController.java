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

import static it.jorge.MyPetsWeb.util.Functions.*;

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
    public ResponseEntity<?> findAllEmployees(@RequestHeader("Authorization") String aut){
        Claims claim = new Functions().getClaims(aut);
        if (claim.get("rol").equals("2")){
            return ResponseEntity.ok(employeeService.findAll());
        }
        return new ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
    @PostMapping("/admin")
    public ResponseEntity<?> AddEmployee(@RequestHeader("Authorization") String token,@RequestBody Employee employee){
        Claims claim = new Functions().getClaims(token);
        if (claim.get("rol").equals("2")) {
            employee.setPass(encodePass(employee.getPass()));
            return ResponseEntity.ok(employeeService.save(employee));
        }
        return new ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
    @DeleteMapping("/admin")
    public ResponseEntity<?> deleteEmployee(@RequestHeader("Authorization") String aut, @RequestBody Employee employee){
        Claims claim = new Functions().getClaims(aut);
        if ((Integer)claim.get("rol")==2){
            employeeService.delete(employee);
            return ResponseEntity.ok(employee);
        }
        return new ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

}
