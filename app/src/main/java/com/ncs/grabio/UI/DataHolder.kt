package com.ncs.grabio.UI

import com.ncs.grabio.Utility.Positive


/**
 * Created by Alok Ranjan on 29-09-2022
 * Project : NCS Grab.io
 * Description :
 * Github : https://github.com/arpitmx
 **/
class DataHolder {
    data class progData(@Positive var show:Int, var text: String, var time : Long)
}