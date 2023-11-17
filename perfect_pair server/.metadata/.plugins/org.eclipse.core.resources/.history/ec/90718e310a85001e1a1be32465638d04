package com.olduo.last_dance.controller.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olduo.last_dance.model.dto.User;
import com.olduo.last_dance.model.service.UserService;

import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/rest/user")
@CrossOrigin("*")
public class UserRestController {
	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	
	@Autowired
    UserService uService;
	
	@PostMapping
    @ApiOperation(value = "사용자 정보를 추가한다. 성공하면 true를 리턴한다. ", response = Boolean.class)
    public Boolean insert(@RequestBody User user) {
		logger.info(user.toString());
        uService.join(user);
        return true;
    }

    @GetMapping("/isUsed")
    @ApiOperation(value = "request parameter로 전달된 id가 이미 사용중인지 반환한다.", response = Boolean.class)
    public Boolean isUsedId(String userId) {
        return uService.isUsedId(userId);
    }


    @PostMapping("/login")
    @ApiOperation(value = "로그인 처리 후 성공적으로 로그인 되었다면 loginId라는 쿠키를 내려보낸다.", response = User.class)
    public User login(@RequestBody User user, HttpServletResponse response) throws UnsupportedEncodingException {
        User selected = uService.login(user.getUserId(), user.getPass());
        if (selected != null) {
            Cookie cookie = new Cookie("loginId", URLEncoder.encode(selected.getUserId(), "utf-8"));
            cookie.setMaxAge(1000 * 1000);
            response.addCookie(cookie);
        }
        return selected;
    }


    @GetMapping("/info")
    @ApiOperation(value = "사용자의 정보를 반환한다.",
    	notes = "6단계에서 사용됨. 로그인 성공한 cookie 정보가 없으면 전체 null이 리턴됨", response = Map.class)
    public Map<String, Object> getInfo(HttpServletRequest request, String userId) {
  	  String idInCookie = "";
        Cookie [] cookies = request.getCookies();
        if(cookies != null) {
  		  for (Cookie cookie : cookies) {
  			try {
  			  if("loginId".equals(cookie.getName())){
  				  idInCookie = URLDecoder.decode(cookie.getValue(), "utf-8");
  				  System.out.println("value : "+URLDecoder.decode(cookie.getValue(), "utf-8"));
  			  }
  			} catch (UnsupportedEncodingException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  		  }
        }
        
        User selected = uService.selectUser(userId);

        if(!userId.equals(idInCookie)) {
  		  logger.info("different cookie value : inputValue : {}, inCookie:{}", userId, idInCookie);
  		  selected = null; // 사용자 정보 삭제.
  	  }else {
  		  logger.info("valid cookie value : inputValue : {}, inCookie:{}", userId, idInCookie);
  	  }
  	  
  	  if (selected == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("user", new User());
            return map;
        } else {
            Map<String, Object> info = new HashMap<>();
            info.put("user", selected);
            return info;
        }
    }
    
    @PostMapping("/info")
    @ApiOperation(value = "사용자의 정보를 반환한다.",
    notes = "아래 User객체에서 id, pass 두 개의 정보만 json으로 넘기면 정상동작한다.",  response = Map.class)
    public Map<String, Object> getInfo(@RequestBody User user) {
        User selected = uService.login(user.getUserId(), user.getPass());
        if (selected == null) {
            return null;
        } else {
            Map<String, Object> info = new HashMap<>();
            info.put("user", selected);
            return info;
        }
    }
}
