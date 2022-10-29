package com.example.bbbmeetings.data.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "response")
public class End {
    @Element(name = "returncode")
    private String returncode;
    @Element(name = "messageKey")
    private String messageKey;
    @Element(name = "message")
    private String message;

    public String ifSuccess(){
        return returncode;
    }
}
