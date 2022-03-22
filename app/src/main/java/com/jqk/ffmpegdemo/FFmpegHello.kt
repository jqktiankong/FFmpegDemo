package com.jqk.ffmpegdemo

object FFmpegHello {
    init {
        System.loadLibrary("ffmpeg-demo")
    }

    external fun urlprotocolinfo(): String?

    external fun avformatinfo(): String?

    external fun avcodecinfo(): String?

    external fun avfilterinfo(): String?

    external fun configurationinfo(): String?
}