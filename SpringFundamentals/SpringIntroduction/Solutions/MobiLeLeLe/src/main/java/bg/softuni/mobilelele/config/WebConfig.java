package bg.softuni.mobilelele.config;

import bg.softuni.mobilelele.web.interceptor.StatsInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class WebConfig implements WebMvcConfigurer {

    private final StatsInterceptor statsInterceptor;

    public WebConfig(StatsInterceptor statsInterceptor) {
        this.statsInterceptor = statsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.statsInterceptor);
    }
}
