package cn.ucai.elts.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.widget.TextView;

public class Utils {

	public static <T> ArrayList<T> array2List(T[] array){
		List<T> list = Arrays.asList(array);
		ArrayList<T> arrayList=new ArrayList<T>(list);
		return arrayList;
	}

	/**
	 * 启动倒计时
	 * @param context
	 * @param time
	 * @param tvLeftTime
	 * @param biz
	 */
	public static void startTime(final Activity context,int time,final TextView tvLeftTime,final IExamBiz biz) {
		final Timer timer=new Timer();
		long beginTime=System.currentTimeMillis();
		final long endTime=beginTime+time*60*1000;
		timer.schedule(new TimerTask() {
			long second,minute;
			@Override
			public void run() {
				long leftTime=endTime-System.currentTimeMillis();
				minute=leftTime/1000/60;
				second=leftTime/1000%60;
				context.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						tvLeftTime.setText("剩余时间:"+minute+":"+second);
					}
				});
			}
		}, 0,1000);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				context.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						timer.cancel();
						DialogUtils.commit(context, "交卷", "您的成绩:"+biz.over()+"分", "返回");
					}
				});
				
			}
		}, new Date(endTime));
	}
	
}
