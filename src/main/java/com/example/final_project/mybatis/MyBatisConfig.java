package com.example.final_project.mybatis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/*
스프링 컨테이너에 빈을 등록할 때 사용하는 어노테이션은 @Component이다.
빈을 등록하는 어노테이션이 하나만 있는게 아니다.
여러가지 어노테이션 중 하나가 @Configuration이다.
빈의 사용 목적을 구분짓기위해 이름을 달리 만든것이며 설정을 위한 빈이라고 알려주는 것이다.
 */
@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {
    /*
    ApplicationContext객체는 스프링 부트의 핵심 인터페이스이다.
    우리가 스프링 컨테이너라고 부르는 논리적인 구조를 실체화한 것이 ApplicationContext이다.
    즉,  Bean관리, DI, AOP지원 등을 담당하는 객체인 것이다.
    이 객체를 통해 우리가 설정에 필요한 값을 가져올 수 있다.
     */
    private final ApplicationContext applicationContext;

//    이 어노테이션은 외부파일의 설정 값들을 자바 객체로 가져올때 사용한다.
//    스프링부트에서는 application.properties라는 파일에 설정값을 작성하므로 해당 파일에서 특정 속성을 가져올 때 사용한다.
//    prefix는 어노테이션의 설정이다.
//    접두사를 설정할 때 사용하는 설정이며 "spring.datasource.hikari"로 시작하는 설정값을 전부 가져오라는 의미이다.
//    가져온 값을 new HikariConfig()로 만든 객체의 필드에 바인딩한다.
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig(){
//        Hikari란?
//        HikariCP(히카리 커넥션 풀) 라이브러리를 의미한다.
//        빠르고, 가볍고, 설정이 쉽고, 안정성이 높다는 장점이 있다.
//        JSP에서 사용한 DBCP는 아파치톰캣에서 지원하는 라이브러리였으나
//        Spring에서는 Hikari를 사용한다. 성능이 상대적으로 더 좋아 MyBatis와 함께 많이 사용한다.
//        new HikariConfig()로 히카리 객체를 만들었는데 이 객체의 필드에 설정값을 저장하여 사용하면된다.
//        설정값을 필드에 직접 저장하지 않고 어노테이션을 사용하여 넣는다.
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource(){
//        DataSource란?
//        DataSource객체는 CP(커넥션 풀)를 관리하고 CP에 있는 커넥션 객체를 제공해주고 반납답는 등의 일을 한다.
//        모든 CP라이브러리는 DataSource객체를 사용한다.
//        이 객체가 데이터베이스의 커넥션을 관리하고, 커넥션 풀을 통해 커넥션 객체를 재사용한다.
//        정리하자면 CP를 사용하려면 DataSource객체가 필요하며 DataSource객체를 만들기 위해서는 DB정보가 필요하다.
//        해당 정보는 HikariConfig객체가 가지고 있다.
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        /*
        JSP에서는 우리가 직접 SqlSessionFactory객체를 설정하여 만들어 사용했다.
        SqlSessionFactoryBuilder에 설정 파일을 제공하면 SqlSessionFactory를 만들어줬었다.
        그리고 우리가 직접 SqlSessionFactory객체를 사용하여 sqlSession객체를 꺼내 사용했다.

        Spring에서는 SqlSessionFactory객체도 Bean으로 만들어 스프링컨테이너가 관리하도록 한다.
        SqlSessionFactoryBean 객체에 설정을 해주면 팩토리를 지어주는 SqlSessionFactoryBuilder의 역할을 수행하며
        그렇게 만들어진 SqlSessionFactory를 스프링 컨테이너가 관리하게 된다.
         */
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        /*
        매퍼파일들의 경로를 알려주기 위해 설한다.
        applicationContext 객체는 현재 프로젝트의 설정, 파일 경로 등을 알고 있다.
        classpath*는 java 디렉터리와 resources디렉터리 에서 검색을하여 일치하는 모든 파일을 찾아낸다.(여러 파일)
        해당 패턴으로 검색된 파일들의 경로를 sqlSessionFactoryBean에 저장한다.
         */
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:/mapper/*.xml"));
//        config.xml파일의 경로를 알려준다.
//        classpath는 하나의 파일만 검색하여 찾는다.
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/config/config.xml"));

        try {
//        팩토리를 꺼낸다.
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
//        팩토리에 camelcase 설정을 한다.
            sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
            return sqlSessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}








