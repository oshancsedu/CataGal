package sifat.catagal;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;
import de.greenrobot.event.EventBus;
import github.chenupt.dragtoplayout.AttachUtil;
import github.chenupt.dragtoplayout.DragTopLayout;
import sifat.Adapter.ProductListAdapter;

/**
 * Created by sifat on 11/16/2015.
 */
public class MemoGenActivity extends ActionBarActivity {

    DragTopLayout dragTopLayout;
    private RecyclerView recyclerView;
    private StickyHeaderDecoration decor;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_gen);
        init();
    }

    private void init() {
        dragTopLayout = (DragTopLayout) findViewById(R.id.drag_layout);

        recyclerView = (RecyclerView) findViewById(R.id.rvProductList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setAdapterAndDecor(recyclerView);

        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        //recyclerView.setOnScrollListener(this);


        // attach top listener
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                EventBus.getDefault().post(AttachUtil.isRecyclerViewAttach(recyclerView));
            }
        });

    }

    protected void setAdapterAndDecor(RecyclerView list) {
        final ProductListAdapter adapter = new ProductListAdapter(this);
        decor = new StickyHeaderDecoration(adapter);

        list.setAdapter(adapter);
        list.addItemDecoration(decor, 0);
    }

    // Handle scroll event from fragments
    public void onEvent(Boolean b) {
        dragTopLayout.setTouchMode(b);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

}
