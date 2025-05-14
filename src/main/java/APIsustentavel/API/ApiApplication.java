package APIsustentavel.API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API de ações Sustentáveis",
				version = "1.0.0",
				description = "API para gerenciamento de ações sustentáveis promovidas por pessoas físicas, ONGs e empresas"
		)
)
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
