package br.com.sevenestoque.sevenestoque
import android.app.Application

class SevenApplication: Application() {
    // chamado quando android iniciar o processo da aplicação
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        // singleton
        private var appInstance: SevenApplication?  = null
        fun getInstance(): SevenApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configurar application no Android Manifest")
            }
            return appInstance!!
        }
    }

    // chamado quando android terminar processo da aplicação

    override fun onTerminate() {
        super.onTerminate()
    }
}