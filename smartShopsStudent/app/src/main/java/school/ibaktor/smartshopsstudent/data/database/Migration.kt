package school.ibaktor.smartshopsstudent.data.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_TO_2 = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS `usuario_pedido` (
                `pedidoId` INTEGER NOT NULL,
                `numero` TEXT NOT NULL,
                `fecha` TEXT NOT NULL,
                `estadoPago` TEXT NOT NULL,
                `total` REAL NOT NULL,
                `usuarioId` INTEGER NOT NULL,
                PRIMARY KEY(`pedidoId`),
                FOREIGN KEY(`usuarioId`) REFERENCES `usuario`(`id`) ON DELETE CASCADE
            )
        """)
    }
}