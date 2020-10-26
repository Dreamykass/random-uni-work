package com.dkass.canvasstuff;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DrawingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Graphics graphics = new Graphics(this);
        graphics.setIntent(getIntent());
        setContentView(graphics);
    }

    static public class Graphics extends View {
        private final Paint paint = new Paint();
        private Intent intent;

        public Graphics(Context context) {
            super(context);
        }

        void setIntent(Intent _intent) {
            intent = _intent;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            paint.setStyle(Paint.Style.FILL);

            try {
                paint.setColor(Color.parseColor(intent.getStringExtra("color")));
            } catch (Exception ignored) {
                paint.setColor(Color.RED);
            }

            try {
                paint.setShader(new LinearGradient(0, 0, 0, getHeight(),
                        Color.parseColor(intent.getStringExtra("color1")),
                        Color.parseColor(intent.getStringExtra("color2")),
                        Shader.TileMode.MIRROR));
            } catch (Exception ignored) {
            }

            try {
                intent.getStringExtra("rect").toCharArray();
                canvas.drawRect(intent.getFloatExtra("left", 1),
                        intent.getFloatExtra("top", 2),
                        intent.getFloatExtra("right", 3),
                        intent.getFloatExtra("bottom", 6),
                        paint);
            } catch (Exception ignored) {
            }

            try {
                canvas.drawText(intent.getStringExtra("text"),
                        intent.getFloatExtra("x", 100),
                        intent.getFloatExtra("y", 100),
                        paint);
                canvas.drawText(intent.getStringExtra("text"),
                        400, 400,
                        intent.getFloatExtra("x", 100),
                        intent.getFloatExtra("y", 100),
                        paint);
            } catch (Exception ignored) {
            }

            try {
                intent.getStringExtra("circle").toCharArray();
                canvas.drawCircle(intent.getFloatExtra("cx", 100),
                        intent.getFloatExtra("cy", 100),
                        intent.getFloatExtra("radius", 100),
                        paint);
            } catch (Exception ignored) {
            }


            invalidate();
        }
    }

}