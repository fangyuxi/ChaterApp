package net.qiujuer.italker.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import android.widget.CheckBox;
import net.qiujuer.genius.ui.widget.ImageView;
import net.qiujuer.italker.common.R;
import net.qiujuer.italker.common.widget.recycler.RecyclerAdapter;

import java.io.File;
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
    private static final int LOADER_ID = 0x0100;
    private static final int MIN_IMAGE_FILE_SIZE = 1024;

    private LoaderCallback mLoaderCallback = new LoaderCallback();
    private SelectedChangeListener mListener;

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

    public int setup(@NonNull LoaderManager loaderManager, @Nullable SelectedChangeListener listener){
        mListener = listener;
        loaderManager.initLoader(LOADER_ID,null,mLoaderCallback);
        return LOADER_ID;
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
        if (needRefresh){
            notifySelectChanged();
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
        notifySelectChanged();
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
            imageView = (android.widget.ImageView) itemView.findViewById(R.id.im_image);
            shade = itemView.findViewById(R.id.view_shade);
            checkBox = (CheckBox) itemView.findViewById(R.id.cb_select);
        }

        @Override
        protected void onBind(ImageData imageData) {
            Glide.with(getContext())
                    .load(imageData.path) // 加载路径
                    .diskCacheStrategy(DiskCacheStrategy.NONE) // 不使用缓存，直接从原图加载
                    .centerCrop() // 居中剪切
                    .placeholder(R.color.grey_200) // 默认颜色
                    .into(imageView);

            shade.setVisibility(imageData.isSelected ? VISIBLE : INVISIBLE);
            checkBox.setChecked(imageData.isSelected);
            checkBox.setVisibility(VISIBLE);
        }
    }

    private void notifySelectChanged() {
        // 得到监听者，并判断是否有监听者，然后进行回调数量变化
        SelectedChangeListener listener = mListener;
        if (listener != null) {
            listener.onSelectedCountChanged(mSelectImages.size());
        }
    }

    public interface SelectedChangeListener{
        void onSelectedCountChanged(int count);
    }

    private class LoaderCallback implements LoaderManager.LoaderCallbacks<Cursor>{

        private final String[] IMAGE_PROJECTION = new String[]{
                MediaStore.Images.Media._ID, // Id
                MediaStore.Images.Media.DATA, // 图片路径
                MediaStore.Images.Media.DATE_ADDED // 图片的创建时间ø
        };

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            if (id != LOADER_ID){
                return null;
            }
            return new CursorLoader(getContext(),
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    IMAGE_PROJECTION,
                    null,
                    null,
                    IMAGE_PROJECTION[2] + " DESC"
                    );
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            List<ImageData> images = new ArrayList<>();
            if (data != null){
                int count = data.getCount();
                if (count > 0){

                    data.moveToFirst();
                    int indexId = data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]);
                    int indexPath = data.getColumnIndexOrThrow(IMAGE_PROJECTION[1]);
                    int indexDate = data.getColumnIndexOrThrow(IMAGE_PROJECTION[2]);

                    do {

                        int id = data.getInt(indexId);
                        String path =data.getString(indexPath);
                        long date = data.getLong(indexDate);

                        File file = new File(path);
                        if (!file.exists() || file.length() < MIN_IMAGE_FILE_SIZE){
                            continue;
                        }

                        // 添加一条新的数据
                        ImageData image = new ImageData();
                        image.id = id;
                        image.path = path;
                        image.date = date;
                        images.add(image);

                    }while(data.moveToNext());

                }
            }
            updateSource(images);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            updateSource(null);
        }
    }

    private void updateSource(List<ImageData> images) {
        mAdapter.replace(images);
    }
}
