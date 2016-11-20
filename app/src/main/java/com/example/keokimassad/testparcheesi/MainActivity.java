package com.example.keokimassad.testparcheesi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout rl_Main;
    private ParState parState;

    GameConfig config  = null;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.config = createDefaultConfig();















        parState = new ParState();
        parState.initBoardPieces();

        final TextView textView = (TextView)findViewById(R.id.textView);
        final TextView textView1 = (TextView)findViewById(R.id.textView1);
        // this is the view on which you will listen for touch events
        final View touchView = findViewById(R.id.imageView);

        touchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                textView.setText("Touch coordinates : " +
                        String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
                textView1.setText(parState.containsInRect(event.getX(), event.getY()));
                return true;
            }
        });
    }
}
