package com.stockingd.cinedex.movie_details.trailer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.stockingd.cinedex.ViewScope;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@ViewScope
public class TrailerViewPager extends FragmentStatePagerAdapter {

    private List<String> model = new ArrayList<>();

    @Inject
    public TrailerViewPager(FragmentManager fm) {
        super(fm);
    }

    public void swapModel(List<String> model) {
        this.model = model;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return MovieTrailerFragment.create(model.get(position));
    }

    @Override
    public int getCount() {
        return model.size();
    }
}
