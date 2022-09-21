package com.example.bbbmeetings.data.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "response")
public class Create {
    @Element(name = "returncode")
    private String returncode;
    @Element(name = "meetingID")
    private String meetingId;
    @Element(name = "internalMeetingID")
    private String internalMeetingID;
    @Element(name = "parentMeetingID")
    private String parentMeetingID;
    @Element(name = "attendeePW")
    private String attendeePW;
    @Element(name = "moderatorPW")
    private String moderatorPW;
    @Element(name = "createTime")
    private String createTime;
    @Element(name = "voiceBridge")
    private Integer voiceBridge;
    @Element(name = "dialNumber")
    private String dialNumber;
    @Element(name = "createDate")
    private String createDate;
    @Element(name = "hasUserJoined")
    private Boolean hasUserJoined;
    @Element(name = "duration")
    private Integer duration;
    @Element(name = "hasBeenForciblyEnded")
    private Boolean hasBeenForciblyEnded;
    @Element(name = "messageKey", required = false)
    private String messageKey;
    @Element(name = "message", required = false)
    private String message;

    public Create(){}
}
