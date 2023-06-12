package com.websafe.ccmsbe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "CookieCategory")
@Table(name = "cookie_category")
public class CookieCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;
    private String categoryDescription;

    @ManyToOne
    @JoinColumn(
            name = "website_id",
            referencedColumnName = "websiteId",
            foreignKey = @ForeignKey(name = "fk_website_id_cc")
    )
    @JsonBackReference("website-cookieCategory")
    private Website website;

    @OneToMany(
            mappedBy = "cookieCategory",
            fetch = FetchType.EAGER
    )
    @JsonBackReference
    private List<Cookie> cookies = new ArrayList<>();

    @ManyToMany(mappedBy = "allowedCookieCategories")
    @JsonBackReference("allowed-consents")
    private List<Consent> allowedByConsents = new ArrayList<>();

    @ManyToMany(mappedBy = "rejectedCookieCategories")
    @JsonBackReference("rejected consents")
    private List<Consent> rejectedByConsents = new ArrayList<>();

}
