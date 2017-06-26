package net.qiujuer.italker.push;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import net.qiujuer.italker.common.app.Activity;
import net.qiujuer.italker.common.widget.recycler.RecyclerAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @BindView(R.id.imageView)
    ImageView mImageView;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mImageView.setImageResource(R.drawable.default_portrait);
    }
}