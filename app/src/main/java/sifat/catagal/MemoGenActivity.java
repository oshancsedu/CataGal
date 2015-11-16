package sifat.catagal;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;
import de.greenrobot.event.EventBus;
import github.chenupt.dragtoplayout.AttachUtil;
import github.chenupt.dragtoplayout.DragTopLayout;
import sifat.Adapter.ProductListAdapter;

/**
 * Created by sifat on 11/16/2015.
 */
public class MemoGenActivity extends ActionBarActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    public static final String DATEPICKER_TAG = "datepicker";
    DragTopLayout dragTopLayout;
    private RecyclerView recyclerView;
    private StickyHeaderDecoration decor;
    private Toolbar toolbar;
    private TextView tvSelectDate;
    private DatePickerDialog datePickerDialog;
    private Spinner spDistributor, spAreaCode, spAreaName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_gen);
        init();
    }

    private void init() {

        spDistributor = (Spinner) findViewById(R.id.spDistributor);
        spAreaName = (Spinner) findViewById(R.id.spAreaName);
        spAreaCode = (Spinner) findViewById(R.id.spAreaCode);
        dragTopLayout = (DragTopLayout) findViewById(R.id.drag_layout);
        tvSelectDate = (TextView) findViewById(R.id.tvSupplyDateSelector);
        tvSelectDate.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.rvProductList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setAdapterAndDecor(recyclerView);

        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        final Calendar calendar = Calendar.getInstance();

        datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), true);

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


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvSupplyDateSelector) {
            datePickerDialog.setVibrate(true);
            datePickerDialog.setYearRange(2010, 2036);
            datePickerDialog.setCloseOnSingleTapDay(false);
            datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
        }
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

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        Toast.makeText(MemoGenActivity.this, "new date:" + year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();
        tvSelectDate.setText("Supply Date: " + year + "-" + month + "-" + day);
    }
}
