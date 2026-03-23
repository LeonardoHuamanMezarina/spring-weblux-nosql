package pe.edu.vallegrande.app.service;

import pe.edu.vallegrande.app.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    
    // CRUD básico
    Mono<Product> crear(Product product);
    
    Mono<Product> obtenerPorId(Long id);
    
    Flux<Product> obtenerTodos();
    
    Mono<Product> actualizar(Long id, Product product);
    
    Mono<Void> eliminar(Long id);
}

