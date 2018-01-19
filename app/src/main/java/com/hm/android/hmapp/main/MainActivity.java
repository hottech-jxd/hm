package com.hm.android.hmapp.main;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hm.android.hmapp.BaseActivity;
import com.hm.android.hmapp.BaseApplication;
import com.hm.android.hmapp.R;
import com.hm.android.hmapp.bean.DeviceBean;
import com.hm.android.hmapp.bean.DeviceResult;
import com.hm.android.hmapp.device.DeviceActivity;
import com.hm.android.hmapp.setting.SettingActivity;
import com.hm.android.hmapp.utils.DensityUtils;
import com.hm.android.hmapp.utils.ToastUtils;
import com.hm.android.hmapp.widget.GridDivider;
import com.hm.android.hmapp.widget.RecycleItemDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<IMainPresenter> implements IMainView , BaseQuickAdapter.OnItemClickListener{

    @BindView(R.id.header_left)
    ImageView header_left;
    @BindView(R.id.header_title)
    TextView header_title;
    @BindView(R.id.header_right_image)
    ImageView header_right_image;
    @BindView(R.id.main_recyclerView)
    RecyclerView main_recyclerview;
    @BindView(R.id.main_toppicture)
    SimpleDraweeView main_toppicture;
    List<DeviceBean> manus;
    MainAdapter mainAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder= ButterKnife.bind(this);


        header_left.setImageResource(R.drawable.style_left_arrow);
        header_right_image.setImageResource(R.mipmap.person);
        header_title.setText("智能家具系统");

        int sw = DensityUtils.getScreenW(this);
        int h = sw * 1/2;
        ViewGroup.LayoutParams layoutParams = main_toppicture.getLayoutParams();
        layoutParams.height = h;
        layoutParams.width = sw;
        main_toppicture.setLayoutParams(layoutParams);

        Uri uri = Uri.parse("res://"+getPackageName()+"/"+R.mipmap.none);

        main_toppicture.setImageURI(uri);

        manus=new ArrayList<>();
        DeviceBean bean = new DeviceBean();
        bean.setDeviceName("照明");
        manus.add(bean);
        bean = new DeviceBean();
        bean.setDeviceName("安防");
        manus.add(bean);
        bean = new DeviceBean();
        bean.setDeviceName("电器");
        manus.add(bean);
        bean = new DeviceBean();
        bean.setDeviceName("门禁");
        manus.add(bean);
        mainAdapter=new MainAdapter(manus);

        main_recyclerview.setLayoutManager(new GridLayoutManager(this ,2));
        main_recyclerview.setAdapter(mainAdapter);

        GridDivider.Builder builder = new GridDivider.Builder(this , 2);
        builder.setmDivider(new ColorDrawable(ContextCompat.getColor(this , R.color.space_color)));
        main_recyclerview.addItemDecoration( builder.build());
        mainAdapter.setOnItemClickListener(this);


        DaggerMainComponent
                .builder()
                .appComponent(BaseApplication.single.getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        iPresenter.main(BaseApplication.single.getUserBean().getPukId() );

    }

    @OnClick({R.id.header_right_image})
    public void onViewClicked(View v){
        if(v.getId()==R.id.header_right_image){
            Intent intent=new Intent(this, SettingActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent=new Intent(this , DeviceActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void mainCallback(List<DeviceResult> object) {
        //ToastUtils.showLongToast("ssss");

        if(object==null || object.size()<1 || object.get(0).getLoadDevices()==null || object.get(0).getLoadDevices().size()<1){
            return;
        }

        manus= object.get(0).getLoadDevices();

        mainAdapter.setNewData( object.get(0).getLoadDevices() );
    }

    @Override
    public void updateTriggerCallback(Object object) {

    }
}
