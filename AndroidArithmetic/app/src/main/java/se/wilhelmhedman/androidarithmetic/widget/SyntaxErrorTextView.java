package se.wilhelmhedman.androidarithmetic.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import se.wilhelmhedman.androidarithmetic.R;

public class SyntaxErrorTextView extends AppCompatTextView {
    private boolean isShown = false;

    public SyntaxErrorTextView(Context context) {
        super(context);
    }

    public SyntaxErrorTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SyntaxErrorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void show(String message) {
        if (!isShown) {
            isShown = true;
            // Prepare the View for the animation
            setVisibility(VISIBLE);
            setAlpha(0.0f);
            // Start the animation
            animate().translationY(0).alpha(1.0f).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                }
            });

            setText(message);
        }
    }

    protected void hide() {
        if (isShown) {
            isShown = false;
            animate().translationY(30).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    setVisibility(View.GONE);
                }
            });
        }
    }
}
