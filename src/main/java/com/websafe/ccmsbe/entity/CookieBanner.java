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
@Entity(name = "CookieBanner")
@Table(name = "cookie_banner")
public class CookieBanner {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "banner_id_generator")
    @SequenceGenerator(name = "banner_id_generator", sequenceName = "banner_id_seq", initialValue = 4000, allocationSize = 1)
    @Column(name = "bannerId")
    private Long id;
    private String bannerPosition;
    private String bannerColor;
    private String bannerAlignment;
    private String bannerTextColor;

    @OneToOne
    @JoinColumn(
            name = "website_id",
            referencedColumnName = "websiteId",
            foreignKey = @ForeignKey(name = "fk_website_id_cb")
    )

    @JsonBackReference
    private Website website;

    @OneToMany(
            mappedBy = "cookieBanner",
            cascade = CascadeType.REMOVE
    )
    @JsonBackReference("banner template")
    private List<CookieBannerTemplate> cookieBannerTemplates = new ArrayList<>();

}


