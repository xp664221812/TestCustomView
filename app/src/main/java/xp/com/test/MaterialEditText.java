package xp.com.test;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.blankj.utilcode.util.SizeUtils;

public class MaterialEditText extends AppCompatEditText {
    private static final int TEXT_SIZE = SizeUtils.dp2px(12);

    private static final int TEXT_MARGIN = SizeUtils.dp2px(8);

    private static final int TEXT_VERTICAL_OFFSET = SizeUtils.dp2px(20);

    private static final int TEXT_HORIZONTAL_OFFSET = SizeUtils.dp2px(5);

    private static final int TEXT_ANIMATION_OFFSET = SizeUtils.dp2px(16);

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Rect backgroundPadding = new Rect();


    private boolean floatingLabelShown;

    private float floatingLabelFraction;

    boolean useFloatingLabel = true;

    ObjectAnimator animator;

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText);
        useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_userFloatingLabel, true);

        init();
//        typedArray.recycle();
    }

    public void setFloatingLabelFraction(float floatingLabelFraction) {
        this.floatingLabelFraction = floatingLabelFraction;
        invalidate();
    }

    public float getFloatingLabelFraction() {
        return floatingLabelFraction;
    }

    public void setUseFloatingLabel(boolean useFloatingLabel) {
        if (this.useFloatingLabel != useFloatingLabel) {
            this.useFloatingLabel = useFloatingLabel;

            onUseFloatingLabelChanged();

        }
    }


    void init() {
        onUseFloatingLabelChanged();
        paint.setTextSize(TEXT_SIZE);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (useFloatingLabel) {
                    if (floatingLabelShown && TextUtils.isEmpty(s)) {
                        floatingLabelShown = false;
                        //逐渐消失
                        getAnimator().reverse();
                    } else if (!floatingLabelShown && !TextUtils.isEmpty(s)) {
                        floatingLabelShown = true;
                        getAnimator().start();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void onUseFloatingLabelChanged() {
        getBackground().getPadding(backgroundPadding);
        if (useFloatingLabel) {
            setPadding(getPaddingLeft(), backgroundPadding.top + TEXT_SIZE + TEXT_MARGIN
                    , getPaddingRight(), getPaddingBottom());
        } else {
            setPadding(getPaddingLeft(), backgroundPadding.top, getPaddingRight(), getPaddingBottom());

        }

    }


    private ObjectAnimator getAnimator() {
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(MaterialEditText.this
                    , "floatingLabelFraction", 0, 1);
        }
        return animator;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setAlpha((int) (0xff * floatingLabelFraction));

        float extraOffset = ((1 - floatingLabelFraction) * TEXT_ANIMATION_OFFSET);

        canvas.drawText(getHint().toString(), TEXT_HORIZONTAL_OFFSET, TEXT_VERTICAL_OFFSET + extraOffset, paint);

    }
}
