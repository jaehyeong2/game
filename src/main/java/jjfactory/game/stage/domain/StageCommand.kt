package jjfactory.game.stage.domain

class StageCommand {
    data class Create(
        val name: String,
        val ordering: Int,
    ) {
        fun toEntity(chapter: Chapter): Stage {
            return Stage(
                chapter = chapter,
                name = name,
                ordering = ordering,
            )
        }
    }

    data class Update(
        val name: String,
        val ordering: Int,
    )
}