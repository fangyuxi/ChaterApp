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

public class NavHelper {

    private final Context context;
    private final FragmentManager fragmentManager;
    private final int containerId;
    private final TabChangeListener listener;
    private final SparseArray<Tab> tabs = new SparseArray<>();

    private Tab currentTab;

    public NavHelper(Context context, FragmentManager fragmentManager, int containerId, TabChangeListener listener) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
        this.listener = listener;
    }

    public void addTab(Tab tab){
        if (tab != null){
            this.tabs.put(tab.menuId,tab);
        }
    }

    public boolean performSelect(int menuId) {
        Tab tab = this.tabs.get(menuId);
        return tab != null && doSelect(tab);
    }

    private boolean doSelect(Tab tab){

        if (currentTab == tab){
            if (listener != null){
                listener.tabChangedReSelect(tab);
            }
            return false;
        }

        Tab oldTab = null;
        if (currentTab != null){
            oldTab = currentTab;
        }

        currentTab = tab;
        return doChange(oldTab, currentTab);
    }

    private boolean doChange(Tab oldTab, Tab newTab){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (oldTab != null){
            ft.detach(oldTab.fragment);
        }

        if (newTab.fragment == null){
            newTab.fragment = Fragment.instantiate(context,newTab.clz.getName(),null);
            ft.add(containerId,newTab.fragment);
        }else{
            ft.attach(newTab.fragment);
        }
        ft.commit();

        if (listener != null){
            listener.tabChangedSelect(oldTab,newTab);
        }
        return true;
    }

    public static class Tab{
            private Class clz;
            private int menuId;

            private Fragment fragment;
            TabData extra;

            public Tab(Class clz, int menuId, TabData extra) {
                this.clz = clz;
                this.menuId = menuId;
                this.extra = extra;
            }
    }

    public static class TabData{
        String title;
    }

    public interface TabChangeListener{
        void tabChangedSelect(Tab oldTab, Tab newTab);
        void tabChangedReSelect(Tab tab);
    }
}
