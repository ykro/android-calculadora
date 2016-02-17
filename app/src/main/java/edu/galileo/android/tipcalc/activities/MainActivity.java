package edu.galileo.android.tipcalc.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.galileo.android.tipcalc.R;
import edu.galileo.android.tipcalc.TipCalcApp;
import edu.galileo.android.tipcalc.adapter.OnItemClickListener;
import edu.galileo.android.tipcalc.model.TipRecord;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.txtTip)
    TextView txtTip;
    @Bind(R.id.container)
    RelativeLayout container;

    public final static String TIMESTAMP_KEY = "timestamp";
    public final static String BILL_TOTAL_KEY = "total";
    public final static String TIP_KEY = "tip";
    private final static String DEFAULT_TIP_PERCENTAGE = "10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
        String strInputTotal = inputBill.getText().toString();
        if (!strInputTotal.isEmpty()) {
            try {

            } catch (ClassCastException e) {

            }
        }
    }

    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease(){
    }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease(){

    }

    private void handleTipChange(){
        String strInputPercentage = inputPercentage.getText().toString();
        if (!strInputPercentage.isEmpty()) {
            try {

            } catch (ClassCastException e) {

            }
        } else {
            txtTip.setText(DEFAULT_TIP_PERCENTAGE);
        }
    }

    @OnClick(R.id.btnClear)
    public void handleClickClear(){
        showMessage(R.string.main_notice_clear);
    }

    @Override
    public void onItemClick(TipRecord element) {
        Intent i = new Intent(this, TipDetailActivity.class);
        i.putExtra(TIMESTAMP_KEY, "");
        i.putExtra(BILL_TOTAL_KEY, element.getBill());
        i.putExtra(TIP_KEY, element.getTip());
        startActivity(i);
    }

    private void about(){
        TipCalcApp app = (TipCalcApp)getApplication();
        String strUrl = app.getAboutUrl();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(strUrl));
        startActivity(i);
    }

    private void showMessage(String msg) {
        Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).show();
    }

    private void showMessage(int stringRes) {
        Snackbar.make(container, stringRes, Snackbar.LENGTH_SHORT).show();
    }
}
