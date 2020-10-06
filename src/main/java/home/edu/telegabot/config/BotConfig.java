package home.edu.telegabot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("service")
@PropertySource("application.properties")
public class BotConfig {
}