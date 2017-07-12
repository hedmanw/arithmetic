package se.wilhelmhedman.androidarithmetic.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import se.wilhelmhedman.androidarithmetic.R;


public class StableArrayAdapter<T> extends ArrayAdapter<T> {
    private List<T> items;

    public StableArrayAdapter(Context context, int layoutResource, List<T> items) {
        super(context, layoutResource, items);
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item_history, parent, false);
        }
        TextView tv = convertView.findViewById(R.id.textView_history);
        T item = items.get(position);
        tv.setText(item != null ? item.toString() : "");
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public T getItemByIndex(int index) {
        return items.get(index);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}