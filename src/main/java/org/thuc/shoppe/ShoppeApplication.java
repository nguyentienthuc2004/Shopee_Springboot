package org.thuc.shoppe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShoppeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppeApplication.class, args);
    }

}
