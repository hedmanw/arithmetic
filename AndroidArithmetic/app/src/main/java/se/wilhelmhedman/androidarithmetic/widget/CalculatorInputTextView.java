package se.wilhelmhedman.androidarithmetic.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


public class CalculatorInputTextView extends android.support.v7.widget.AppCompatTextView {
    private final Paint caretPaint;
    private int lineHeight;
    private float charWidth;
    private int caretIndex;
    private int charsPerLine = 1;
    private String debugStatement = "";

    public CalculatorInputTextView(Context context) {
        super(context);
        caretPaint = new Paint();
        init();
    }

    public CalculatorInputTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        caretPaint = new Paint();
        init();
    }

    public CalculatorInputTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        caretPaint = new Paint();
        init();
    }

    private void init() {
        caretPaint.setColor(Color.GRAY);
        caretPaint.setStrokeWidth(3f);
        lineHeight = getLineHeight();
        Paint textPaint = getPaint();
        charWidth = textPaint.measureText("1");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        charsPerLine = (int) Math.floor(getWidth()/charWidth);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN ||
            action == MotionEvent.ACTION_MOVE) {
                float x = event.getX();
                float y = event.getY();
            updateCaretIndex(x, y);
            invalidate();
        }
        return true;
    }

    private int textLength() {
        return getText().length();
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
        caretIndex = optimisticIndex;
        debugStatement = "L=" + pressedLine + ",C=" + pressedColumn + ",API=" + actualPressedIndex + ",LCPL=" + optimisticIndex + ",CPL=" + charsPerLine;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (textLength() > 0) {
            float x = (caretIndex%charsPerLine)*charWidth;
            int y = (caretIndex/charsPerLine) * lineHeight + 4;

            canvas.drawLine(x, y, x, y+lineHeight, caretPaint);
        }

        canvas.drawText(debugStatement, 2, 10, caretPaint);
    }

    public void addText(CharSequence string) {
        CharSequence text = getText();
        String newText = text.subSequence(0, caretIndex).toString() + string + text.subSequence(caretIndex, text.length()).toString();
        setText(newText);
        caretIndex += string.length();
    }

    public void delete() {
        if (textLength() > 0) {
            CharSequence text = getText();
            if (caretIndex == 0) {
                setText(text.subSequence(1, text.length()));
            }
            else {
                String newText = text.subSequence(0, caretIndex-1).toString() + text.subSequence(caretIndex, textLength()).toString();
                setText(newText);
                caretIndex -= 1;
            }
        }
    }

    public void clear() {
        caretIndex = 0;
        setText("");
    }
}
