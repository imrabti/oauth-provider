package org.nuvola.oauth;

import org.nuvola.oauth.business.Application;
import org.nuvola.oauth.business.User;
import org.nuvola.oauth.repository.AccountRepository;
import org.nuvola.oauth.repository.ApplicationRepository;
import org.nuvola.oauth.repository.AuthorityRepository;
import org.nuvola.oauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

        User mkecha = new User();
        mkecha.setEmail("mkecha@gmail.com");
        mkecha.setFirstName("Mohammed");
        mkecha.setLastName("Kecha");
        mkecha.setUserName("mkecha");

        User bamine = new User();
        bamine.setEmail("bamine@gmail.com");
        bamine.setFirstName("Amine");
        bamine.setLastName("Bouaggad");
        bamine.setUserName("bamine");

        userRepository.save(imrabti);
        userRepository.save(mkecha);
        userRepository.save(bamine);

        // Preparing application authorities


        // Preparing users account in the applications
    }
}
