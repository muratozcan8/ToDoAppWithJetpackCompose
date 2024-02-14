package com.muratozcan.todoappwithjetpackcompose

import android.app.Application
import androidx.room.Room
import com.muratozcan.todoappwithjetpackcompose.database.TodoDatabase
import com.muratozcan.todoappwithjetpackcompose.repositories.TodoRepo
import com.muratozcan.todoappwithjetpackcompose.repositories.TodoRepoImpl
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module

class KoinApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(module {
                single {
                    Room
                        .databaseBuilder(this@KoinApp, TodoDatabase::class.java, "db")
                        .build()
                }
                single {
                    TodoRepoImpl(database = get())
                } bind TodoRepo::class
            })
        }
    }
}