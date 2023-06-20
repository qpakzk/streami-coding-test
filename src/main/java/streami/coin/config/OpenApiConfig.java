package streami.coin.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("스트리미 코딩 테스트")
                .version("v1")
                .description("스트리미 코딩 테스트 API 구현");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}