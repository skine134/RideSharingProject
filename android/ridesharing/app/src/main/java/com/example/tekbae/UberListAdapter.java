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


public class UberListAdapter extends BaseAdapter {

    private Context context;
    private List<Uber> UberList;

    public UberListAdapter(Context context, List<Uber>UberList){
        this.context = context;
        this.UberList = UberList;
    }


    @Override
    public int getCount() {
        return UberList.size();
    }

    @Override
    public Object getItem(int i) {
        return UberList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context,R.layout.uber_info,null);
        TextView ReceiverName = (TextView)v.findViewById(R.id.Uber_receiverName);
        TextView ReceiverNumber = (TextView)v.findViewById(R.id.Uber_receiverNumber);
        TextView ReceiverAddress = (TextView)v.findViewById(R.id.Uber_receiverAddress);
        TextView Item = (TextView)v.findViewById(R.id.Uber_item);
        TextView SenderAddress = (TextView)v.findViewById(R.id.Uber_senderAddress);
        TextView SenderNumber = (TextView)v.findViewById(R.id.Uber_senderNumber);
        TextView UberId = (TextView)v.findViewById(R.id.Uber_uberId);
        TextView UberName = (TextView)v.findViewById(R.id.Uber_uberName);
        TextView Date = (TextView)v.findViewById(R.id.Uber_date);
        TextView No = (TextView)v.findViewById(R.id.Uber_No);

        final CheckBox DeliverCheck = (CheckBox)v.findViewById(R.id.Uber_deliverCheck);
        final CheckBox postCheck = (CheckBox)v.findViewById(R.id.Uber_postCheck);



        ReceiverName.setText("수령자 이름: " +UberList.get(i).getReceiverName());
        ReceiverNumber.setText("수령자 번호: "+Integer.toString(UberList.get(i).getReceiverNumber()));
        ReceiverAddress.setText("수령자 주소: "+UberList.get(i).getReceiverAddress());
        Item.setText("택배물: "+UberList.get(i).getItem());
        SenderAddress.setText("배송자 주소: "+UberList.get(i).getSenderAddress());
        SenderNumber.setText("배송자 번호: "+Integer.toString(UberList.get(i).getSenderNumber()));
        UberId.setText("배송자 ID: "+UberList.get(i).getUberId());
        UberName.setText("배송자 이름 :"+UberList.get(i).getUberName());
        Date.setText("배송 날짜: "+UberList.get(i).getDate());
        No.setText("번호: "+UberList.get(i).getNo());
        DeliverCheck.setChecked(UberList.get(i).getDeliverCheck());
        postCheck.setChecked(UberList.get(i).getPostCheck());

        postCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked){

                    Toast.makeText(context.getApplicationContext(), "수령 가능", Toast.LENGTH_SHORT).show();

                   /* postCheck.setEnabled(false);   // Will Disable checkbox*/
                }
            }
        });

        DeliverCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked){

                    Toast.makeText(context.getApplicationContext(), "배송 가능", Toast.LENGTH_SHORT).show();

/*                    DeliverCheck.setEnabled(false);   // Will Disable checkbox*/
                }
            }
        });

        v.setTag(UberList.get(i).getNo());
        return v;
    }








}
