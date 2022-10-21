package com.example.bbbmeetings.net;

import com.example.bbbmeetings.data.dto.Create;
import com.example.bbbmeetings.data.dto.Join;
import com.example.bbbmeetings.data.dto.Status;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface bbbApi {
    @GET("api")
    Call<Status> checkStatus();

    @GET("create")
    Call<Create> createMeeting(
            @Query("allowStartStopRecording") boolean allowStartStopRecording,
            @Query("attendeePW") String attendeePW,
            @Query("autoStartRecording") boolean autoStartRecording,
            @Query("meetingID") String meetingId,
            @Query("moderatorPW") String moderatorPW,
            @Query("name") String name,
            @Query("record") boolean record,
            @Query("voiceBridge") int voiceBridge,
            @Query("welcome") String welcomeS
    );
    @GET("join")
    Call<Join> joinMeeting(
            @Query("fullName") String fullName,
            @Query("meetingID") String meetingID,
            @Query("password") String password,
            @Query("redirect") boolean redirect
    );
}
