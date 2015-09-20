package org.nuvola.oauth.client;

import org.nuvola.oauth.client.unauthorized.UnauthorizedModule;
import org.nuvola.oauth.client.util.CurrentUser;

import com.google.gwt.inject.client.AbstractGinModule;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;

public class SSOModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(CurrentUser.class).asEagerSingleton();
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.getUnauthorized());

        install(new UnauthorizedModule());
    }
}
