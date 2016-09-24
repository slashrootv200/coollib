package in.curium.textwatcher;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScrollAndAnimationDemoActivity extends AppCompatActivity {
  private SwipeRefreshLayout swipeRefreshLayout;
  private Handler handler;
  private ViewGroup vg;
  private TextView videoPlayer;
  private ViewGroup listContainerVg;
  private final String TAG = "swipetag";

  private final void log(String message) {
    Log.d(TAG, message);
  }

  private TextView inflateRow() {
    return (TextView) LayoutInflater.from(this).inflate(R.layout.row, null);
  }

  private void addRows(int size) {
    for (int i = 0; i < size; i++) {
      TextView tv = inflateRow();
      tv.setText(String.valueOf(i));
      LinearLayout.LayoutParams lp =
          new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
              LinearLayout.LayoutParams.WRAP_CONTENT);
      lp.setMargins(5, 5, 5, 5);
      tv.setLayoutParams(lp);
      listContainerVg.addView(tv);
    }
  }

  @Override

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    handler = new Handler();
    swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container_id);
    vg = (ViewGroup) findViewById(R.id.ll);
    listContainerVg = (ViewGroup) findViewById(R.id.list_container);
    addRows(20);
    videoPlayer = (TextView) findViewById(R.id.video_player);
    videoPlayer.postDelayed(new Runnable() {
      @Override
      public void run() {
        doPositionLogging("postDelayed");
        int bottom = videoPlayer.getBottom();
        swipeRefreshLayout.setProgressViewOffset(false, videoPlayer.getBottom(), bottom + 50);
      }
    }, 300L);
    doPositionLogging("onCreate");
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        handler.postDelayed(new Runnable() {
          @Override
          public void run() {
            if (!isFinishing() && swipeRefreshLayout != null) {
              swipeRefreshLayout.setRefreshing(false);
            }
          }
        }, 3000L);
      }
    });
  }

  private void doPositionLogging(String id) {
    log("--START--" + id + "--START--");
    log("videoPlayer.getBottom() = " + videoPlayer.getBottom());
    log("vg.getBottom() = " + vg.getBottom());
    int[] coords = { 0, 0 };
    videoPlayer.getLocationOnScreen(coords);
    int absoluteTop = coords[1];
    int absoluteBottom = coords[1] + videoPlayer.getHeight();
    log("videoPlayer top, bottom = " + absoluteTop + " " + absoluteBottom);
    vg.getLocationOnScreen(coords);
    absoluteTop = coords[1];
    absoluteBottom = coords[1] + vg.getHeight();
    log("vg top, bottom = " + absoluteTop + " " + absoluteBottom);
    log("--END--" + id + "--END--");
  }

  @Override
  protected void onStart() {
    super.onStart();
    doPositionLogging("onStart");
  }

  @Override
  protected void onResume() {
    super.onResume();
    doPositionLogging("onResume");
  }

  @Override
  protected void onDestroy() {
    handler.removeCallbacksAndMessages(null);
    super.onDestroy();
  }
}
