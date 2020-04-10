package org.apache.ibatis.plugin.mapper;

import java.util.List;
import java.util.Map;

public class CityMapperImpl implements CityMapper {
    @Override
    public List<Map<String, Object>> query(String name) {
        System.out.println("query");
        return null;
    }

    @Override
    public int insert(String name, String code) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> queryByXml() {
        return null;
    }

    @Override
    public Map<String, Object> queryOne() {
        return null;
    }
}
