package com.nk.multipleitemselectionrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Test_code";

    private Button show_selected_items_btn, delete_selected_items_btn;
    private RecyclerView recycle_view_list;
    private ItemRecycleViewAdapter itemRecycleViewAdapter;
    List<ItemModel> listItems = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setReferences();

        createListItems(10);

        itemRecycleViewAdapter = new ItemRecycleViewAdapter(this, listItems);
        recycle_view_list.setAdapter(itemRecycleViewAdapter);

        show_selected_items_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemRecycleViewAdapter.getSelected().size() > 0){
                    StringBuilder stringBuilder = new StringBuilder();

                    for (int i = 0; i < itemRecycleViewAdapter.getSelected().size(); i++){
                        stringBuilder.append(itemRecycleViewAdapter.getSelected().get(i).getItemName());
                        stringBuilder.append("\n");
                    }

                    Toast.makeText(getBaseContext(), stringBuilder.toString().trim(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "No selected items", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete_selected_items_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemRecycleViewAdapter.getSelected().size() > 0){
                    StringBuilder stringBuilder = new StringBuilder();

                    while (itemRecycleViewAdapter.getSelected().size() > 0){
                        int index = listItems.indexOf(itemRecycleViewAdapter.getSelected().get(0));

                        stringBuilder.append(itemRecycleViewAdapter.getSelected().get(0).getItemName());
                        stringBuilder.append("\n");

                        listItems.remove(index);

                        Log.d(TAG, "onClick: size list: " + listItems.size() + " size selected list: " + itemRecycleViewAdapter.getSelected().size());
                    }

                    Log.d(TAG, "onClick: Sending list items: " + listItems + "\nNum items: " + listItems.size());
//                    itemRecycleViewAdapter.setItems(listItems); // in this method not have reason, because in adapter list with same source address
                    itemRecycleViewAdapter.notifyDataSetChanged();

                    stringBuilder.append("Items Deleted");

                    Toast.makeText(getBaseContext(), stringBuilder.toString().trim(), Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(getBaseContext(), "No item for delete!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setReferences(){
        show_selected_items_btn = findViewById(R.id.show_selected_items_btn);
        delete_selected_items_btn = findViewById(R.id.delete_selected_items_btn);
        recycle_view_list = findViewById(R.id.recycle_view_list);
    }

    private void createListItems(int size){
        for(int x = 0; x < size; x++){
            ItemModel item = new ItemModel("Item " + x, false);

            if (x == 0){
                item.setChecked(true);
            }

            listItems.add(item);
        }
    }
}