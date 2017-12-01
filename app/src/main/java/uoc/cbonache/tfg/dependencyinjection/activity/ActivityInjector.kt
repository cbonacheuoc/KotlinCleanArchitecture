package uoc.cbonache.tfg.dependencyinjection.activity

import uoc.cbonache.tfg.ui.login.LoginActivity
import uoc.cbonache.tfg.ui.login.LoginActivityModule

import uoc.cbonache.tfg.ui.main.MainActivity
import uoc.cbonache.tfg.ui.main.MainActivityModule

import uoc.cbonache.tfg.ui.shippingList.ShippingListActivity
import uoc.cbonache.tfg.ui.shippingList.ShippingListModule

import uoc.cbonache.tfg.ui.shippingDetail.ShippingDetailActivity
import uoc.cbonache.tfg.ui.shippingDetail.ShippingDetailModule

import uoc.cbonache.tfg.ui.shippingSign.ShippingSignActivity
import uoc.cbonache.tfg.ui.shippingSign.ShippingSignModule

import uoc.cbonache.tfg.ui.shippingMap.ShippingMapActivity
import uoc.cbonache.tfg.ui.shippingMap.ShippingMapModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author cbonache
 */
@Module
abstract class ActivityInjector {

    @ContributesAndroidInjector(modules = arrayOf(LoginActivityModule::class))
    abstract fun contributeLoginActivityInjector(): LoginActivity

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun contributeMainActivityInjector(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(ShippingListModule::class))
    abstract fun contributeShippingListInjector(): ShippingListActivity

    @ContributesAndroidInjector(modules = arrayOf(ShippingDetailModule::class))
    abstract fun contributeShippingDetailInjector(): ShippingDetailActivity

    @ContributesAndroidInjector(modules = arrayOf(ShippingSignModule::class))
    abstract fun contributeShippingSignInjector(): ShippingSignActivity

    @ContributesAndroidInjector(modules = arrayOf(ShippingMapModule::class))
    abstract fun contributeShippingMapInjector(): ShippingMapActivity
}
