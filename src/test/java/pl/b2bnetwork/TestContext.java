package pl.b2bnetwork;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
@ContextConfiguration
@ComponentScan(basePackages = "pl.b2bnetwork")
@EnableJpaRepositories("pl.b2bnetwork.repository")
public class TestContext {
}
