package com.game.space.shoote.white

enum class BoardSize(val numCards: Int) {
    EASY( 8);
    fun getWidth(): Int{
        return when(this){
            EASY -> 2
        }
    }
    fun getHeight(): Int {
        return numCards / getWidth()
    }
    fun getNumPairs(): Int{
        return numCards / 2;
    }
}