package com.eazybytes.audit;

import org.springframework.stereotype.Component;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

@Component("auditorAwareImpl")
public class AuditorAwareImpl implements AuditorAware<String> {

	public AuditorAwareImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
	}

}
