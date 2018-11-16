package xp.com.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.blankj.utilcode.util.SizeUtils;

public class MaterialEditText extends AppCompatEditText {
    private static final int TEXT_SIZE = SizeUtils.dp2px(12);

    private static final int TEXT_MARGIN = SizeUtils.dp2px(8);

    private static final int TEXT_VERTICAL_OFFSET = SizeUtils.dp2px(20);

    private static final int TEXT_HORIZONTAL_OFFSET = SizeUtils.dp2px(5);

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    {
        setPadding(getPaddingLeft(), getPaddingTop() + TEXT_SIZE + TEXT_MARGIN, getPaddingRight(), getPaddingBottom());
        paint.setTextSize(TEXT_SIZE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(getHint().toString(), TEXT_HORIZONTAL_OFFSET, TEXT_VERTICAL_OFFSET, paint);

    }
}
