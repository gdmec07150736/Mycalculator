package com.example.administrator.mycalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button ccltb;
    private EditText wet;
    private CheckBox man;
    private CheckBox woman;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ccltb=(Button) findViewById(R.id.done);
        wet=(EditText) findViewById(R.id.weight);
        man=(CheckBox) findViewById(R.id.man);
        woman=(CheckBox) findViewById(R.id.woman);
        result=(TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        register();
    }
    private void register(){
        ccltb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!wet.getText().toString().trim().equals("")){
                    if(man.isChecked()||woman.isChecked()){
                        Double weight=Double.parseDouble(wet.getText().toString());
                        StringBuffer sb=new StringBuffer();
                        sb.append("~~~~结果~~~~ \n");
                        if(man.isChecked()){
                            sb.append("男性标准身高：");
                            double result=evaluateHeight(weight,"男");
                            sb.append((int)result+"(厘米)");
                        }
                        if(man.isChecked()){
                            sb.append("女性标准身高：");
                            double result=evaluateHeight(weight,"女");
                            sb.append((int)result+"(厘米)");
                        }
                        result.setText(sb.toString());
                    }else{
                        showMessage("先选择性别！！");
                    }
                }else{
                    showMessage("先输入体重！！");
                }
            }
        });

    }
    private double evaluateHeight(double weight,String sex){
        double height = 0;
        if(sex=="男"){
            height=170-(62-weight)/0.6;
        }else{
            height=158-(52-weight)/0.5;
        }
        return height;
    }
    private void showMessage(String message){
        AlertDialog ad=new AlertDialog.Builder(this).create();
        ad.setTitle("信息");
        ad.setMessage(message);
        ad.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:finish();break;
        }
        return super.onOptionsItemSelected(item);
    }
}
