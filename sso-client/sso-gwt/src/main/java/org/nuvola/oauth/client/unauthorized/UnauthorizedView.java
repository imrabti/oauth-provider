package org.nuvola.oauth.client.unauthorized;

import javax.inject.Inject;

import org.nuvola.oauth.client.unauthorized.UnauthorizedPresenter.MyView;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtplatform.mvp.client.ViewImpl;

public class UnauthorizedView extends ViewImpl implements MyView {
    interface Binder extends UiBinder<HTMLPanel, UnauthorizedView> {
    }

    @Inject
    UnauthorizedView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
