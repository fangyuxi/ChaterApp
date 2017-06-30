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
public class GroupFragment extends Fragment {


    public GroupFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_group;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.e("Group","Group onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("Group","Group onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.e("Group","Group onCreateView");

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e("Group","Group onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.e("Group","Group onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.e("Group","Group onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.e("Group","Group onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.e("Group","Group onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Group","Group onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("Group","Group onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Group","Group onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.e("Group","Group onDetach");
    }
}
