package com.route.todp3.dateformat

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

class Converter(var epocTime:Long) {
    var date =Date(epocTime)
    var format=SimpleDateFormat("dd-MM-yyyy")


    fun format():String{
      return  format.format(date)
    }

}