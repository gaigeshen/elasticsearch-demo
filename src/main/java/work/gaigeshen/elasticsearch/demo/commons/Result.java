package work.gaigeshen.elasticsearch.demo.commons;

import lombok.Data;

@Data
public class Result<D> {

  private int code;

  private String message;

  private D data;

}
