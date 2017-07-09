package se.wilhelmhedman.androidarithmetic;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import se.wilhelmhedman.androidarithmetic.calc.CalculationRepository;
import se.wilhelmhedman.androidarithmetic.calc.IArithmeticQuery;

public class History extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        final ListView viewContainer = (ListView) findViewById(android.R.id.list);
        final List<IArithmeticQuery> list = CalculationRepository.getHistory();
        final ArrayAdapter<IArithmeticQuery> adapter = new StableArrayAdapter<>(this, R.layout.list_item_history, list);
        viewContainer.setAdapter(adapter);
    }

    private class StableArrayAdapter<T> extends ArrayAdapter<T> {
        private HashMap<T, Integer> itemToId = new HashMap<>();

        public StableArrayAdapter(Context context, int layoutResource, List<T> objects) {
            super(context, layoutResource, objects);
            for (int i = 0; i < objects.size(); ++i) {
                itemToId.put(objects.get(i), i);
            }
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_history, parent, false);
            }
            TextView tv = convertView.findViewById(R.id.textView_history);
            T item = getItem(position);
            tv.setText(item != null ? item.toString() : "");
            return convertView;
        }

        @Override
        public long getItemId(int position) {
            T item = getItem(position);
            return itemToId.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
