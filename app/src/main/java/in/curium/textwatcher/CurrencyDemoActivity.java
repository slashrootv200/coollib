package in.curium.textwatcher;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import in.curium.coollib.CurrencyWithTwoDecimalInputFilter;

public class CurrencyDemoActivity extends AppCompatActivity {
  private TextInputLayout til;
  private TextInputEditText tiet;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_currency_demo);
    til = (TextInputLayout) findViewById(R.id.til);
    tiet = (TextInputEditText) findViewById(R.id.tiet);
    tiet.setFilters(new InputFilter[] { new CurrencyWithTwoDecimalInputFilter() });
  }
}
