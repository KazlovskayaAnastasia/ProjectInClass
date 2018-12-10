package com.nastia.administrator.data.database.entity

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.nastia.administrator.data.database.entity.dao.StudentDao

@Database(entities = [StudentDB::class],
        version = 1) // для миграции баз данных
abstract class AppDatabase : RoomDatabase(){

    companion object {
        const val DATABASE_NAME = "qwerty"

        fun getInstance(context: Context): AppDatabase {

            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration().build() // не применяются правила миграции

        }
    }

    abstract fun getStudentDao():StudentDao
}