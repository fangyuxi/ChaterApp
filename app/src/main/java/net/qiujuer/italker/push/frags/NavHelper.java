package net.qiujuer.italker.push.frags;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by yuxi.
 */

public class NavHelper<T> {

    private final Context context;
    private final FragmentManager fragmentManager;
    private final int containerId;
    private final TabChangeListener listener;
    private final SparseArray<Tab<T>> tabs = new SparseArray<>();

    private Tab<T> currentTab;

    public NavHelper(Context context, FragmentManager fragmentManager, int containerId, TabChangeListener listener) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
        this.listener = listener;
    }

    public void addTab(Tab<T> tab){
        if (tab != null){
            this.tabs.put(tab.menuId,tab);
        }
    }

    public boolean performSelect(int menuId) {
        Tab<T> tab = this.tabs.get(menuId);
        return tab != null && doSelect(tab);
    }

    private boolean doSelect(Tab<T> tab){
        FragmentTransaction ft = fragmentManager.beginTransaction();

        if (currentTab == tab){
            // TODO 双击同一个Tab
            return false;
        }

        if (currentTab == null){
            if (tab.fragment == null){
                tab.fragment = Fragment.instantiate(context,tab.clz.getName(),null);
                ft.add(containerId,tab.fragment);
            }else {
                ft.attach(tab.fragment);
            }
            currentTab = tab;
        }else{
            Tab<T> oldTab = currentTab;
            ft.detach(oldTab.fragment);
            if (tab.fragment == null){
                tab.fragment = Fragment.instantiate(context,tab.clz.getName(),null);
                ft.add(containerId,tab.fragment);
            }else {
                ft.attach(tab.fragment);
            }
            currentTab = tab;
        }

        ft.commit();

        return true;
    }

    public static class Tab<T>{

        Class clz;
        Fragment fragment;
        int menuId;
        T extra;

        public Tab(Class clz, int menuId, T extra) {
            this.clz = clz;
            this.menuId = menuId;
            this.extra = extra;
        }
    }

    public interface TabChangeListener{

    }
}
