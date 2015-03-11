package com.example.convocrypt1;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;


public class StatusAdapter extends ArrayAdapter<ParseObject> {
    protected Context mContext;
    protected List<ParseObject> mStatus;

    public StatusAdapter(Context context, List<ParseObject> status) {
        super(context, R.layout.homepage_customlayout, status);
        mContext = context;
        mStatus = status;
    }

    @Override
    public int getCount() {
        return mStatus.size();
    }

    @Override
    public ParseObject getItem(int position) {
        return mStatus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.homepage_customlayout, null);
            holder = new ViewHolder();
            holder.usernameHomepage = (TextView) convertView
                    .findViewById(R.id.usernameHP);
            holder.statusHomepage = (TextView) convertView
                    .findViewById(R.id.statusHP);

            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        ParseObject statusObject = mStatus.get(position);

        // title
        String username = statusObject.getString("user");
        holder.usernameHomepage.setText(username);

        // content
        String status = statusObject.getString("newStatus");
        holder.statusHomepage.setText(status);



        /**
         * The following list not implemented reusable list items as list items
         * are showing incorrect data Add the solution if you have one
         * *

        Message m = mStatus.get(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        // Identifying the message owner
        if (mStatus.get(position).isSelf()) {
            // message belongs to you, so load the right aligned layout
            convertView = mInflater.inflate(R.layout.list_message_right,
                    null);
        } else {
            // message belongs to other person, load the left aligned layout
            convertView = mInflater.inflate(R.layout.list_message_right,
                    null);
        }

        TextView lblFrom = (TextView) convertView.findViewById(R.id.lblMsgFrom);
        TextView txtMsg = (TextView) convertView.findViewById(R.id.txtMsg);

        txtMsg.setText(m.getMessage());
        lblFrom.setText(m.getFromName());

        */


        return convertView;
    }

    public static class ViewHolder {
        TextView usernameHomepage;
        TextView statusHomepage;

    }

}