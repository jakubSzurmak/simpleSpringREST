package pl.runnerData;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;


@SpringBootApplication
public class SimpleGatewayApp {

	public static void main(String[] args) {
		SpringApplication.run(SimpleGatewayApp.class, args);
	}

	@Bean
	public RouteLocator routeLocator(
			RouteLocatorBuilder builder,
			@Value("${lab.shoe.url}") String shoeUrl,
			@Value("${lab.runner.url}") String runnerUrl,
			@Value("${lab.gate.host}") String host
	){
		return builder
				.routes()
				.route("shoes", route -> route
						.host(host)
						.and()
						.path(
								"/api/shoes/{id}",
								"/api/shoes",
								"/api/runners/{id}/shoes"
						)
						.uri(shoeUrl)
				)
				.route("runners", route -> route
						.host(host)
						.and()
						.path(
								"/api/runners",
								"/api/runners/{id}"
						)
						.uri(runnerUrl)
				)
				.build();
	}

}
