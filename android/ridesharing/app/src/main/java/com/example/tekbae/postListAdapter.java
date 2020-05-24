package com.example.tekbae;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class postListAdapter extends BaseAdapter {
    private Context context;
    private List<Post> postList;

    public postListAdapter(Context context, List<Post>postList){
        this.context = context;
        this.postList = postList;
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object getItem(int i) {
        return postList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context,R.layout.post_info,null);
        TextView userID = (TextView)v.findViewById(R.id.userID);
        TextView userName = (TextView)v.findViewById(R.id.userName);
        TextView userAddress = (TextView)v.findViewById(R.id.userAddress);
        TextView userNumber = (TextView)v.findViewById(R.id.userNumber);
        TextView postInfo = (TextView)v.findViewById(R.id.postInfo);
        TextView distance = (TextView)v.findViewById(R.id.distance);
        final CheckBox postCheck = (CheckBox)v.findViewById(R.id.postCheck);

        userID.setText(postList.get(i).getUserID());
        userName.setText(postList.get(i).getUserName());
        userAddress.setText(postList.get(i).getUserAddress());
        userNumber.setText(postList.get(i).getUserNumber());
        postInfo.setText(postList.get(i).getPostInfo());
        distance.setText(postList.get(i).getDistance());
        postCheck.setChecked(postList.get(i).getPostCheck());



        postCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked){

                    Toast.makeText(context.getApplicationContext(), "배송 완료", Toast.LENGTH_SHORT).show();

                    postCheck.setEnabled(false);   // Will Disable checkbox
                }
            }
        });

        v.setTag(postList.get(i).getUserID());
        return v;
    }








}
