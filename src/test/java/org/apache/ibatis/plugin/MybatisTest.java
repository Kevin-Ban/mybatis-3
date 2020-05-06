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
package org.apache.ibatis.plugin;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.plugin.mapper.City;
import org.apache.ibatis.plugin.mapper.CityMapper;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;

public class MybatisTest {

  private static SqlSessionFactory sqlSessionFactory = null;

  static {
    try {
      buildFactory();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 构造数据库连接信息，并配置session
   */
  private static void buildFactory() throws IOException {
    PooledDataSource dataSource = new PooledDataSource();
    dataSource.setDriver("com.mysql.cj.jdbc.Driver");
    dataSource.setUsername("root");
    dataSource.setPassword("123456");
    dataSource.setUrl("jdbc:mysql://127.0.0.1/mybatis?useUnicode=true&serverTimezone=UTC&characterEncoding=utf-8&allowMultiQueries=true");
    TransactionFactory transactionFactory = new JdbcTransactionFactory();
    Environment environment = new Environment("development", transactionFactory, dataSource);
    Configuration configuration = new Configuration(environment);
    configuration.setCacheEnabled(false);
    configuration.addMapper(CityMapper.class);
    // 注册一个插件
    configuration.addInterceptor(new MyPlugin());
    // SqlSession是线程不安全的，每个线程必须拥有一个session，不允许线程共享或设置成静态变量等
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
  }

  public static void main(String[] args) {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    // 通过session获取到mapper类
    CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);

//        List<Map<String, Object>> list = cityMapper.queryByXml();
    City city = new City("testCode", "testName", null);
    int result = cityMapper.insertCity(city);
    System.out.println(result);
//        Map<String, Object> result = cityMapper.queryOne();
    sqlSession.close();
  }

}
