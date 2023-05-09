package se.joeldenke.jtemplate

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.createRoute

fun NavController.navigate(route: String, bundle: Bundle = Bundle()) {
    val r = createRoute(route)
    navigate(r.hashCode(), bundle)
}