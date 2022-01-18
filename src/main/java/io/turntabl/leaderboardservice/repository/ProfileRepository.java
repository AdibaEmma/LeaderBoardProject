package io.turntabl.leaderboardservice.repository;

import io.turntabl.leaderboardservice.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
}
