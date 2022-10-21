package com.example.bbbmeetings.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bbbmeetings.App;
import com.example.bbbmeetings.R;
import com.example.bbbmeetings.data.dto.Create;
import com.example.bbbmeetings.data.dto.Join;
import com.example.bbbmeetings.databinding.FragmentHomeBinding;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private static final String attendeePW = "ap";
    private static final String moderatorPW = "mp";
    private static final String welcomeS = "<br>Welcome+to+<b>%%CONFNAME%%</b>!";
    private static final String TAG = "HomeFragment: ";
    private String meetindId;

    private FragmentHomeBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View v = inflater.inflate(R.layout.fragment_home, null);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button addButton = binding.addButton;
        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        EditText nameConf = binding.nameConf;
        nameConf.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addButton.setEnabled(s.length() > 7);
            }
        });
        addButton.setOnClickListener(view -> {
            Log.d(TAG, "it works ");
        });
        addButton.setOnClickListener(v1 -> createMeetingFast(nameConf.getText().toString()));
        return root;
    }
    private void createMeetingFast(String name){

        String uuid = UUID.randomUUID().toString();
        meetindId = uuid;
//        meetindId = "random-9711938";
        App.api.createMeeting(true,attendeePW, false,meetindId,
                moderatorPW,name, false, 78980,welcomeS).enqueue(new Callback<Create>(){
            @Override
            public void onResponse(Call<Create> call, Response<Create> response) {
                if (response.code() == 200) {
                    Button addButton = binding.addButton;
                    addButton.setText("Recreate meeting");
                    Button joinButton = binding.joinButton;
                    TextView infotext = binding.infoText;
                    EditText nameConf = binding.nameConf;
                    nameConf.setText("");
                    infotext.setText("Success!");
                    joinMeeting(meetindId,"moderator");
                    joinButton.setEnabled(true);
                } else {
                    TextView infoText = binding.infoText ;
                    infoText.setText("Error:"+response.message());
                }
            }
            @Override
            public void onFailure(Call<Create> call, Throwable t) {
                Log.d(TAG, "onFailure: "+ t.getMessage());
            }
        });
    }
    private void joinMeeting(String meetindId,String role){
        String password;
        if (role == "moderator") {
            password = moderatorPW;
        }else{
            password = attendeePW;
        }
        App.api.joinMeeting("John Wick",meetindId,password, false).enqueue(new Callback<Join>() {
            @Override
            public void onResponse(Call<Join> call, Response<Join> response) {
                if (response.code() == 200) {
                    TextView infotext = binding.infoText;
                    infotext.setText("Success!");
                    Button joinButton = binding.joinButton;
                    joinButton.setOnClickListener(v2 -> {
                        Intent view = new Intent();
                        view.setAction(Intent.ACTION_VIEW);
                        view.setData(Uri.parse(response.body().getUrl()));
                        startActivity(view);
                    });
                    Log.d(TAG, "onResponse: Success!!!");
                } else {
                    TextView infoText = binding.infoText ;
                    infoText.setText("Error:"+response.message());
                }
            }

            @Override
            public void onFailure(Call<Join> call, Throwable t) {
                Log.d(TAG, "onFailure Join method:"+ t.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}