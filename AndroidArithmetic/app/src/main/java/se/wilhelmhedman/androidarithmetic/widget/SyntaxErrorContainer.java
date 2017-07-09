package se.wilhelmhedman.androidarithmetic.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import se.wilhelmhedman.androidarithmetic.R;

public class SyntaxErrorContainer extends LinearLayoutCompat {
    private boolean isShown = false;

    public SyntaxErrorContainer(Context context) {
        super(context);
    }

    public SyntaxErrorContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SyntaxErrorContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void show(String message) {
        if (!isShown) {
            isShown = true;
//            // Prepare the View for the animation
//            setAlpha(0.0f);
//            // Start the animation
//            animate().translationY(-20).alpha(1.0f).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    setVisibility(View.VISIBLE);
//                }
//            });
//        }
//        TextView errorView = findViewById(R.id.textViewError);
//        errorView.setText(message);

            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layoutContainer = layoutInflater.inflate(R.layout.list_item_error_notification, null);
            TextView errorView = layoutContainer.findViewById(R.id.textViewError);
            errorView.setText(message);
            addView(layoutContainer);
        }
    }

    protected void hide() {
        if (isShown) {
            isShown = false;
//            animate().translationY(0).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    super.onAnimationEnd(animation);
//                    setVisibility(View.GONE);
//                }
//            });
//        }
            removeViewAt(0);
        }
    }
}
