package sifat.catagal;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.iangclifton.android.floatlabel.FloatLabel;

import sifat.Controller.ServerCommunicator;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    private FloatLabel etUserId, etPassword;
    private Button btLogin;
    private ServerCommunicator serverCommunicator;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        serverCommunicator = new ServerCommunicator(this);
        btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);
        etUserId = (FloatLabel) findViewById(R.id.etUserId);
        etPassword = (FloatLabel) findViewById(R.id.etPassword);
    }

    @Override
    public void onClick(View v) {
        String userId, password;
        userId = etUserId.getEditText().getText().toString();
        password = etPassword.getEditText().getText().toString();
        serverCommunicator.login(userId, password);
    }
}