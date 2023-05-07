package com.websafe.ccmsbe.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToMany
    @JoinTable(
            name = "website_privacy_regulation",
            joinColumns = @JoinColumn(name = "website_id", foreignKey = @ForeignKey(name = "fk_website_id_p")),
            inverseJoinColumns = @JoinColumn(name = "privacy_regulation_id", foreignKey = @ForeignKey(name = "fk_privacy_regulation_id_w"))
    )
    private List<PrivacyRegulation> privacyRegulations = new ArrayList<>();

    @OneToOne(
            mappedBy = "website",
            cascade = CascadeType.REMOVE
    )
    private CookieBanner cookieBanner;
}
