package work.gaigeshen.elasticsearch.demo.service;

import org.springframework.validation.annotation.Validated;

import work.gaigeshen.elasticsearch.demo.commons.PageParameters;
import work.gaigeshen.elasticsearch.demo.commons.PageResponse;
import work.gaigeshen.elasticsearch.demo.service.dto.ProductCreateParameters;
import work.gaigeshen.elasticsearch.demo.service.dto.ProductCreateResponse;
import work.gaigeshen.elasticsearch.demo.service.dto.ProductQueryParameters;
import work.gaigeshen.elasticsearch.demo.service.dto.ProductQueryResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author gaigeshen
 */
@Validated
public interface ProductService {

  ProductCreateResponse createProduct(@NotNull @Valid ProductCreateParameters createParameters);

  PageResponse<ProductQueryResponse> queryProducts(
          @NotNull @Valid ProductQueryParameters queryParameters,
          @NotNull @Valid PageParameters pageParameters);
}
