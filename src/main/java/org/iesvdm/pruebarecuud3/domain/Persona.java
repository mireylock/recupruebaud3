package org.iesvdm.pruebarecuud3.domain;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    private int id;

    //    Utiliza la siguiente expresión regular para validar el nif/nie
//            ((([X-Z])|([LM])){1}([-]?)((\d){7})([-]?)([A-Z]{1}))|((\d{8})([-]?)([A-Z]))

    @Pattern(regexp = "((([X-Z])|([LM])){1}([-]?)((\\d){7})([-]?)([A-Z]{1}))|((\\d{8})([-]?)([A-Z]))", message = "{msg.nif}")
    private String nif;

    //    Campo nombre no puede estar vacío y debe tener una longitud mínima de 3 caracteres.
    @NotNull(message = "{msg.not.null}")
    @NotBlank(message="{msg.not.blank}")
    @Size(min=3, message="{msg.min}")
    private String nombre;

    private String apellido1;
    private String apellido2;

    //    Campo ciudad no puede contener más de 25 caracteres.
    @Size(max=25, message = "{msg.max}")
    private String ciudad;
    private String direccion;
    private String telefono;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha_nacimiento;
    private String tipo;

    private Departamento departamento;


}
