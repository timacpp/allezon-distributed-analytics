package com.allezon.controller;

import com.allezon.domain.UserTag;

import com.allezon.service.UserTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_tags")
public class UserTagsController {

	@Autowired
	private UserTagsService userTagsService;

	@PostMapping
	public ResponseEntity<Void> addUserTag(@RequestBody UserTag userTag) {
		userTagsService.addUserTag(userTag);
		return ResponseEntity.noContent().build();
	}
}
