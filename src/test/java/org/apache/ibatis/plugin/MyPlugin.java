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

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Statement;
import java.util.Properties;
import java.util.stream.Stream;

@Intercepts({
  @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
    Object.class, RowBounds.class, ResultHandler.class})
})
public class MyPlugin implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    Object[] params = invocation.getArgs();
    MappedStatement mappedStatement = Stream.of(params).filter(c -> c.getClass().equals(MappedStatement.class)).map(c -> (MappedStatement) c).findFirst().orElse(null);
    if (mappedStatement != null) {
      Object param = null;
      if (params.length > 1) {
        param = params[1];
        BoundSql boundSql = mappedStatement.getBoundSql(param);
        System.out.println(boundSql.getSql());
      }
    }


    Object result = invocation.proceed();
    return result;
  }

  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  @Override
  public void setProperties(Properties properties) {

  }
}

@Intercepts({
  @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})
})
class SqlPlugin implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    Object result = invocation.proceed();

    return result;
  }

  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  @Override
  public void setProperties(Properties properties) {

  }
}
