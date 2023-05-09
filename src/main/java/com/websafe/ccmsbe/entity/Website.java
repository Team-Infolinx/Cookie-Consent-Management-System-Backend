package com.websafe.ccmsbe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Long userId;

    @OneToMany(
            mappedBy = "website",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    @JsonBackReference
    private List<Cookie> cookies = new ArrayList<>();

    @OneToMany(
            mappedBy = "website",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    @JsonBackReference("website-category")
    private List<CookieCategory> cookieCategories = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "website_privacy_regulation",
            joinColumns = @JoinColumn(name = "website_id", foreignKey = @ForeignKey(name = "fk_website_id_p")),
            inverseJoinColumns = @JoinColumn(name = "privacy_regulation_id", foreignKey = @ForeignKey(name = "fk_privacy_regulation_id_w"))
    )
    @JsonBackReference("website-privacy-regulation")
    private List<PrivacyRegulation> privacyRegulations = new ArrayList<>();

    @OneToOne(
            mappedBy = "website",
            cascade = CascadeType.REMOVE
    )
    @JsonBackReference("website-cookie-banner")
    private CookieBanner cookieBanner;

    @OneToMany(targetEntity = Consent.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "websiteId",referencedColumnName = "websiteId")
    private List<Consent> consent;

//    Related to adding new cookie categories to the website.
    public void addCookieCategoryToWebsite(CookieCategory cookieCategory){
        cookieCategory.setWebsite(this);
        cookieCategories.add(cookieCategory);
    }
//    Related to adding cookies to website.
    public void addCookieToWebsite(Cookie cookie) {
        cookie.setWebsite(this);
        cookies.add(cookie);
    }

}
