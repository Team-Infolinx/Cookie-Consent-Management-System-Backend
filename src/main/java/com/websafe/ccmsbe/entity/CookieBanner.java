package com.websafe.ccmsbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Repeatable;

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
}
