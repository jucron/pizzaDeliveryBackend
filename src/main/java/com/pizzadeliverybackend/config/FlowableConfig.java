package com.pizzadeliverybackend.config;

import org.flowable.common.engine.impl.history.HistoryLevel;
import org.flowable.engine.ProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowableConfig {
    @Bean
    void configHistoryLevel() {
//        ProcessEngine processEngine = ProcessEngineConfiguration
//                .createStandaloneInMemProcessEngineConfiguration()
//                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
//                .setJdbcDriver("org.h2.Driver")
//                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
////                .setEnableProcessDefinitionHistoryLevel(true)
//                .setHistory(HistoryLevel.AUDIT.getKey())
//                .buildProcessEngine();
        ProcessEngineConfiguration
                .createStandaloneInMemProcessEngineConfiguration()
                .setHistoryLevel(HistoryLevel.AUDIT);
    }
}
