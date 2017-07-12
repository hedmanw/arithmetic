package se.wilhelmhedman.androidarithmetic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import se.wilhelmhedman.androidarithmetic.calc.CalculationRepository;
import se.wilhelmhedman.androidarithmetic.calc.IArithmeticQuery;
import se.wilhelmhedman.androidarithmetic.widget.StableArrayAdapter;

public class History extends AppCompatActivity {
    public static final int RESULT_ANS = 0x1;
    public static final int RESULT_REDO = 0x2;

    private ActionMode actionMode;
    private int selectedIndex = -1;

    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.history_menu, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_hist_ans:
                    mode.finish();
                    return true;
                case R.id.menu_hist_redo:
                    mode.finish();
                    actionRedo();
                    return true;
                case R.id.menu_hist_copy:
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };
    private ListView viewContainer;
    private StableArrayAdapter<IArithmeticQuery> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewContainer = (ListView) findViewById(android.R.id.list);
        final List<IArithmeticQuery> list = CalculationRepository.getHistory();
        adapter = new StableArrayAdapter<>(this, R.layout.list_item_history, list);
        viewContainer.setAdapter(adapter);

        viewContainer.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (actionMode == null) {
                    actionMode = startSupportActionMode(actionModeCallback);
                }

                if (selectedIndex > -1) {
                    viewContainer.getChildAt(selectedIndex).setSelected(false);
                }
                view.setSelected(true);
                selectedIndex = i;
                return true;
            }
        });

    }

    private void actionRedo() {
        IArithmeticQuery selectedItem = adapter.getItemByIndex(selectedIndex);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("content", selectedItem.getRequest());
        setResult(RESULT_REDO, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // animate and finish
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.history_menu, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
