package jjfactory.game.stage.domain

class StageCommand {
    data class Create(
        val name: String,
        val ordering: Int,
        val clearPoint: Int
    ) {
        fun toEntity(chapter: Chapter): Stage {
            return Stage(
                chapter = chapter,
                name = name,
                ordering = ordering,
                clearPoint = clearPoint
            )
        }
    }

    data class Update(
        val name: String,
        val ordering: Int,
    )
}