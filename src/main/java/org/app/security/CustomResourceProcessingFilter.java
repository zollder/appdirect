package org.app.security;

import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

//--------------------------------------------------------------------------------------------------
/** CustomResourceProcessingFilter implementation. */
//--------------------------------------------------------------------------------------------------
public class CustomResourceProcessingFilter extends ProtectedResourceProcessingFilter
{
    private List<RequestMatcher> requestMatchers;

    // --------------------------------------------------------------------------------------------------
    public CustomResourceProcessingFilter(List<RequestMatcher> requestMatchers)
    { this.requestMatchers = requestMatchers; }

    //--------------------------------------------------------------------------------------------------
    /** Goes through the filter chain trying to find a match.
     *  Returns true if the match is found, false otherwise. */
    //--------------------------------------------------------------------------------------------------
    @Override
    protected boolean requiresAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
    {
        boolean matches = false;
        if ((requestMatchers != null) && !requestMatchers.isEmpty())
            for (RequestMatcher requestMatcher : requestMatchers)
                if (requestMatcher.matches(request))
                {
                    matches = true;
                    break;
                }

        return matches;
    }
}