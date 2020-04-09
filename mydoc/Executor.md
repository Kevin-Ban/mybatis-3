# 1.Executor的分类
    org.apache.ibatis.executor.SimpleExecutor：每执行一次update或select，就开启一个Statement对象，用完立刻关闭Statement对象。
    org.apache.ibatis.executor.BatchExecutor：对批量操作做了封装，不建议使用，我们可以自己在程序中构造sql，然后使用SimpleExecutor
    org.apache.ibatis.executor.ReuseExecutor：可重复使用Statement；
    org.apache.ibatis.executor.ClosedExecutor：执行器关闭，无法执行sql；
    org.apache.ibatis.executor.CachingExecutor：开启二级缓存后使用的执行器，其底层也是使用SimpleExecutor，只不过在顶层封装了一层二级缓存
    
# 2.确定Executor
    在org.apache.ibatis.session.Configuration.newExecutor(org.apache.ibatis.transaction.Transaction, org.apache.ibatis.session.ExecutorType)方法中确定executor
    