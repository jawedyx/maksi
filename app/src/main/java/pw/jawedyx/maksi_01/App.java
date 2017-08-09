package pw.jawedyx.maksi_01;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;



public class App extends Application{

    private static SharedPreferences settings;
    public static final String PREFERENCES = "maxi_0.1_settings";
    public static final String PREFERENCES_CARDNUMBER = "card_number";

    @Override
    public void onCreate() {
        super.onCreate();

        settings = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getSettings() {
        return settings;
    }


}
