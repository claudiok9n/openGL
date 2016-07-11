package com.example.claudio_pc.myapplication;

public class Vector3f {
    public float x, y, z;

    public Vector3f()
    {
        setTo(0, 0, 0);
    }

    public Vector3f(float x, float y, float z)
    {
        setTo(x, y, z);
    }

    public void setTo(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float lengthSquared()
    {
        return x*x + y*y + z*z;
    }

    public float length()
    {
        return (float) Math.sqrt(lengthSquared());
    }

    public Vector3f add(Vector3f v)
    {
        return new Vector3f(x + v.x, y + v.y, z + v.z);
    }

    public Vector3f addAndSet(Vector3f v)
    {
        x += v.x;
        y += v.y;
        z += v.z;
        return this;
    }

    public Vector3f crossProduct(Vector3f v)
    {
        return new Vector3f(y * v.z - z * v.y,
                z * v.x - x * z,
                x * v.y - y * v.x
        );
    }

    public Vector3f negate()
    {
        x *= -1;
        y *= -1;
        z *= -1;
        return this;
    }

    public Vector3f normalize()
    {
        float l = length();

        return new Vector3f(x / l, y / l, z / l);
    }

    public float dotProduct(Vector3f v)
    {
        return x * v.x + y * v.y + z * v.z;
    }

    public float angleBetween(Vector3f v)
    {
        float dls = dotProduct(v) / (length() * v.length());
        if (dls < -1f)
            dls = -1f;
        else if (dls > 1.0f)
            dls = 1.0f;
        return (float)Math.acos(dls);
    }

    public Vector3f scale(float scale)
    {
        return new Vector3f(x * scale, y * scale, z * scale);
    }

    public Vector3f scaleAndSet(float scale)
    {
        x *= scale;
        y *= scale;
        z *= scale;
        return this;
    }


}