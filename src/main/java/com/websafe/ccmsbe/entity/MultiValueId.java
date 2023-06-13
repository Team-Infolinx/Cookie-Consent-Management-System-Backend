package com.websafe.ccmsbe.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class MultiValueId implements Serializable {
    private Long consent_id;
    private Long category_id;
}