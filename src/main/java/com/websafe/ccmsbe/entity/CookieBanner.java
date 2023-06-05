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
@Entity(name = "CookieBanner")
@Table(name = "cookie_banner")
public class CookieBanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bannerId;
    private String bannerPosition;
    private String bannerColor;
    private String bannerAlignment;

    @OneToOne
    @JoinColumn(
            name = "website_id",
            referencedColumnName = "websiteId",
            foreignKey = @ForeignKey(name = "fk_website_id_cb")
    )
    private Website website;

    @OneToMany(
            mappedBy = "cookieBanner"
    )
    private List<CookieBannerTemplate> cookieBannerTemplates = new ArrayList<>();
}
