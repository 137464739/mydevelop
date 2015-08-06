package com.lianyue.welcome;


import com.baidu.welcome.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MyViewGroup extends LinearLayout {

	public MyViewGroup(Context context) {
		super(context);

		//初始化
		initView(context);
	}

	
	public MyViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}


	private void initView(Context context) {
		this.setOrientation(LinearLayout.HORIZONTAL);
		//循环4个图片
		for (int i = 0; i < 4; i++) {
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.presence_invisible);
			this.addView(image);
		}
		
	}

	/**
	 * 设置选择的位置
	 * @param position
	 */
	public void setSelection(int position) {
		
		int count = this.getChildCount();
		for (int i = 0; i < count; i++) {
			ImageView view = (ImageView) this.getChildAt(i);
			view.setImageResource(R.drawable.presence_invisible);
		}
		ImageView view = (ImageView) this.getChildAt(position);
		view.setImageResource(R.drawable.presence_online);
	}
	
}
