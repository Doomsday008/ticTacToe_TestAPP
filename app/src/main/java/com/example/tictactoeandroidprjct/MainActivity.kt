package com.example.tictactoeandroidprjct

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener{
    var Player = true
    var Turn_Count = 0
    var boardstatus = Array(3){ IntArray(3)}
    lateinit var board:Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board = arrayOf(
            arrayOf(button,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )
        for (i in board){
            for (button in i){
                button.setOnClickListener(this)
            }
        }
        initializeboardstatus()
        resetbtn.setOnClickListener(){
            initializeboardstatus()
            Player = true
            Turn_Count = 0
        }
    }

    private fun initializeboardstatus() {
        for(i in 0..2){
            for (j in 0..2){
                boardstatus[i][j]=-1
                board[i][j].isEnabled = true
                board[i][j].text = ""
            }
        }
        updatedisplay( "Player 1st(X) Turn")

    }

    override fun onClick(view: View) {
       when(view.id){
           R.id.button->{
               updateValue(row=0, column=0 , player = Player)
           }
           R.id.button2->{
               updateValue(row=0, column=1 , player = Player)
           }
           R.id.button3->{
               updateValue(row=0, column=2 , player = Player)
           }
           R.id.button4->{
               updateValue(row=1, column=0 , player = Player)
           }
           R.id.button5->{
               updateValue(row=1, column=1 , player = Player)
           }
           R.id.button6->{
               updateValue(row=1, column=2 , player = Player)
           }
           R.id.button7 -> {
               updateValue(row=2, column=0 , player = Player)
           }
           R.id.button8 -> {
               updateValue(row=2, column=1 , player = Player)
           }
           R.id.button9 -> {
               updateValue(row=2, column=2 , player = Player)
           }
       }
        Turn_Count++
        Player = !Player
//        Player = if((Turn_Count+1)%2 != 0) true else false
//        var text = if((Turn_Count+1)%2 != 0) "Player 1st(X) Turn" else "Player 2nd(O) Turn"
        if(Player){
            updatedisplay( "Player 1st(X) Turn")
        }
        else if(Turn_Count ==9)
        {
            updatedisplay( "Match Draw")
        }
        else{
            updatedisplay( "Player 2nd(O) Turn")
        }
        checkwinner()

//        Toast.makeText(applicationContext,"Turn Count is $Turn_Count",Toast.LENGTH_SHORT).show()
    }

    private fun checkwinner() {
        for (i in 0..2){
            if (boardstatus[i][0] ==boardstatus[i][1] && boardstatus[i][0] == boardstatus[i][2])
            {
                if(boardstatus[i][0]==1){
                    updatedisplay( "Player 1st(X) Wins")
                    break
                }
                else if(boardstatus[i][0]==0){
                    updatedisplay( "Player 2nd(O) Wins")
                    break
                }
            }
            else if (boardstatus[0][i] ==boardstatus[1][i] && boardstatus[0][i] == boardstatus[2][i])
            {
                if(boardstatus[0][i]==1){
                    updatedisplay( "Player 1st(X) Wins")
                    break
                }
                else if(boardstatus[0][i]==0){
                    updatedisplay( "Player 2nd(O) Wins")
                    break
                }
            }
        }
        if (boardstatus[0][0] ==boardstatus[1][1] && boardstatus[0][0] == boardstatus[2][2])
        {
            if(boardstatus[0][0]==1){
                updatedisplay( "Player 1st(X) Wins")
            }
            else if(boardstatus[0][0]==0){
                updatedisplay( "Player 2nd(O) Wins")
            }
        }
        if (boardstatus[0][2] ==boardstatus[1][1] && boardstatus[0][2] == boardstatus[2][0])
        {
            if(boardstatus[0][2]==1){
                updatedisplay( "Player 1st(X) Wins")
            }
            else if(boardstatus[0][2]==0){
                updatedisplay( "Player 2nd(O) Wins")
            }
        }

    }

    private fun updatedisplay(text: String) {
        displaytv.setText(text)
        if(text.contains("Wins")){
            disablebuttons()
        }
    }

    private fun disablebuttons() {
        for (i in board){
            for (button in i){
                button.isEnabled = false
            }
        }
    }


    private fun updateValue(row: Int, column: Int, player: Boolean) {
        var text = if(player) "X" else "O"
        var value = if(player) 1 else 0
        board[row][column].apply {
            isEnabled = false
            setText(text)
        }
        boardstatus[row][column] = value


    }
}