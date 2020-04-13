package org.apache.ibatis.plugin.mapper;

public class City {

  private String code;

  private String name;

  private Integer id;

  public City(String code, String name, Integer id) {
    this.code = code;
    this.name = name;
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
