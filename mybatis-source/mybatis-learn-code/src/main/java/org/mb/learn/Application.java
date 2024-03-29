package org.mb.learn;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mb.learn.entity.User;

import java.io.IOException;
import java.io.Reader;

public class Application {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis.xml";
        Reader reader = Resources.getResourceAsReader(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession session = sqlSessionFactory.openSession();

        try {
            User user = session.selectOne("org.mb.learn.mapper.UserMapper.selectById", 2);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
