package com.treeze.data;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;



/**
 * A cubic parametric curve segment specified with
 * {@code float} coordinates.
 * @since 1.2
 */
public class Float extends CubicCurve2D implements Serializable {
/**
 * The X coordinate of the start point
 * of the cubic curve segment.
     * @since 1.2
     * @serial
 */
public float x1;

/**
 * The Y coordinate of the start point
 * of the cubic curve segment.
     * @since 1.2
     * @serial
 */
public float y1;

/**
 * The X coordinate of the first control point
 * of the cubic curve segment.
     * @since 1.2
     * @serial
 */
public float ctrlx1;

/**
 * The Y coordinate of the first control point
 * of the cubic curve segment.
     * @since 1.2
     * @serial
 */
public float ctrly1;

/**
 * The X coordinate of the second control point
 * of the cubic curve segment.
     * @since 1.2
     * @serial
 */
public float ctrlx2;

/**
 * The Y coordinate of the second control point
 * of the cubic curve segment.
     * @since 1.2
     * @serial
 */
public float ctrly2;

/**
 * The X coordinate of the end point
 * of the cubic curve segment.
     * @since 1.2
     * @serial
 */
public float x2;

/**
 * The Y coordinate of the end point
 * of the cubic curve segment.
     * @since 1.2
     * @serial
 */
public float y2;

/**
 * Constructs and initializes a CubicCurve with coordinates
 * (0, 0, 0, 0, 0, 0).
     * @since 1.2
 */
public Float() {
}

/**
     * Constructs and initializes a {@code CubicCurve2D} from
     * the specified {@code float} coordinates.
     *
     * @param x1 the X coordinate for the start point
     *           of the resulting {@code CubicCurve2D}
     * @param y1 the Y coordinate for the start point
     *           of the resulting {@code CubicCurve2D}
     * @param ctrlx1 the X coordinate for the first control point
     *               of the resulting {@code CubicCurve2D}
     * @param ctrly1 the Y coordinate for the first control point
     *               of the resulting {@code CubicCurve2D}
     * @param ctrlx2 the X coordinate for the second control point
     *               of the resulting {@code CubicCurve2D}
     * @param ctrly2 the Y coordinate for the second control point
     *               of the resulting {@code CubicCurve2D}
     * @param x2 the X coordinate for the end point
     *           of the resulting {@code CubicCurve2D}
     * @param y2 the Y coordinate for the end point
     *           of the resulting {@code CubicCurve2D}
     * @since 1.2
 */
public Float(float x1, float y1,
                 float ctrlx1, float ctrly1,
                 float ctrlx2, float ctrly2,
                 float x2, float y2)
    {
    setCurve(x1, y1, ctrlx1, ctrly1, ctrlx2, ctrly2, x2, y2);
}

/**
     * {@inheritDoc}
     * @since 1.2
 */
public double getX1() {
    return (double) x1;
}

/**
     * {@inheritDoc}
     * @since 1.2
 */
public double getY1() {
    return (double) y1;
}

/**
     * {@inheritDoc}
     * @since 1.2
 */
public Point2D getP1() {
    return new Point2D.Float(x1, y1);
}

/**
     * {@inheritDoc}
     * @since 1.2
 */
public double getCtrlX1() {
    return (double) ctrlx1;
}

/**
     * {@inheritDoc}
     * @since 1.2
 */
public double getCtrlY1() {
    return (double) ctrly1;
}

/**
     * {@inheritDoc}
     * @since 1.2
 */
public Point2D getCtrlP1() {
    return new Point2D.Float(ctrlx1, ctrly1);
}

/**
     * {@inheritDoc}
     * @since 1.2
 */
public double getCtrlX2() {
    return (double) ctrlx2;
}

/**
     * {@inheritDoc}
     * @since 1.2
 */
public double getCtrlY2() {
    return (double) ctrly2;
}

/**
     * {@inheritDoc}
     * @since 1.2
 */
public Point2D getCtrlP2() {
    return new Point2D.Float(ctrlx2, ctrly2);
}

/**
     * {@inheritDoc}
     * @since 1.2
 */
public double getX2() {
    return (double) x2;
}

/**
     * {@inheritDoc}
     * @since 1.2
 */
public double getY2() {
    return (double) y2;
}

/**
     * {@inheritDoc}
     * @since 1.2
 */
public Point2D getP2() {
    return new Point2D.Float(x2, y2);
}

/**
     * {@inheritDoc}
     * @since 1.2
 */
public void setCurve(double x1, double y1,
		     double ctrlx1, double ctrly1,
		     double ctrlx2, double ctrly2,
                         double x2, double y2)
    {
    this.x1     = (float) x1;
    this.y1     = (float) y1;
    this.ctrlx1 = (float) ctrlx1;
    this.ctrly1 = (float) ctrly1;
    this.ctrlx2 = (float) ctrlx2;
    this.ctrly2 = (float) ctrly2;
    this.x2     = (float) x2;
    this.y2     = (float) y2;
}

/**
     * Sets the location of the end points and control points
     * of this curve to the specified {@code float} coordinates.
     *
     * @param x1 the X coordinate used to set the start point
     *           of this {@code CubicCurve2D}
     * @param y1 the Y coordinate used to set the start point
     *           of this {@code CubicCurve2D}
     * @param ctrlx1 the X coordinate used to set the first control point
     *               of this {@code CubicCurve2D}
     * @param ctrly1 the Y coordinate used to set the first control point
     *               of this {@code CubicCurve2D}
     * @param ctrlx2 the X coordinate used to set the second control point
     *               of this {@code CubicCurve2D}
     * @param ctrly2 the Y coordinate used to set the second control point
     *               of this {@code CubicCurve2D}
     * @param x2 the X coordinate used to set the end point
     *           of this {@code CubicCurve2D}
     * @param y2 the Y coordinate used to set the end point
     *           of this {@code CubicCurve2D}
     * @since 1.2
 */
public void setCurve(float x1, float y1,
		     float ctrlx1, float ctrly1,
		     float ctrlx2, float ctrly2,
                         float x2, float y2)
    {
    this.x1     = x1;
    this.y1     = y1;
    this.ctrlx1 = ctrlx1;
    this.ctrly1 = ctrly1;
    this.ctrlx2 = ctrlx2;
    this.ctrly2 = ctrly2;
    this.x2     = x2;
    this.y2     = y2;
}

/**
     * {@inheritDoc}
     * @since 1.2
 */
public Rectangle2D getBounds2D() {
    float left   = Math.min(Math.min(x1, x2),
			    Math.min(ctrlx1, ctrlx2));
    float top    = Math.min(Math.min(y1, y2),
			    Math.min(ctrly1, ctrly2));
    float right  = Math.max(Math.max(x1, x2),
			    Math.max(ctrlx1, ctrlx2));
    float bottom = Math.max(Math.max(y1, y2),
			    Math.max(ctrly1, ctrly2));
    return new Rectangle2D.Float(left, top,
				 right - left, bottom - top);
}

    /*
     * JDK 1.6 serialVersionUID
     */
    private static final long serialVersionUID = -1272015596714244385L;
}

