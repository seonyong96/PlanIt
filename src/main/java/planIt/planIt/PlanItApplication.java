// jdbc:h2:tcp://localhost/~/test
// java -jar h2-2.3.232.jar

package planIt.planIt;

import org.hibernate.annotations.processing.Exclude;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class PlanItApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanItApplication.class, args);
	}

}

