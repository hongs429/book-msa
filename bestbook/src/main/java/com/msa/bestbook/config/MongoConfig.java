package com.msa.bestbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class MongoConfig {

    @Bean
    public MappingMongoConverter mappingMongoConverter(
            MongoMappingContext mongoMappingContext,
            MongoDatabaseFactory mongoDatabaseFactory
    ) {
        DefaultDbRefResolver defaultDbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory);

        MappingMongoConverter converter = new MappingMongoConverter(
                defaultDbRefResolver,
                mongoMappingContext
        );

        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return converter;
    }
}
