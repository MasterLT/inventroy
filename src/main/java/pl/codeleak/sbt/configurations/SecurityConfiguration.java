package pl.codeleak.sbt.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.codeleak.sbt.configurations.interceptors.MyInterceptor;

/**
 * Created by foo on 2017/2/8.
 */
@Configuration
public class SecurityConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin")
        ;
    }

}
