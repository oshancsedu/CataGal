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

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;
import de.greenrobot.event.EventBus;
import github.chenupt.dragtoplayout.AttachUtil;
import github.chenupt.dragtoplayout.DragTopLayout;
import sifat.Adapter.MemoAdapter;
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
public class MemoGenActivity extends ActionBarActivity implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener,
        LogoutDialogFragment.Communicator,
        ServerCommunicator.RefreshList{

    public static final String DATEPICKER_TAG = "datepicker";
    private static ArrayList<String> areaCodes = new ArrayList<>();
    private static ArrayList<String> areaNames = new ArrayList<>();
    private static ArrayList<String> distributorNames = new ArrayList<>();
    private DragTopLayout dragTopLayout;
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
    private MemoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_gen);
        init();
    }

    private void init() {

        sharedPreferences = getPref(this);

        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        fabSendMemo = (FloatingActionButton) findViewById(R.id.fab);
        fabSendMemo.setOnClickListener(this);

        provider = getMyProvider(this);

        supplyDate = "";

        tvItemAdded = (TextView) findViewById(R.id.tvTotalItemOrder);
        tvTotalCost = (TextView) findViewById(R.id.tvTotalCost);
        tvSelectDate = (TextView) findViewById(R.id.tvSupplyDateSelector);
        tvSelectDate.setOnClickListener(this);

        tvOrderDate = (RobotoTextView) findViewById(R.id.tvOrderDate);
        tvOrderDate.setText("Order Date:\n" + getOrderingDate());

        tvUserID = (RobotoTextView) findViewById(R.id.tvUserID);
        tvUserID.setText("User ID:\n" + getUserID());


        final Calendar calendar = Calendar.getInstance();

        datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), true);

        memoBasicInfoProvider = MemoBasicInfoProvider.getProvider(this);

        tvDistributorName = (TextView) findViewById(R.id.tvDistributorName);
        tvAreaName = (TextView) findViewById(R.id.tvAreaName);
        spAreaCode = (Spinner) findViewById(R.id.spAreaCode);

        areaCodes = memoBasicInfoProvider.getAreaCodes();
        areaNames = memoBasicInfoProvider.getAreaNames();
        distributorNames = memoBasicInfoProvider.getDistributorNames();

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_header_item, areaCodes);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spAreaCode.setAdapter(spinnerArrayAdapter);
        spAreaCode.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int index = spAreaCode.getSelectedItemPosition();
                tvDistributorName.setText(distributorNames.get(index));
                tvAreaName.setText(areaNames.get(index));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tvDistributorName.setText(DEFULT_DISTRIBUTOR_NAME);
                tvAreaName.setText(DEFULT_AREA_NAME);
            }
        });


        dragTopLayout = (DragTopLayout) findViewById(R.id.drag_layout);

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
            String areaName, areaCode, distributorName;
            areaName = tvAreaName.getText().toString();
            areaCode = spAreaCode.getSelectedItem().toString();
            distributorName = tvDistributorName.getText().toString();

            if (areaCode.equalsIgnoreCase(DEFULT_AREA_CODE))
                showToast(this, "Enter Area Code");
            else if (supplyDate.equalsIgnoreCase(""))
                showToast(this, "Enter Supply date");
            else if (MemoAdapter.totalItemAdded == 0)
                showToast(this, "No Item Was Added");
            else if (MemoAdapter.totalCost == 0)
                showToast(this, "No Quantity Was Selected");
            else
                showConfirmationDialog(areaName, areaCode, distributorName);

        }
    }

    private void showConfirmationDialog(String areaName, String areaCode, String distributorName) {
        FragmentManager fragmentManager = getFragmentManager();
        ConfirmationMemoFragment memoFragment = ConfirmationMemoFragment.newInstance(supplyDate, areaName, areaCode, distributorName);
        memoFragment.show(fragmentManager, CONFIRM_FRAG_TAG);
    }


    protected void setAdapterAndDecor(RecyclerView list) {
        adapter = new MemoAdapter(this, tvTotalCost, tvItemAdded,memoBasicInfoProvider);
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
        provider.setAddedProduct(MemoAdapter.addedProduct);
        provider.setTotalCost(MemoAdapter.totalCost);
        provider.setTotalItemAdded(MemoAdapter.totalItemAdded);
        provider.setProductMemoInfo(MemoAdapter.memoProductInfos);
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

    private String getUserID() {
        return sharedPreferences.getString(SHAREDPREF_TAG_USERID, "");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_memo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.update) {
            ServerCommunicator serverCommunicator = new ServerCommunicator(this);
            serverCommunicator.getMemoBasicInfo();
        }

        if (id == R.id.logout) {


            FragmentManager manager = getFragmentManager();
            LogoutDialogFragment logoutDialog = new LogoutDialogFragment();
            logoutDialog.setCancelable(false);
            logoutDialog.show(manager, "logoutFragment");

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getMessage(String message) {
        if(message.equalsIgnoreCase("Yes"))
        {
            editor = sharedPreferences.edit();
            editor.remove(SHAREDPREF_TAG_USERID);
            editor.commit();
            changeActivity(this, LoginActivity.class);
            finish();
        }
    }

    @Override
    public void refresh() {
        synchronized (adapter)
        {
            showToast(this,"Data has been sent successfully");
            adapter.notifyDataSetChanged();
            adapter.totalBiscuitCost=0.0;
            adapter.totalCandyCost=0.0;
            adapter.totalBiscuitAdded=0;
            adapter.totalCandyAdded=0;
            adapter.updateItemOrdered();
            adapter.updateTotalCost();
            adapter.clearAddedList();
        }
    }
}