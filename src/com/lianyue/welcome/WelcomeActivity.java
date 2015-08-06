package com.lianyue.welcome;

import java.util.ArrayList;
import java.util.List;

import com.baidu.welcome.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class WelcomeActivity extends Activity {

	private ViewPager mViewPager;
	private MyViewGroup mViewGroup;  //自定义的4个圆点
	private Button mButton;
	
	List<View> mViews;
	SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		mViewPager = (ViewPager) findViewById(R.id.voewpager);
		mViewGroup = (MyViewGroup) findViewById(R.id.viewgroup);
		mButton = (Button) findViewById(R.id.button);
		
		mButton.setVisibility(View.INVISIBLE);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),HomeAcitivity.class);
				startActivity(intent);
				Editor edit = sp.edit();
				edit.putBoolean("isFirst", true);
				edit.commit();
				finish();
				
			}
		});
		
		//设置如果不是第一次进入向导就进入主界面
		sp = getSharedPreferences("config", MODE_PRIVATE);
		//如果值是true就直接进入HomeActivity
		if(sp.getBoolean("isFirst", false)) {
			Intent intent = new Intent(this,HomeAcitivity.class);
			startActivity(intent);
			//这里要加finish,要不然退出后,还会显示想到ViewPager
			finish();
		}
		
		//设置选择的条目
		mViewGroup.setSelection(0);
		
		mViews = new ArrayList<View>();
		mViews.add(View.inflate(this, R.layout.page1, null));
		mViews.add(View.inflate(this, R.layout.page2, null));
		mViews.add(View.inflate(this, R.layout.page3, null));
		mViews.add(View.inflate(this, R.layout.page4, null));
		
		mViewPager.setAdapter(new MyAdapter());
		//设置ViewPager页面改变事件
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				mViewGroup.setSelection(position);
				if(position == (mViews.size() -1) ) {
					mButton.setVisibility(View.VISIBLE);
				} else {
					
					mButton.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				
			}
		});
		
	}
	
	
	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mViews.size();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mViews.get(position));
			return mViews.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(mViews.get(position));
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			
			return view==object;
		}
		
	}
}
