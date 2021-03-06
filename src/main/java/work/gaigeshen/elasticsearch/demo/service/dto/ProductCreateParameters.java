package work.gaigeshen.elasticsearch.demo.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 *
 * @author gaigeshen
 */
@Data
public class ProductCreateParameters {

  @NotNull
  private Long id;

  @NotBlank
  private String name;

  @NotNull
  private BigDecimal price;

  @NotNull
  private Integer quantity;

  @NotBlank
  private String category;

  @NotBlank
  private String description;

  @NotBlank
  private String manufacturer;
}
