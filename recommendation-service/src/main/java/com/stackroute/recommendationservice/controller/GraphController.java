package com.stackroute.recommendationservice.controller;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.stackroute.recommendationservice.domain.Post;
import com.stackroute.recommendationservice.domain.User;
import com.stackroute.recommendationservice.services.QueryService;
import com.stackroute.recommendationservice.services.RecommendationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class GraphController {

	private final RecommendationService recommendationService;
	private final QueryService queryService;
	
	public GraphController(RecommendationService recommendationService, QueryService queryService) {
		this.recommendationService = recommendationService;
		this.queryService = queryService;
	}

//    @GetMapping("/graph")
//	public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
//		return recommendationService.graph(limit == null ? 100 : limit);
//	}


	@GetMapping("/recommend/{id}")
	public Collection<Post> recommend(@PathVariable(value = "id") String userId) throws ExecutionException, InterruptedException {
		return recommendationService.recommend(userId);
	}

	@GetMapping("/news/{id}")
	public Collection<User> views(@PathVariable(value = "id") String id) {
		int videoId = Integer.parseInt(id);
		Collection<User> users = queryService.graph(videoId);
		return users;
	}


	@GetMapping("/location/{name}")
	public Collection<Post> byLocation(@PathVariable(value = "name") String name) {
		return queryService.byLocation(name);
	}

	@GetMapping("/addToViewed/{id}/{name}")
	public String addToViewed(@PathVariable(value = "id") String id, @PathVariable(value = "name") String name) {
		Long videoId = Long.parseLong(id);
		queryService.addToViewed(name, videoId);
		return "qqqqqqq";
	}

	@GetMapping("/addToLiked/{id}/{name}")
	public String addToLiked(@PathVariable(value = "id") String id, @PathVariable(value = "name") String name) {
		Long videoId = Long.parseLong(id);
		queryService.addToLiked(name, videoId);
		return "qqqqqqq";
	}

}
