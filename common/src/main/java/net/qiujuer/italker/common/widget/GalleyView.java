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
import android.widget.Toast;

import net.qiujuer.genius.ui.widget.CheckBox;
import net.qiujuer.genius.ui.widget.ImageView;
import net.qiujuer.italker.common.R;
import net.qiujuer.italker.common.widget.recycler.RecyclerAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * 相册 Cell
 */
public class GalleyView extends RecyclerView {

    private Adapter mAdapter = new Adapter();
    private static final int MAX_IMAGE_COUNT = 3;

    private List<ImageData> mSelectImages = new LinkedList<>();

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

        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<ImageData>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder<ImageData> holder, ImageData imageData) {

                if (onSelectItem(imageData)){
                    holder.updateData(imageData);
                }
            }
        });
    }

    private boolean onSelectItem(ImageData data){
        boolean needRefresh;
        if (mSelectImages.contains(data)){
            needRefresh = true;
            data.isSelected = false;
            mSelectImages.remove(data);
        }else{

            if (mSelectImages.size() >= MAX_IMAGE_COUNT){

                String str = getResources().getString(R.string.label_gallery_select_max_size);
                str = String.format(str, MAX_IMAGE_COUNT);
                Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                needRefresh = false;
            }else{
                data.isSelected = true;
                mSelectImages.add(data);
                needRefresh = true;
            }
        }
        return needRefresh;
    }

    public List<String> getSelectImagePath(){
        List<String> paths = new ArrayList<>();
        for (ImageData mSelectImage : mSelectImages) {
            paths.add(mSelectImage.path);
        }
        return paths;
    }

    public void clear(){
        for (ImageData mSelectImage : mSelectImages) {
            mSelectImage.isSelected = false;
        }

        mSelectImages.clear();
        mAdapter.notifyDataSetChanged();
    }


    private class ImageData{
        int id;
        String path;
        long date;
        boolean isSelected;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ImageData)) return false;

            ImageData imageData = (ImageData) o;

            return path != null ? path.equals(imageData.path) : imageData.path == null;

        }

        @Override
        public int hashCode() {
            return path != null ? path.hashCode() : 0;
        }
    }

    private class Adapter extends RecyclerAdapter<ImageData>{

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

        private android.widget.ImageView imageView;
        private View shade;
        private CheckBox checkBox;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = (android.widget.ImageView) findViewById(R.id.im_image);
            shade = findViewById(R.id.view_shade);
            checkBox = (CheckBox) findViewById(R.id.cb_select);
        }

        @Override
        protected void onBind(ImageData imageData) {

        }
    }
}
