package com.exvoto.qrapplication.services;

import android.graphics.Bitmap;
import android.util.Log;

import net.glxn.qrgen.android.QRCode;
import net.glxn.qrgen.core.scheme.EMail;

public class QRGenerator {

    private static final String TAG = QRGenerator.class.getName();

//    private Bitmap generateQRXing(String s, int dimensions) {
//        if (s == null) throw new IllegalArgumentException("String can not be null to generate QR code");
//        if (dimensions == 0) throw new IllegalArgumentException("QRCode dimension can not be zero (0)");
//        QRCodeWriter qrCodeWriter = new QRGenerator(s, null, QRGContents.Type.TEXT, dimensions);
//        Bitmap bitmap;
//        try {
//            bitmap = qrCodeWriter.encodeAsBitmap();
//        } catch (WriterException e) {
//            Log.e(TAG, e.toString());
//        }
//
//        return bitmap;
//    }

    public static Bitmap generateQRCodeBitmap(String s) {
        if (s == null) throw new IllegalArgumentException("String can not be null to generate QR code");

        Log.i(TAG, "String contents: " + s);
        return QRCode.from(s).withSize(256, 256).bitmap();
    }
}
