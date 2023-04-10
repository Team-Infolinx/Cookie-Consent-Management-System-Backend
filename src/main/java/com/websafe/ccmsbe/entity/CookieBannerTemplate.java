package com.websafe.ccmsbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "CookieBannerTemplate")
@Table(name = "cookie_banner_template")
public class CookieBannerTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long templateId;
    private String templateName;
    private String templateContent;

    @ManyToOne
    @JoinColumn(
            name = "cookie_banner_id",
            referencedColumnName = "bannerId",
            foreignKey = @ForeignKey(name = "fk_cookie_banner_id_cbt")
    )
    private CookieBanner cookieBanner;

    @ManyToOne
    @JoinColumn(
            name = "privacy_regulation_id",
            referencedColumnName = "regulationId",
            foreignKey = @ForeignKey(name = "fk_privacy_regulation_cbt")
    )
    private PrivacyRegulation privacyRegulation;

}
