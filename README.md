# :page_with_curl:Study
Spring MVC를 공부하며 각 프로젝트를 구분하고 정리했습니다.<br><br><br><br>

#  :file_folder:Project
:pencil2:  **ServletMVC :** 

	Servlet-Controller, View-jsp, model-java 를 통한 MVC 구조 이해
:pencil2:  **SpringMVCStep1 :** 

	공통 부분 세팅(pom.xml) 라이브러리 Servlet, Jsp, Jstl, SpringMVC
:pencil2:  **SpringMVCXml :**

	xml을 통한 web.xml, root-context.xml, servlet-context.xml 설정<br><br>
:pencil2:  **SpringMVCJava :**

	Java를 통한 web.xml -> AbstractAnnotationConfigDispatcherServletInitializer 상속 or WebApplicationInitializer 인터페이스 구현,
	 root-context.xml -> 상속없음,
	 servlet-context.xml -> WebMvcConfigurer 인터페이스 구현  으로 설정
:pencil2:  **URLMapping :** 

	Controller에 @RequestMapping(value="/", method = RequestMethod.GET)을 통해 맵핑처리를 할 수 있다.
:pencil2:  **RequestMethod :** 

	@GetMapping, @PostMapping 어노테이션을 통해서도 get방식과 post방식을 사용가능하다.
	@GetMapping("/test1"), @PostMapping("/test1")으로 같은 url로 설정해주어도 각각 매핑된 리턴으로 실행된다.<br>
	@RequestMapping와 @GetMapping, @PostMapping에는 차이가 있는데 @RequestMapping은 get과 post둘다 method 속성에 기입해서<br>
	사용할 수 있고 @GetMapping과 @PostMapping은 한 메서드에 동시사용이 불가능하다.
 	(@GetMapping으로 설정된 메서드에서 @postMapping으로 설정된 메서드를 실행시키면 가능하다.)
	get과 post방식 둘다 사용할건데 메서드 내 처리 할 코드가 동일하다면 @RequestMapping으로 처리하면 된다.
 
:pencil2: **GetParameter : 파라미터를 매개변수로 주입받기 4가지 방법<br>**

	-HttpServletRequest로 파라미터 받기
	-HttpServletRequest를 확장한 WebRequest로 파라미터 받기(기능확장)
	-@PathVariable 어노테이션 사용하여 파라미터 받기
	-@RequestParam 어노테이션 사용하여 파라미터 받기

 :pencil2: **GetParameterObject : 파라미터를 객체로 주입받는 방법**
 
	`@RequestParam`을 통해 `Map`으로 주입받을 수 있다. 
 	동일한 명으로 전달되는 2개 이상 파라미터는 하나만 담기며, 2개 이상을 담으려면 `List`로 주입받아야 한다.<br> 형변환이 자동으로 되지 않기 때문에 무조건 	`String`으로 받아야 한다. 그렇기에 번거로울 수 있다.
 	`@ModelAttribute`를 통해 파라미터를 객체로 받을 수 있다.
 	전달되는 파라미터의 이름과 객체 안의 동일한 필드(프로퍼티)에 자동으로 주입된다.자동 형변환이 이루어진다. `@ModelAttribute`는 생략이 가능하다.

:pencil2: **ViewResolver :**

	1.ViewResolver에 의해 JSP가 실행되고 응답결과가 만들어진다.
	2. Controller에서 View를 지정할 때 ViewResolver가 사용할 데이터를 Request영역에 저장할 수 있다.
 
	HttpSerVletRequest와 Model, ModelAndView 사용방법
	-HttpSerVletRequest request는 request.setAttribute("?", ?)
	-Model model 은 mode.addAttribute("?", ?)
	-ModelAndView mv 는 mv.addObject("?", ?)로 데이터세팅 mv.setViewName("?")으로 ViewName세팅
	get이나 post방식으로 데이터를 보낼 때 컨트롤러에서 따로 받고 return에 담아서 보내지 않아도 model에 자동으로 담기며, jsp에서 추출할 수 있음

:pencil2: **CommandObject :** 
커멘드 객체는 자동으로 HttpServletRequest 객체에 담기며, 커멘드 객체로 데이터를 받을 수 있다.
  
	ex)@ModelAttribute DataBean bean으로 데이터를 받으면 넘어온 파라미터 이름과 DataBean에 있는 프로퍼티의 이름이 같은곳에 데이터가 담긴다.
	@ModelAttribute는 생략 가능하며 생략하면 request에 담길 떄 앞글자는 소문자로 담긴다 dataBean
	@ModelAttribute의 클래스 이름을 정의하면 @ModelAttribute("testData") DataBean bean  request영역에 testDate라고 정의한 이름으로 담긴다.

:pencil2: **FormCustomTag :**
<form:> 커스텀 태그를 이용하면 model 객체와 유기적으로 동작할 수 있다.

	<%@ taglig prifix="form" uri="uri="http://www.springframework.org/tags/form" %>를 사용하면 쓸 수 있다.
		
:pencil2: **FormElement1 :**

	<form:>태그의 hidden, input, password,textarea, button에 대해서 공부한 내용.

:pencil2: **FormElement1 :**
	
 	<form:>태그의 select, checkbox, radiobutton에 대해서 공부한 내용.

:pencil2: **RedirectForward :**

	redirect와 foraward를 통해 코드의 흐름을 제어할 수 있다.
	return에 "redirect:/?"; or "forward:/?";로 사용한다.
	redirect는 클라이언트에게 요청할 주소를 응답결과로 전달하며 클라이언트는 응답받은 주소를 요청하게된다.
	주소가 변경이 되며 HttpServletRequest객체는 소멸 후 새롭게 생성되며 HttpSession 객체는 유지된다.
	forward는 서버상에서 흐름이 이동되며 브라우저는 다른 곳으로 흐름이 이동되었다는 것을 알 수 없기 떄문에 주소창에 주소는 변경되지 않는다.
	HttpServletRequest, HttpSession 모두 유지된다.

:pencil2: **RequestScope :** 

	새로운 요청이 발생하고 응답결과가 브라우저로 다시 전달 될 때 까지 요청 정보가 담긴 Request객체를 사용할 수 있다.
	이러한 사용 범위를 'RequestScope'라고한다.
	HttpServletRequest나 Model, ModelAndView, @ModelAttribute로 객체에 데이터를 저장하고 포워드 시 데이터는 request영역에 저장되기 때문에
	forward된 메서드에서는 HttpServletRequest로 받아야한다.

:pencil2: **RequestScopeBeanJava :** 

	@RequestScope로 정의하면 요청이 발생할 떄 마다 Bean객체가 생성되어 자동으로 주입된다.
	주입된 Bean은 요청 발생시 주입만 이루어지는 것이므로 request 영역에 저장되지 않는다.

:pencil2: **RequestScopeBeanXml :**

	scope="request"로 정의하면 요청이 발생할 떄 마다 Bean객체가 생성되어 자동으로 주입된다.
	주입된 Bean은 요청 발생시 주입만 이루어지는 것이므로 request 영역에 저장되지 않는다.
	xml로 bean을 설정하고 byName으로 주입 받을 경우에만 request 영역에 자동 저장된다.

:pencil2: **SessionScope :**

	session 영역에 데이터를 저장하면 SessionScope 내에서 사용이 가능하다.
	@SessionAttribute, @SessionAttributes

:pencil2: **SessionScopeBeanJava :**

	Bean을 정의할 떄 @SessionScope로 정의하면 브라우저가 서버에 최초의 요청을 보낼 때 객체가 주입된다.
	주입된 Bean은 주입만 이루어지므로 session 영역에 저장되지 않는다.
	model에 담고 jsp에서 requestScope로 받아서 사용한다.

:pencil2: **SessionScopeBeanXML :**

	Bean을 정의할 때 Scope="session"으로 정의하면 브라우저가 서버에 최초의 요청을 보낼 때 Bean 객체가 주입된다.
	느낀점 : 무슨이유에서인지 Resource로 sessionBean2로 주입받은 빈은 session영역에 저장이 되어 jsp에서 sessionScope.으로 받을 수 있었다.
	또한 컴포넌트로 스캔한 애들은 lazy를 넣어주지 않아도 오류가 나지않았다. 나머지는 lazy하지 않으면 오류가 생긴다.

:pencil2: **ApplicationScope :**

	HttpServletRequest 객체로 부터 추출이 가능하다. Controller에서 주입 받을 수 있다.
	ServletContext 객체에 데이터나 객체를 담으면 서버가 종료될 떄 까지 사용할 수 있다.

:pencil2: **ApplicationScopeBeanJava :**

	Bean을 정의할 때 scope를 application으로 설정하면 서버가 가동될 때 객체가 주입된다.
	@ApplicationScope 를 사용한다.

:pencil2: **ApplicationScopeBeanXML :** 

	Bean을 정의할 때 scope를 application으로 설정하면 서버가 가동될 때 객체가 주입된다.
	scope = "appliction"으로 설정한다.

:pencil2: **Cookie :** 

	servlet,jsp에서는 쿠키를 인코딩 디코딩해야하며 원하는 쿠키를 꺼낼 수 없고 꺼낼 때 반복문을 돌려서 꺼내야하는 번거로움이 있지만,
	spring에서 제공하는 @CookieValue 어노테이션을 사용하여 쿠키 이름으로 주입받으면 원하는 쿠키를 바로 받을 수 있고 디코딩도 하지 않아도 된다.

:pencil2: **Properties :**

	프로그램 실행 중 절대 변하지 않는 값들을 지정한다.
	Spring MVC에는 이러한 값들을 properties 파일에 작성하고 가져다 사용할 수 있도록 제공한다.
	@PropertySource나 @PropertySources를 사용하여 properties 파일을 지정한다. 하나 또는 다수의 파일을 지정할 수 있다.
	@Value로 properties파일에 작성한 값을 주입받는다. @Value("${aaa.a1}")
	Property Editor를 설치하여 유니코드 형식의 문자열로 변경할 수 있다.

:pencil2: **Message :**

	Properties파일을 Message로 등록하면 데이터를 jsp에서도 사용할 수 있고 다국어 처리도 가능하다.
	java설정 -  Spring MVC 설정 파일에서 ReloadableResourceBundleMessageSource로 Properties파일의 경로를 등록하고 message로 등록할 수 있다.
		message로 등록된 데이터를 java에서 주입받아 사용한다.
	xml설정 - MessageSource와 Accessor을 등록해 사용한다.
	jsp에서 사용할 때 message 커스텀 태그를 사용한다. <spring:message code ="aaa.a1"/>

:pencil2: **Validate :** 

	Validate 라이브러리를 활용하면 사용자 입력 데이터에 대한 유효성 검사를 할 수 있다.
	Bean에 데이터가 입력될 때 어떤 검사를 할 것인지 어노테이션으로 지정하고 조건에 맞지않으면 개발자에게 입력값에 오류가 있다는 정보를 전달함(bean에 저장은됨)
	Controller의 메서드에 주입받는 Bean에 @Valid를 설정하면 유효성검사를 실시함. 유효성 검사 결과를 사용하려면 BindingResult 객체를 주입받아야함.
	jsp에서 잘못 입력한 항목에 대해 메세지를 보여주고싶으면 errors를 사용함. BindingResult객체는 errors라는 이름으로 request영역에 저장됨.

:pencil2: **ValidationMessage :**

	Properties 파일을 활용하면 에러 메세지를 설정할 수 있다.
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>를 사용하여
	<form:errors path="data1"/>를 사용하면 코드를 짧고 간략하게 에러메시지를 표현할 수 있다.

:pencil2: **JSR-303 :**

	JSR-303 스펙을 활용한 여러 입력 값 유효성 검사. javax.validation 라이브러리를 받으면 사용할 수 있다.
	@AssertTrue, @AssertFalse, @Max(값), @Min(값), @DecimalMax(value=값, inclusive=true/false), @DecimalMin(value=값, inclusive=true/false)
	@Null, @NotNull, @Digits(integer=자릿수,fraction=자릿수), @Size(min=글자수,max=글자수), @Pattern(regexp=정규식)

:pencil2: **JSR-380 :** 

	JSR-380 스펙을 활용한 여러 입력 값 유효성 검사. javax.validation 라이브러리와 JSR-380(JSR-303과 JSR-380 (Bean Validation 2.0)) 을 받으면 사용할 수 있다.
	@NotEmpty, @NotBlank, @Positive, @PositiveOrZero, @Negative, @NegativeOrZero, @Email
	
:pencil2: **CustomValidator :**
	
 	추가적인 다른 유효성 검사를 하고자할 떄 사용한다.
	컨트롤러에 @InitBinder 어노테이션을 추가하고 WebDataBinder객체를 인자로 주입받는다. 인자에 Validator클래스를 넣는다.
	Validator 클래스에 supports와 validate 메서드를 오버라이드해 작성한다.

:pencil2: **InterceptorJava :** 

	Interceptor는 요청 주소에 대해 관심을 갖고 요청이 발생하게 되면 요청 주소를 확인하여 Controller의 메서드를 호출 하기 전이나 후에 다른 메서드를 호출 할 수 있도록 가로 채 가는 개념
	HandlerInterceptor 인터페이스를 구현하고 preHandle(메서드 호출 전), postHandle(메서드 수행완료, view처리전), afterCompletion(view처리 완료 응답결과가 브라우저로 전달되기 전)등을 구현하여 사용.
	public void addInterceptors(InterceptorRegistry registry) {}로 등록.
	ex)public void addInterceptors(InterceptorRegistry registry) {
	TestInterceptor1 inter1 = new TestInterceptor1();	인스턴스 생성
	InterceptorRegistration reg1 = registry.addInterceptor(inter1);	인터셉터를 레지스트리에 추가
	reg1.addPathPatterns("/test1");	경로에 인터셉터를 활성화
	*경로 하나 아무거나, **경로 모두포함

:pencil2: **InterceptorXML :**

	위 InterceptorJava 와 유사.
	HandlerInterceptor 인터페이스를 구현하고 preHandle(메서드 호출 전), postHandle(메서드 호출후, view처리전), afterCompletion(view처리 완료 응답결과가 브라우저로 전달되기 전)등을 구현하여 사용.
	<interceptors>
		<interceptor>
			<mapping path="/test1"/>
			<beans:ref bean="inter1"/>
		</interceptor>
	</interceptors> 식으로 등록
	*경로 하나 아무거나, **경로 모두포함

:pencil2: **GlobalExcetpion :** 

	오류처리, @ExceptionHandler사용하고 오류설정.
	ex) @ExceptionHandler(ArrayIndexOutOfBoundsException.class){
		return "error1";
		}
	컨트롤러마다 오류 메서드를 작성하면 비효율적임. Global ExceptionHandler를 구현.
	@ControllerAdvice
	public class GolobalExceptionHandler extends RuntimeException{}내에 오류 메서드 구현

:pencil2: **MyBatisJava :** 

	\Properties파일 생성 후 데이터베이스 접속정보 저장. ServletAppContext파일에 @PropertySource어노테이션을 사용해서 properties 경로 지정. Value어노테이션을 사용해서 값 주입.
	BasicDataSource 메소드, SqlSessionFactory 메소드, MapperFactroyBean 메소드 생성.
	Mapper인터페이스 생성 후 @Insert, @Select 등 어노테이션을 사용한 추상메서드 생성.
	컨트롤러에서 추상메서드 인터페이스를 @Autowired로 설정하고 각 메서드 생성 후 메서드 내부에서 mapper인터페이스.???으로 메서드 실행

:pencil2: **MyBatisXML :**

	Properties파일 생성 후 데이터베이스 접속정보 저장. servlet-context.xml 파일에서 PropertyPlaceholderConfigurer클래스 빈 생성 후 경로를 지정해줌.
	BasicDataSource 빈을 생성하고 property값으로 데이터접속정보 생성.
	SqlSessionFactoryBean 빈을 생성하고 property값으로 BasicDataSource빈(dataSource)와 mapperLocations를 넣어줌. mapperLocations의 value는 경로를 넣어줌.
	SqlSessionTemplate 빈 생성. constructor-arg의 0번째 인덱스에 SqlSessionFactoryBean을 넣어줌.
	mapper.xml파일에 namespace를 지정하고 그안에 insert나 select등 id와 각 type(parameterType이나 resultType등)을 작성하고 쿼리를 작성. 쿼리는 <![CDATA[ 이부분 쿼리작성 ]]>내부에 쿼리 작성하면 특수기호도 문자열로 취급됨.
	Controller에서 SqlSessionTemplate를 @Autowired로 주입하고 각 메서드 생성 후 메서드 내부에서 SqlSessionTemplate인스턴스를 이용해 메서드 실행

:pencil2: **RestfulAPI :** 

	@Controller는 html 페이지를 생성하고 반환하는데 사용되며 뷰 렌더링을 위해 주로 jsp,thymeleaf같은 템플릿 엔진과 함께 사용한다.
	HTTP 요청과 관련된 데이터를 처리하고 HTML 또는 다른 뷰 템플릿(jsp등)으로 전달한다.
	@RestController는 Restful 웹 서비스를 생성하는데 사용한다. 주로 JSON 또는 XML같은 데이터 형식을 반환하는데 사용한다.
	데이터를 반환할 때 자동으로 직렬화되어 HTTP응답으로 전송되며 크롬 스토어의 JSON Formatter 확장프로그램을 추가하면 보기편하다.
	주로 Ajax 요청에 대한 JSON 응답을 처리하거나 REST API를 구축하는 데 사용된다.
	@RestController를 사용하기위해 jackson-databind의존성을 추가한다. (단 . boot는 의존성을 자동제공해주므로 받을필요없음)

----------------------------------------------------------------------------------------------------------------------------
:pencil2: **MiniProject :** 만들 미니 프로젝트의 html,css 구성<br><br>

:pencil2: **MVCProject :** MVC (model, view, controller)구조
	@Bean, @Component, @Controller, @RestController, @ControllerAdvice, @Service, @Repository<br><br>

:pencil2: **MiniProjectJava :**

	42강. 기본 구조 세팅 컨텍스트 패스로 절대경로 정적으로 지정하기. taglib prefix="c"를 이용하며 <c:url var="root" value="/"/>로 지정. 
 	<c:import url="">로 게시판 상단하단 인클루드<br><br>

	43강. 상단 메뉴 외의 링크처리 <c:set var="root" value="${pageContext.request.contextPath}/"/>로 절대경로를 동적으로 세팅하기.

	44강. table 구성하고 Bean클래스 만들기

	45강. Java와 XML 방식의 Mybatis 설정

	46강. 상단 메뉴 구성하기(Java) 
	db에서 게시판 이름을 가져와 메뉴를 구성. 상단 메뉴는 모든 요청에서 처리해야 하는 부분이므로 Interceptor에서 처리

	47강. 상단 메뉴 구성하기(XML)
	db에서 게시판 이름을 가져와 메뉴를 구성. 상단 메뉴는 모든 요청에서 처리해야 하는 부분이므로 Interceptor에서 처리

	48강. Java방식과 XML 방식을 이용한 회원가입 유효성처리.
	validation message처리와 validation 커스텀 처리

	49강. Java방식과 XML 방식을 이용한 회원가입 아이디 중복확인( 유효성처리. Rest API와 ajax통신)

	50강. Java방식과 XML 방식을 이용한 회원가입 데이터 저장처리(INSERT)

	51강. Java방식과 XML 방식을 이용한 로그인 처리
	<Java방식>
	로그인 상태 변수를 빈의 생성자에 false로 초기 세팅.
	로그인 form 입력 시 db로 아이디 비번을 보내고 아이디 비번을 이용해 idx와 name을 가져오며 값이 있을 때 로그인상태 변수에 true 세팅,
	및 sessionScope로 설정한 빈에 idx와 name 등 추가. 컨트롤러에서 sessionScope로 설정된 빈의 로그인 상태 변수에 값이 true경우 성공, false경우 실패로 		return값 나누기.
	로그인 메서드에 @RequestParam으로 fail값을 받고 값이 true면 <c:if>를 이용해 아이디 비번 로그인 실패 시 다시 확인하라는 문구 출력.
	false면 나타나지 않음.

	52강. Java방식과 XML 방식을 이용한 상단 메뉴, 로그 아웃처리
	로그인상태면 정보수정과 로그아웃 보이게 로그아웃상태면 로그인과 회원가입 보이게(<c:choose>를 <c:when>와 <c:otherwise>를통해 사용).
	로그아웃 컨트롤러에서 로그인 상태변수를 false로 설정.

	53강. Java방식과 XML 방식을 이용한 로그인 여부 확인처리
	로그인을 하지 않고 글보기나 글쓰기나 로그아웃이나 정보수정 페이지에 접근 시 로그인창으로 redirect시킴-interceptor를 이용해 구현한다.

	54강. Java방식과 XML 방식을 이용한 사용자 정보수정 처리

	55강. Java방식과 XML 방식을 이용한 글 작성 유효성검사 및 form태그 처리

	56강. Java방식과 XML 방식을 이용한 글 작성 파일업로드.
	<java 파일업로드 순서>
	jsp의 form에 enctype=enctype="multipart/form-data"설정하고 ServletAppContext 파일에 StandardServletMultipartResolver 객체를 반환하는 메서드를 빈으로 설정.
	SpringConfigClass에서 멀티파트 파일 업로드 구성을 설정하는 customizeRegistration오버라이드 하고 MultipartConfigElement객체를 생성하며 생성자에 매개변수로 경로, 파일크기, 멀티파트 요청크기, 저장되기전 메모리에 유지하는 파일최소크기 설정.
	properties파일에 path.upload로 실제 프로젝트가 실행되는 경로 설정. service에 PropertySource로 properties파일 경로 지정. 파일을 저장하고 이름을 리턴하는 메소드 생성
	파일의 사이즈가 0보다 크면 set하고 dao를 호출하는 메서드 생성.

	<xml 파일업로드 순서>
	jsp의 form에 enctype=enctype="multipart/form-data"설정하고 servlet-context.xml 파일에 StandardServletMultipartResolver 객체 빈으로 설정(id꼭 주기)
	web.xml의 servlet태그 안에 multipart-config안에 max-file-size, max-request-size, file-size-threshold 로 파일 업로드 구성 설정. web.xml에 filter설정과 filter-mapping 설정
	properties파일에 path.upload로 실제 프로젝트가 실행되는 경로 설정. service에 PropertySource로 properties파일 경로 지정. 파일을 저장하고 이름을 리턴하는 메소드 생성
	파일의 사이즈가 0보다 크면 set하고 dao를 호출하는 메서드 생성.

	57강. Java방식과 XML 방식을 이용한 글목록 구현하기.

	58강. Java방식과 XML 방식을 이용한 글 읽는 페이지 구현.
	글 인덱스 번호를 구하기위해 selectKey사용.

	59강. Java방식과 XML 방식을 이용한 글 수정 처리 중 수정 권한에 대한 처리
	Interceptor를 이용해 로그인한 사람의 idx값과 게시판의 idx값이 다를경우 수정과 삭제 접근 불가. 및 url도 접근 불가

	60강. Java방식과 XML 방식을 이용한 글 수정하기.

	61강. Java방식과 XML 방식을 이용한 글 삭제하기.

	62강. Java방식과 XML 방식을 이용한 페이징 구현하기.
	springMVC에서는 mybatis를 이용하여 RowBounds 클래스를 이용하여 데이터베이스에서 쉽게 원하는 개수의 데이터 row를 가져올 수 있음. 생성자로 (시작, 개수)넣어주기.
	pageBean을 만듬. 필드에 min(최소 페이지번호), max(최대 페이지번호), prevPage(이전 버튼의 페이지번호), nextPage(다음 버튼의 페이지번호), pageCnt(전체 페이지 개수), currentPage(현재 페이지번호)를 만듬.
	pageBean의 생성자로 contentCnt(전체 글 개수), currentPage(현재 글 번호), contentPageCnt(페이지당 글의 개수), paginationCnt(페이지 버튼의 개수)를 받음.
	생성자 내부에서 필드 값들 초기화. 서비스에서 pageBean의 생성자에 넣어줄 전체 게시글 수 메소드 구현 후  값 pageBean 인스턴스의 생성자에 값넣어주며 인스턴스 생성. pageBean 리턴.
	jsp에서 pageBean을 이용하여 페이지네이션 버튼 생성.

	63강. Java방식과 XML 방식을 이용한 게시판에 관련된 곳 페이지번호 세팅, 메인페이지 구성

	


	
