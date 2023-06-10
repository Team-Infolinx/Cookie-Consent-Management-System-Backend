package com.websafe.ccmsbe.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "template_id_generator")
    @SequenceGenerator(name = "template_id_generator",sequenceName = "template_id_seq",initialValue = 6000,allocationSize = 1)
    private Long templateId;
    private String templateName;
    private String templateRegulation;
    private String templateDefault;
    private String templatePrivacyPolicyLink;
    @Column(length = 1000)
    private String templateContent;

    @ManyToOne
    @JoinColumn(
            name = "cookie_banner_id",
            referencedColumnName = "bannerId",
            foreignKey = @ForeignKey(name = "fk_cookie_banner_id_cbt")
    )
    @JsonBackReference
    private CookieBanner cookieBanner;

    @ManyToOne
    @JoinColumn(
            name = "privacy_regulation_id",
            referencedColumnName = "regulationId",
            foreignKey = @ForeignKey(name = "fk_privacy_regulation_cbt")
    )
    @JsonIgnoreProperties("privacyRegualtions")
    private PrivacyRegulation privacyRegulation;
}
