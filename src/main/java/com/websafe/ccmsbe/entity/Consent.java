package com.websafe.ccmsbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
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
    private Boolean consent;

    @ManyToMany
    @JoinTable(
            name = "consent_allowed_categories",
            joinColumns = @JoinColumn(name = "consent_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CookieCategory> allowedCookieCategories = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "consent_rejected_categories",
            joinColumns = @JoinColumn(name = "consent_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CookieCategory> rejectedCookieCategories = new ArrayList<>();

}
