package org.nuvola.oauth.provider.spring;

import java.security.Principal;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "userInfo";

    @RequestMapping(value = "/user")
    public Principal user(Principal principal) {
        return principal;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }
}
