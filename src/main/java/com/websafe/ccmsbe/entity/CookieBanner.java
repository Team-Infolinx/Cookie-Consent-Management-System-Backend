package com.websafe.ccmsbe.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "CookieBanner")
@Table(name = "cookie_banner")
public class CookieBanner {

    @Id
    @Column(name = "banner_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "my_seq_gen")
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "my_sequence", initialValue = 100, allocationSize = 1)
    private Long id;
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


