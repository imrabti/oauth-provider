package org.nuvola.oauth.client;

import javax.inject.Inject;

import org.nuvola.oauth.client.service.SessionService;
import org.nuvola.oauth.client.util.ClientId;
import org.nuvola.oauth.client.util.CurrentUser;
import org.nuvola.oauth.shared.UserProfile;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

public class SSOBootstrapper implements Bootstrapper {
    private final PlaceManager placeManager;
    private final RestDispatch dispatch;
    private final SessionService sessionService;
    private final CurrentUser currentUser;
    private final String clientId;

    @Inject
    SSOBootstrapper(PlaceManager placeManager,
                    RestDispatch dispatch,
                    SessionService sessionService,
                    CurrentUser currentUser,
                    @ClientId String clientId) {
        this.placeManager = placeManager;
        this.dispatch = dispatch;
        this.sessionService = sessionService;
        this.currentUser = currentUser;
        this.clientId = clientId;
    }

    @Override
    public void onBootstrap() {
        dispatch.execute(sessionService.currentUser(), new AsyncCallback<UserProfile>() {
            @Override
            public void onFailure(Throwable throwable) {
                placeManager.revealUnauthorizedPlace(NameTokens.getUnauthorized());
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                if (userProfile == null) {
                    placeManager.revealUnauthorizedPlace(NameTokens.getUnauthorized());
                } else {
                    currentUser.initCurrentUser(userProfile, clientId);
                    checkIfUserAllowed();
                }
            }
        });
    }

    private void checkIfUserAllowed() {
        if (currentUser.isAllowed()) {
            placeManager.revealDefaultPlace();
        } else {
            placeManager.revealUnauthorizedPlace(NameTokens.getUnauthorized());
        }
    }
}
