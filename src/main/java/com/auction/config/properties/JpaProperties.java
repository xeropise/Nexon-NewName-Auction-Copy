package com.auction.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.orm.jpa.vendor.Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "auction.jpa")
@Data
public class JpaProperties {
    public Map<String, String> properties = new HashMap<>();

    public List<String> mappingResources = new ArrayList<>();

    public String databasePlatform = "";

    public Database database = Database.MYSQL;

    public Boolean generateDdl = false;

    public Boolean showSql = false;

    public Boolean openInView = false;
}
