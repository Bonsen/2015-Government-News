package com.news.addNews.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.text.Editable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.EditText;

public class ImageBo {

	@SuppressLint("NewApi")
	public static void InsertBitmap(Bitmap bitmap, int S, String imgPath,
			Context context, EditText editText,
			List<Map<String, String>> imgList) {

		bitmap = resize(bitmap, S);
		// 添加边框效果
		bitmap = getBitmapHuaSeBianKuang(bitmap);
		final ImageSpan imageSpan = new ImageSpan(context, bitmap);
		// test
		System.out.println("imgPath is : " + imgPath);
		SpannableString spannableString = new SpannableString(imgPath);
		spannableString.setSpan(imageSpan, 0, spannableString.length(),
				SpannableString.SPAN_MARK_MARK);
		// 光标移到下一行
		Editable editable = editText.getEditableText();
		int selectionIndex = editText.getSelectionStart();
		spannableString.getSpans(0, spannableString.length(), ImageSpan.class);
		// 将图片添加进EditText中
		editable.insert(selectionIndex, spannableString);
		// 添加图片后自动空出两行
		editText.append("\n");
		// 用List记录该录音的位置及所在路径，用于单击事件
		Map<String, String> map = new HashMap<String, String>();
		map.put("location", selectionIndex + "-"
				+ (selectionIndex + spannableString.length()));
		map.put("path", imgPath);
		imgList.add(map);
	}
	// 等比例缩放图片
	public static Bitmap resize(Bitmap bitmap, int S) {
		int imgWidth = bitmap.getWidth();
		int imgHeight = bitmap.getHeight();
		double partion = imgWidth * 1.0 / imgHeight;
		double sqrtLength = Math.sqrt(partion * partion + 1);
		// 新的缩略图大小
		double newImgW = S * (partion / sqrtLength);
		double newImgH = S * (1 / sqrtLength);
		float scaleW = (float) (newImgW / imgWidth);
		float scaleH = (float) (newImgH / imgHeight);

		Matrix mx = new Matrix();
		// 对原图片进行缩放
		mx.postScale(scaleW, scaleH);
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, imgWidth, imgHeight, mx,
				true);
		return bitmap;
	}

	// 给图片加边框，并返回边框后的图片
	public static Bitmap getBitmapHuaSeBianKuang(Bitmap bitmap) {
		float frameSize = 0.2f;
		Matrix matrix = new Matrix();

		// 用来做底图
		Bitmap bitmapbg = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Bitmap.Config.ARGB_8888);

		// 设置底图为画布
		Canvas canvas = new Canvas(bitmapbg);
		canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
				| Paint.FILTER_BITMAP_FLAG));

		float scale_x = (bitmap.getWidth() - 2 * frameSize - 2) * 1f
				/ (bitmap.getWidth());
		float scale_y = (bitmap.getHeight() - 2 * frameSize - 2) * 1f
				/ (bitmap.getHeight());
		matrix.reset();
		matrix.postScale(scale_x, scale_y);

		// 对相片大小处理(减去边框的大小)
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);

		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(1);
		paint.setStyle(Style.FILL);

		// 绘制底图边框
		canvas.drawRect(
				new Rect(0, 0, bitmapbg.getWidth(), bitmapbg.getHeight()),
				paint);
		// 绘制灰色边框
		paint.setColor(Color.BLUE);
		canvas.drawRect(
				new Rect((int) (frameSize), (int) (frameSize), bitmapbg
						.getWidth() - (int) (frameSize), bitmapbg.getHeight()
						- (int) (frameSize)), paint);
		canvas.drawBitmap(bitmap, frameSize + 1, frameSize + 1, paint);
		return bitmapbg;
	}
}
