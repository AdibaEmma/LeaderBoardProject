package io.turntabl.leaderboardservice.repository;

import io.turntabl.leaderboardservice.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Profile p WHERE p.username = ?1")
    Boolean existsByUsername(String username);
}
