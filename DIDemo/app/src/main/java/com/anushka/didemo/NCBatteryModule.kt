package com.anushka.didemo

import dagger.Binds
import dagger.Module

@Module
abstract class NCBatteryModule {
    @Binds
    abstract  fun bindsNCBattery(nickelCadmiumBattery: NickelCadmiumBattery): Battery
}