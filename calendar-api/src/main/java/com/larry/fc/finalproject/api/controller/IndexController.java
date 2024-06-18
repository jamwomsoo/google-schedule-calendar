package com.larry.fc.finalproject.api.controller;

import com.larry.fc.finalproject.core.domain.RequestReplyType;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import static com.larry.fc.finalproject.api.service.LoginService.LOGIN_SESSION_KEY;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model , HttpSession httpSession, @RequestParam(required = false) String redirect){
        model.addAttribute("isSignIn", httpSession.getAttribute(LOGIN_SESSION_KEY) != null);
        return "index";
    }

    @GetMapping("events/engagements/{engagementId}")
    public String updateEngagement(
            @PathVariable Long engagementId,
            @RequestParam RequestReplyType type,
            Model model,
            HttpSession httpSession
            ){
        model.addAttribute("isSignIn",
                httpSession.getAttribute(LOGIN_SESSION_KEY) != null);
        model.addAttribute("updateType", type.name());
        model.addAttribute("engagementId", engagementId);
        model.addAttribute("path", "/events/engagements/" + engagementId + "?type=" + type.name());
        return "update-event";
    }
}
