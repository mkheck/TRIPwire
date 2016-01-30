package org.thehecklers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by markheckler on 1/29/2016.
 */
@RestController
public class Location {
    @RequestMapping("/remote")
    public String getRemoteWsUrl() {
        String baseEnvLinkURL;

        HttpServletRequest currentRequest =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        if ("localhost".equals(currentRequest.getLocalName())) {
            // Standard WebSocket connection
            baseEnvLinkURL = "ws://" + currentRequest.getLocalName() + ":" + currentRequest.getLocalPort();
        } else {
            // PWS on AWS opens port 4443 only; PEZ runs secure WebSocket on port 443
            baseEnvLinkURL = "wss://" + currentRequest.getServerName() + ":" +
                    (currentRequest.getServerName().indexOf("pez") > -1 ? "443" : "4443");

            System.out.println("baseEnvLinkURL=|" + baseEnvLinkURL + "|");
        }

        return baseEnvLinkURL;
    }

    @RequestMapping("/remoteTest")
    public String getRemoteParamsTest() {
        String baseEnvLinkURL;

        HttpServletRequest currentRequest =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        return "Remote user: " + currentRequest.getRemoteUser() +
                "<br>Servlet path: " + currentRequest.getServletPath() +
                "<br>Servlet ctxt: " + currentRequest.getServletContext() +
                "<br>Local name: " + currentRequest.getLocalName() +
                "<br>Local port: " + currentRequest.getLocalPort() +
                "<br>Auth type: " + currentRequest.getAuthType() +
                "<br>Context path: " + currentRequest.getContextPath() +
                "<br>Path info: " + currentRequest.getPathInfo() +
                "<br>Path xlatd: " + currentRequest.getPathTranslated() +
                "<br>Req URI: " + currentRequest.getRequestURI() +
                "<br>Local addr: " + currentRequest.getLocalAddr() +
                "<br>Remote addr: " + currentRequest.getRemoteAddr() +
                "<br>Remote host: " + currentRequest.getRemoteHost() +
                "<br>Remote port: " + currentRequest.getRemotePort() +
                "<br>Remote user: " + currentRequest.getRemoteUser() +
                "<br>Server name: " + currentRequest.getServerName() +
                "<br>Server port: " + currentRequest.getServerPort() +
                "<br>Servlet ctxt: " + currentRequest.getServletContext();
    }
}
