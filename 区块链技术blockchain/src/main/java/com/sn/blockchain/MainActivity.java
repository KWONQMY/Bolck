package com.sn.blockchain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static com.sn.blockchain.SHATest.getSHA256StrJava;

/**
 * 10分钟实现一个Java简版区块链:https://www.jianshu.com/p/6f2c8125b112
 * 中本聪与拜占庭将军问题:https://www.jianshu.com/p/5fea30b25f0a
 * 区块链的java实现:http://blog.csdn.net/xiangzhihong8/article/details/53931213
 */
public class MainActivity extends AppCompatActivity {

    //A.我们要加密的数据,比特币要对这个机密数据再加上随机数,加密后的前几位都是0,交易方有效
    String mString = "ycf give yiDaShi $100";
    //B.挖到的比特币,进行验证
    int mBtb;

    /**
     * 进行碰撞挖矿,挖矿要挖很久
     * @param view
     */
    public void waKuang(View view) {
        //A.我们先对数据进行hash256的加密操作
        String cheShi = SHATest.getSHA256StrJava(mString);
        System.out.println("加密后的密码是:" + cheShi);

        //B.区块链挖矿操作,进行一次无限循环挖矿,直到挖到比特币停止
        for (int x = 0; x < Integer.MAX_VALUE; x++) {
            //根据比特币的性质,一般要加密数据后面有随机数,加密后的密码,前10位都是0,才算挖矿成功,我们要做的就是找到这个随机数是多少
            String BTB = SHATest.getSHA256StrJava(mString + x);
            if (BTB.startsWith("000")) {
                mBtb=x;
                System.out.println(x);
                break;
            }
        }
    }

    /**
     * 进行结果的校验,交易几秒钟
     * 怎么算是成功,前10位的值都为0,则代表交易是有效的
     * @param view
     */
    public void jiaoYan(View view) {
        String money = SHATest.getSHA256StrJava(mString + mBtb);
        System.out.println(money);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
