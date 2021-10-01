package ru.shalkoff.annotationexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import ru.shalkoff.annotation.OnlyPrivate

class MainActivity : AppCompatActivity() {

    private var resultTv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultTv = findViewById(R.id.result_tv)

        resultTv?.let {
            it.text = calculated().toString()
        }
    }


    @OnlyPrivate
    fun calculated(): Int {
        return 5 + 5
    }
}