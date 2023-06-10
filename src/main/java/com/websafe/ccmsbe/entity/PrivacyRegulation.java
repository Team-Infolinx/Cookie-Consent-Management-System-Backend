package com.websafe.ccmsbe.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "PrivacyRegulation")
@Table(name = "privacy_regulation")
@JsonIgnoreProperties("cookieBannerTemplates")
public class PrivacyRegulation {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "privacy_regulation_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long regulationId;

    private String regulationName;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "privacy_regulation_geolocation_types")
    private List<String> geolocationTypes = new ArrayList<>();

    @ManyToMany(
            mappedBy = "privacyRegulations"
    )
    @JsonBackReference
    @Column(name= "websites")
    private List<Website> websites = new ArrayList<>();


    @OneToMany(
            mappedBy = "privacyRegulation"
    )
    private List<CookieBannerTemplate> cookieBannerTemplates = new ArrayList<>();

}
