package com.jqk.ffmpeg_cmd;

import android.util.Log;

// 封装FFmpeg命令的调用
public class FFmpegUtil {
    private static final String TAG = "FFmpegUtil";

    public static void execCmd(CmdList cmd, long duration, final OnVideoProcessListener listener) {
        String[] cmds = cmd.toArray(new String[0]);
        Log.i(TAG, "cmd:" + cmd);

        for (String s : cmds) {
            Log.i(TAG, "a:" + s);
        }

        listener.onProcessStart();
        FFmpegCmd.exec(cmds, duration, new FFmpegCmd.OnCmdExecListener() {
            @Override
            public void onSuccess() {
                listener.onProcessSuccess();
            }

            @Override
            public void onFailure() {
                listener.onProcessFailure();
            }

            @Override
            public void onProgress(float progress) {
                listener.onProcessProgress(progress);
            }
        });
    }

}

