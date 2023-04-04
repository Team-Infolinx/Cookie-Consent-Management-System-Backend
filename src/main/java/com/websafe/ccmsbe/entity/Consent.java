package com.websafe.ccmsbe.entity;

import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Consent")
@Table(name = "consent")
public class Consent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consentId;

//    private List<Category> allowedCookieCategories = new ArrayList<>();
//
//    private List<Category> rejectedCookieCategories = new ArrayList<>();
    private Date createdDate;
    private Time createdAt;
}
