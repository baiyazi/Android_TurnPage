package com.example.utils;

import android.util.Log;
import com.example.pojo.MyPoint;
import java.util.HashMap;
import java.util.Map;

public class CalcPoints {

    // 传入数据
    private MyPoint a, h;
    // 目标
    private MyPoint b, c, d, e, f, g, i, j;

    private Map<String, MyPoint> points;

    public Map<String, MyPoint> calcuation(){
        MyPoint s = new MyPoint((a.x + h.x) / 2, (a.y + h.y) / 2);
        c.x = s.x - (h.y - s.y) * (h.y - s.y) / (h.x - s.x);
        c.y = h.y;
        g.x = h.x;
        g.y = s.y - (h.x - s.x) * (h.x - s.x) / (h.y - s.y);
        b.x = 0.5f * (3*s.x - h.x - 3*(h.y - s.y)*(h.y - s.y) / (h.x - s.x));
        if(b.x <= 0){
            return null;
        }
        b.y = h.y;
        f.x = h.x;
        f.y = 0.5f * (3*s.y - h.y - 3 * (h.x - s.x) * (h.x - s.x) / (h.y - s.y));

        float k_1 = (c.y - a.y) / (c.x - a.x);
        float b_1 = a.y - k_1 * a.x;

        float k_2 = (g.y - a.y) / (g.x - a.x);
        float b_2 = a.y - k_2 * a.x;

        float k_3 = (b.y - f.y) / (b.x - f.x);
        float b_3 = f.y - k_3 * f.x;

        Log.e("k_3", String.valueOf(k_3));
        Log.e("k_1", String.valueOf(k_1));
        d.x = (b_1 - b_3) / (k_3 - k_1);
        d.y = k_1*d.x+b_1;

        e.x = (b_2 - b_3) / (k_3 - k_2);
        e.y = k_2 * e.x + b_2;

        float k_4 = -1.0f / k_3;
        float b_4 = c.y - k_4 * c.x;
        MyPoint p_1 = new MyPoint();
        p_1.x = (b_3 - b_4) / (k_4 - k_3);
        p_1.y = k_3 * p_1.x + b_3;

        i.x = (p_1.x + c.x) / 2;
        i.y = (p_1.y + c.y) / 2;

        float k_5 = -1.0f / k_3;
        float b_5 = g.y - k_5 * g.x;
        MyPoint p_2 = new MyPoint();
        p_2.x = (b_3 - b_5) / (k_5 - k_3);
        p_2.y = k_3 * p_2.x + b_3;

        j.x = (p_2.x + g.x) / 2;
        j.y = (p_2.y + g.y) / 2;


        points.put("b", b);
        points.put("c", c);
        points.put("d", d);
        points.put("e", e);
        points.put("f", f);
        points.put("g", g);
        points.put("i", i);
        points.put("j", j);


        for (String ss : points.keySet()) {
            Log.e(ss, points.get(ss).toString());
        }

        return points;
    }

    public CalcPoints(MyPoint a, MyPoint h){
        this.h = h;
        this.a = a;
        b = new MyPoint();
        c = new MyPoint();
        d = new MyPoint();
        e = new MyPoint();
        f = new MyPoint();
        g = new MyPoint();
        i = new MyPoint();
        j = new MyPoint();
        this.points = new HashMap<>();
    }
}
