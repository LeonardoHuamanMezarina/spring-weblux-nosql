package pe.edu.vallegrande.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.app.model.Product;
import pe.edu.vallegrande.app.repository.ProductRepository;
import pe.edu.vallegrande.app.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    
    @Override
    public Mono<Product> crear(Product product) {
        return productRepository.save(product);
    }
    
    @Override
    public Mono<Product> obtenerPorId(Long id) {
        return productRepository.findById(id);
    }
    
    @Override
    public Flux<Product> obtenerTodos() {
        return productRepository.findAll();
    }
    
    @Override
    public Mono<Product> actualizar(Long id, Product product) {
        return productRepository.findById(id)
            .flatMap(existente -> {
                existente.setNombre(product.getNombre());
                existente.setDescripcion(product.getDescripcion());
                existente.setPrecio(product.getPrecio());
                existente.setStock(product.getStock());
                existente.setCategoria(product.getCategoria());
                existente.setMarca(product.getMarca());
                existente.setActivo(product.getActivo());
                return productRepository.save(existente);
            });
    }
    
    @Override
    public Mono<Void> eliminar(Long id) {
        return productRepository.deleteById(id);
    }
}

