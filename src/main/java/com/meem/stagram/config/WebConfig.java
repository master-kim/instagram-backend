package com.meem.stagram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 설명 : WebConfig > 스프링 부트 초기 설정
 * ------------------------------------------------------------- 
 * 작업일          작업자    작업내용
 * ------------------------------------------------------------- 
 * 2022.10.01    김요한    최초작성 
 * -------------------------------------------------------------
 */
/*
* preflight
* Simple request가 아닌 요청 메시지보다 먼저 보내는 메시지로, 브라우저는 응답값으로 실제 데이터 전송 여부를 판단.
* CORS는 응답이 Access-Control-Allow-Credentials: true 을 가질 경우, Access-Controll-Allow-Origin의 값으로 *를 사용하지 못하게 막고 있다
* Access-Control-Allow-Credentials: true 사용 시 사용자 인증이 필요한 리소스 접근이 필요한 경우이다.
* Access-Control-Allow-Origin: * 허용 시, CSRF 공격에 매우 취약해져 악의적인 사용자가 인증이 필요한 리소스를 마음대로 접근할 수 있다.
*/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 개발 환경에서의 크로스 도메인 이슈를 해결하기 위한 코드로
     * 운영 환경에 배포할 경우에는 15~18행을 주석 처리합니다.
     * 
     * ※크로스 도메인 이슈: 브라우저에서 다른 도메인으로 URL 요청을 하는 경우 나타나는 보안문제
     */
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                         // 프로그램에서 제공하는 URL  
                //.allowedHeaders("*")                     // 어떤 헤더들을 허용할 것인지 
                .allowedMethods("GET", "POST")             // 어떤 메서드를 허용할 것인지 (GET, POST...) 
                .allowCredentials(true)                    // 쿠키 요청을 허용한다(다른 도메인 서버에 인증하는 경우에만 사용해야하며, true 설정시 보안상 이슈가 발생할 수 있다) 
                .allowedOrigins("http://localhost:3000");  // 요청을 허용할 출처를 명시, 전체 허용 (가능하다면 목록을 작성한다)
    }
}