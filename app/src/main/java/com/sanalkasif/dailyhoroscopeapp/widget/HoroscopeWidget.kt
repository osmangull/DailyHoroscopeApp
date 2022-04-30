package com.sanalkasif.dailyhoroscopeapp.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import com.sanalkasif.dailyhoroscopeapp.R

/**
 * Implementation of App Widget functionality.
 */
class HoroscopeWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val GET: SharedPreferences
    GET = context.getSharedPreferences(context.packageName, AppCompatActivity.MODE_PRIVATE)
    var horoscopeName = GET.getString("dailyHoroscope", "")
    // Construct the RemoteViews object
    horoscopeName = horoscopeName.toString().substring(0,200)+" Devamı için tıklayın...";
    val views = RemoteViews(context.packageName, R.layout.horoscope_widget)
    views.setTextViewText(R.id.yorum, horoscopeName.toString())

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}