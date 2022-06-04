package Interface

import model.App

interface DataSource {
    fun getAllApps(): List<App>
}