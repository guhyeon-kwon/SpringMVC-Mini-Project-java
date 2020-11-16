package kr.co.springtest.config;

import kr.co.springtest.database.MapperInterface;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Spring MVC 프로젝트에 관련된 설정을 하는 클래스
// xml설정에서 servlet-context.xml을 대체하는 클래스
@Configuration
// Controller 어노테이션이 셋팅되어 있는 클래스를 Controller로 등록한다.
@EnableWebMvc
// 스캔할 패키지를 지정한다.
@ComponentScan("kr.co.springtest.controller")
@ComponentScan("kr.co.springtest.service")
@ComponentScan("kr.co.springtest.dao")
// property 사용 (@Value 사용)
@PropertySource("/WEB-INF/properties/db.properties")
public class ServletAppContext implements WebMvcConfigurer {

    @Value("${db.classname}")
    private String db_classname;

    @Value("${db.url}")
    private String db_url;

    @Value("${db.username}")
    private String db_username;

    @Value("${db.password}")
    private String db_password;

    // Controller의 메서드가 반환하는 JSP의 이름 앞 뒤에 경로와 확장자를 붙여주도록 설정한다.
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        WebMvcConfigurer.super.configureViewResolvers(registry);
        registry.jsp("/WEB-INF/views/",".jsp");
    }

    // 정적 파일의 경로는 매핑한다.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/resources/");
    }

    // 데이터베이스 접속 정보 관리
    // 접속정보를 셋팅하고 정보를 담은 객체를 리턴하는 메서드
    @Bean
    public BasicDataSource dataSource(){
        // BasicDataSource : 아파치에서 제공하는 DB 연결 클래스
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName(db_classname);
        source.setUrl(db_url);
        source.setUsername(db_username);
        source.setPassword(db_password);

        return source; // DB 접속 정보를 가진 객체를 리턴
    }

    // 쿼리문과 접속 관리하는 객체
    // 위에서 셋팅한 접속정보를 가지고 DB연결 객체를 만든다.
    @Bean
    // BasicDataSource가 매개변수로 자동 주입된다.
    // SqlSessionFactory : 데이터베이스와의 연결과 SQL의 실행에 대한 모든 것을 가진 객체
    public SqlSessionFactory factory(BasicDataSource source) throws Exception {
        // SqlSessionFactoryBean : SqlSessionFactory를 만들기 위한 클래스
        // SqlSessionFactoryBean 객체를 만들고 DataSource를 주입한뒤
        // getObject로 리턴한 값을 factory에 담는 방식으로 factory 생성
        // 기존 마이바티스에서 SqlSessionFactoryBuilder를 대체하는 클래스
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(source);
        // 셋팅이 완료된 팩토리를 getObject로 가져와서 사용해야 한다.
        // 이유는 모름. 그냥 그렇게 사용하도록 만들었다고 생각하자
        SqlSessionFactory factory = factoryBean.getObject();
        return factory;
    }

    // 쿼리문 실행을 위한 객체
    // 여기서 mapper(쿼리문)와 데이터베이스 연결을 관리하는 factory를 받아서 DB에 Sql문을 날리는 객체를 만든다.
    @Bean
    public MapperFactoryBean<MapperInterface> test_mapper(SqlSessionFactory factory) throws Exception{
        MapperFactoryBean<MapperInterface> factoryBean = new MapperFactoryBean<>(MapperInterface.class);
        factoryBean.setSqlSessionFactory(factory);
        return factoryBean;
    }

}
