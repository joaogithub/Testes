package com.testes.fragment;
import com.testes.android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentTabsFragmentSupport extends Fragment {
    private FragmentTabHost mTabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.fragment1);

        mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator("Simple"),
                EditTextFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator("Contacts"),
        		ImageFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("throttle").setIndicator("Throttle"),
                TextFragment.class, null);

        return mTabHost;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }
}