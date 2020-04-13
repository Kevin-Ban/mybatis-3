package org.apache.ibatis.plugin.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CityMapper {

    @Select("select * from city where name like concat('%', #{name}, '%')")
    List<Map<String, Object>> query(@Param("name") String name);

    @Insert("insert into city (name, code) values (#{name}, #{code})")
    int insert(@Param("name") String name, @Param("code") String code);

    List<Map<String, Object>> queryByXml();

    @Select("select * from city")
    Map<String, Object> queryOne();

  int insertCity(City city);
}
