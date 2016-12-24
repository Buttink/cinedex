package com.stockingd.cinedex.app;

import android.support.v7.app.AppCompatActivity;

import com.stockingd.cinedex.CinedexApplication;
import com.stockingd.cinedex.CinedexComponent;

public class BaseActivity extends AppCompatActivity {

    protected CinedexApplication application() {
        return ((CinedexApplication)this.getApplicationContext());
    }

    protected CinedexComponent component() {
        return application().component();
    }

}
