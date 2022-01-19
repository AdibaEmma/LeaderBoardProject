package io.turntabl.leaderboardservice.controller;


import io.turntabl.leaderboardservice.controller.response.LanguageLevelDto;
import io.turntabl.leaderboardservice.client.CodewarsClient;
import io.turntabl.leaderboardservice.client.response.UserDto;
import io.turntabl.leaderboardservice.controller.response.ProfileDto;
import io.turntabl.leaderboardservice.converter.ProfileToProfileDtoConverter;
import io.turntabl.leaderboardservice.model.LanguageLevel;
import io.turntabl.leaderboardservice.client.response.UserDto;
import io.turntabl.leaderboardservice.converter.UserDtoToProfileConverter;
import io.turntabl.leaderboardservice.model.Profile;
import io.turntabl.leaderboardservice.service.LeaderboardRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Component
public class LeaderboardFacade {

    private final LeaderboardRepositoryService leaderboardRepositoryService;
    private final ProfileToProfileDtoConverter profileToProfileDtoConverter;
    private final UserDtoToProfileConverter userDtoToProfileConverter;
    private final CodewarsClient codewarsClient;

    public List<ProfileDto> getLeaderboard() {
        return leaderboardRepositoryService.getProfiles().stream()
                .map(profileToProfileDtoConverter::convert)
                .collect(toList());
    }

    public List<ProfileDto> getProfileByLanguage(String language) {
        List<ProfileDto> list = new ArrayList<>();
        for (ProfileDto profileDto : getLeaderboard()) {
            for (LanguageLevelDto languageLevelDto : profileDto.getLanguages()) {
                if (languageLevelDto.getName().equals(language))
                    list.add(profileDto);
            }
        }
        return list;
    }

    public ProfileDto addProfileToLeaderboard(String username) {
        UserDto user = codewarsClient.getUser(username);
        if(user == null) throw new IllegalArgumentException("username " + username + " does not exists");
        Profile profile = userDtoToProfileConverter.convert(user);
        return profileToProfileDtoConverter.convert(leaderboardRepositoryService.addNewProfile(profile));

    }
}
