package com.whiteout.pantrytracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.whiteout.pantrytracker.R;
import com.whiteout.pantrytracker.data.model.Item;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Author:  Kendrick Cline
 * Date:    12/2/14
 * Email:   kdecline@gmail.com
 */
public class ItemListAdapter extends ArrayAdapter<Item> {

    private Context mContext;
    private List<Item> mItemsArray;
    private LayoutInflater inflater;

    public ItemListAdapter(Context context, int rId, ArrayList<Item> items) {
        super(context, rId);
        mContext = context;
        mItemsArray = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.item_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        Item currItem = getItem(position);

        holder.name.setText(currItem.getName());
        holder.quantity.setText(currItem.getQuantity().toString());
        if (currItem.getUnit() != null)
            holder.unit.setText(currItem.getUnit());

        if (currItem.getExpiration() != null) {
            Timestamp ts = new Timestamp(currItem.getExpiration());
            holder.expiration.setText(ts.toString());
        }
        holder.increment.setOnClickListener(incrementClickListener);
        holder.decrement.setOnClickListener(decrementClickListener);

        return convertView;
    }

    private View.OnClickListener incrementClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO: increment quantity
        }
    };

    private View.OnClickListener decrementClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO: decrement quantity
        }
    };

    @Override
    public int getCount() {
        return mItemsArray.size();
    }

    public void deletePosition(int position) {
        mItemsArray.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public Item getItem(int position) {
        return mItemsArray.get(position);
    }

    @Override
    public void add(Item object) {
        mItemsArray.add(object);
        notifyDataSetChanged();
    }

    public void edit(int position, Item object) {
        mItemsArray.set(position, object);
        notifyDataSetChanged();
    }

    @Override
    public void remove(Item object) {
        mItemsArray.remove(object);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @InjectView(R.id.item_name) TextView name;
        @InjectView(R.id.item_quantity) EditText quantity;
        @InjectView(R.id.item_unit) TextView unit;
        @InjectView(R.id.increment_button) Button increment;
        @InjectView(R.id.decrement_button) Button decrement;
        @InjectView(R.id.item_expiration) TextView expiration;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
