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
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "my_seq_gen1")
    @SequenceGenerator(name = "my_seq_gen1", sequenceName = "my_sequence1", initialValue = 299, allocationSize = 1)
    private Long templateId;
    private String templateName;
    private String templateRegulation;
    @Column(length = 1000)
    private String templateContent;

    @ManyToOne
    @JoinColumn(
            name = "cookie_banner_id",
            referencedColumnName = "banner_id",
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
