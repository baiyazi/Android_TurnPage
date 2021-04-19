package com.example.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.pojo.MyPoint;
import com.example.utils.CalcPoints;

import java.util.Map;

public class Learn extends View {

    private Paint mAPaint;
    private Paint mBPaint;
    private Paint mCPaint;

    private MyPoint a, h;
    private Map<String, MyPoint> points;

    private Path path;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;

    private int viewWidth = 450;
    private int viewHeight = 600;

    public Learn(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mAPaint = new Paint();
        mAPaint.setColor(Color.GREEN);
        mAPaint.setAntiAlias(true);//设置抗锯齿

        mBPaint = new Paint();
        mBPaint.setColor(Color.RED);
        mBPaint.setAntiAlias(true);//设置抗锯齿

        mCPaint = new Paint();
        mCPaint.setColor(Color.YELLOW);
        mCPaint.setAntiAlias(true);//设置抗锯齿

        a = new MyPoint(160, 400);
        h = new MyPoint(viewWidth, viewHeight);

        points = new CalcPoints(a, h).calcuation();


        path = new Path();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = getPathC();

        bitmap1 = Bitmap.createBitmap((int) viewWidth, (int) viewHeight, Bitmap.Config.ARGB_8888);
        Canvas bitmapCanvas1 = new Canvas(bitmap1);
        bitmapCanvas1.drawPath(getPathA(), mAPaint); // GREEN  A
        canvas.drawBitmap(bitmap1,0,0,null);

        bitmap2 = Bitmap.createBitmap((int) viewWidth, (int) viewHeight, Bitmap.Config.ARGB_8888);
        Canvas bitmapCanvas2 = new Canvas(bitmap2);
        bitmapCanvas2.drawPath(getPathB(), mBPaint); // RED  B
        canvas.clipPath(getPathB(), Region.Op.INTERSECT); // 指定裁剪区域为B+C
        canvas.drawBitmap(bitmap2,0,0,null);

        bitmap3 = Bitmap.createBitmap((int) viewWidth, (int) viewHeight, Bitmap.Config.ARGB_8888);
        Canvas bitmapCanvas3 = new Canvas(bitmap3);
        bitmapCanvas3.drawPath(getPathC(), mCPaint); // YELLOW  C
        canvas.clipPath(getPathC(), Region.Op.INTERSECT); // 指定裁剪区域为C，取和上次的裁剪区域的交集
        canvas.drawBitmap(bitmap3,0,0,null);
    }

    private Path getPathA(){
        path.reset();
        path.lineTo(0, viewHeight);
        path.lineTo(points.get("b").x,points.get("b").y);
        path.quadTo(points.get("c").x,points.get("c").y, points.get("d").x, points.get("d").y);
        path.lineTo(a.x, a.y);
        path.lineTo(points.get("e").x, points.get("e").y);
        path.quadTo(points.get("g").x,points.get("g").y, points.get("f").x, points.get("f").y);
        path.lineTo(viewWidth,0);
        path.close();
        return path;
    }


    /**
     * 绘制默认的界面
     * @return
     */
    private Path getPathDefault(){
        path.reset();
        path.lineTo(0, viewHeight);
        path.lineTo(viewWidth,viewHeight);
        path.lineTo(viewWidth,0);
        path.close();
        return path;
    }

    private Path getPathB(){
        path.reset();
        path.lineTo(0, viewHeight);
        path.lineTo(points.get("b").x,points.get("b").y);
        path.quadTo(points.get("c").x,points.get("c").y, points.get("d").x, points.get("d").y);
        path.lineTo(a.x,a.y);
        path.lineTo(points.get("e").x, points.get("e").y);
        path.quadTo(points.get("g").x,points.get("g").y, points.get("f").x, points.get("f").y);
        path.lineTo(viewWidth,viewHeight);
        path.lineTo(0, viewHeight);
        path.close();
        return path;
    }

    /**
     * 绘制区域C
     * @return
     */
    private Path getPathC(){
        path.reset();
        path.moveTo(points.get("i").x, points.get("i").y);
        path.lineTo(points.get("d").x, points.get("d").y);
        path.lineTo(a.x,a.y);
        path.lineTo(points.get("e").x, points.get("e").y);
        path.lineTo(points.get("j").x, points.get("j").y);
        path.close();
        return path;
    }

}
