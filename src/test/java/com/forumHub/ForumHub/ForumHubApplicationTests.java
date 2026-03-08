package com.forumHub.ForumHub;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


class ForumHubApplicationTests {

	@Test
	void gerarHash() {
		var encoder = new BCryptPasswordEncoder();
		var hash = encoder.encode("12345!@#$%");
		System.out.println(hash);
	}
}
