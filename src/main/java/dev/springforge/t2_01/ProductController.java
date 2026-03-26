package dev.springforge.t2_01;

import org.springframework.http.ResponseEntity;

/**
 * Exercise: Product CRUD Controller
 *
 * Build a full REST controller for managing products.
 *
 * YOUR TASKS:
 *
 * Ex01 — GET endpoints (list all + get by ID)
 *   1. Annotate class with @RestController and @RequestMapping("/api/products")
 *   2. Inject ProductRepository via constructor
 *   3. GET /api/products → return all products (200 OK with List<Product>)
 *   4. GET /api/products/{id} → return one product or 404
 *      Hint: Use ResponseEntity.ok() and ResponseEntity.notFound().build()
 *
 * Ex02 — POST endpoint (create)
 *   5. POST /api/products → accept CreateProductRequest as @RequestBody
 *   6. Save it, return 201 Created with the saved product
 *      Hint: Use ResponseEntity.status(HttpStatus.CREATED).body(saved)
 *      Or: ResponseEntity.created(URI).body(saved)
 *
 * Ex03 — PUT and DELETE endpoints (update + delete)
 *   7. PUT /api/products/{id} → accept CreateProductRequest, update existing product
 *      Return 200 with updated product, or 404 if not found
 *   8. DELETE /api/products/{id} → delete product
 *      Return 204 No Content if found, 404 if not found
 *      Hint: Use ResponseEntity.noContent().build()
 */
// TODO: Add @RestController and @RequestMapping("/api/products")
public class ProductController {

    // TODO: Inject ProductRepository via constructor

    // TODO: Ex01 — GET /api/products (list all)
    public ResponseEntity<?> getAllProducts() {
        throw new UnsupportedOperationException("Implement getAllProducts()");
    }

    // TODO: Ex01 — GET /api/products/{id} (get one)
    public ResponseEntity<?> getProduct(Long id) {
        throw new UnsupportedOperationException("Implement getProduct()");
    }

    // TODO: Ex02 — POST /api/products (create)
    public ResponseEntity<?> createProduct(CreateProductRequest request) {
        throw new UnsupportedOperationException("Implement createProduct()");
    }

    // TODO: Ex03 — PUT /api/products/{id} (update)
    public ResponseEntity<?> updateProduct(Long id, CreateProductRequest request) {
        throw new UnsupportedOperationException("Implement updateProduct()");
    }

    // TODO: Ex03 — DELETE /api/products/{id} (delete)
    public ResponseEntity<?> deleteProduct(Long id) {
        throw new UnsupportedOperationException("Implement deleteProduct()");
    }
}
