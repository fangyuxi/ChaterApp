package net.qiujuer.italker.push.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.widget.FloatActionButton;
import net.qiujuer.italker.common.app.Activity;
import net.qiujuer.italker.common.widget.PortraitView;
import net.qiujuer.italker.common.widget.recycler.RecyclerAdapter;
import net.qiujuer.italker.push.R;
import net.qiujuer.italker.push.frags.NavHelper;
import net.qiujuer.italker.push.frags.main.ActiveFragment;
import net.qiujuer.italker.push.frags.main.ContactFragment;
import net.qiujuer.italker.push.frags.main.GroupFragment;

import java.security.acl.Group;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity
        implements BottomNavigationView.OnNavigationItemSelectedListener,NavHelper.TabChangeListener{

    @BindView(R.id.appbar)
    View mActionBar;

    @BindView(R.id.im_portrait)
    PortraitView mPortrait;

    @BindView(R.id.txt_title)
    TextView mTitle;

    @BindView(R.id.lay_container)
    FrameLayout mContainer;

    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;

    @BindView(R.id.btn_action)
    FloatActionButton actionButton;

    NavHelper mNavHelper = new NavHelper(this,getSupportFragmentManager(),R.id.lay_container,this);


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    @SuppressWarnings("unchecked")
    protected void initWidget() {
        super.initWidget();

        mNavigation.setOnNavigationItemSelectedListener(this);

        Glide.with(this)
                .load(R.drawable.bg_src_morning)
                .centerCrop()
                .into(new ViewTarget<View,GlideDrawable>(mActionBar) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setBackground(resource.getCurrent());
                    }
                });

        initNav();
    }

    public static void show(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @OnClick(R.id.im_search)
    void onSearchMenuClick(){

    }

    @OnClick(R.id.btn_action)
    void floatButtonAction(){

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mTitle.setText(item.getTitle());
        floatingButtonAnimation(item.getItemId());
        return  mNavHelper.performSelect(item.getItemId());
    }

    @SuppressWarnings("unchecked")
    private void initNav(){
        NavHelper.Tab tab1 = new NavHelper.Tab(ActiveFragment.class,R.id.action_home,null);
        NavHelper.Tab tab2 = new NavHelper.Tab(GroupFragment.class,R.id.action_group,null);
        NavHelper.Tab tab3 = new NavHelper.Tab(ContactFragment.class,R.id.action_contact,null);

        mNavHelper.addTab(tab1);
        mNavHelper.addTab(tab2);
        mNavHelper.addTab(tab3);
        mNavigation.setSelectedItemId(R.id.action_home);
    }

    private void floatingButtonAnimation(int menuId){
        float transY = 0;
        float rotation = 0;

        if (menuId == R.id.action_home){
            transY = Ui.dipToPx(getResources(),76);
        }else if (menuId == R.id.action_group){
            actionButton.setImageResource(R.drawable.ic_group_add);
            rotation = -360;
        }else if (menuId == R.id.action_contact){
            actionButton.setImageResource(R.drawable.ic_contact_add);
            rotation = 360;
        }

        actionButton.animate()
                .rotation(rotation)
                .translationY(transY)
                .setInterpolator(new AnticipateOvershootInterpolator(1))
                .setDuration(480)
                .start();
    }

    @Override
    public void tabChangedSelect(NavHelper.Tab oldTab, NavHelper.Tab newTab) {

    }

    @Override
    public void tabChangedReSelect(NavHelper.Tab tab) {

    }
}