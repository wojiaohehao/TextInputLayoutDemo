package com.example.administrator.textinputlayoutdemo;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create 何浩 2017-04-15
 */
public class LoginActivity extends AppCompatActivity {
    private Button btn;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";//邮箱正则
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);//验证字符串
    private Matcher matcher;
    private TextInputLayout usernameWrapper;
    private TextInputLayout passwordWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                String username = usernameWrapper.getEditText().getText().toString();
                String password = passwordWrapper.getEditText().getText().toString();
                if (!validateEmail(username)) {
                    usernameWrapper.setError("Not a valid email address!");
                } else if (!validatePassword(password)) {
                    passwordWrapper.setError("Not a valid password!");
                } else {
                    usernameWrapper.setErrorEnabled(false);
                    passwordWrapper.setErrorEnabled(false);
                    doLogin();
                }
            }
        });
    }

    /**
     * 模拟登陆成功
     */
    private void doLogin() {
        Toast.makeText(getApplicationContext(), "OK! I'm performing login.", Toast.LENGTH_SHORT).show();
    }

    /**
     * 初始化控件
     */
    private void init() {
        btn = (Button) findViewById(R.id.btn);
        usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        usernameWrapper.setHint("Username");
        passwordWrapper.setHint("Password");
    }

    /**
     * 告诉安卓隐藏键盘
     */
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 校验账号是否为邮箱
     */
    public boolean validateEmail(String email) {//校验账号是否为邮箱
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 校验账密码是否大于5位
     */
    public boolean validatePassword(String password) {
        return password.length() > 5;
    }
}
