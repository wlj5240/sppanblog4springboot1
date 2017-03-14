package net.sppan.blog.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import net.sppan.blog.common.Constat;
import net.sppan.blog.dao.LoginLogRepository;
import net.sppan.blog.dao.SessionRepository;
import net.sppan.blog.dao.UserRepository;
import net.sppan.blog.entity.LoginLog;
import net.sppan.blog.entity.Session;
import net.sppan.blog.entity.User;
import net.sppan.blog.exception.ServiceException;
import net.sppan.blog.service.UserService;
import net.sppan.blog.utils.CacheKit;
import net.sppan.blog.utils.MD5Kit;
import net.sppan.blog.utils.StrKit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserRepository userRepository;
	
	@Resource
	private LoginLogRepository loginLogRepository;
	
	@Resource
	private SessionRepository sessionRepository;
	
	@Resource
	private CacheKit cacheKit;
	
	@Override
	public Session login(String userName, String password, Boolean keepLogin, String ip) throws ServiceException {
		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
			throw new ServiceException("用户名或者密码不能为空");
		}
		User user = userRepository.findByUserName(userName);
		if(user == null){
			throw new ServiceException("用户不存在");
		}
		String passwordMD5 = MD5Kit.generatePasswordMD5(password, user.getSalt());
		if(passwordMD5 != null && passwordMD5.equalsIgnoreCase(user.getPassword())){
			// 如果用户勾选保持登录，暂定过期时间为 3 年，否则为 120 分钟，单位为秒
			long liveSeconds =  keepLogin!= null && keepLogin ? 3 * 365 * 24 * 60 * 60 : 120 * 60;
			// expireAt 用于设置 session 的过期时间点，需要转换成毫秒
			long expireAt = System.currentTimeMillis() + (liveSeconds * 1000);
			// 保存登录 session 到数据库
			Session session = new Session();
			String sessionId = StrKit.getRandomUUID();
			session.setSessionId(sessionId);
			session.setUser(user);
			session.setExpireAt(expireAt);
			sessionRepository.save(session);
			
			cacheKit.put(Constat.cache_loginUser, sessionId, user);
			
			//添加登录日志
			loginLogRepository.save(new LoginLog(user,new Date(),ip));
			
			return session;
		}else{
			throw new ServiceException("用户名或者密码不正确");
		}
	}

	@Override
	public Page<User> findAll(PageRequest pageRequest) {
		return userRepository.findAll(pageRequest);
	}

}