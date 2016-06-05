package com.pragu.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pragu.service.model.UserDetails;
import com.pragu.service.model.UserRepos;

@RestController
public class MainController {

	@RequestMapping(value = "/getDetails")
	UserDetails getDetails(@RequestParam(value = "username") String username) {

		RestTemplate restTemplate = new RestTemplate();
		UserDetails userDetails = restTemplate.getForObject(
				"https://api.github.com/users/" + username, UserDetails.class);

		return userDetails;
	}


	@RequestMapping(value = "/getRepos")
	UserRepos[] getRepos(@RequestParam(value = "username") String username) {

		RestTemplate restTemplate = new RestTemplate();
		UserRepos[] userRepos = restTemplate.getForObject(
				"https://api.github.com/users/" + username + "/repos",
				UserRepos[].class);

		return userRepos;
	}

}
