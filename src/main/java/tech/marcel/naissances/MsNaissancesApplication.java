package tech.marcel.naissances;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import tech.marcel.naissances.Security.RsaKeys;

@EnableConfigurationProperties(RsaKeys.class)
@SpringBootApplication
	/*	(
		exclude = {
				SecurityAutoConfiguration.class,
				ManagementWebSecurityAutoConfiguration.class,

				// ce qui est en bas c'est ce qui doit etre en commentaire
				//DataSourceTransactionManagerAutoConfiguration.class,
				//DataSourceAutoConfiguration.class,
				//HibernateJpaAutoConfiguration.class
		}
)*/
public class  MsNaissancesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNaissancesApplication.class, args);
	}

}
