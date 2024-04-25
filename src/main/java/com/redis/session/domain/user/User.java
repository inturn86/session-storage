package com.redis.session.domain.user;

import lombok.Builder;

import java.io.Serializable;


@Builder
public record User(
		String userId,
		String email,
		String password,
		String role
) implements Serializable {

}
