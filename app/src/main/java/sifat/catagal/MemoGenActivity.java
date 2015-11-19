package sifat.catagal;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.devspark.robototextview.widget.RobotoTextView;
import com.fourmob.datetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;
import de.greenrobot.event.EventBus;
import github.chenupt.dragtoplayout.AttachUtil;
import github.chenupt.dragtoplayout.DragTopLayout;
import sifat.Adapter.MemoAdapter;
import sifat.Fragments.ConfirmationMemoFragment;
import sifat.Provider.MemoBasicInfoProvider;

import static sifat.Utilities.CommonUtilities.CONFIRM_FRAG_TAG;
/**
 * Created by sifat on 11/16/2015.
 */
public class MemoGenActivity extends ActionBarActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    public static final String DATEPICKER_TAG = "datepicker";
    DragTopLayout dragTopLayout;
    private RecyclerView recyclerView;
    private StickyHeaderDecoration decor;
    private Toolbar toolbar;
    private TextView tvSelectDate, tvItemAdded, tvTotalCost;
    private DatePickerDialog datePickerDialog;
    private Spinner spDistributor, spAreaCode, spAreaName;
    private MemoBasicInfoProvider memoBasicInfoProvider;
    private RobotoTextView tvOrderDate;
    private String orderDate, supplyDate;
    private FloatingActionButton fabSendMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_gen);
        init();
    }

    private void init() {

        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        fabSendMemo = (FloatingActionButton) findViewById(R.id.fab);
        fabSendMemo.setOnClickListener(this);

        tvItemAdded = (TextView) findViewById(R.id.tvTotalItemOrder);
        tvTotalCost = (TextView) findViewById(R.id.tvTotalCost);

        final Calendar calendar = Calendar.getInstance();

        datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), true);

        memoBasicInfoProvider = MemoBasicInfoProvider.getProvider(this);

        spDistributor = (Spinner) findViewById(R.id.spDistributor);
        spAreaName = (Spinner) findViewById(R.id.spAreaName);
        spAreaCode = (Spinner) findViewById(R.id.spAreaCode);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_header_item, memoBasicInfoProvider.getAreaCodes());
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spAreaCode.setAdapter(spinnerArrayAdapter);

        spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_header_item, memoBasicInfoProvider.getDistributorNames());
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spDistributor.setAdapter(spinnerArrayAdapter);

        spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_header_item, memoBasicInfoProvider.getAreaNames());
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spAreaName.setAdapter(spinnerArrayAdapter);

        dragTopLayout = (DragTopLayout) findViewById(R.id.drag_layout);
        tvSelectDate = (TextView) findViewById(R.id.tvSupplyDateSelector);
        tvSelectDate.setOnClickListener(this);

        tvOrderDate = (RobotoTextView) findViewById(R.id.tvOrderDate);
        tvOrderDate.setText("Order Date:\n" + getOrderingDate());

        recyclerView = (RecyclerView) findViewById(R.id.rvMemoList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setAdapterAndDecor(recyclerView);

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
        } else if (v.getId() == R.id.fab) {
            showConfirmationDialog();
        }
    }

    private void showConfirmationDialog() {
        FragmentManager fragmentManager = getFragmentManager();
        ConfirmationMemoFragment memoFragment = new ConfirmationMemoFragment();

        memoFragment.show(fragmentManager, CONFIRM_FRAG_TAG);
    }


    protected void setAdapterAndDecor(RecyclerView list) {
        final MemoAdapter adapter = new MemoAdapter(this, tvTotalCost, tvItemAdded);
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
        //Toast.makeText(MemoGenActivity.this, "new date:" + year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();
        month++;
        supplyDate = year + "-" + month + "-" + day;
        tvSelectDate.setText("Supply Date: " + supplyDate);
    }

    public String getOrderingDate() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int monthInt = c.get(Calendar.MONTH);
        monthInt++;
        int day = c.get(Calendar.DAY_OF_MONTH);
        orderDate = year + "-" + monthInt + "-" + day;
        return orderDate;
    }

}
