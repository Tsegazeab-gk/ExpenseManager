package com.tsegazeabg.com.expensemanager.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.tsegazeabg.com.expensemanager.R;
import com.tsegazeabg.com.expensemanager.ui.activities.HomeActivity;

public class SplashActivity extends AppCompatActivity
        //implements SplashContract.View
{

    SplashPresenter splashPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

splashPresenter=new SplashPresenter();

        TextView logo1 = (TextView) findViewById(R.id.txt_top_title);
        Animation fade1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo1.startAnimation(fade1);

        TextView logo2 = (TextView) findViewById(R.id.txt_bottom_title);

        Animation fade2 = AnimationUtils.loadAnimation(this, R.anim.fade_in2);
        logo2.startAnimation(fade2);
        fade2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //splashPresenter.startMainActivity();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        Animation spinin = AnimationUtils.loadAnimation(this, R.anim.custom_anim);
        LayoutAnimationController controller =
                new LayoutAnimationController(spinin);
        TableLayout table = (TableLayout) findViewById(R.id.tbl_splash_layout);
        for (int i = 0; i < table.getChildCount(); i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            row.setLayoutAnimation(controller);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        TextView logo1 = (TextView) findViewById(R.id.txt_top_title);
        logo1.clearAnimation();


        TableLayout table = (TableLayout) findViewById(R.id.tbl_splash_layout);
        for (int i = 0; i < table.getChildCount(); i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            row.clearAnimation();
        }
        TextView logo2 = (TextView) findViewById(R.id.txt_bottom_title);
        logo2.clearAnimation();

    }

    @Override
    protected void onResume() {
        super.onResume();
       // splashPresenter.takeView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
      //  splashPresenter.dropView();
    }

//    @Override
//    public void openMainActivity() {
//        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
//       /* SplashActivity.this.finish();*/
//    }
//
//    @Override
//    public void showProgress() {
//
//    }
//
//    @Override
//    public void hideProgress() {
//
//    }
//
//    @Override
//    public void showError(String message) {
//
//    }
}
