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
		// ��ӱ߿�Ч��
		bitmap = getBitmapHuaSeBianKuang(bitmap);
		final ImageSpan imageSpan = new ImageSpan(context, bitmap);
		// test
		System.out.println("imgPath is : " + imgPath);
		SpannableString spannableString = new SpannableString(imgPath);
		spannableString.setSpan(imageSpan, 0, spannableString.length(),
				SpannableString.SPAN_MARK_MARK);
		// ����Ƶ���һ��
		Editable editable = editText.getEditableText();
		int selectionIndex = editText.getSelectionStart();
		spannableString.getSpans(0, spannableString.length(), ImageSpan.class);
		// ��ͼƬ��ӽ�EditText��
		editable.insert(selectionIndex, spannableString);
		// ���ͼƬ���Զ��ճ�����
		editText.append("\n");
		// ��List��¼��¼����λ�ü�����·�������ڵ����¼�
		Map<String, String> map = new HashMap<String, String>();
		map.put("location", selectionIndex + "-"
				+ (selectionIndex + spannableString.length()));
		map.put("path", imgPath);
		imgList.add(map);
	}
	// �ȱ�������ͼƬ
	public static Bitmap resize(Bitmap bitmap, int S) {
		int imgWidth = bitmap.getWidth();
		int imgHeight = bitmap.getHeight();
		double partion = imgWidth * 1.0 / imgHeight;
		double sqrtLength = Math.sqrt(partion * partion + 1);
		// �µ�����ͼ��С
		double newImgW = S * (partion / sqrtLength);
		double newImgH = S * (1 / sqrtLength);
		float scaleW = (float) (newImgW / imgWidth);
		float scaleH = (float) (newImgH / imgHeight);

		Matrix mx = new Matrix();
		// ��ԭͼƬ��������
		mx.postScale(scaleW, scaleH);
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, imgWidth, imgHeight, mx,
				true);
		return bitmap;
	}

	// ��ͼƬ�ӱ߿򣬲����ر߿���ͼƬ
	public static Bitmap getBitmapHuaSeBianKuang(Bitmap bitmap) {
		float frameSize = 0.2f;
		Matrix matrix = new Matrix();

		// ��������ͼ
		Bitmap bitmapbg = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Bitmap.Config.ARGB_8888);

		// ���õ�ͼΪ����
		Canvas canvas = new Canvas(bitmapbg);
		canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
				| Paint.FILTER_BITMAP_FLAG));

		float scale_x = (bitmap.getWidth() - 2 * frameSize - 2) * 1f
				/ (bitmap.getWidth());
		float scale_y = (bitmap.getHeight() - 2 * frameSize - 2) * 1f
				/ (bitmap.getHeight());
		matrix.reset();
		matrix.postScale(scale_x, scale_y);

		// ����Ƭ��С����(��ȥ�߿�Ĵ�С)
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);

		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(1);
		paint.setStyle(Style.FILL);

		// ���Ƶ�ͼ�߿�
		canvas.drawRect(
				new Rect(0, 0, bitmapbg.getWidth(), bitmapbg.getHeight()),
				paint);
		// ���ƻ�ɫ�߿�
		paint.setColor(Color.BLUE);
		canvas.drawRect(
				new Rect((int) (frameSize), (int) (frameSize), bitmapbg
						.getWidth() - (int) (frameSize), bitmapbg.getHeight()
						- (int) (frameSize)), paint);
		canvas.drawBitmap(bitmap, frameSize + 1, frameSize + 1, paint);
		return bitmapbg;
	}
}
