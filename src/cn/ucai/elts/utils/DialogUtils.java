package cn.ucai.elts.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DialogUtils {

	/**
	 * 交卷
	 * @param context
	 * @param title
	 * @param message
	 * @param ok
	 */
	public static void commit(final Activity context,String title,String message,String ok){
		AlertDialog.Builder builder=new AlertDialog.Builder(context);
		builder.setTitle(title)
			.setMessage(message)
			.setPositiveButton(ok, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					context.finish();
				}
			}).create().show();
	}
	
	/**
	 * 显示退出对话框
	 * @param context
	 * @param title
	 * @param message
	 * @param ok
	 * @param cancel
	 */
	public static void exit(final Activity context,String title,String message,String ok,String cancel){
		AlertDialog.Builder builder=new AlertDialog.Builder(context);
		builder.setTitle(title)
			.setMessage(message)
			.setPositiveButton(ok, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					context.finish();
				}
			})
			.setNegativeButton(cancel, null)
			.create().show();
	}
	
	private static ProgressDialog mDialog;
	/**
	 * 显示进度对话框
	 * @param context
	 * @param title
	 * @param message
	 */
	public static void showProgress(Activity context,String title,String message){
		mDialog=new ProgressDialog(context);
		mDialog.setTitle(title);
		mDialog.setMessage(message);
		mDialog.show();
	}
	
	/**
	 * 关闭进度对话框
	 */
	public static void closeProgress(){
		if(mDialog!=null){
			mDialog.dismiss();
		}
	}
	
	/**
	 * 显示列表对话框
	 * @param context
	 * @param title
	 * @param courseList
	 * @param listener
	 */
	public static void selectList(Activity context,String title,String[] itemsList,
			OnClickListener listener){
		AlertDialog.Builder builder=new AlertDialog.Builder(context);
		builder.setTitle(title)
			.setItems(itemsList, listener).create().show();
	}
}
