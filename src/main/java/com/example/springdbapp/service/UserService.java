package com.example.springdbapp.service;

import com.example.springdbapp.YamlDataSourcesProperties;
import com.example.springdbapp.dto.UserDto;
import com.example.springdbapp.mapper.UserModelMapper;
import com.example.springdbapp.model.DataSourceConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final YamlDataSourcesProperties properties;
    private final UserModelMapper userModelMapper;

    public UserService(@Qualifier("yamlDataSourcesProperties") YamlDataSourcesProperties properties,
                       UserModelMapper userModelMapper) {
        this.properties = properties;
        this.userModelMapper = userModelMapper;
    }


    public List<UserDto> findAll() {
        List<UserDto> aggregatedUsers = new ArrayList<>();

        for (DataSourceConfig config : properties.getDataSources()) {
            DataSource dataSource = createDataSource(config);
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String query = buildQuery(config);
            List<Map<String, Object>> users = jdbcTemplate.queryForList(query);

            users.forEach(user -> aggregatedUsers.add(userModelMapper.userToDto(user)));
        }
        return aggregatedUsers;
    }

    private DataSource createDataSource(DataSourceConfig config) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(config.getUrl());
        dataSource.setDriverClassName(getDriverClass(config.getStrategy()));
        dataSource.setUsername(config.getUser());
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

    private String getDriverClass(String strategy) {
        return switch (strategy) {
            case "mysql" -> "com.mysql.cj.jdbc.Driver";
            case "postgres" -> "org.postgresql.Driver";
            case "mssql" -> "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            case "oracle" -> "oracle.jdbc.OracleDriver";
            case "db2" -> "com.ibm.db2.jcc.DB2Driver";
            case "h2" -> "org.h2.Driver";
            case "derby" -> "org.apache.derby.jdbc.EmbeddedDriver";
            case "hsqldb" -> "org.hsqldb.jdbc.JDBCDriver";
            case "mariadb" -> "org.mariadb.jdbc.Driver";
            default -> throw new IllegalArgumentException("Unknown strategy: " + strategy);
        };
    }


}
