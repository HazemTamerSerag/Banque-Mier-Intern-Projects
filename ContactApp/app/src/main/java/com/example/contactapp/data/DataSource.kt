package com.example.contactapp.data

import com.example.contactapp.R
import com.example.contactapp.model.Human

class DataSource {
    fun getHumanData(): List<Human> {
        return listOf(
            Human("Auntie", "01271669552", R.drawable.auntie),
            Human("Friend 1", "01271669552", R.drawable.friend_1),
            Human("Friend 2", "01271669552", R.drawable.friend_2),
            Human("Brother", "01271669552", R.drawable.brother),
            Human("Sister", "01271669552", R.drawable.sister),
            Human("Son", "01271669552", R.drawable.son),
            Human("Grand Father", "01271669552", R.drawable.grandfather),
            Human("Grand Mother", "01271669552", R.drawable.granny),
            Human("Uncle", "01271669552", R.drawable.uncle),
            Human("Daughter", "01271669552", R.drawable.daughter),
            Human("Neighbour", "01271669552", R.drawable.neigbour)
        )
    }
}
