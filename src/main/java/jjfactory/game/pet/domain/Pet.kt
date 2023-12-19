package jjfactory.game.pet.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.util.StringUtils

@Entity
class Pet(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    var description: String,
    var price: Int,
) {
    fun modify(price: Int, description: String){
        this.price = price
        if(StringUtils.hasText(description)) this.description = description
    }
}