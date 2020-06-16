package com.art_int_labs.lead_up

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.art_int_labs.lead_up.data.db.AppDataBase
import com.art_int_labs.lead_up.data.network.*
import com.art_int_labs.lead_up.data.repository.MainRepository
import com.art_int_labs.lead_up.data.repository.MainRepositoryImpl
import com.art_int_labs.lead_up.ui.Course.CourseViewModelFactory
import com.art_int_labs.lead_up.ui.Course.detail.CourseDetailViewModelFactory
import com.art_int_labs.lead_up.ui.Knowledge.KnowledgeViewModelFactory
import com.art_int_labs.lead_up.ui.Knowledge.detail.KnowledgeDetailViewModelFactory
import com.art_int_labs.lead_up.ui.Main.MainViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class LeadApplication:Application(),KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@LeadApplication))

        bind() from singleton { AppDataBase(instance()) }
        bind() from singleton { instance<AppDataBase>().courseDao() }
        bind() from singleton { instance<AppDataBase>().knowledgeDao() }
        bind() from singleton { instance<AppDataBase>().newsDao() }
        bind() from singleton { instance<AppDataBase>().lessonDao() }
        bind() from singleton { instance<AppDataBase>().productDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApiLeadApiServices(instance()) }
        bind<NetworkDataSourse>() with singleton { NetworkDataSourseImpl(instance()) }
        bind<MainRepository>() with singleton { MainRepositoryImpl(instance(),instance(),instance(),instance(),instance(),instance()) }
        bind() from provider { MainViewModelFactory(instance()) }
        bind() from provider {CourseViewModelFactory(instance())}
        bind() from provider { KnowledgeViewModelFactory(instance()) }
        bind() from provider { CourseDetailViewModelFactory(instance()) }
        bind() from provider { KnowledgeDetailViewModelFactory(instance()) }
    }

    companion object {
        lateinit var instance: LeadApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}