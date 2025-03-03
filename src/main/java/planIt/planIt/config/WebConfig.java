//package planIt.planIt.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.nio.charset.StandardCharsets;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    /** JSON요청의 기본 인코딩 UTF-8 설정
//     *
//     */
//    @Bean
//    public HttpMessageConverter<Object> customMessageConverter() {
//
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setDefaultCharset(StandardCharsets.UTF_8);
//
//        return converter;
//
//    }
//
//}
