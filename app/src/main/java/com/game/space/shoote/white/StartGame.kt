package com.game.space.shoote.white

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.game.space.shoote.R

class StartGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)

        val constLayout:ConstraintLayout = findViewById(R.id.mainLayout)
        val drawable: AnimationDrawable = constLayout.background as AnimationDrawable
        drawable.setEnterFadeDuration(2000)
        drawable.setExitFadeDuration(4000)
        drawable.start()


        val btn: Button = findViewById(R.id.btn_play)
        btn.setOnClickListener{
            startActivity(Intent(this, GameActivity::class.java))
        }

    }
}