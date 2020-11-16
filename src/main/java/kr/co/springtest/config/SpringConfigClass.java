package kr.co.springtest.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.persistence.criteria.Root;
import javax.servlet.*;

// WebApplicationInitializer
// web.xml 파일을 대체하는 인터페이스
// 서버를 실행하면 WebApplicationInitializer을 implements하는 클래스의 onStartup를 자동으로 호출한다.
//public class SpringConfigClass implements WebApplicationInitializer {
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        System.out.println("onStartup");
//        // Spring MVC 프로젝트 설정을 위해 작성하는 클래스의 객체를 생성한다.
//        // AnnotationConfigWebApplicationContext
//        // 어노테이션을 읽어 bean을 등록하는 역할을 담당
//        // @Configuration 클래스뿐만 아니라 평범한 @Component 클래스와 JSR-330 메타데이터로
//        // 어노테이션이 붙은 클래스들도 받아들일 수 있다.
//        // 어노테이션을 이용한 설정 리소스를 사용하기 위하여 사용한다.
//        // 어노테이션이 붙은 클래스를 사용하기 위해서는 이 클래스에 담아서 사용해야 한다.
//        AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
//        // register
//        // 기본 생성자에 포함되어 반드시 호출하게 되어 있다.
//        // 미리 만들어둔 Spring MVC 설정 파일을 register를 사용하여 bean으로 등록한다.
//        // 이제 servletAppContext는 ServletAppContext 클래스의 어노테이션을 읽고 해당 클래스의 설정값을 읽고 해당 값들을 담았다.
//        // register는 지정한 클래스 파일의 어노테이션들을 스캔하지만 패키지 파일을 통째로 스캔하고 싶다면 scan 메서드를 사용해야 한다.
//        servletAppContext.register(ServletAppContext.class);
//
//        // 요청 발생 시 요청을 처리하는 서블릿을 DispatcherServlet으로 설정해준다.
//        // 이제 DispatcherServlet은 servletAppContext에서 설정한 ServletAppContext.class의 설정값을 사용한다.
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext); // dispatcher 객체 생성
//        // 요청을 받고 처리할 dispatcher를 위에서 설정한 dispatcherServlet 객체를 사용하도록 지정한다.
//        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet); // dispatcherServlet 등록
//
//        // 부가설정
//        servlet.setLoadOnStartup(1); // 요청에 대해 가장 먼저 받아들인다.
//        servlet.addMapping("/"); // 루트 경로 밑으로 들어오는 모든 요청을 처리한다.
//
//        //Bean을 정의하는 클래스를 지정한다.
//        AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
//        rootAppContext.register(RootAppContext.class);
//        // rootAppContext 객체에 RootAppContext.class를 담았다.
//        // 이제 rootAppContext 객체를 통해 RootAppContext.class를 사용할 수 있게 되었다
//        // 이걸 리스너에 등록하여 객체를 사용해 보도록 하자.
//
//        // 리스너 등록
//        // 리스너 객체를 생성하고 rootAppContext를 리스너 객체로 만들어준다.
//        ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
//        // 생성된 리스너가 요청을 감시하도록 지정한다.(리스너 등록)
//        servletContext.addListener(listener);
//
//        // 파라미터 인코딩 설정
//        // 필터의 이름은 encodingFilter
//        // 필터가 사용할 클래스는 CharacterEncodingFilter
//        // 해당 필터를 들어온 요청에 대해 적용하도록 addFilter 사용
//        FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
//        // 필터 값 설정
//        // 인코딩은 UTF-8로
//        filter.setInitParameter("encoding", "UTF-8");
//        // dispatcher라는 이름으로 등록된 서블릿이 받아들이는 요청에 대해 이 필터를 적용한다.
//        filter.addMappingForServletNames(null, false, "dispatcher");
//    }
//}

public class SpringConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer{
    // DispatcherServlet에 매핑할 요청 주소를 셋팅
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    // 프로젝트에서 사용할 Bean들을 정의하기 위한 클래스를 지정한다.
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootAppContext.class};
    }
    // Spring MVC 프로젝트 설정을 위한 클래스를 지정한다.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ServletAppContext.class};
    }
    
    // 파라미터 인코딩 필터 설정
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter(); // 인코딩 설정을 위한 인코딩 객체 초기화
        encodingFilter.setEncoding("UTF-8"); // 인코딩 설정
        return new Filter[] {encodingFilter}; // 필터에 적용
    }
}