package com.example.bbbmeetings.data.dto;

import org.simpleframework.xml.Element;

public class Status {
    @Element(name = "returncode")
    private String returncode;
    @Element(name = "version")
    private float version;
    @Element(name = "apiVersion")
    private float apiv;
    @Element(name = "bbbVersion", required = false)
    private String bbbVersion;
    public Status(){}
}
