package org.nuvola.oauth;

import org.nuvola.oauth.business.Account;
import org.nuvola.oauth.business.Application;
import org.nuvola.oauth.business.Authority;
import org.nuvola.oauth.business.User;
import org.nuvola.oauth.repository.AccountRepository;
import org.nuvola.oauth.repository.ApplicationRepository;
import org.nuvola.oauth.repository.AuthorityRepository;
import org.nuvola.oauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AuthServerApplication {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @PostConstruct
    private void initData() {
        ShaPasswordEncoder passwordEncode = new ShaPasswordEncoder();

        // Preparing Applications
        Application myApp = new Application();
        myApp.setClientId("myapp");
        myApp.setName("My Application");
        myApp.setUrl("http://localhost:9999/");

        Application vacancyApp = new Application();
        vacancyApp.setClientId("vacapp");
        vacancyApp.setName("Vacancy Management");
        vacancyApp.setUrl("http://localhost:7070/");

        Application portalApp = new Application();
        portalApp.setClientId("portalapp");
        portalApp.setName("My Portal");
        portalApp.setUrl("http://localhost/");

        applicationRepository.save(myApp);
        applicationRepository.save(vacancyApp);
        applicationRepository.save(portalApp);

        // Preparing users
        User imrabti = new User();
        imrabti.setEmail("imrabti@gmail.com");
        imrabti.setFirstName("Idriss");
        imrabti.setLastName("Mrabti");
        imrabti.setUserName("imrabti");
        imrabti.setPassword(passwordEncode.encodePassword("test123", null));

        User mkecha = new User();
        mkecha.setEmail("mkecha@gmail.com");
        mkecha.setFirstName("Mohammed");
        mkecha.setLastName("Kecha");
        mkecha.setUserName("mkecha");
        mkecha.setPassword(passwordEncode.encodePassword("test456", null));

        User bamine = new User();
        bamine.setEmail("bamine@gmail.com");
        bamine.setFirstName("Amine");
        bamine.setLastName("Bouaggad");
        bamine.setUserName("bamine");
        bamine.setPassword(passwordEncode.encodePassword("test789", null));

        userRepository.save(imrabti);
        userRepository.save(mkecha);
        userRepository.save(bamine);

        // Preparing application authorities
        Authority authorityManagerMyApp = new Authority();
        authorityManagerMyApp.setValue("ROLE_MANAGER_APP");
        authorityManagerMyApp.setApplication(myApp);
        authorityRepository.save(authorityManagerMyApp);

        Authority authorityAdminMyApp = new Authority();
        authorityAdminMyApp.setValue("ROLE_ADMIN_APP");
        authorityAdminMyApp.setApplication(myApp);
        authorityRepository.save(authorityAdminMyApp);

        Authority authorityUserMyApp = new Authority();
        authorityUserMyApp.setValue("ROLE_USER_APP");
        authorityUserMyApp.setApplication(myApp);
        authorityRepository.save(authorityUserMyApp);

        Authority authorityUserVacApp = new Authority();
        authorityUserVacApp.setValue("ROLE_USER_VAC_APP");
        authorityUserVacApp.setApplication(vacancyApp);
        authorityRepository.save(authorityUserVacApp);

        Authority authorityAdminVacApp = new Authority();
        authorityAdminVacApp.setValue("ROLE_ADMIN_VAC_APP");
        authorityAdminVacApp.setApplication(vacancyApp);
        authorityRepository.save(authorityAdminVacApp);

        Authority authorityUserPortalApp = new Authority();
        authorityUserPortalApp.setValue("ROLE_USER_PORTAL");
        authorityUserPortalApp.setApplication(portalApp);
        authorityRepository.save(authorityUserPortalApp);

        Authority authorityAdminPortalApp = new Authority();
        authorityAdminPortalApp.setValue("ROLE_ADMIN_PORTAL");
        authorityAdminPortalApp.setApplication(portalApp);
        authorityRepository.save(authorityAdminPortalApp);

        // Preparing users account in the applications
        Account imrabtiMyAppAccount = new Account();
        imrabtiMyAppAccount.setUser(imrabti);
        imrabtiMyAppAccount.setApplication(myApp);
        imrabtiMyAppAccount.setAuthority(authorityManagerMyApp);
        accountRepository.save(imrabtiMyAppAccount);

        Account imrabtiVacAppAccount = new Account();
        imrabtiVacAppAccount.setUser(imrabti);
        imrabtiVacAppAccount.setApplication(vacancyApp);
        imrabtiVacAppAccount.setAuthority(authorityAdminVacApp);
        accountRepository.save(imrabtiVacAppAccount);

        Account imrabtiPortalAccount = new Account();
        imrabtiPortalAccount.setUser(imrabti);
        imrabtiPortalAccount.setApplication(portalApp);
        imrabtiPortalAccount.setAuthority(authorityUserPortalApp);
        accountRepository.save(imrabtiPortalAccount);

        Account mkechaMyAppAccount = new Account();
        mkechaMyAppAccount.setUser(mkecha);
        mkechaMyAppAccount.setApplication(myApp);
        mkechaMyAppAccount.setAuthority(authorityUserMyApp);
        accountRepository.save(mkechaMyAppAccount);

        Account mkechaPortalAccount = new Account();
        mkechaPortalAccount.setUser(mkecha);
        mkechaPortalAccount.setApplication(portalApp);
        mkechaPortalAccount.setAuthority(authorityUserPortalApp);
        accountRepository.save(mkechaPortalAccount);

        Account baminePortalAccount = new Account();
        baminePortalAccount.setUser(bamine);
        baminePortalAccount.setApplication(portalApp);
        baminePortalAccount.setAuthority(authorityUserPortalApp);
        accountRepository.save(baminePortalAccount);
    }
}
