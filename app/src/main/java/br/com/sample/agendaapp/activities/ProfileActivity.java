package br.com.sample.agendaapp.activities;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.sample.agendaapp.R;
import br.com.sample.agendaapp.core.ControllerProfileActivity;

public class ProfileActivity extends AppCompatActivity {

    private EditText et_name;
    private String profileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        et_name = (EditText) findViewById(R.id.et_name);

    }

    public void back(View view){
        profileName = et_name.getText().toString();
        ControllerProfileActivity.getInstance().setValue(profileName);
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
