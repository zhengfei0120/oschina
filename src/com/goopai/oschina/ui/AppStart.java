package com.goopai.oschina.ui;

import com.goopai.oschina.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class AppStart extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View view = View.inflate(this, R.layout.activity_app_start, null);
		setContentView(view);
		animationLoading(view);
	}

	private void animationLoading(final View view) {
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1.0f);
		alphaAnimation.setDuration(2000);
		view.startAnimation(alphaAnimation);
		alphaAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				redirectIntent();
			}
		});
	}

	private void redirectIntent() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
