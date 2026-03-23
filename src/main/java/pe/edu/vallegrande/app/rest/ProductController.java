package pe.edu.vallegrande.app.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.app.model.Product;
import pe.edu.vallegrande.app.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;
    
    // CREATE - POST
    @PostMapping
    public Mono<ResponseEntity<Product>> crear(@RequestBody Product product) {
        return productService.crear(product)
            .map(p -> ResponseEntity.status(HttpStatus.CREATED).body(p))
            .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }
    
    // READ - GET ALL
    @GetMapping
    public Flux<Product> obtenerTodos() {
        return productService.obtenerTodos();
    }
    
    // READ - GET BY ID
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> obtenerPorId(@PathVariable Long id) {
        return productService.obtenerPorId(id)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    // UPDATE - PUT
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Product>> actualizar(@PathVariable Long id, @RequestBody Product product) {
        return productService.actualizar(id, product)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build())
            .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }
    
    // DELETE
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable Long id) {
        return productService.eliminar(id)
            .then(Mono.just(ResponseEntity.ok().<Void>build()))
            .onErrorResume(e -> Mono.just(ResponseEntity.notFound().build()));
    }
}

