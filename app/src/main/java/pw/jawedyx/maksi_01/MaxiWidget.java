package pw.jawedyx.maksi_01;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;


public class MaxiWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int i : appWidgetIds) {
            updateWidget(context, appWidgetManager, i);
        }
    }

    private void updateWidget(Context context, AppWidgetManager appWidgetManager, int widgetID) {

        RemoteViews widgetView = new RemoteViews(context.getPackageName(),  R.layout.widget);

        Intent configIntent = new Intent(context, MainActivity.class);
        configIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_CONFIGURE);
        configIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent = PendingIntent.getActivity(context, widgetID,  configIntent, 0);
        widgetView.setOnClickPendingIntent(R.id.ucard_logo, pIntent);

        appWidgetManager.updateAppWidget(widgetID, widgetView);
    }


}
