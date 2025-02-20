package com.example.springdbapp;

import com.example.springdbapp.model.DataSourceConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "data-sources")
public class DataSourceProperties {
    private List<DataSourceConfig> sources;

}

