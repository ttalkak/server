package kr.kro.ttalkak.deployment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
<<<<<<< Updated upstream:deployment-service/src/main/java/kr/kro/ttalkak/deployment/DeploymentServiceApplication.java
=======
@EnableJpaAuditing
@EnableFeignClients
>>>>>>> Stashed changes:deployment-service/src/main/java/com/ttalkak/deployment/DeploymentServiceApplication.java
public class DeploymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeploymentServiceApplication.class, args);
    }

}
