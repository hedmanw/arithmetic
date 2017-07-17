package se.wilhelmhedman.androidarithmetic.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;

import se.wilhelmhedman.androidarithmetic.R;


public class CalculatorInputTextView extends android.support.v7.widget.AppCompatTextView {
    public static final float STROKE_WIDTH = 3f;
    private static final float[] CHEVRON = new float[] { // (x0, y0), (x1, y1)... see Canvas.drawLines
            -5, 2, 1, -7, 1, -7, 7, 2
    };
    private final Paint caretPaint;
    private final Paint errorPaint;
    private int lineHeight;
    private float charWidth;
    private int caretIndex;
    private int charsPerLine = 1;
    private String debugStatement = "";
    private boolean showError = false;

    public CalculatorInputTextView(Context context) {
        super(context);
        caretPaint = new Paint();
        errorPaint = new Paint();
        init();
    }

    public CalculatorInputTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        caretPaint = new Paint();
        errorPaint = new Paint();
        init();
    }

    public CalculatorInputTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        caretPaint = new Paint();
        errorPaint = new Paint();
        init();
    }

    private void init() {
        caretPaint.setColor(Color.GRAY);
        caretPaint.setStrokeWidth(STROKE_WIDTH);
        lineHeight = getLineHeight();
        Paint textPaint = getPaint();
        charWidth = textPaint.measureText("1");
        errorPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorError));
        errorPaint.setStrokeWidth(STROKE_WIDTH);
        errorPaint.setAntiAlias(true);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // TODO: correct charsPerLine w.r.t. padding
        int adjustedWidth = getWidth() - getPaddingStart() - getPaddingEnd();
        charsPerLine = (int) (adjustedWidth/charWidth);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN ||
            action == MotionEvent.ACTION_MOVE) {
            float x = event.getX();
            float y = event.getY();
            updateCaretIndex(x, y);
            clearEvaluationError();
            invalidate();
        }
        return true;
    }

    private static float[] getErrorChevrons(float x, int y) {
        float[] adjustedChevron = new float[CHEVRON.length];
        for (int i = 0; i < adjustedChevron.length; i++) {
            if (i % 2 == 0) {
                adjustedChevron[i] = CHEVRON[i] + x;
            }
            else {
                adjustedChevron[i] = CHEVRON[i] + y;
            }
        }
        return adjustedChevron;
    }

    private int textLength() {
        return getText().length();
    }

    private void setCaretIndex(int index) {
        caretIndex = index;
//        debugStatement = "CI=" + caretIndex + ",CPL=" + charsPerLine;
    }

    private void updateCaretIndex(float x, float y) {
        int pressedLine = (int) (y / lineHeight);
        int pressedColumn = (int) (x / charWidth);
        int actualPressedIndex = pressedColumn + pressedLine*charsPerLine;
        int optimisticIndex;
        // Optimistic index: (optimistic w.r.t usability)
        // Single line: place cursor at any legal position left of EOL, right of EOL results in EOL
        // Double line: place cursor at position, right of EOL results in EOL
        if (textLength() <= charsPerLine) {
            optimisticIndex = Math.min(actualPressedIndex % charsPerLine, textLength());
        }
        else {
            optimisticIndex = Math.min(actualPressedIndex, textLength());
        }
        setCaretIndex(Math.max(0, optimisticIndex));
//        debugStatement = "L=" + pressedLine + ",C=" + pressedColumn + ",API=" + actualPressedIndex + ",LCPL=" + optimisticIndex + ",CPL=" + charsPerLine;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (textLength() > 0) {
            float x = (caretIndex%charsPerLine)*charWidth + getPaddingStart();
            int y = (caretIndex/charsPerLine) * lineHeight + 4 + getPaddingTop();

            int stopY = y + lineHeight;
            canvas.drawLine(x, y, x, stopY, caretPaint);
            if (showError) {
                canvas.drawLines(getErrorChevrons(x, stopY + 4), errorPaint);
            }
        }

//        canvas.drawText(debugStatement, 2, 10, caretPaint);
    }

    public void addText(CharSequence string) {
        CharSequence text = getText();
        String newText = text.subSequence(0, caretIndex).toString() + string + text.subSequence(caretIndex, text.length()).toString();
        super.setText(newText);
        setCaretIndex(caretIndex + string.length());
    }

    public void delete() {
        if (textLength() > 0) {
            CharSequence text = getText();
            if (caretIndex == 0) {
                super.setText(text.subSequence(1, text.length()));
            }
            else {
                String newText = text.subSequence(0, caretIndex-1).toString() + text.subSequence(caretIndex, textLength()).toString();
                super.setText(newText);
                setCaretIndex(caretIndex - 1);
            }
        }
    }

    protected void setEvaluationError(int index) {
        setCaretIndex(index);
        showError = true;
        invalidate();
    }

    protected void clearEvaluationError() {
        showError = false;
        invalidate();
    }

    public void clear() {
        setText("");
    }

    public void setText(String string) {
        super.setText(string);
        setCaretIndex(string.length());
    }
}
