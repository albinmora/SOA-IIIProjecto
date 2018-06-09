package com.example.albin.sportec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.albin.sportec.Model.Sport;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private LayoutInflater mLayoutinflater;
    private List<Sport> mListStorage;
    private Context mContext;

    public GridViewAdapter(Context context, List<Sport> customizedListView) {
        this.mContext = context;
        mLayoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mListStorage = customizedListView;
    }

    @Override
    public int getCount() {
        return mListStorage.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder listViewHolder;
        if (convertView == null) {
            listViewHolder = new ViewHolder();
            convertView = mLayoutinflater.inflate(R.layout.grid_item, parent, false);
            listViewHolder.textInListView = (TextView) convertView.findViewById(R.id.textView);
            listViewHolder.imageInListView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (ViewHolder) convertView.getTag();
        }

        listViewHolder.textInListView.setText(mListStorage.get(position).getSport());
        String imageName = mListStorage.get(position).getSport();
        int imageResourceId = this.mContext.getResources().getIdentifier(imageName, "drawable", this.mContext.getPackageName());
        listViewHolder.imageInListView.setImageResource(imageResourceId);

        return convertView;
    }

    static class ViewHolder {
        TextView textInListView;
        ImageView imageInListView;
    }
}
