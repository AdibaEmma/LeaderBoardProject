package io.turntabl.leaderboardservice.service;

import io.turntabl.leaderboardservice.model.Profile;
import io.turntabl.leaderboardservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LeaderboardRepositoryService {

    private final ProfileRepository profileRepository;

    public List<Profile> getProfiles() {
        return profileRepository.findAll();
    }

    public Profile addNewProfile(Profile profile) {
        boolean isUsernameExist = profileRepository.existsById(profile.getId());
        if(isUsernameExist) throw new IllegalArgumentException("username is already taken");

        return profileRepository.save(profile);
    }
}
