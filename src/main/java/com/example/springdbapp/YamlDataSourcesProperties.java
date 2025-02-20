package com.example.springdbapp;

import com.example.springdbapp.model.DataSourceConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "data-sources")
public class YamlDataSourcesProperties {
    private List<DataSourceConfig> dataSources;

    public List<DataSourceConfig> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<DataSourceConfig> dataSources) {
        this.dataSources = dataSources;
    }
}