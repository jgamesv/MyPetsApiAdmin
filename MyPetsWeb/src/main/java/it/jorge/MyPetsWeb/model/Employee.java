package it.jorge.MyPetsWeb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="empleados")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Column(name = "rol")
    private int rol;
}
