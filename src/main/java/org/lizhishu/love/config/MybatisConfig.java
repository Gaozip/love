package org.lizhishu.love.config;

import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sleo
 * @date 2019/3/27
 */
@Configuration
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new MybatisPlusCustomizers();
    }

    static class MybatisPlusCustomizers implements ConfigurationCustomizer {
        @Override
        public void customize(org.apache.ibatis.session.Configuration configuration) {
            configuration.setJdbcTypeForNull(JdbcType.NULL);
        }
    }
}
