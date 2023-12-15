package com.d121211048.mybook.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.d121211048.mybook.ui.MybookApp
import com.d121211048.mybook.ui.theme.MybookTheme
//import com.google.android.material.color.DynamicColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //DynamicColors.applyToActivitiesIfAvailable(this);
        setContent {
            MybookTheme {
                MybookApp()
            }
        }
    }
}