package com.hello.webdownimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * http://www.chebao360.com/images_3/logo.png
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void down(View view) {
        //1 确定网址
        String path = "http://www.chebao360.com/images_3/logo.png";
        try {
            // 2 把网址封装成一个Url ,使用java api
            URL url = new URL(path);
            // 3 获取客户端和服务器的连接对象，此时还没有建立任何连接和网络交互
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 初始化参数

            // 4 设置请求方法 GET
            connection.setRequestMethod("GET");
            // 5 设置连接超时 5秒
            connection.setConnectTimeout(5000);
            // 6 设置读取超时
            connection.setReadTimeout(3000);

            // 7 发送请求 与服务器交互  ，注意：添加internat访问权限
            connection.connect();

            // 服务器响应码是200 说明请求成功
            if (connection.getResponseCode() == 200){
                //获取服务器响应头中的数据流，数据流就是客户端请求的数据
                InputStream inputStream = connection.getInputStream();
                // 读取出数据流，并构造成位图对象 BitmapFactory  是Android API
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);


                //显示图片
                ImageView iv = (ImageView) findViewById(R.id.iv);
                iv.setImageBitmap(bitmap);



            }
            else{
                Toast.makeText(this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
