package net.qiujuer.italker.push.frags.main;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.qiujuer.italker.common.app.Fragment;
import net.qiujuer.italker.push.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {


    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_contact;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.e("Contact","Contact onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("Contact","Contact onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.e("Contact","Contact onCreateView");

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e("Contact","Contact onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.e("Contact","Contact onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.e("Contact","Contact onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.e("Contact","Contact onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.e("Contact","Contact onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Contact","Contact onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("Contact","Contact onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Contact","Contact onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.e("Contact","Contact onDetach");
    }
}
