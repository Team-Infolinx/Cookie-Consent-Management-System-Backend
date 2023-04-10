package com.websafe.ccmsbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.catalog.Catalog;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Website")
@Table(name = "website")
public class Website {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long websiteId;
    private String configName;
    private String domain;
    private String userId;

    @OneToMany(
            mappedBy = "website",
            cascade = CascadeType.REMOVE
    )
    private List<Cookie> cookies = new ArrayList<>();

    @OneToMany(
            mappedBy = "website",
            cascade = CascadeType.REMOVE
    )
    private List<CookieCategory> cookieCategories = new ArrayList<>();
}
