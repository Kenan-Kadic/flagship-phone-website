package org.launchcode.flagshipphonewebsite;

import org.launchcode.flagshipphonewebsite.controllers.AuthenticationController;
import org.launchcode.flagshipphonewebsite.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.awt.*;
import javax.swing.JOptionPane;

public class AuthenticationFilter extends HandlerInterceptorAdapter {

    @Autowired
    AuthenticationController authenticationController;

    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/css");

    private static boolean isWhitelisted(String path) {
        for (String pathRoot : whitelist) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        // Don't require sign-in for whitelisted pages
        if (isWhitelisted(request.getRequestURI())) {
            // returning true indicates that the request may proceed
            return true;
        }

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        

        // The user is logged in
              
        if (user == null) {
            response.sendRedirect("/login");
            return false;
        }
        else if ((request.getRequestURI().startsWith("/admin")) && (!user.getUsertype().equals("admin"))) {

            final Runnable SOUND = (Runnable)Toolkit.getDefaultToolkit().getDesktopProperty
                    ("win.sound.default");
            if(SOUND != null)SOUND.run();

            JOptionPane.showMessageDialog(null, "Please log in as Administrator to view this page", "Admin needed!", JOptionPane.WARNING_MESSAGE);
            response.sendRedirect(request.getContextPath());
            return false;
        }
        else {
            return true;
        }
   
    }
}