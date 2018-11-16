package xp.com.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.StaticLayout;
import android.util.AttributeSet;
import android.view.View;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.SizeUtils;

public class CustomView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float[] cutWidth = new float[1];

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setTextSize(SizeUtils.dp2px(10));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String text = "海关总署11月8日发布的数据显示，今年前10个月，我国货物贸易进出口总值为25万亿元人民币，同比增长11.3%。其中，出口13.35万亿元，增长7.9%；进口11.7万亿元，增长15.5%；贸易顺差1.65万亿元，收窄26.1%。10月当月，我国进出口总值为2.75万" +
                "亿元，增长22.9%。其中，出口1.49万亿元，增长20.1%；进口1.26万亿元，增长26.3%；贸易顺差2336.3亿元，收窄5%";

        Bitmap bitmap = ImageUtils.getBitmap(R.mipmap.xxx);


        canvas.drawBitmap(bitmap, getWidth() - bitmap.getWidth(), SizeUtils.dp2px(25), paint);


        int index = paint.breakText(text, true, getWidth(), cutWidth);
        canvas.drawText(text, 0, index, 0, 50, paint);
        int oldIndex = index;
        index = paint.breakText(text, index, text.length(), true, getWidth(), cutWidth);
        canvas.drawText(text, oldIndex, oldIndex + index, 0, 50 + paint.getFontSpacing(), paint);
        oldIndex =  index+oldIndex;
        index = paint.breakText(text, oldIndex + index, text.length()
                , true, getWidth() - bitmap.getWidth(), cutWidth);
        canvas.drawText(text, oldIndex, oldIndex + index, 0, 50 + paint.getFontSpacing() * 2, paint);


    }
}
