package com.nk.multipleitemselectionrecyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Naftali
 * @Date: 08.12.2021 13:23
 */
public class ItemRecycleViewAdapter extends RecyclerView.Adapter<ItemRecycleViewAdapter.ItemRecycleViewHolder> {

    private static final String TAG = "Test_code";

    private Context context;
    private List<ItemModel> items; //it's to be same address of list, like in MainActivity

    public ItemRecycleViewAdapter(Context context, List<ItemModel> items) {
        this.context = context;
        this.items = items;
    }

    public void setItems(List<ItemModel> inputItems) {
        Log.d(TAG, "Adapter setItems: " + inputItems.toString());
//        this.items.clear(); // was delete reference of main list.
        this.items = new ArrayList<>();
        this.items.addAll(inputItems);
        this.items = inputItems;
        notifyDataSetChanged(); //update recycleView
    }


    @NonNull
    @Override
    public ItemRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item, parent, false);
        return new ItemRecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRecycleViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }




    class ItemRecycleViewHolder extends RecyclerView.ViewHolder {

        private TextView item_name_tv;
        private ImageView checked_iv;

        public ItemRecycleViewHolder(@NonNull View itemView) {
            super(itemView);

            item_name_tv = itemView.findViewById(R.id.item_name_tv);
            checked_iv = itemView.findViewById(R.id.checked_iv);
        }

        // set settings to one item of recycleView
        void bind(final ItemModel itemModel){
            checked_iv.setVisibility(itemModel.isChecked()? View.VISIBLE: View.GONE);
            item_name_tv.setText(itemModel.getItemName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemModel.setChecked(!itemModel.isChecked());
                    checked_iv.setVisibility(itemModel.isChecked()? View.VISIBLE : View.GONE);
                }
            });
        }
    }



    // get selected items
    public List<ItemModel> getSelected(){

        List<ItemModel> selectedItems = new ArrayList<>();

        for(int i = 0; i < items.size(); i++){
            if(items.get(i).isChecked()){
                selectedItems.add(items.get(i));
            }
        }

        return selectedItems;
    }
}
