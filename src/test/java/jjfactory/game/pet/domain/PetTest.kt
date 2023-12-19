package jjfactory.game.pet.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PetTest {

    @Test
    fun modify() {
        val goril = Pet(
            name = "고릴",
            description = "귀여운 고릴라입니다.",
            price = 5000
        )

        goril.modify(price = 20000, description = "안귀여움")

        assertThat(goril.price).isEqualTo(20000)
        assertThat(goril.description).isEqualTo("안귀여움")
    }
}