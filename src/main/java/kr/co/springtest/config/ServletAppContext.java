package kr.co.springtest.config;

import kr.co.springtest.beans.BoardInfoBean;
import kr.co.springtest.interceptor.TopMenuInterceptor;
import kr.co.springtest.mapper.BoardMapper;
import kr.co.springtest.mapper.TopMenuMapper;
import kr.co.springtest.service.TopMenuService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.*;


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

    @Autowired
    private TopMenuService topMenuService;

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
    public BasicDataSource dataSource() {
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName(db_classname);
        source.setUrl(db_url);
        source.setUsername(db_username);
        source.setPassword(db_password);

        return source;
    }

    @Bean
    public SqlSessionFactory factory(BasicDataSource source) throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(source);
        SqlSessionFactory factory = factoryBean.getObject();
        return factory;
    }

    @Bean
    public MapperFactoryBean<BoardMapper> getBoardMapper(SqlSessionFactory factory) throws Exception{
        MapperFactoryBean<BoardMapper> factoryBean = new MapperFactoryBean<BoardMapper>(BoardMapper.class);
        factoryBean.setSqlSessionFactory(factory);
        return factoryBean;
    }

    @Bean
    public MapperFactoryBean<TopMenuMapper> getTopMenuMapper(SqlSessionFactory factory) throws Exception{
        MapperFactoryBean<TopMenuMapper> factoryBean = new MapperFactoryBean<TopMenuMapper>(TopMenuMapper.class);
        factoryBean.setSqlSessionFactory(factory);
        return factoryBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(topMenuService);
        InterceptorRegistration reg1 = registry.addInterceptor(topMenuInterceptor);
        reg1.addPathPatterns("/**");
    }
}







