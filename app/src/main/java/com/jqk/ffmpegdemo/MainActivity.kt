package com.jqk.ffmpegdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.jqk.ffmpegdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvInfo.text = FFmpegHello.urlprotocolinfo()

            btUrlprotocol.setOnClickListener {
                tvInfo.text = FFmpegHello.urlprotocolinfo()
            }

            btAvformat.setOnClickListener {
                tvInfo.text = FFmpegHello.avformatinfo()
            }

            btAvcodec.setOnClickListener {
                tvInfo.text = FFmpegHello.avcodecinfo()
            }

            btAvfilter.setOnClickListener {
                tvInfo.text = FFmpegHello.avfilterinfo()
            }

            btConfiguration.setOnClickListener {
                tvInfo.text = FFmpegHello.configurationinfo()
            }
        }
    }

}