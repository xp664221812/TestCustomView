package xp.com.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.SizeUtils;

public class ImageView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Camera camera = new Camera();
    Bitmap bitmap;
    int size;

    public ImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setTextSize(SizeUtils.dp2px(10));
        bitmap = ImageUtils.getBitmap(R.mipmap.icon);
        size = bitmap.getWidth();
//        camera.rotateX();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.clipRect(100, 100, size - 100, size - 100);
        canvas.drawBitmap(bitmap, 100, 100, paint);

    }
}
