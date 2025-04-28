package br.com.fillms.projeto_java_ia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProjetoJavaIaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoJavaIaApplication.class, args);
	}

}
