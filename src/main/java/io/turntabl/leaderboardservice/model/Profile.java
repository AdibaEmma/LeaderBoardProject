package io.turntabl.leaderboardservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "PROFILES")
@Getter
@Setter
@Accessors(chain = true)
public class Profile {

    @Id
    private String id;

    private String username;

    private String name;

    private String clan;

    private Integer honour;

    private Integer overallRank;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LanguageLevel> languageLevels;

    public Profile setLanguageLevels(List<LanguageLevel> languageLevels) {
        for (LanguageLevel languageLevel: languageLevels) {
            languageLevel.setProfile(this);
        }
        this.languageLevels = languageLevels;
        return this;
    }
}
