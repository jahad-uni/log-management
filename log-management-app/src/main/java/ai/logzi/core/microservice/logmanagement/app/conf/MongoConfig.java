package ai.logzi.core.microservice.logmanagement.app.conf;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories({"ai.logzi.core.microservice.logmanagement.repository"})
@Configuration
@AllArgsConstructor
public class MongoConfig extends AbstractMongoClientConfiguration {



    private Environment env;

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }


    @SneakyThrows
    @Override
    protected String getDatabaseName() {

        if (env.getProperty("spring.data.mongodb.database") == null ||
                env.getProperty("spring.data.mongodb.database").isEmpty())
            throw new Exception("spring.data.mongodb.database in application-*.yml file is empty");
        return env.getProperty("spring.data.mongodb.database");
    }

    @SneakyThrows
    @Override
    public MongoClient mongoClient() {

        if (env.getProperty("spring.data.mongodb.url") == null ||
                env.getProperty("spring.data.mongodb.url").isEmpty())
            throw new Exception("spring.data.mongodb.url in application-*.yml file is empty");
        final ConnectionString connectionString =
                new ConnectionString(env.getProperty("spring.data.mongodb.url"));
        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
}
