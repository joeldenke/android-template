
import com.android.build.api.dsl.ApplicationExtension
import se.joeldenke.jtemplate.configureGradleManagedDevices
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import se.joeldenke.jtemplate.configureKotlinAndroid
import se.joeldenke.jtemplate.configurePrintApksTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("jtemplate.android.showkase")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 33
                configureGradleManagedDevices(this)
            }
            extensions.configure<ApplicationAndroidComponentsExtension> {
                configurePrintApksTask(this)
            }
        }
    }
}