package com.websafe.ccmsbe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Time;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Consent")
@Table(name = "consent")
public class Consent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consentId;
    private Date createdDate;
    private Time createdAt;
    private String isGiven;

    @ManyToMany
    @JoinTable(
            name = "allowed_cookie_categories",
            joinColumns = @JoinColumn(name = "consent_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CookieCategory> allowedCookieCategories = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "rejected_cookie_categories",
            joinColumns = @JoinColumn(name = "consent_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CookieCategory> rejectedCookieCategories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(
            name = "website_id",
            referencedColumnName = "websiteId",
            foreignKey = @ForeignKey(name = "website_id_consent")
    )
    @JsonBackReference
    private Website website;

}
