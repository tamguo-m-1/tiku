package com.tamguo.web.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.tamguo.util.ExceptionSupport;
import com.tamguo.util.Result;
import com.tamguo.util.ShiroUtils;
import com.tamguo.model.SysMenuEntity;
import com.tamguo.service.ISysMenuService;

/**
 * 登录
 */
@Controller(value="adminLoginController")
public class LoginController {
	
	private final String LOGIN_PAGE = "admin/login";

	@Autowired
	private Producer producer;
	@Autowired
	private ISysMenuService sysMenuService;

	@RequestMapping("admin/captcha.jpg")
	public void captcha(HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");
		// 生成文字验证码
		String text = producer.createText();
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		// 保存到shiro session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
	}

	/**
	 * 登录
	 */
	@RequestMapping(value = "admin/login", method = RequestMethod.GET)
	public String login() throws IOException {
		return LOGIN_PAGE;
	}

	/**
	 * 登录
	 */
	@ResponseBody
	@RequestMapping(value = "admin/login", method = RequestMethod.POST)
	public Result toLogin(HttpServletRequest request, String username, String password, String captcha)
			throws IOException {
		try {
			String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
			if (!captcha.equalsIgnoreCase(kaptcha)) {
				return Result.failResult("验证码错误");
			}

			Subject subject = ShiroUtils.getSubject();
			// sha256加密
			password = new Sha256Hash(password).toHex();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
		} catch (UnknownAccountException e) {
			return ExceptionSupport.resolverResult("找不到账户", this.getClass(), e);
		} catch (IncorrectCredentialsException e) {
			return ExceptionSupport.resolverResult("账户验证失败", this.getClass(), e);
		} catch (LockedAccountException e) {
			return ExceptionSupport.resolverResult("账户验证失败", this.getClass(), e);
		} catch (AuthenticationException e) {
			return ExceptionSupport.resolverResult("账户验证失败", this.getClass(), e);
		}
		List<SysMenuEntity> userMenuList = sysMenuService.getUserMenuList(ShiroUtils.getUserId());
		request.getSession().setAttribute("userMenuList", userMenuList);
		return Result.successResult(username);
	}

	/**
	 * 退出
	 */
	@RequestMapping(value = "admin/logout", method = RequestMethod.GET)
	public String logout() {
		ShiroUtils.logout();
		return "redirect:admin/login";
	}
}
