package com.kuteki.tictactoe

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import com.kuteki.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    // 0 - cross, 1 - circle, 2 - empty
    var activePlayer = 0
    var gameState = arrayListOf(2,2,2,2,2,2,2,2,2)
    val winningPositions = listOf(listOf(0,1,2),
                                    listOf(3,4,5),
                                    listOf(6,7,8),
                                    listOf(0,3,6),
                                    listOf(1,4,7),
                                    listOf(2,5,8),
                                    listOf(0,4,8),
                                    listOf(2,4,6))
    var gameActive = true
    var k = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR)

    }

    fun dropIn(view:View){
        val slotImage = view as ImageView
        val tappedSlot = slotImage.tag.toString().toInt()
        if(gameState[tappedSlot] == 2 && gameActive){
            gameState[tappedSlot] = activePlayer
            Log.e("tag", gameState.toString())
            slotImage.translationY = -2000f
            if(activePlayer==0 && slotImage.alpha == 0f) {
                slotImage.setImageResource(R.drawable.ic_x_me_www)
                slotImage.alpha = 1f
                activePlayer = 1
            }else if(activePlayer == 1 && slotImage.alpha == 0f){
                slotImage.setImageResource(R.drawable.ic_o_me_www)
                slotImage.alpha = 1f
                activePlayer = 0
            }
            slotImage.animate().translationYBy(2000f).setDuration(500)
            for (combo in winningPositions) {
                if (gameState[combo[0]] == gameState[combo[1]] &&
                        gameState[combo[1]] == gameState[combo[2]] &&
                        gameState[combo[0]] != 2) {
                    binding.resetButton.visibility = View.VISIBLE
                    gameActive = false
                    if(activePlayer == 0)
                        binding.textWinner.text = "O wins"
                    else
                        binding.textWinner.text = "X wins"
                }else if(gameActive){
                    k=0
                    for(i in gameState)
                        if(i!=2)
                            k++
                        if(k==9){
                            binding.resetButton.visibility = View.VISIBLE
                            gameActive = false
                            binding.textWinner.text = "Draw"
                        }
                }
            }

        }
    }
    fun reset(view:View){
        activePlayer = 0
        gameState = arrayListOf(2,2,2,2,2,2,2,2,2)
        gameActive = true
        binding.imageView0.alpha = 0f
        binding.imageView1.alpha = 0f
        binding.imageView2.alpha = 0f
        binding.imageView3.alpha = 0f
        binding.imageView4.alpha = 0f
        binding.imageView5.alpha = 0f
        binding.imageView6.alpha = 0f
        binding.imageView7.alpha = 0f
        binding.imageView8.alpha = 0f
        binding.textWinner.text = ""
        binding.resetButton.visibility = View.INVISIBLE

    }
}