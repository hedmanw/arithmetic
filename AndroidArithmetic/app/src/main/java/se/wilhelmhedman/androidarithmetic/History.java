package se.wilhelmhedman.androidarithmetic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import se.wilhelmhedman.androidarithmetic.calc.CalculationRepository;
import se.wilhelmhedman.androidarithmetic.calc.IArithmeticQuery;
import se.wilhelmhedman.androidarithmetic.widget.StableArrayAdapter;

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
}
