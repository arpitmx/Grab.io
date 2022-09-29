package com.ncs.grabio.Utility

import com.ncs.grabio.UI.DataHolder


/**
 * Created by Alok Ranjan on 29-09-2022
 * Project : NCS Grab.io
 * Description :
 * Github : https://github.com/arpitmx
 **/
class Validator() {

    fun isValid(item : DataHolder.progData): Boolean {

        val fields = item::class.java.declaredFields
        for (field in fields) {

            field.isAccessible = true
            for (annotation in field.annotations) {
                val value = field.get(item)

                if (field.isAnnotationPresent(Positive::class.java)) {

                    val amount = value as Int
                    if (amount < 0) {
                        return false
                    }
                }

            }
        }
        return true
    }
}