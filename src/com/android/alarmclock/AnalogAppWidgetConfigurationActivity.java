/*
 * Copyright (C) 2025 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.alarmclock;

import static android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_ID;
import static android.appwidget.AppWidgetManager.INVALID_APPWIDGET_ID;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.deskclock.R;

public class AnalogAppWidgetConfigurationActivity extends AppCompatActivity {
    private int mAppWidgetId = INVALID_APPWIDGET_ID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setResult(RESULT_CANCELED);
        setContentView(R.layout.analog_widget_configuration);

        findViewById(R.id.option_scallop).setOnClickListener(v -> onStyleClicked(WidgetUtils.STYLE_SCALLOP));
        findViewById(R.id.option_numeral).setOnClickListener(v -> onStyleClicked(WidgetUtils.STYLE_NUMERAL));
        findViewById(R.id.option_day).setOnClickListener(v -> onStyleClicked(WidgetUtils.STYLE_DAY));
        findViewById(R.id.option_clover).setOnClickListener(v -> onStyleClicked(WidgetUtils.STYLE_CLOVER));

        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                mAppWidgetId = extras.getInt(EXTRA_APPWIDGET_ID, INVALID_APPWIDGET_ID);
            }
        }
    }

    private void onStyleClicked(int style) {
        WidgetUtils.saveAnalogWidgetStyle(this, mAppWidgetId, style);
        AppWidgetManager wm = AppWidgetManager.getInstance(this);
        AnalogAppWidgetProvider.updateAppWidget(this, wm, mAppWidgetId);

        Intent result = new Intent();
        result.putExtra(EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, result);
        finish();
    }
}
