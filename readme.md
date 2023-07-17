Spring Boot
----------
Errors Faced
------------
1. Java Versions, gradle and dependencies should sync
2. If you are using annotation processor like lombok, MapStruct always declare annotation processor dependencies along with lombok, MapStruct dependencies
   ```
       implementation 'org.springframework.boot:spring-boot-starter-web:2.1.4.RELEASE'
       implementation 'org.springframework.boot:spring-boot-configuration-processor:2.1.14.RELEASE'
       compileOnly group: 'org.projectlombok', name: 'lombok', version: '1.18.6'
       annotationProcessor 'org.projectlombok:lombok:1.18.6'
   ```
What is SpringBoot?
-------------------
1. Spring Boot is a Java Framework that allows you to easily create stand-alone,
   production-grade Spring based Java Applications. It is often used in Microservice
   Architecture because of simplicity that it allows.
2. Applications created with Spring Boot can be executed with simple java –jar
   command and also allows traditional war deployment.
3. Spring Boot supports following embedded containers:
   1. Tomcat
   2. Jetty
   3. Undertow
4. By embedding the web server, Spring Boot simplifies the deployment process. You don't need to separately install and configure an external web server to run your application.
5. Simplicity of deployment and execution has many advantages, for example, it allows
   for Dev/Prod parity (https://12factor.net/) which increases product quality.
   1. The goal is to ensure that the development environment closely resembles the production environment in terms of configuration, dependencies, and infrastructure.
6. Spring Boot provides number of features that can be used to fulfill non-functional
   requirements for the project (externalized configuration, security, metrics, health
   checks).
7. Spring Boot provides many modules under common umbrella:
   1. Spring Boot DevTools – live-reload to speed-up development
   2. Spring Boot Actuator – monitoring and management of application
   3. Spring Boot Starters – dependency set for technologies to minimize setup time
   4. Spring Boot Autoconfiguration – configuration templates for technologies to
      minimize setup time
8. On top of it, you can use all Spring Framework technologies, like:
   1. Spring Web – Spring MVC Framework
   2. Template Engines – server side rendering engines for web pages
   3. Spring Security – authentication and authorization framework
   4. Spring Data MongoDB – NoSQL database client
   5. ... and many more
9. advantages of using Spring Boot
   1. Maximizes productivity
      1. by reducing the setup times through starter dependencies
   2. Simplifies deployment, by allowing to create executable jar, and also supports
      traditional deployment on top of application server
      1. by providing embedded servers
   3. Provides automatic configuration which reduces boilerplate configuration, and
      allows easy customization when defaults are not sufficient
      1. Like autoconfiguration of embedded databases, template engines, mvc view resolving 
   4. Allows for Dev/Prod Parity
   5. Provides set of managed dependencies
   6. Provides Maven Plugins
   7. Provides non-functional features common for projects - externalized
      configuration, security, metrics, health checks
   8. Integrates with Micro Service Architecture Tools for building Highly Available and
      Fault Tolerant Applications – Eureka, Ribbon, OpenFeign
   9. Integrates with systemd and init.d, which allows to easily run applications as
      Linux Service
   10. Uses IoC/DI from Spring Framework

SpringBoot is opinionated
------------------------
1. Spring Boot is “opinionated” framework because it comes with general idea on how application should be organized, provides default configurations and modules setups for technology related aspect of application. (embedded databases, mvc view resolvers, template rendering engines, ...)
2. In comparison with Spring Framework, Spring Boot provides starters and autoconfigurations which
   intelligently fits default configuration based on defined dependencies.
3. Main advantage on how Spring Boot approaches “opinionated” style, is that you can always override
   default configuration if it does not fit your use case.
4. “Opinionated” has following advantages:
   1. Simplifies application setup
   2. Maximizes productivity, by allowing you to focus on business code instead of setup of technology related code
   3. Allows you to write configuration only in case when defaults are not a good fit for your case
   4. Allows easy integration with technology modules (Embedded Databases, Containers ...)
   5. Minimizes amount of setup code
5. disadvantage of “opinionated” framework
   1. The main disadvantage of “opinionated” framework is that, if your application does not fall into most use cases supported by framework, you will have to override most of default setup, configurations and project organization, which might harm your productivity.

Things affected on Spring Boot start up
---------------------------------------
1. Spring Boot uses autoconfiguration to detect dependencies on the class path, based
   on detected dependencies, spring beans are configured to allow integration with
   technologies, like JPA, Data Sources, Embedded Databases, Template Rendering
   engines etc
2. Spring Boot searches for META-INF/spring.factories on classpath that should
   contain entry org.springframework.boot.autoconfigure.EnableAutoConfiguration that lists all
   autoconfiguration classes provided by the autoconfiguration module
3. Autoconfiguration class is using @ConditionalOn... annotations to specify under
   which conditions, certain Autoconfiguration should be applied.
4. Spring Boot provides starter modules, which are empty jars with set of dependencies
   specified with correct dependencies versions to allow easy start with the library.
5. Starter module may provide only set of dependencies, or set of dependencies with
   autoconfiguration code.
6. Spring Boot supports following Conditional Annotations for AutoConfiguration classes:
    ConditionalOnBean – presence of Spring Bean
    ConditionalOnMissingBean – absence of Spring Bean
    ConditionalOnClass – presence of class on classpath
    ConditionalOnMissingClass – absence of class on classpath
    ConditionalOnCloudPlatform – if specified cloud platform is active – for
   example Cloud Foundry
    ConditionalOnExpression – if SpEL expression is true
    ConditionalOnJava – presence of Java in specified version
    ConditionalOnJndi – if JNDI location exists
    ConditionalOnWebApplication – if a web application that uses
   WebApplicationContext or StandardServletEnvironment
    ConditionalOnNotWebApplication – application that is not a web application
    ConditionalOnProperty – presence of spring property
    ConditionalOnResource – presence of resource
    ConditionalOnSingleCandidate – only one candidate for the bean found.
7. Example [Link1](CustomAutoConfig) [Link2](CustomConfig)
   ```java
   //disable required classes where auto config is not required
   @Configuration
   @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
   public class ApplicationConfiguration {
   }
   
   //add CustomConfig Project As a Dependency
   //implementation group: 'org.example', name: 'CustomConfig',version: '1.0-SNAPSHOT'
   
   //Config for CustomAutoConfig Project
   @Configuration
   @EnableTransactionManagement
   @ConditionalOnClass(name = "org.hsqldb.Database")
   public class DataSourceAutoConfiguration {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .build();
    }
   }
   @Configuration
   @ConditionalOnClass(name = "org.hsqldb.Database")
   @EnableJpaRepositories(basePackages = {"com.spring.professional.exam.tutorial.module04.question04"})
   public class JpaAutoConfiguration {
   @Bean
   @Autowired
   public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
   LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
   em.setDataSource(dataSource);
   em.setPackagesToScan("com.spring.professional.exam.tutorial.module04.question04");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        em.setJpaVendorAdapter(vendorAdapter);

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }
   }
   
   //resources/META-INF/spring.factories - declare the path to custom auto config classes
   # Auto Configure
   org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
   com.spring.professional.exam.tutorial.module04.question04.my.autoconfiguration.DataSourceAutoConfiguration,\
    com.spring.professional.exam.tutorial.module04.question04.my.autoconfiguration.JpaAutoConfiguration
    ```

Spring Boot starter POM
-------------------------
1. Spring Starter POM is a maven module that represents empty jar with set of
   dependencies required to work with specified technology. Spring Starter may also
   provide autoconfiguration to create beans required to integrate project with
   technologies that you intend to use.
2. Spring Starters are useful, because they simplify project setup by assuring that
   all dependencies in correct versions are set. If Starter provides autoconfiguration
   as well, it integrates technology with Spring Framework.
3. This allows you to focus on business code instead of having to spend time on
   identifying which dependency set is required and which versions are correct.
   Autoconfiguration allows you to use technology within Spring Framework without
   having to integrate technology with it manually
4. Example
   1. First, let's look at developing the REST service; we can use libraries like Spring MVC, Tomcat and Jackson – a lot of dependencies for a single application.Spring Boot starters can help to reduce the number of manually added dependencies just by adding one dependency.
   ```
     <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
     </dependency>

     ```

properties and YML files - Externalizing Configuration
------------------------
1. Spring Boot allows you to externalize configuration for the application by using
   properties stored in properties files that can be in format:
   1. YAML
   2. Java Properties File
2. YAML is a superset of JSON and it is convenience for specifying hierarchical data.
   Spring Boot supports YAML properties with usage of SnakeYAML library, which is
   included by default by spring-boot-starter.
   ![img.png](img.png)
3. Example
   ```java
      //@EnableConfigurationProperties  -- set config class to map properties
      @SpringBootApplication
      @EnableConfigurationProperties(ApplicationConfiguration.class)
      public class SpringBootConsoleApplication implements CommandLineRunner {

      @Autowired
      private ApplicationConfiguration applicationConfiguration;

       }
   
   //@ConfigurationProperties -- set prefix and define mapping class
   @ConfigurationProperties(prefix = "app")
   @Getter
   @Setter
   @ToString
   public class ApplicationConfiguration {
    private String name;
    private String description;
    private List<String> servers;
    private Map<String, EnvironmentConfiguration> environments;
   }
   
   @Getter
   @Setter
   @ToString
   public class EnvironmentConfiguration {
      private String name;
      private String url;
   }
   ```
   ```
   //application.properties
   app.name=spring-boot-app
   app.description=Example Spring Boot Application
   app.servers[0]=server1
   app.servers[1]=server2
   app.servers[2]=server3
   app.environments['dev'].name=Development Environment
   app.environments['dev'].url=https://dev.example.com
   app.environments['prod'].name=Prod Environment
   app.environments['prod'].url=https://prod.example.com
   
   //or application.yaml
   app:
   name: spring-boot-app
   description: Example Spring Boot Application

   servers:
   - server1
   - server2
   - server3

   environments:
   dev:
   name: Development Environment
   url: https://dev.example.com

    prod:
      name: Prod Environment
      url: https://prod.example.com

   ```


