package in.curium.textwatcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LauncherActivity extends AppCompatActivity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_launcher);
    findViewById(R.id.launch_currency_demo).setOnClickListener(this);
    findViewById(R.id.launch_scroll_shrink_animation_demo).setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    Intent intent = null;
    switch (v.getId()) {
      case R.id.launch_currency_demo:
        intent = new Intent(this, CurrencyDemoActivity.class);
        break;
      case R.id.launch_scroll_shrink_animation_demo:
        intent = new Intent(this, ScrollAndAnimationDemoActivity.class);
        break;
    }
    if (intent != null) startActivity(intent);
  }
}
