package com.jh.commonadapter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.jinhui.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.jinhui.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.jinhui.irecyclerview.universaladapter.recyclerview.ViewHolderHelper;

import java.util.List; /**
 * Created by jh on 2018/9/27.
 * Email: 1004260403@qq.com
 */
public class MyAdapter extends MultiItemRecycleViewAdapter<String> {

    public MyAdapter(Context context, List<String> data) {
        super(context, data, new MultiItemTypeSupport<String>() {
            @Override
            public int getLayoutId(int itemType) {
                return R.layout.item_test;
            }

            @Override
            public int getItemViewType(int position, String s) {
                return 0;
            }

        });
    }

    @Override
    public void convert(ViewHolderHelper helper, String s) {
        setItemValues(helper, s, getPosition(helper));


    }

    private void setItemValues(ViewHolderHelper helper, String s, final int position) {
        helper.setText(R.id.tv_text, s);

        helper.setOnClickListener(R.id.tv_text, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "position = " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
