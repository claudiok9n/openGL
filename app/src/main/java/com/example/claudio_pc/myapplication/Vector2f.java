package com.example.claudio_pc.myapplication;

public class Vector2f {
    public float x, y;

    public Vector2f()
    {
        setTo(0, 0);
    }

    public Vector2f(float x, float y)
    {
        setTo(x, y);
    }

    public void setTo(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float lengthSquared()
    {
        return x * x + y * y;
    }

    public float length()
    {
        return (float) Math.sqrt(lengthSquared());
    }

    public Vector2f add(float x, float y)
    {
        return new Vector2f(this.x + x, this.y + y);
    }

    public Vector2f addAndSet(float x, float y)
    {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2f negate()
    {
        x *= -1;
        y *= -1;
        return this;
    }

    public Vector2f normalize()
    {
        float l = length();
        return new Vector2f(x / l, y / l);
    }

    public float dotProduct(Vector2f v)
    {
        return x * v.x + y * v.y;
    }

    public float angleBetween(Vector2f v)
    {
        float dls = dotProduct(v) / (length() * v.length());
        if (dls < -1f)
            dls = -1f;
        else if (dls > 1.0f)
            dls = 1.0f;
        return (float)Math.acos(dls);
    }

    public Vector2f scale(float scale)
    {
        return new Vector2f(x * scale, y * scale);
    }

    public Vector2f scaleAndSet(float scale)
    {
        x *= scale;
        y *= scale;
        return this;
    }
}
