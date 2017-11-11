package com.risenphoenix.tilelink;

import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.appodeal.gdx.GdxAppodeal;
import com.appodeal.gdx.callbacks.BannerCallback;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {

	private boolean flag;


	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useCompass=false;
		config.useAccelerometer=false;
		config.useGyroscope=false;
		flag = false;
		initialize(new Rope_1(), config);
		Appodeal.disableNetwork(this, "cheetah");
		String appKey = "ccbf8a996e0d64c7f61b5266330f6f7e1fbe454f782470c0";
		GdxAppodeal.disableLocationPermissionCheck();
		GdxAppodeal.initialize(appKey, GdxAppodeal.BANNER);//GdxAppodeal.INTERSTITIAL | GdxAppodeal.BANNER);



		/*Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
			public void onInterstitialLoaded(boolean isPrecache) {
				Log.d("Appodeal", "onInterstitialLoaded");
				if(!flag) {
					if (Appodeal.isLoaded(Appodeal.INTERSTITIAL)) {
						GdxAppodeal.show(GdxAppodeal.INTERSTITIAL);
						flag=true;
					}
				}
			}
			public void onInterstitialFailedToLoad() { }
			public void onInterstitialShown() { }
			public void onInterstitialClicked() { }
			public void onInterstitialClosed() { }
		});*/
//When you want to show the ad



		GdxAppodeal.setBannerCallbacks(new BannerCallback() {
			@Override
			public void onBannerLoaded() {
				Log.d("Appodeal", "onBannerLoaded");
				if (Appodeal.isLoaded(GdxAppodeal.BANNER)) {
					GdxAppodeal.show(GdxAppodeal.BANNER_BOTTOM);
				}
			}

			@Override
			public void onBannerFailedToLoad() { }

			@Override
			public void onBannerShown() { }

			@Override
			public void onBannerClicked() { }
		});

	}
}
