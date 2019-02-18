package bestbroadcastpractice.sofia.com.bestbraodcastpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
    private EditText accountEdit;
    private EditText passordEdit;

    private Button login;

    private SharedPreferences pref;

    private SharedPreferences.Editor editor;

    private CheckBox rememberPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_login);
        accountEdit = (EditText)findViewById(R.id.account);
        passordEdit = (EditText)findViewById(R.id.password);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass = (CheckBox)findViewById(R.id.remember_pass);
        login = (Button)findViewById(R.id.login);

        boolean isRemember = pref.getBoolean("remember_password",false);
        if(isRemember){
            //读取数据
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            accountEdit.setText(account);
            passordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = accountEdit.getText().toString();
                String password = passordEdit.getText().toString();
                if(account.equals("admin") && password.equals("123456")){
                    //存入数据
                    editor = pref.edit();
                    if(rememberPass.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account","admin");
                        editor.putString("password","123456");
                    }else{
                        editor.clear();
                    }
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"account or passwork is invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
