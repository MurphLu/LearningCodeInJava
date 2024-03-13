spring 整合 mybatis 后一级缓存失效

未开启事务的的情况下，为保证线程安全 sqlSessionTemplate 会保证每次拿到的是一个新的 sqlSession，来保证每个 sql 执行是线程安全的，

开启事务后则能够保证事务中所使用的 sqlSession 为同一个