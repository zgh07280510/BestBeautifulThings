package com.lanou.bestbeautifulthings.magazine.magazinedetail.comment;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou.bestbeautifulthings.R;
import com.lanou.bestbeautifulthings.base.BaseActivity;

/**
 * Created by zouguohua on 16/7/30.
 */
public class CommentActivity extends BaseActivity {
    private EditText commentEt;
    private String mCommentContent;//评论内容
    private TextView mCommentSend;//发送

    @Override
    public int setLayout() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initView() {
        commentEt = (EditText) findViewById(R.id.comment_et);
        mCommentSend = (TextView) findViewById(R.id.comment_send);
    }

    @Override
    protected void initData() {
        mCommentSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommentContent = commentEt.getText().toString();
                if (mCommentContent.isEmpty()) {
                    Toast.makeText(CommentActivity.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(CommentActivity.this, "发送", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
