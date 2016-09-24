package in.curium.coollib;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyWithTwoDecimalInputFilter implements InputFilter {
  private Pattern mPattern = Pattern.compile("(\\$)(0|[1-9]+[0-9]*)?(\\.[0-9]{0,2})?");
  private final String TAG = "currencymatch";
  private final String DOLLAR = "$";

  @Override
  public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart,
      int dend) {
    String result =
        dest.subSequence(0, dstart) + source.toString() + dest.subSequence(dend, dest.length());
    log(result);
    String dollarAppendedResult;
    if (result.startsWith(DOLLAR)) {
      dollarAppendedResult = result;
    } else {
      dollarAppendedResult = DOLLAR + result;
    }
    Matcher matcher = mPattern.matcher(dollarAppendedResult);
    if (!matcher.matches()) {
      log("does not match pattern");
      return dest.subSequence(dstart, dend);
    } else {
      log("matches pattern");
      if (!result.toString().startsWith(DOLLAR) && !result.isEmpty()) {
        return dollarAppendedResult;
      } else {
        // result starts with dollar
        if (result.isEmpty()) {
          return "";
        }
      }
    }
    return null;
  }

  private void log(String message) {
    Log.d(TAG, message);
  }
}
