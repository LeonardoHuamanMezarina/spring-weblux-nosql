package pe.edu.vallegrande.app.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.app.model.Product;

@Repository
public interface ProductRepository extends R2dbcRepository<Product, Long> {
}

