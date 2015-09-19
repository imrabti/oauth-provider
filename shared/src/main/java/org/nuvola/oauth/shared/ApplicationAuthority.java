package org.nuvola.oauth.shared;

public class ApplicationAuthority {
    private String clientId;
    private String authority;

    public ApplicationAuthority() {
    }

    public ApplicationAuthority(String clientId,
                                String authority) {
        this.clientId = clientId;
        this.authority = authority;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
