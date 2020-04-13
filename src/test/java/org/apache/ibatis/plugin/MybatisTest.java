package org.apache.ibatis.plugin;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.plugin.mapper.CityMapper;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

        List<Map<String, Object>> list = cityMapper.query("");
        System.out.println(list);
//        Map<String, Object> result = cityMapper.queryOne();
        sqlSession.close();
    }

}
