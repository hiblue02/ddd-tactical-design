package kitchenpos.products.ui;

import kitchenpos.products.application.ProductService;
import kitchenpos.products.dto.ProductChangePriceRequest;
import kitchenpos.products.tobe.domain.Product;
import kitchenpos.products.dto.ProductResponse;
import kitchenpos.products.dto.ProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/products")
@RestController
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductRequest> create(@Valid @RequestBody final ProductResponse request) {
        final Product response = productService.create(request);
        return ResponseEntity.created(URI.create("/api/products/" + response.getId()))
            .body(ProductRequest.fromEntity(response));
    }

    @PutMapping("/{productId}/price")
    public ResponseEntity<Product> changePrice(@PathVariable final UUID productId, @Valid @RequestBody final ProductChangePriceRequest request) {
        return ResponseEntity.ok(productService.changePrice(productId, request));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }
}
