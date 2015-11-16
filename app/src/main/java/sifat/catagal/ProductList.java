package sifat.catagal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;
import sifat.Adapter.ProductListAdapter;

/**
 * Created by sifat on 11/12/2015.
 */
public class ProductList extends ActionBarActivity {

    private RecyclerView recyclerView;
    private StickyHeaderDecoration decor;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        init();
    }

    private void init() {
        recyclerView= (RecyclerView) findViewById(R.id.rvProductList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setAdapterAndDecor(recyclerView);

        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
    }

    protected void setAdapterAndDecor(RecyclerView list) {
        final ProductListAdapter adapter = new ProductListAdapter(this);
        decor = new StickyHeaderDecoration(adapter);

        list.setAdapter(adapter);
        list.addItemDecoration(decor, 0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Intent intent = new Intent(this, MemoGenActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
