package com.example.coroutinelessons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Thread.State
import kotlin.random.Random

//TODO: Необходимо изучить Dispatchers что это и какой и где использовать
//TODO: Необходимо изучить доступные CorountineScope и рекомендации к их применению

class MainActivity : AppCompatActivity() {

    val liveData: MutableLiveData<Int> = MutableLiveData(123)
    val flowVar: MutableStateFlow<Int> = MutableStateFlow(123)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launchWhenCreated {
//            updateLiveDataValue()
//            flowVar.collectLatest {
//                findViewById<TextView>(R.id.txt).apply {
//                    text = it.toString()
//                    textSize = it.toFloat()
//                    x = it.toFloat()
//                    y = it.toFloat() / 2
//                }
//            }
        }
        CoroutineScope(Dispatchers.Main).apply {
            /**
             * */
            launch {
                /**updateLiveDataValue() - Если запустить метод updateLiveDataValue без запуска собственной корутины
                 * методом launch, то последующий collect не сработает поскольку он ждёт заверщения предыдущей функции
                 * Поэтому для метода необходимо запусисть отдельную свою корутину
                 * Либо запустить один из методов в отдельном скоупе
                **/
                //asdasd
                launch { updateLiveDataValue() }
                flowVar
                    .filter {
                        println(it)
                        it % 2 == 0
                    }
                    .map {
                        println(it)
                        listOf(it - 20, it * 2)
                    }
                    .collect {
                        println(123123)
                        findViewById<TextView>(R.id.txt).apply {
                            text = it[0].toString()
                            textSize = it[1].toFloat()
                            x = it[0].toFloat()
                            y = it[1].toFloat() / 2
                        }
                    }

            }
        }
//        liveData.observe(this) {
//            findViewById<TextView>(R.id.txt).apply {
//                text = it.toString()
//                textSize = it.toFloat()
//                x = it.toFloat()
//                y = it.toFloat() / 2
//            }
//        }
        val state = flowVar.asStateFlow()
//        lifecycleScope.launchWhenResumed {
//            updateLiveDataValue()
//            flowVar
//                .filter {
//                    println(it)
//                    it % 2 == 0
//                }
//                .map {
//                    println(it)
//                    listOf(it - 20, it * 2)
//                }
//                .collect {
//                    println(123123)
//                    findViewById<TextView>(R.id.txt).apply {
//                        text = it[0].toString()
//                        textSize = it[1].toFloat()
//                        x = it[0].toFloat()
//                        y = it[1].toFloat() / 2
//                    }
//                }
//        }
    }

    private suspend fun updateLiveDataValue() {
        coroutineScope {
            launch {
                while (true) {
//                    liveData.value = Random.nextInt(-100, 100)
                    flowVar.value = Random.nextInt(-100, 100)
                    println(flowVar.value)
//                    if (flowVar.value > 90) break
                    delay(100L)
                }
            }
            launch {
                while (true) {
                    print("asdddddddasjkdhokjhaosjdfhoiajsdhfoiahdoifhaosdfhaodisfha")
                    delay(100000000000)
                }
            }
        }
    }
}