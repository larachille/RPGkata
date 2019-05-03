package example

import org.junit.Test
import kotlin.test.assertEquals

class LevelsShould {

    @Test
    fun `start at 1`(){
        val character = Player(PlayerType.MELEE)
        assertEquals(1,character.level)
    }

}