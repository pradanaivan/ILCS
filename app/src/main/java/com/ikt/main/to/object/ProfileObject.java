package com.ikt.main.to.object;

/**
 * Created by Arifin on 3/20/16.
 */
public class ProfileObject {

    private long id;
    private String userId;
    private String username;
    private String name;
    private String orgId;
    private String orgCode;
    private String orgName;
    private String session;
    private String previlige;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getPrevilige() {
        return previlige;
    }

    public void setPrevilige(String previlige) {
        this.previlige = previlige;
    }
}
