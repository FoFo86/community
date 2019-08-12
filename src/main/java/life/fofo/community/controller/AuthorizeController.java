package life.fofo.community.controller;

import life.fofo.community.dto.AccessTokenDTO;
import life.fofo.community.dto.GithubUser;
import life.fofo.community.model.User;
import life.fofo.community.provider.GithubProvider;
import life.fofo.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.clinet.id}")
    private String ClientId;

    @Value("${github.client.secret}")
    private String ClientSecret;

    @Value("${github.redirect.uri}")
    private String RedirectUri;

    @Autowired
    private UserService userService;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(ClientId);
        accessTokenDTO.setClient_secret(ClientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(RedirectUri);
        //获取Token
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        //通过token拿到User对象
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null && githubUser.getId() != null) {
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setAvatarUrl(githubUser.getAvatar_url());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        } else {
            //登陆失败，重新登陆
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
