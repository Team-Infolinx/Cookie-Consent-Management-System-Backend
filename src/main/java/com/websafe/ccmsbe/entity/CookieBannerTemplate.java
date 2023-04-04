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
    private String templateRegulationType;
    private String templateContent;
}
