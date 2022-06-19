package work.gaigeshen.elasticsearch.demo.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

/**
 *
 * @author gaigeshen
 */
@Document(indexName = "product")
@Data
public class Product {

  @Id
  @Field(type = FieldType.Long, name = "id")
  private Long id;

  @Field(type = FieldType.Keyword, name = "name")
  private String name;

  @Field(type = FieldType.Double, name = "price")
  private BigDecimal price;

  @Field(type = FieldType.Integer, name = "quantity")
  private Integer quantity;

  @Field(type = FieldType.Keyword, name = "category")
  private String category;

  @Field(type = FieldType.Text, name = "description")
  private String description;

  @Field(type = FieldType.Keyword, name = "manufacturer")
  private String manufacturer;
}
