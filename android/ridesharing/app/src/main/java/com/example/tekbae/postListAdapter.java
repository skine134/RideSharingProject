package com.example.tekbae;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
        CheckBox postCheck = (CheckBox)v.findViewById(R.id.postcheck);
        Button deliveryCheck = (Button)v.findViewById(R.id.deliverycheck);
        TextView date=(TextView)v.findViewById(R.id.deliverydate) ;


        postName.setText("수령자 : "+postList.get(i).getPostName());
        postNumber.setText("수령자 번호 : "+postList.get(i).getPostNumber()+"");
        postAddress.setText("수령자 주소 : "+postList.get(i).getPostAddress());
        item.setText("배송물 : "+postList.get(i).getThing());
        receiverAddress.setText("배송자 주소 : "+postList.get(i).getReceiverAddress());
        receiverNumber.setText("배송자 번호 : "+postList.get(i).getReceiverNumber()+"");
        date.setText("배송일 : "+postList.get(i).getDate());
        deliveryCheck.setClickable(!postList.get(i).isDeliverCheck());
        postCheck.setChecked(postList.get(i).isPostcheck());
        v.setTag(postList.get(i).getReceiverNumber());
        return v;
    }








}
