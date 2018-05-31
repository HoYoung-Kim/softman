package com.jerryfamily.mymuseumtrip;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 호영 on 2018-05-23.
 */

public class ExhibitionInfoDialog extends DialogFragment implements View.OnClickListener {

    private static final String TAG = ExhibitionInfoDialog.class.getSimpleName();

    private Context mContext;
    private int item_position;
    private ImageView imageView_close;
    private TextView ex_title, ex_description, ex_supervision, ex_place;
    private TextView ex_telephone, ex_url, ex_date;
    private TextView ex_telephone_call, ex_url_view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    static ExhibitionInfoDialog newInstance(int position) {
        ExhibitionInfoDialog f = new ExhibitionInfoDialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("item_position", position);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item_position = getArguments().getInt("item_position");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.exhibition_info_dialog, null);

        // DialogFragment 닫기
        imageView_close = view.findViewById(R.id.imageView_close);
        imageView_close.setOnClickListener(this);

        // 변수 할당
        ex_title             = view.findViewById(R.id.ex_title);
        ex_description      = view.findViewById(R.id.ex_description);
        ex_supervision      = view.findViewById(R.id.ex_supervision);
        ex_place             = view.findViewById(R.id.ex_place);
        ex_telephone        = view.findViewById(R.id.ex_telephone);
        ex_url               = view.findViewById(R.id.ex_url);
        ex_date              = view.findViewById(R.id.ex_date);

        ex_telephone_call   = view.findViewById(R.id.ex_telephone_call);
        ex_url_view          = view.findViewById(R.id.ex_url_view);

        // 클릭 이벤트 설정
        ex_telephone_call.setOnClickListener(this);
        ex_url_view.setOnClickListener(this);

        // 데이타 입력
        ex_title.setText(ConstantManager.exhibitionInfos.get(item_position).getTitle());
        ex_description.setText(ConstantManager.exhibitionInfos.get(item_position).getDescription());
        ex_supervision.setText(ConstantManager.exhibitionInfos.get(item_position).getSupervision());
        ex_place.setText(ConstantManager.exhibitionInfos.get(item_position).getPlace());
        ex_telephone.setText(ConstantManager.exhibitionInfos.get(item_position).getTelephone());

        String url = ConstantManager.exhibitionInfos.get(item_position).getUrl().trim();
        if (url.length() > 40) {
            url = url.substring(0, 30) + "...";
        }
        ex_url.setText(url);

        ex_date.setText(ConstantManager.exhibitionInfos.get(item_position).getDate());

        return view;
    }

    //
    // 클릭 이벤트
    //
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_close:
                dismiss();
                break;
            case R.id.ex_url_view:

                String url = ConstantManager.exhibitionInfos.get(item_position).getUrl().trim();
                if(!url.equals("")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }

                break;
            case R.id.ex_telephone_call:

                String telephone = ex_telephone.getText().toString().trim();
                if(!telephone.equals("")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telephone));
                    startActivity(intent);
                }

                break;
        }
    }
}
