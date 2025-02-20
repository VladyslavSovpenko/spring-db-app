package com.example.springdbapp.service;

import com.example.springdbapp.DataSourceProperties;
import com.example.springdbapp.mapper.UserModelMapper;
import com.example.springdbapp.model.DataSourceConfig;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService {
    private final UserModelMapper userModelMapper;
    private final DataSourceProperties dataSourceProperties;

    public UserService(UserModelMapper userModelMapper, DataSourceProperties dataSourceProperties) {
        this.userModelMapper = userModelMapper;
        this.dataSourceProperties = dataSourceProperties;
    }


    @Override
    public List<Map<String, Object>> findAll() {
        List<Map<String, Object>> aggregatedUsers = new ArrayList<>();

        for (DataSourceConfig config : dataSourceProperties.getSources()) {
            DataSource dataSource = createDataSource(config);
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String query = buildQuery(config);
            List<Map<String, Object>> users = jdbcTemplate.queryForList(query);
            aggregatedUsers.addAll(users);
        }

        return null;
    }

    private DataSource createDataSource(DataSourceConfig config) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getName());
        dataSource.setPassword(config.getPassword());
        return dataSource;
    }


    private String buildQuery(DataSourceConfig config) {
        Map<String, String> mapping = config.getMapping();
        return String.format("SELECT %s AS id, %s AS username, %s AS name, %s AS surname FROM %s",
                mapping.get("id"),
                mapping.get("username"),
                mapping.get("name"),
                mapping.get("surname"),
                config.getTable());
    }
}
