package work.gaigeshen.elasticsearch.demo.controller;

import org.springframework.web.bind.annotation.*;
import work.gaigeshen.elasticsearch.demo.commons.PageParameters;
import work.gaigeshen.elasticsearch.demo.commons.PageResponse;
import work.gaigeshen.elasticsearch.demo.service.ProductService;
import work.gaigeshen.elasticsearch.demo.service.dto.ProductCreateParameters;
import work.gaigeshen.elasticsearch.demo.service.dto.ProductCreateResponse;
import work.gaigeshen.elasticsearch.demo.service.dto.ProductQueryParameters;
import work.gaigeshen.elasticsearch.demo.service.dto.ProductQueryResponse;

/**
 *
 * @author gaigeshen
 */
@RequestMapping("/products")
@RestController
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ProductCreateResponse createProduct(@RequestBody ProductCreateParameters createParameters) {
    return productService.createProduct(createParameters);
  }

  @GetMapping
  public PageResponse<ProductQueryResponse> queryProducts(
          ProductQueryParameters queryParameters,
          PageParameters pageParameters) {
    return productService.queryProducts(queryParameters, pageParameters);
  }
}
