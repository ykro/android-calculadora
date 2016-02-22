package edu.galileo.android.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.galileo.android.tipcalc.R;
import edu.galileo.android.tipcalc.TipCalcApp;
import edu.galileo.android.tipcalc.fragments.TipHistoryListFragment;
import edu.galileo.android.tipcalc.fragments.TipHistoryListFragmentListener;
import edu.galileo.android.tipcalc.model.TipRecord;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.txtTip)
    TextView txtTip;
    @Bind(R.id.container)
    RelativeLayout container;

    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_PERCENTAGE = 10;
    private TipHistoryListFragmentListener fragmentListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true);
        fragmentListener = (TipHistoryListFragmentListener)fragment;
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
            about();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnSubmit)
    public void handleClickSubmit(){
        hideKeyboard();
        String strInputTotal = inputBill.getText().toString().trim();
        if (!strInputTotal.isEmpty()) {
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();
            TipRecord tipRecord = new TipRecord();
            tipRecord.setBill(total);
            tipRecord.setTipPercentage(tipPercentage);
            tipRecord.setTimestamp(new Date());

            fragmentListener.addToList(tipRecord);

            String strTip = String.format(getString(R.string.global_message_tip),
                                          tipRecord.getTip());

            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }

    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease(){
        hideKeyboard();
        handleTipChange(TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease(){
        hideKeyboard();
        handleTipChange(-TIP_STEP_CHANGE);
    }

    private void handleTipChange(int change){
        int tipPercentage = getTipPercentage();
        tipPercentage += change;
        inputPercentage.setText(String.valueOf(tipPercentage));
    }

    private int getTipPercentage(){
        int tipPercentage;
        String strInputPercentage = inputPercentage.getText().toString().trim();
        if (!strInputPercentage.isEmpty()) {
            tipPercentage = Integer.parseInt(strInputPercentage);
        } else {
            tipPercentage = DEFAULT_TIP_PERCENTAGE;
            inputPercentage.setText(String.valueOf(tipPercentage));
        }

        return tipPercentage;
    }

    @OnClick(R.id.btnClear)
    public void handleClickClear(){
        hideKeyboard();
        fragmentListener.clearList();
        Snackbar.make(container, R.string.main_notice_clear, Snackbar.LENGTH_SHORT).show();
    }

    private void about(){
        TipCalcApp app = (TipCalcApp)getApplication();
        String strUrl = app.getAboutUrl();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(strUrl));
        startActivity(i);
    }

    private void hideKeyboard(){
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
