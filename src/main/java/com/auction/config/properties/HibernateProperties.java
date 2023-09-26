package com.auction.config.properties;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties.Naming;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("auction.jpa.hibernate")
@Data
public class HibernateProperties {
    public String ddlAuto = "";
    public Naming naming = new Naming();
}
