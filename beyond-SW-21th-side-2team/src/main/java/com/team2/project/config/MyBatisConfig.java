package com.team2.project.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatis 설정 및 SqlSession 관리 클래스
 */
public class MyBatisConfig {
    
    private static SqlSessionFactory sqlSessionFactory;
    
    static {
        try {
            // MyBatis 설정 파일 로드
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("MyBatis 초기화 실패", e);
        }
    }
    
    /**
     * SqlSession 반환 (자동 커밋 false)
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(false);
    }
    
    /**
     * SqlSession 반환 (자동 커밋 설정 가능)
     */
    public static SqlSession getSqlSession(boolean autoCommit) {
        return sqlSessionFactory.openSession(autoCommit);
    }
}
