package com.acp.terjelonoy.androidcertificationpreparation.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acp.terjelonoy.androidcertificationpreparation.R;

/**
 * Created by terjelonoy on 2/14/17.
 */

public class DiscoText extends TextView {
    private Context thisContext;
    private String text;
    private int textSize;

    private TextView mainText;
    private TextView shadow1;
    private TextView shadow2;

    public DiscoText(Context context, AttributeSet attrs) {
        super(context, attrs);

        thisContext = context;
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.DiscoText,
                0, 0);

        try {
            text = typedArray.getString(R.styleable.DiscoText_text);
            textSize = typedArray.getInteger(R.styleable.DiscoText_textSize, 12);
        } finally {
            // Make it reusable
            typedArray.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        shadow2 = drawShadow(canvas, getResources().getColor(R.color.colorPrimary, null), 0, 0);
        shadow1 = drawShadow(canvas, getResources().getColor(R.color.colorAccent, null), 10, 10);
        mainText = drawShadow(canvas, getResources().getColor(R.color.colorGreen, null), 10, 10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(700, 300);
    }

    private TextView drawShadow(Canvas canvas, int color, int yPos, int xPos) {
        TextView textView = new TextView(thisContext);
        textView.setText(text);
        textView.setTextColor(color);
        textView.setTextSize(sp(textSize));

        textView.measure(canvas.getWidth(), canvas.getHeight());
        textView.layout(0, 0, canvas.getWidth(), canvas.getHeight());

        canvas.translate(xPos, yPos);
        textView.draw(canvas);

        return textView;
    }

    private int sp(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, getResources().getDisplayMetrics());
    }
}