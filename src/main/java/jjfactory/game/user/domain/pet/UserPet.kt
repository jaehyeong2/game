package jjfactory.game.user.domain.pet

import jakarta.persistence.*
import jjfactory.game.user.domain.User
import org.springframework.util.StringUtils
import java.time.LocalDateTime

@Entity
class UserPet(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,
    val petId: Long,
    var name: String,

    var purchaseDt: LocalDateTime

) {

    fun changeName(name: String){
        if(StringUtils.hasText(name)) this.name = name
    }

}