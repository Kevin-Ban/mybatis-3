/**
 * Copyright 2009-2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
