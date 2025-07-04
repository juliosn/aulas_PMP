package br.com.fatec.frete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FreteApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreteApplication.class, args);
	}

}
