package com.jerryfamily.mymuseumtrip;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 *
 * 이색 박물관 상세 정보 보는 DialogFragment
 *
 */

public class StrangeMuseumDialog extends DialogFragment implements View.OnClickListener {

    private static final String TAG = StrangeMuseumDialog.class.getSimpleName();

    private Context mContext;
    private ImageView imageView_close;
    private int item_position;
    private TextView s_title, s_description, s_address, s_homepage_url, s_telephone;
    private TextView s_address_copy, s_homepage_url_view, s_telephone_call;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    static StrangeMuseumDialog newInstance(int position) {
        StrangeMuseumDialog f = new StrangeMuseumDialog();

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
        View view  = inflater.inflate(R.layout.strange_museum_dialog, null);

        // DialogFragment 닫기
        imageView_close = view.findViewById(R.id.imageView_close);
        imageView_close.setOnClickListener(this);

        // 변수 할당
        s_title             = view.findViewById(R.id.s_title);
        s_description       = view.findViewById(R.id.s_description);
        s_address           = view.findViewById(R.id.s_address);
        s_homepage_url      = view.findViewById(R.id.s_homepage_url);
        s_telephone         = view.findViewById(R.id.s_telephone);

        s_address_copy      = view.findViewById(R.id.s_address_copy);
        s_homepage_url_view = view.findViewById(R.id.s_homepage_url_view);
        s_telephone_call    = view.findViewById(R.id.s_telephone_call);

        // 클릭 이벤트 설정
        s_address_copy.setOnClickListener(this);
        s_homepage_url_view.setOnClickListener(this);
        s_telephone_call.setOnClickListener(this);

        // 데이타 입력
        s_title.setText(ConstantManager.strangeMuseums.get(item_position).getTitle());
        s_description.setText(ConstantManager.strangeMuseums.get(item_position).getDescription());
        s_address.setText(ConstantManager.strangeMuseums.get(item_position).getAddress());
        s_homepage_url.setText(ConstantManager.strangeMuseums.get(item_position).getHomepage_url());
        s_telephone.setText(ConstantManager.strangeMuseums.get(item_position).getTelephone());

        return view;
    }

    //
    // 클릭 이벤트 처리
    //
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.imageView_close:
                dismiss();
                break;
            case R.id.s_address_copy:

                ClipboardManager clipboardManager = (ClipboardManager)mContext.getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("label", s_address.getText().toString());
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(getActivity(), "주소가 복사 되었습니다.", Toast.LENGTH_SHORT).show();

                break;
            case R.id.s_homepage_url_view:

                String homepage_url = s_homepage_url.getText().toString().trim();
                if(!homepage_url.equals("")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(homepage_url));
                    startActivity(intent);
                }

                break;
            case R.id.s_telephone_call:

                String telephone = s_telephone.getText().toString().trim();
                if(!telephone.equals("")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telephone));
                    startActivity(intent);
                }

                break;
        }
    }
}
