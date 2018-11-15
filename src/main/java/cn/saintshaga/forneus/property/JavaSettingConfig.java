package cn.saintshaga.forneus.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by huang on 18-11-5.
 */
@Configuration
@PropertySource("forneus.properties")
public class JavaSettingConfig {

    @Bean
    @ConfigurationProperties(prefix = "forneus.java")
    public JavaSetting javaSetting() {

        return new JavaSetting();
    }

}
