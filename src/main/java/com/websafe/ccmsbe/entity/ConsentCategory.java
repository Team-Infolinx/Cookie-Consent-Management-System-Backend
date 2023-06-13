package com.websafe.ccmsbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "ConsentCategory")
@Table(name = "consentCategory")
public class ConsentCategory {
    @EmbeddedId
    private MultiValueId id;

    @ManyToOne
    @JoinColumn(
            name = "website_id",
            nullable = false,
            referencedColumnName = "websiteId",
            foreignKey = @ForeignKey(name = "website_id")
    )
    private Website website;

}
