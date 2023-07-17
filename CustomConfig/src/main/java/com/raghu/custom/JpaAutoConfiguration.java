package com.raghu.custom;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ConditionalOnClass(name = "org.hsqldb.Database")
@EnableJpaRepositories(basePackages = {"com.raghu.custom"})
public class JpaAutoConfiguration {
}
