package net.sppan.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.sppan.blog.dao.SessionRepository;
import net.sppan.blog.entity.Session;
import net.sppan.blog.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {
	
	@Resource
	private SessionRepository sessionRepository;

	@Override
	public Session findBySessionId(String sessionId) {
		return sessionRepository.findBySessionId(sessionId);
	}

}
