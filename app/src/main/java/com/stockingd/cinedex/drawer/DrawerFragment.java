package com.stockingd.cinedex.drawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stockingd.cinedex.BaseFragment;
import com.stockingd.cinedex.R;

public class DrawerFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.drawer_fragment, container, false);
    }
}
