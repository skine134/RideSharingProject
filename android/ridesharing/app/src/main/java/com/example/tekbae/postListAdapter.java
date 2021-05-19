package com.example.tekbae;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class postListAdapter extends BaseAdapter {
    private Context context;
    private List<Uber> postList;

    public postListAdapter(Context context, List<Uber> postList) {
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
        TextView Receiver = (TextView) v.findViewById(R.id.receiver);
        TextView ReceiverPhone = (TextView) v.findViewById(R.id.receiverPhonenum);
        TextView PostUber = (TextView) v.findViewById(R.id.postUber);
        TextView PosterPhone= (TextView) v.findViewById(R.id.posterPhonenum);
        TextView PosterAddress = (TextView) v.findViewById(R.id.posterAdress);
        TextView ReceiverAddress = (TextView) v.findViewById(R.id.receiverAddress);
        TextView PostItem = (TextView) v.findViewById(R.id.postItem);
        Button signBtn = (Button) v.findViewById(R.id.btnSign);

        Receiver.setText("받는분 이름 :" + postList.get(i).getReceiverName());
        ReceiverPhone.setText("받는분 번호 : " + "0"+postList.get(i).getReceiverNumber());
        PostUber.setText("담당우버 : " + postList.get(i).getUberName());
        PosterPhone.setText("보내는분 번호 : " + "0"+postList.get(i).getSenderNumber());
        PosterAddress.setText("보내는분 주소 : " + postList.get(i).getSenderAddress());
        ReceiverAddress.setText("받는분 주소 " + postList.get(i).getReceiverAddress());
        PostItem.setText("제품명 : " + postList.get(i).getItem());

        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //서명패드 ON
                Intent intent = new Intent(context.getApplicationContext(), check_sign.class);

                //SignCheck 클래스 객체 생성 : 서명패드 확인버튼 클릭 유무 확인 용도
                SignCheck sc = new SignCheck();
                Bundle bundle = new Bundle();
                bundle.putSerializable("sc", sc);
                intent.putExtras(bundle);


                context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                signBtn.setEnabled(false);
                PostItem.setText("제품명 : " + postList.get(i).getItem() + "(배송완료)");

                //다시 돌아오면 값을 확인한 후, Connection 하여 postCheck 을 1 로만듬(미해결)
                try{
                new Connection("Uber", "update", "1", "deliveryCheck", postList.get(i).getReceiverName(), postList.get(i).getItem()).
                        execute(BuildConfig.SERVER_HOST+"/regosterUser.php?").get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });


        return v;
    }






}
