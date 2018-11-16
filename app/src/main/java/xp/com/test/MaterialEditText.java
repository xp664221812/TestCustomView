package xp.com.test;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.blankj.utilcode.util.SizeUtils;

public class MaterialEditText extends AppCompatEditText {
    private static final int TEXT_SIZE = SizeUtils.dp2px(12);

    private static final int TEXT_MARGIN = SizeUtils.dp2px(20);

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    {
        setPadding(getPaddingLeft(), getPaddingTop() + TEXT_SIZE + TEXT_MARGIN, getPaddingRight(), getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
