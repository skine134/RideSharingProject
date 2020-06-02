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
        TextView postName = (TextView)v.findViewById(R.id.postname);
        TextView postNumber = (TextView)v.findViewById(R.id.postnumber);
        TextView postAddress = (TextView)v.findViewById(R.id.postaddress);
        TextView item = (TextView)v.findViewById(R.id.thing);
        TextView receiverAddress = (TextView)v.findViewById(R.id.receiveraddress);
        TextView receiverNumber = (TextView)v.findViewById(R.id.receivernumber);
        final CheckBox deliveryCheck = (CheckBox)v.findViewById(R.id.deliverycheck);
        final CheckBox postCheck = (CheckBox)v.findViewById(R.id.postcheck);

        postName.setText(postList.get(i).getPostName());
        postNumber.setText(postList.get(i).getPostNumber()+"");
        postAddress.setText(postList.get(i).getPostAddress());
        item.setText(postList.get(i).getThing());
        receiverAddress.setText(postList.get(i).getReceiverAddress());
        receiverNumber.setText(postList.get(i).getReceiverNumber()+"");
        deliveryCheck.setChecked(postList.get(i).isDeliverCheck());
        postCheck.setChecked(postList.get(i).isPostcheck());



        /*postCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked){

                    Toast.makeText(context.getApplicationContext(), "배송 완료", Toast.LENGTH_SHORT).show();

                    postCheck.setEnabled(false);   // Will Disable checkbox
                }
            }
        });*/

        v.setTag(postList.get(i).getReceiverNumber());
        return v;
    }








}
