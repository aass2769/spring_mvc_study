package config;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import beans.UserBean;
import interceptor.TopInterceptor;
import interceptor.UserInterceptor;
import mapper.BoardMapper;
import mapper.CourseMapper;
import mapper.MainMapper;
import mapper.TopMapper;
import mapper.UserMapper;
import service.TopService;

// Spring MVC 프로젝트에 관련된 설정을 하는 클래스
@Configuration
// Controller 어노테이션이 셋팅되어 있는 클래스를 Controller로 등록한다.
@EnableWebMvc
// 스캔할 패키지를 지정한다.
@ComponentScan("controller")
@ComponentScan("dao")
@ComponentScan("service")
@ComponentScan("restcontroller")
@PropertySource("/WEB-INF/properties/db.properties")
public class ServletAppContext implements WebMvcConfigurer {

	//@Value를 사용하여 properties 파일에 있는 변수 이름으로 프로퍼티 값을 주입할 수 있다.
	@Value("${db.classname}")
	private String db_classname;

	@Value("${db.url}")
	private String db_url;

	@Value("${db.username}")
	private String db_username;

	@Value("${db.password}")
	private String db_password;

	@Autowired
	private TopService topService;

	// 로그인 sessionScope 빈
	@Resource(name = "userSession")
	private UserBean userSession;

	// Controller의 메서드가 반환하는 jsp의 이름 앞뒤에 경로와 확장자를 붙혀주도록 설정한다.
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	// 정적 파일의 경로를 매핑한다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}

	// 데이터베이스 접속 정보를 관리하는 Bean
	@Bean
	public BasicDataSource dataSource() {

		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(db_classname);
		source.setUrl(db_url);
		source.setUsername(db_username);
		source.setPassword(db_password);

		return source;
	}

	// SqlSessionFactory : SqlSessionFactory는 MyBatis의 핵심 객체로, JDBC 연결 및 SQL 세션을
	// 관리합니다.
	// 이 메서드는 데이터베이스 접속 정보를 받아와서 SqlSessionFactoryBean을 설정하고 SqlSessionFactory를 생성한
	// 후 반환합니다.
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception {

		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(source);
		SqlSessionFactory factory = factoryBean.getObject();

		return factory;
	}

	@Bean
	public MapperFactoryBean<TopMapper> getTopMapper(SqlSessionFactory factory) throws Exception {

		MapperFactoryBean<TopMapper> factoryBean = new MapperFactoryBean<TopMapper>(TopMapper.class);
		factoryBean.setSqlSessionFactory(factory);

		return factoryBean;
	}

	// UserMapper추가
	@Bean
	public MapperFactoryBean<UserMapper> getUserMapper(SqlSessionFactory factory) throws Exception {

		MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<UserMapper>(UserMapper.class);
		factoryBean.setSqlSessionFactory(factory);

		return factoryBean;

	}

	// CourseMapper추가
	@Bean
	public MapperFactoryBean<CourseMapper> getCourseMapper(SqlSessionFactory factory) throws Exception {

		MapperFactoryBean<CourseMapper> factoryBean = new MapperFactoryBean<CourseMapper>(CourseMapper.class);
		factoryBean.setSqlSessionFactory(factory);

		return factoryBean;

	}

	// MainMapper추가
	@Bean
	public MapperFactoryBean<MainMapper> getMainMapper(SqlSessionFactory factory) throws Exception {

		MapperFactoryBean<MainMapper> factoryBean = new MapperFactoryBean<MainMapper>(MainMapper.class);
		factoryBean.setSqlSessionFactory(factory);

		return factoryBean;

	}

	// BoardMapper 추가
	@Bean
	public MapperFactoryBean<BoardMapper> getBoardMapper(SqlSessionFactory factory) throws Exception {

		MapperFactoryBean<BoardMapper> factoryBean = new MapperFactoryBean<BoardMapper>(BoardMapper.class);
		factoryBean.setSqlSessionFactory(factory);

		return factoryBean;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addInterceptors(registry);

		TopInterceptor topInterceptor = new TopInterceptor(userSession, topService);

		InterceptorRegistration reg1 = registry.addInterceptor(topInterceptor);
		reg1.addPathPatterns("/**");
		
		UserInterceptor userInterceptor = new UserInterceptor(userSession);
		InterceptorRegistration reg2 = registry.addInterceptor(userInterceptor);
		reg2.addPathPatterns("/course/registration", "/course/registration_check", "/board/detail");
	}

	// @PropertySource로 등록한 properties파일과 메시지로 등록한 properties파일이 충돌해서 오류가 나기 떄문에
	// PropertySourcesPlaceholderConfigurer 객체를 생성해준다.
	// 정적으로 구성하는 이유는 이 빈이 매우 일찍 초기화되어야 프로퍼티 파일의 값을 초기 단계에서 사용할 수 있다. 빈 초기화 과정에서 정확한
	// 프로퍼티 값을 보장하고 다른 빈들이 올바르게 초기화될 수 있도록 정적으로 작성.
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	// Properties 파일을 Message로 등록한다.
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
	    ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
	    res.setBasenames("/WEB-INF/properties/userErrors", "/WEB-INF/properties/boardErrors");

	    return res;
	}

	// 파일 업로드 위한 'MultipartResolver' 인터페이스의 구현체
	// setting은 springconfigclass에서 함.
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

}
