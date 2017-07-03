package net.qiujuer.italker.push.frags.main;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.qiujuer.italker.common.app.Fragment;
import net.qiujuer.italker.common.widget.GalleyView;
import net.qiujuer.italker.push.R;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActiveFragment extends Fragment {

    @BindView(R.id.galley)
    GalleyView galleyView;

    public ActiveFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_active;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.e("Active","Active onAttach");
    }

    @Override
    protected void initData() {
        super.initData();

        galleyView.setup(getLoaderManager(), new GalleyView.SelectedChangeListener() {
            @Override
            public void onSelectedCountChanged(int count) {

            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("Active","Active onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.e("Active","Active onCreateView");

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e("Active","Active onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.e("Active","Active onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.e("Active","Active onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.e("Active","Active onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.e("Active","Active onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Active","Active onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("Active","Active onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Active","Active onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.e("Active","Active onDetach");
    }
}
