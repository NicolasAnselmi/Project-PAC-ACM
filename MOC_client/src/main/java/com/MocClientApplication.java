package com;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.macchine.Fresa;
import com.macchine.ThreadMacchina;
import com.macchine.Tornio;

@SpringBootApplication
public class MocClientApplication {

	static float pg = 0.9f;
	static float pf = 0.9f;
	static float nTorni = 2;
	static float nFrese = 3;
	static int quant = 30;

	public static void main(String[] args) {
		SpringApplication.run(MocClientApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	CommandLineRunner run(RestTemplate restTemplate) {
		return args -> {
			List<Runnable> macchine = new ArrayList<Runnable>();

			for (int i = 0; i < nTorni; i++)
				macchine.add(new ThreadMacchina(quant, new Tornio(pg, pf, restTemplate)));

			for (int i = 0; i < nFrese; i++)
				macchine.add(new ThreadMacchina(quant, new Fresa(pg, pf, restTemplate)));
			
			for (Runnable runnable : macchine)
				runnable.run();
		};
	}

}
