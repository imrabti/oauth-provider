package org.nuvola.oauth.client.unauthorized;

import javax.inject.Inject;

import org.nuvola.oauth.client.NameTokens;
import org.nuvola.oauth.client.unauthorized.UnauthorizedPresenter.MyProxy;
import org.nuvola.oauth.client.unauthorized.UnauthorizedPresenter.MyView;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class UnauthorizedPresenter extends Presenter<MyView, MyProxy> {
    @ProxyStandard
    @NameToken(NameTokens.UNAUTHORIZED)
    interface MyProxy extends ProxyPlace<UnauthorizedPresenter> {
    }

    interface MyView extends View {
    }

    @Inject
    UnauthorizedPresenter(EventBus eventBus,
                          MyView view,
                          MyProxy proxy) {
        super(eventBus, view, proxy, RevealType.Root);
    }
}
