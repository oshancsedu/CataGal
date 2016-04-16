package sifat.catagal;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.devspark.robototextview.widget.RobotoTextView;
import com.fourmob.datetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import ca.barrenechea.widget.recyclerview.decoration.DividerDecoration;
import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;
import de.greenrobot.event.EventBus;
import github.chenupt.dragtoplayout.AttachUtil;
import github.chenupt.dragtoplayout.DragTopLayout;
import sifat.Adapter.MemoAdapter;
import sifat.Adapter.ProductListAdapter;
import sifat.Adapter.StickyTestAdapter;
import sifat.Controller.ServerCommunicator;
import sifat.Fragments.ConfirmationMemoFragment;
import sifat.Fragments.LogoutDialogFragment;
import sifat.Provider.MemoBasicInfoProvider;
import sifat.Provider.ProductInfoProvider;

import static sifat.Provider.ProviderSelector.getMyProvider;
import static sifat.Utilities.CommonUtilities.CONFIRM_FRAG_TAG;
import static sifat.Utilities.CommonUtilities.DEFULT_AREA_CODE;
import static sifat.Utilities.CommonUtilities.DEFULT_AREA_NAME;
import static sifat.Utilities.CommonUtilities.DEFULT_DISTRIBUTOR_NAME;
import static sifat.Utilities.CommonUtilities.SHAREDPREF_TAG_USERID;
import static sifat.Utilities.CommonUtilities.changeActivity;
import static sifat.Utilities.CommonUtilities.getPref;
import static sifat.Utilities.CommonUtilities.showToast;

/**
 * Created by sifat on 11/16/2015.
 */
public class TestAct extends ActionBarActivity{

    public static final String DATEPICKER_TAG = "datepicker";
    private static ArrayList<String> areaCodes = new ArrayList<>();
    private static ArrayList<String> areaNames = new ArrayList<>();
    private static ArrayList<String> distributorNames = new ArrayList<>();
    //private DragTopLayout dragTopLayout;
    private RecyclerView recyclerView;
    private StickyHeaderDecoration decor;
    private Toolbar toolbar;
    private TextView tvSelectDate, tvItemAdded, tvTotalCost, tvAreaName, tvDistributorName;
    private DatePickerDialog datePickerDialog;
    private Spinner spAreaCode;
    private MemoBasicInfoProvider memoBasicInfoProvider;
    private RobotoTextView tvOrderDate, tvUserID;
    private String orderDate, supplyDate;
    private FloatingActionButton fabSendMemo;
    private ProductInfoProvider provider;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    //private MemoAdapter adapter;
    private ProductListAdapter adapter;
    private DragTopLayout dragTopLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_memo);
        initViews();
    }

    private void initViews() {
        dragTopLayout = (DragTopLayout) findViewById(R.id.drag_layout);
        RecyclerView mList = (RecyclerView) findViewById(R.id.rvMemoList);
        // init recycler view
        /*final DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.default_header_color)
                .build();*/

        mList.setHasFixedSize(true);
        mList.setLayoutManager(new LinearLayoutManager(this));
        //mList.addItemDecoration(divider);
        setAdapterAndDecor(mList);



        // attach top listener
        mList.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
        adapter = new ProductListAdapter(this);
        decor = new StickyHeaderDecoration(adapter);
        //setHasOptionsMenu(true);

        list.setAdapter(adapter);
        list.addItemDecoration(decor, 0);
        //list.addOnItemTouchListener(this);
    }

    public void onEvent(Boolean b) {
        dragTopLayout.setTouchMode(b);
    }
}