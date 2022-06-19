package work.gaigeshen.elasticsearch.demo.service;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import work.gaigeshen.elasticsearch.demo.commons.PageParameters;
import work.gaigeshen.elasticsearch.demo.commons.PageResponse;
import work.gaigeshen.elasticsearch.demo.commons.PageResponseCreator;
import work.gaigeshen.elasticsearch.demo.document.Product;
import work.gaigeshen.elasticsearch.demo.service.dto.ProductCreateParameters;
import work.gaigeshen.elasticsearch.demo.service.dto.ProductCreateResponse;
import work.gaigeshen.elasticsearch.demo.service.dto.ProductQueryParameters;
import work.gaigeshen.elasticsearch.demo.service.dto.ProductQueryResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author gaigeshen
 */
@Service
public class ProductServiceImpl implements ProductService {

  private final ElasticsearchOperations elasticsearchOperations;

  public ProductServiceImpl(ElasticsearchOperations elasticsearchOperations) {
    this.elasticsearchOperations = elasticsearchOperations;
  }

  @Override
  public ProductCreateResponse createProduct(ProductCreateParameters createParameters) {
    Product product = new Product();
    BeanUtils.copyProperties(createParameters, product);

    elasticsearchOperations.save(product);

    return new ProductCreateResponse();
  }

  @Override
  public PageResponse<ProductQueryResponse> queryProducts(ProductQueryParameters queryParameters, PageParameters pageParameters) {
    NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
    if (Objects.nonNull(queryParameters.getName())) {
      searchQueryBuilder.withQuery(QueryBuilders.boolQuery().must(QueryBuilders.wildcardQuery("name", "*" + queryParameters.getName() + "*")));
    }
    BoolQueryBuilder filterQueryBuilder = QueryBuilders.boolQuery();
    if (Objects.nonNull(queryParameters.getCategory())) {
      filterQueryBuilder.must(QueryBuilders.matchQuery("category", queryParameters.getCategory()));
    }
    if (Objects.nonNull(queryParameters.getLowPrice())) {
      filterQueryBuilder.must(QueryBuilders.rangeQuery("price").from(queryParameters.getLowPrice()));
    }
    if (Objects.nonNull(queryParameters.getHighPricie())) {
      filterQueryBuilder.must(QueryBuilders.rangeQuery("price").to(queryParameters.getHighPricie()));
    }
    searchQueryBuilder.withFilter(filterQueryBuilder);

    searchQueryBuilder.withPageable(PageRequest.of(pageParameters.getPage() - 1, pageParameters.getSize()));

    SearchHits<Product> searchHits = elasticsearchOperations.search(searchQueryBuilder.build(), Product.class);
    if (searchHits.isEmpty()) {
      return PageResponseCreator.create(pageParameters);
    }

    List<ProductQueryResponse> queryResponses = searchHits.getSearchHits().stream().map(productSearchHit -> {
      ProductQueryResponse queryResponse = new ProductQueryResponse();
      BeanUtils.copyProperties(productSearchHit.getContent(), queryResponse);
      return queryResponse;
    }).collect(Collectors.toList());

    return PageResponseCreator.create(pageParameters, queryResponses, (int) searchHits.getTotalHits());
  }
}
