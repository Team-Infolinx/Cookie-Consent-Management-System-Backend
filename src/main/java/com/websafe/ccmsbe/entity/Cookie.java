package com.websafe.ccmsbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Cookie")
@Table(name = "cookie")
public class Cookie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cookieId;
    private String CookieName;
    private String domain;
    private String path;
    private Date expireDate;

}
