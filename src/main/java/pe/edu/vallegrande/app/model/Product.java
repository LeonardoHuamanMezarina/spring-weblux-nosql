package pe.edu.vallegrande.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("products")
public class Product {

    @Id
    private Long id;
    
    private String nombre;
    
    private String descripcion;
    
    private BigDecimal precio;
    
    private Integer stock;
    
    private String categoria;
    
    private String marca;
    
    private Boolean activo;
}
