package com.jqk.ffmpeg_cmd

import android.Manifest
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jqk.ffmpeg_cmd.databinding.ActivityMainBinding
import com.tbruyelle.rxpermissions3.RxPermissions
import java.io.File


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var rxPermissions: RxPermissions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCommit.setOnClickListener {
            ffmpegTest()
        }

        rxPermissions = RxPermissions(this)

        rxPermissions
            .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe { granted ->
                if (granted) {
                    Log.d("jqk", "请求权限成功")
                } else {
                    Log.d("jqk", "请求权限失败")
                }
            }

    }

    fun ffmpegTest() {
        val input = "/storage/emulated/0/123456.mp4"
        val output = "/storage/emulated/0/output.mp4"

        val outputFile = File(output)

        if (outputFile.exists()) {
            outputFile.delete()
        }

        var cmd2 = "ffmpeg -y -i %s -b 2097k -r 30 -vcodec libx264 -preset superfast %s"
        cmd2 = String.format(cmd2, input, output)
        cmd2.split(" ")

        val cmdList = CmdList()
        cmdList.append(cmd2.split(" ").toTypedArray())

        FFmpegUtil.execCmd(cmdList, getDuration(input), object : OnVideoProcessListener {
            override fun onProcessStart() {
                Log.d("ffmpegcmd", "onProcessStart")
            }

            override fun onProcessProgress(progress: Float) {
                Log.d("ffmpegcmd", "onProcessProgress = " + progress)
            }

            override fun onProcessSuccess() {
                Log.d("ffmpegcmd", "onProcessSuccess")
            }

            override fun onProcessFailure() {
                Log.d("ffmpegcmd", "onProcessFailure")
            }
        })
    }

    fun getDuration(url: String): Int {
        var duration = 0

        val mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepare()
        duration = mediaPlayer.getDuration()
        mediaPlayer.release()

        return duration
    }
}