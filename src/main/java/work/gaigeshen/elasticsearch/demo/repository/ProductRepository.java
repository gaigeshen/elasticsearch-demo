package work.gaigeshen.elasticsearch.demo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import work.gaigeshen.elasticsearch.demo.document.Product;

/**
 *
 * @author gaigeshen
 */
public interface ProductRepository extends ElasticsearchRepository<Product, Long> {

}
