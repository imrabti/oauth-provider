package org.nuvola.oauth.provider.db;

import javax.annotation.PostConstruct;

import org.nuvola.oauth.provider.business.Account;
import org.nuvola.oauth.provider.business.Application;
import org.nuvola.oauth.provider.business.Authority;
import org.nuvola.oauth.provider.business.OAuthClientDetails;
import org.nuvola.oauth.provider.business.User;
import org.nuvola.oauth.provider.repository.AccountRepository;
import org.nuvola.oauth.provider.repository.ApplicationRepository;
import org.nuvola.oauth.provider.repository.AuthorityRepository;
import org.nuvola.oauth.provider.repository.OAuthClientDetailsRepository;
import org.nuvola.oauth.provider.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInit {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private OAuthClientDetailsRepository oAuthClientDetailsRepository;

    @PostConstruct
    private void initData() {
        populateApplications();
        populateClientDetails();
        populateUsers();
        populateApplicationAuthorities();
        populateUsersAccountApplications();
    }

    private void populateApplications() {
        if (applicationRepository.findByClientId("myapp") == null) {
            Application myApp = new Application();
            myApp.setClientId("myapp");
            myApp.setName("My Application");
            myApp.setUrl("http://localhost:9999/");
            applicationRepository.save(myApp);
        }

        if (applicationRepository.findByClientId("portalapp") == null) {
            Application portalApp = new Application();
            portalApp.setClientId("portalapp");
            portalApp.setName("My Portal");
            portalApp.setUrl("http://localhost:7777/");
            applicationRepository.save(portalApp);
        }
    }

    private void populateClientDetails() {
        if (oAuthClientDetailsRepository.findOne("myapp") == null) {
            OAuthClientDetails myapp = new OAuthClientDetails();
            myapp.setClientId("myapp");
            myapp.setClientSecret("KDV3FT2wCMnmzwzH");
            myapp.setScope("openid");
            myapp.setAuthorizedGrantTypes("authorization_code,refresh_token,password");
            myapp.setAdditionalInformation("{}");
            myapp.setAutoapprove("openid");
            oAuthClientDetailsRepository.save(myapp);
        }

        if (oAuthClientDetailsRepository.findOne("portalapp") == null) {
            OAuthClientDetails portalapp = new OAuthClientDetails();
            portalapp.setClientId("portalapp");
            portalapp.setClientSecret("0eazudRWsUwWL9UL");
            portalapp.setScope("openid");
            portalapp.setAuthorizedGrantTypes("authorization_code,refresh_token,password");
            portalapp.setAdditionalInformation("{}");
            portalapp.setAutoapprove("openid");
            oAuthClientDetailsRepository.save(portalapp);

        }
    }

    private void populateUsers() {
        if (userRepository.findByUserName("root") == null) {
            ShaPasswordEncoder passwordEncode = new ShaPasswordEncoder();

            User root = new User();
            root.setEmail("imrabti@gmail.com");
            root.setFirstName("Idriss");
            root.setLastName("Mrabti");
            root.setUserName("root");
            root.setPassword(passwordEncode.encodePassword("test123", null));

            userRepository.save(root);
        }
    }

    private void populateApplicationAuthorities() {
        Application portalApp = applicationRepository.findByClientId("portalapp");

        if (authorityRepository.findByValue("ROLE_USER_PORTAL") == null) {
            Authority authorityUserPortalApp = new Authority();
            authorityUserPortalApp.setValue("ROLE_USER_PORTAL");
            authorityUserPortalApp.setApplication(portalApp);
            authorityRepository.save(authorityUserPortalApp);
        }

        if (authorityRepository.findByValue("ROLE_ADMIN_PORTAL") == null) {
            Authority authorityAdminPortalApp = new Authority();
            authorityAdminPortalApp.setValue("ROLE_ADMIN_PORTAL");
            authorityAdminPortalApp.setApplication(portalApp);
            authorityRepository.save(authorityAdminPortalApp);
        }

        Application myApp = applicationRepository.findByClientId("myapp");

        if (authorityRepository.findByValue("ROLE_MANAGER_APP") == null) {
            Authority authorityManagerMyApp = new Authority();
            authorityManagerMyApp.setValue("ROLE_MANAGER_APP");
            authorityManagerMyApp.setApplication(myApp);
            authorityRepository.save(authorityManagerMyApp);
        }

        if (authorityRepository.findByValue("ROLE_ADMIN_APP") == null) {
            Authority authorityAdminMyApp = new Authority();
            authorityAdminMyApp.setValue("ROLE_ADMIN_APP");
            authorityAdminMyApp.setApplication(myApp);
            authorityRepository.save(authorityAdminMyApp);
        }

        if (authorityRepository.findByValue("ROLE_USER_APP") == null) {
            Authority authorityUserMyApp = new Authority();
            authorityUserMyApp.setValue("ROLE_USER_APP");
            authorityUserMyApp.setApplication(myApp);
            authorityRepository.save(authorityUserMyApp);
        }
    }

    void populateUsersAccountApplications() {
        User root = userRepository.findByUserName("root");
        Application portalApp = applicationRepository.findByClientId("portalapp");
        Application myApp = applicationRepository.findByClientId("myapp");

        if (accountRepository.findByUser(root).isEmpty()) {
            Account imrabtiMyAppAccount = new Account();
            imrabtiMyAppAccount.setUser(root);
            imrabtiMyAppAccount.setApplication(myApp);
            imrabtiMyAppAccount.setAuthority(authorityRepository.findByValue("ROLE_MANAGER_APP"));
            accountRepository.save(imrabtiMyAppAccount);

            Account imrabtiPortalAccount = new Account();
            imrabtiPortalAccount.setUser(root);
            imrabtiPortalAccount.setApplication(portalApp);
            imrabtiPortalAccount.setAuthority(authorityRepository.findByValue("ROLE_ADMIN_PORTAL"));
            accountRepository.save(imrabtiPortalAccount);
        }
    }
}
