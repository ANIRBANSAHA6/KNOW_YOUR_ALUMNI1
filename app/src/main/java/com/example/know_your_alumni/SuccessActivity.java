package com.example.know_your_alumni;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.know_your_alumni.R;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        ImageView tickMark = findViewById(R.id.tick_mark);
        TextView successMessage = findViewById(R.id.success_message);

        // Animate the tick mark
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(3000);  // 1 second animation
        fadeIn.setFillAfter(true);
        tickMark.startAnimation(fadeIn);

        // Show the success message after the tick animation
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                successMessage.setVisibility(View.VISIBLE);
                successMessage.animate().alpha(1f).setDuration(3000).start();  // Fade-in text animation
                // Start the new activity after a delay to show the success message for a moment
                successMessage.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Intent to navigate to the next activity
                        Intent intent = new Intent(SuccessActivity.this, NavigationActivity.class);  // Replace 'NewActivity' with the activity you want to open
                        startActivity(intent);
                        finish();  // Optionally close the current activity
                    }
                }, 1500);  // Delay of 1.5 seconds before navigating
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });


    }


}
