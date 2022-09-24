
package srdt.co.in.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {     
	
	@Autowired
	UnifConfig unifConfig;
	
	 @Bean
	 public OpenAPI customOpenAPI() {
         return new OpenAPI()
        		   .addServersItem(new Server().url(unifConfig.getBaseUrl()))
	               .components(new Components())
	               .info(new Info().title("Unif API Docs").description("Unif API Docs"));
	}
}
