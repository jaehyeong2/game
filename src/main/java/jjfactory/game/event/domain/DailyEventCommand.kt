package jjfactory.game.event.domain

class DailyEventCommand {
    data class Create(
        val name: String,
        val stock: Int,
        val rewardMoney: Int,
    ) {
        fun toEntity(): DailyEvent {
            return DailyEvent(
                name = name,
                stock = stock,
                rewardMoney = rewardMoney
            )
        }
    }
}