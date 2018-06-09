package com.example.albin.sportec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.albin.sportec.Model.History;

import java.util.List;

public class HistoryListAdapter extends ArrayAdapter<History> {

    private List<History> hist;

    public HistoryListAdapter(Context context, int resource, List<History> objects) {
        super(context, resource, objects);
        hist = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.activity_history_item, parent, false);
        }
        History histN = hist.get(position);
        TextView nameText = (TextView) convertView.findViewById(R.id.txtDate);
        nameText.setText(histN.getDate());
        TextView priceText = (TextView) convertView.findViewById(R.id.txtHistoryDescription);
        priceText.setText(histN.getDescripcion());

        return convertView;
    }


}
