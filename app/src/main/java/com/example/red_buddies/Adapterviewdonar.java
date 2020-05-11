package com.example.red_buddies;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class Adapterviewdonar extends RecyclerView.Adapter<Adapterviewdonar.JaiViewholder> {

        String str;

        int pos;


    Long_list long_list;
     private ArrayList<String> data;
     ArrayList<String> phone;

     Context context;



    public Adapterviewdonar(ArrayList<String> data,ArrayList<String> phone,Context context)
    {
        this.phone=phone;
        this.data=data;
        this.context=context;
    }


    @NonNull
    @Override
    public JaiViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());

        View view=inflater.inflate(R.layout.list_rec_layout,parent,false);
        return new JaiViewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final JaiViewholder holder, final int position) {
        String title=(String) data.get(position);
        String phonet=(String) phone.get(position);
        holder.textView.setText(title);
        holder.textView2.setText(phonet);


        if(title.equals("No Result found"))
        {
             holder.imageView.setVisibility(View.GONE);
        }


        holder.titleimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Anime(holder.titleimg);
            }
        });



        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                   Anime(holder.imageView);

                      str="tel:"+(String)( phone.get(position));


                      makecall(context,holder.imageView,str);





                }

            }


        );

}



    @Override
    public int getItemCount() {
        return data.size();
    }

    public class JaiViewholder extends RecyclerView.ViewHolder {

        TextView textView;
        TextView textView2;
        ImageView imageView,titleimg;


        public JaiViewholder(final View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textitem);
            textView2 = (TextView) view.findViewById(R.id.unip);
            imageView = (ImageView) view.findViewById(R.id.callid);
            titleimg=(ImageView) view.findViewById(R.id.blood_img);

        }

    }

       public void Anime(ImageView imageView)
        {

                final Animation myAnim = AnimationUtils.loadAnimation(imageView.getContext(), R.anim.anime);
                imageView.startAnimation(myAnim);


                Bounceinterloop interpolator = new Bounceinterloop(0.2, 20);
                myAnim.setInterpolator(interpolator);

                imageView.startAnimation(myAnim);




    }

public void makecall(Context context,ImageView imageView,String s)
{


    if(ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
    {
    //    ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CALL_PHONE},REQUEST_CALL);
         Toast.makeText(context,"Allow permission",Toast.LENGTH_SHORT).show();
    }



    else {

        Intent intent = new Intent(Intent.ACTION_CALL);

        intent.setData(Uri.parse(str));

        context.startActivity(intent);
    }
/*



Intent i=new Intent(context,Demo.class);
i.putExtra("message",str);
context.startActivity(i);
*/
}




}
