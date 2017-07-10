package se.wilhelmhedman.androidarithmetic.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import se.wilhelmhedman.androidarithmetic.R;

public class CalculatorHistoryView extends ViewGroup {
    private final Rect childPlacement = new Rect();

    public CalculatorHistoryView(Context context) {
        super(context);
    }

    public CalculatorHistoryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CalculatorHistoryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int count = getChildCount();

        int maxWidth = getSuggestedMinimumWidth();
        int maxHeight = getSuggestedMinimumHeight();
        int childState = 0;

        for (int i = count - 1; i >= 0; i--) {
            final View child = getChildAt(i);

            if (child.getVisibility() != GONE) {
                measureChild(child, widthMeasureSpec, heightMeasureSpec);

                childState = combineMeasuredStates(childState, child.getMeasuredState());
            }
        }

        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState),
                resolveSizeAndState(maxHeight, heightMeasureSpec, childState << MEASURED_HEIGHT_STATE_SHIFT));

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();

        // These are the far left and right edges in which we are performing layout.
        final int leftPos = getPaddingLeft();
        final int rightPos = right - left - getPaddingRight();

        // These are the top and bottom edges in which we are performing layout.
        final int topPos = getPaddingTop();
        final int bottomPos = bottom - top - getPaddingBottom();

        int bottomPlacement = bottomPos;
        Log.d("ARITH", "My dimensions: " + "L=" + left + "T=" + top + "R=" + right + "B=" + bottom);
        for (int i = count - 1; i >= 0; i--) {
            final View child = getChildAt(i);

            if (child.getVisibility() != GONE) {
                // TODO: these have something to do with margins
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();
                final int width = child.getMeasuredWidth();
                final int height = child.getMeasuredHeight();

                childPlacement.left = leftPos;
                childPlacement.right = rightPos;

                final int topPlacement = bottomPlacement - child.getMeasuredHeight();
                childPlacement.top = topPlacement;
                childPlacement.bottom = bottomPlacement;
                bottomPlacement = topPlacement;
                Log.d("ARITH", "Child(" + i + "):" + childPlacement.toString());
                // Place the child.
                child.layout(childPlacement.left, childPlacement.top, childPlacement.right, childPlacement.bottom);
            }
        }

    }

    public void addHistoryEntry(String content) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutContainer = layoutInflater.inflate(R.layout.list_item_history, this, false);
        TextView tv = layoutContainer.findViewById(R.id.textView_history);
        tv.setText(content);
        addView(layoutContainer);
    }
}
