package net.qiujuer.italker.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import net.qiujuer.genius.ui.widget.ImageView;
import net.qiujuer.italker.common.R;
import net.qiujuer.italker.common.widget.recycler.RecyclerAdapter;

import butterknife.BindView;

/**
 * TODO: document your custom view class.
 */
public class GalleyView extends RecyclerView {

    Adapter mAdapter = new Adapter();

    public GalleyView(Context context) {
        super(context);
        init();
    }

    public GalleyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GalleyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLayoutManager(new GridLayoutManager(getContext(),4));
        setAdapter(mAdapter);

        mAdapter.setListener(new RecyclerAdapter.AdapterListener<ImageData>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, ImageData imageData) {

            }

            @Override
            public void onItemLongClick(RecyclerAdapter.ViewHolder holder, ImageData imageData) {

            }
        });
    }



    private class ImageData{

    }

    private class Adapter extends RecyclerAdapter<ImageData>{

        @Override
        public void update(ImageData imageData, ViewHolder<ImageData> holder) {

        }

        @Override
        protected int getItemViewType(int position, ImageData imageData) {
            return R.layout.cell_galley;
        }

        @Override
        protected ViewHolder<ImageData> onCreateViewHolder(View root, int viewType) {
            return new GalleyView.ViewHolder(root);
        }
    }

    private class ViewHolder extends RecyclerAdapter.ViewHolder<ImageData>{

//        @BindView(R.id.imageView)
//        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(ImageData imageData) {

        }
    }
}
