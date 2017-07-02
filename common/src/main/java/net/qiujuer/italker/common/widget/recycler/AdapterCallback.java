package net.qiujuer.italker.common.widget.recycler;

/**
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
interface AdapterCallback<Data> {
    void update(Data data, RecyclerAdapter.ViewHolder<Data> holder);
}
