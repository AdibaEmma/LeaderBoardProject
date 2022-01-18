package io.turntabl.leaderboardservice.service;

import io.turntabl.leaderboardservice.model.Profile;
import io.turntabl.leaderboardservice.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LeaderboardRepositoryServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    private LeaderboardRepositoryService underTest;

    @BeforeEach
    void setUp() {
        underTest = new LeaderboardRepositoryService(profileRepository);
    }

    @Test
    void shouldGetProfiles() {
        // given
        Profile profile1 = mock(Profile.class);
        Profile profile2 = mock(Profile.class);

        when(profileRepository.findAll()).thenReturn(List.of(profile1, profile2));

        // when
        List<Profile> result = underTest.getProfiles();

        // then
        assertThat(result).containsExactly(profile1, profile2);
    }

    @Test
    void shouldAddNewProfile() {
        //given
        Profile profile = mock(Profile.class);
        profile.setId("emma");
        profile.setUsername("aweperi");
        profile.setName("Emmanuel Adiba");
        profile.setClan("turntabl");
        profile.setHonour(332);
        profile.setOverallRank(-5);

        when(profileRepository.save(profile)).thenReturn(profile);
        //when
        Profile result = underTest.addNewProfile(profile);

        //then
        String expectedUserId = "emma";
        assertThat(result.getId()).isEqualTo(expectedUserId);
    }

}
