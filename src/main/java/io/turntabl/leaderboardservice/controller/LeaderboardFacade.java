package io.turntabl.leaderboardservice.controller;

import io.turntabl.leaderboardservice.client.response.UserDto;
import io.turntabl.leaderboardservice.controller.response.ProfileDto;
import io.turntabl.leaderboardservice.converter.ProfileToProfileDtoConverter;
import io.turntabl.leaderboardservice.converter.UserDtoToProfileConverter;
import io.turntabl.leaderboardservice.model.Profile;
import io.turntabl.leaderboardservice.service.LeaderboardRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Component
public class LeaderboardFacade {

    private final LeaderboardRepositoryService leaderboardRepositoryService;
    private final ProfileToProfileDtoConverter profileToProfileDtoConverter;
    private final UserDtoToProfileConverter userDtoToProfileConverter;

    public List<ProfileDto> getLeaderboard() {
        return leaderboardRepositoryService.getProfiles().stream()
                .map(profileToProfileDtoConverter::convert)
                .collect(toList());
    }

    public ProfileDto addProfileToLeaderboard(UserDto user) {
        Profile profile = userDtoToProfileConverter.convert(user);
        return profileToProfileDtoConverter.convert(leaderboardRepositoryService.addNewProfile(profile));
    }
}
