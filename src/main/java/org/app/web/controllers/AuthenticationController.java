package org.app.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.openid.OpenIDAuthenticationProvider;
import org.springframework.security.openid.OpenIDAuthenticationToken;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class AuthenticationController  extends OpenIDAuthenticationProvider
{
    private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Override
    protected Authentication createSuccessfulAuthentication(UserDetails userDetails, OpenIDAuthenticationToken auth)
    {
        logger.info("User found with OpenID identity: {}", auth.getIdentityUrl());
        logger.info("User has OpenID attributes: {}", auth.getAttributes());
        try
        {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            logger.debug(ow.writeValueAsString(auth), ow.writeValueAsString(userDetails));
        }
        catch (Exception e) {}

        return super.createSuccessfulAuthentication(userDetails, auth);
    }
}