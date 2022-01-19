package io.turntabl.leaderboardservice.controller;

import io.turntabl.leaderboardservice.client.response.UserDto;
import io.turntabl.leaderboardservice.controller.response.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/leaderboard")
public class LeaderboardController {

    private final LeaderboardFacade leaderboardFacade;

    @GetMapping
    public List<ProfileDto> getLeaderboard() {
        return leaderboardFacade.getLeaderboard();
    }


    @GetMapping("/{language}")
    public List<ProfileDto> getProfileByLanguage(@PathVariable("language") String language){
        return leaderboardFacade.getProfileByLanguage(language);

    @PostMapping("/add")
    public ProfileDto addUserToLeaderboard(@RequestBody UserDto user) {
        return leaderboardFacade.addProfileToLeaderboard(user);

    }
}
