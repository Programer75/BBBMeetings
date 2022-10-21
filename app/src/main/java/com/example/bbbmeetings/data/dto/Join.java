package com.example.bbbmeetings.data.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "response")
public class Join {
    @Element(name = "returncode")
    private String returncode;
    @Element(name = "messageKey")
    private String messageKey;
    @Element(name = "message")
    private String message;
    @Element(name = "meeting_id")
    private String meeting_id;
    @Element(name="user_id")
    private String user_id;
    @Element(name = "auth_token")
    private String auth_token;
    @Element(name = "session_token")
    private String session_token;
    @Element(name = "guestStatus")
    private String guestStatus;
    @Element(name = "url")
    private String url;

    public String getUrl() {
        return url;
    }
}
