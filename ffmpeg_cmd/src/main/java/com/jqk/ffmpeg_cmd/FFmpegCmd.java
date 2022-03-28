package com.jqk.ffmpeg_cmd;

import android.annotation.SuppressLint;

public class FFmpegCmd {
    static {
        System.loadLibrary("ffmpeg-cmd");
    }

    private static OnCmdExecListener sOnCmdExecListener;
    private static int sDuration;

    public static native int exec(int argc, String[] argv);

    public static native void exit();

    public static void exec(String[] cmds, int duration, OnCmdExecListener listener) {
        sOnCmdExecListener = listener;
        sDuration = duration;
        exec(cmds.length, cmds);
    }

    /**
     * FFmpeg执行结束回调，由C代码中调用
     */
    public static void onExecuted(int ret) {
        if (sOnCmdExecListener != null) {
            if (ret == 0) {
                sOnCmdExecListener.onProgress(1.0f);
                sOnCmdExecListener.onSuccess();
            } else {
                sOnCmdExecListener.onFailure();
            }
        }
    }

    /**
     * FFmpeg执行进度回调，由C代码调用
     */
    @SuppressLint("DefaultLocale")
    public static void onProgress(int duration) {
        if (sOnCmdExecListener != null) {
            if (sDuration != 0) {
                float progress = 0;

                if (duration >= sDuration) {
                    progress = 1.0f;
                } else {
                    String result = String.format("%.2f", (float) duration / sDuration);
                    progress = Float.parseFloat(result);
                }

                sOnCmdExecListener.onProgress(progress);
            }
        }
    }

    public interface OnCmdExecListener {
        void onSuccess();

        void onFailure();

        void onProgress(float progress);
    }
}