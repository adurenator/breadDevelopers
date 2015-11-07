package com.fastpay.suitux.fastpay;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by suitux on 06/11/2015.
 */
public class AccountItemAdapter extends BaseAdapter {

    /**
     * Inflater of the adapter
     */
    LayoutInflater inflater;

    /**
     * Levels of the adapter
     */
    List<AccountItem> items;

    /**
     * Context of the adapter
     */
    Activity context;

    /**
     * Default constructor
     * @param context	Context of the adapter
     * @param items		Items of the adapter
     */
    public AccountItemAdapter(Activity context, List<AccountItem> items) {
        super();
        this.context = context;
        this.items = items;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Get the count of the items
     */
    @Override
    public int getCount() {
        return items.size();
    }

    /**
     * Gets the item at the requested position
     */
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    /**
     * Get the Level id at the requested position
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Fills the View with the level information
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AccountItem item = items.get(position);
        View vi = convertView;

        if(convertView == null) {
            vi = inflater.inflate(R.layout.account_item, null);
        }



        //Views declaration
        TextView number = (TextView) vi.findViewById(R.id.accountNumber);
        TextView name = (TextView) vi.findViewById(R.id.accountName);
        //Views styles:

        number.setText(item.number);
        name.setText(item.name);
        return vi;
    }
}
