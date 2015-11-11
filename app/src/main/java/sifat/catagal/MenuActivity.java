package sifat.catagal;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import sifat.Adapter.NavAdapter;

public class MenuActivity extends ActionBarActivity {

    private GridView menuOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menuOptions = (GridView) findViewById(R.id.gvMenu);
        menuOptions.setAdapter(new NavAdapter(this));
    }
}
