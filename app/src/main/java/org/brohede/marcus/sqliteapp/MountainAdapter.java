package org.brohede.marcus.sqliteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MountainAdapter extends ArrayAdapter {
    private Context context;
    private List<Mountain> mountainList;
    private int resource;
    private LayoutInflater inflater;

    public MountainAdapter(Context context, int resource, List<Mountain> objects) {
        super(context, resource, objects);
        this.context = context;
        mountainList = objects;
        this.resource = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(resource, parent, false);
        }

        Mountain m = mountainList.get(position);

        ImageView image = convertView.findViewById(R.id.item_image);
        TextView name = convertView.findViewById(R.id.item_name);
        TextView height = convertView.findViewById(R.id.item_height);
        TextView location = convertView.findViewById(R.id.item_location);

        new DownloadImage(image).execute(m.getImgURL());
        name.setText(m.getName());
        height.setText("Height: " + m.getHeight() + "m");
        location.setText("Location: " + m.getLocation());

        return convertView;
    }
}