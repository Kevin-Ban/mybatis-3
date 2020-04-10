#  新增插件
```
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
                Object.class, RowBounds.class, ResultHandler.class})
})
public class MyPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        System.out.println("---------" + args);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

```
之后需要再初始化的时候调用 org.apache.ibatis.session.Configuration.addInterceptor进行插件的注册


# 可新增插件的位置
其他地方插件不起效
```
1. Executor: update, query, flushStatements, commit, rollback, getTransaction, close, isClosed
2. ParameterHandler: getParameterObject, setParameters
3. ResultSetHandler: handleResultSets, handleOutputParameters
4. StatementHandler: prepare, parameterize, batch, update, query
```