package com.example.admin.thebestapp.app.extensions

import android.text.TextUtils
import android.util.Log
import android.view.Window
import android.view.WindowManager

object AppUtils

fun AppUtils.printLog(iLogType: Int, iTag: String, iString: String)
{
    val CHAR_LIMITS = 3500
    var dataToPrint = iString
    
    fun getClassName(clazz: Class<*>?): String
    {
        return if(clazz != null)
        {
            if(TextUtils.isEmpty(clazz.simpleName))
            {
                getClassName(clazz.enclosingClass)
            }
            else
            {
                clazz.simpleName
            }
        }
        else ""
    }
    
    fun getLogLocation(): String
    {
        val tmpClassName = AppUtils::class.java.name
        val traces = Thread.currentThread().stackTrace
        var found = false
        
        for(trace in traces)
        {
            try
            {
                if(found)
                {
                    if(!trace.className.startsWith(tmpClassName))
                    {
                        val clazz = Class.forName(trace.className)
                        val className = getClassName(clazz)
                        val methodName = trace.methodName
                        return "[" + className + "::" + methodName + "::" + trace.lineNumber + "]:: "
                    }
                }
                else if(trace.className.startsWith(tmpClassName))
                {
                    found = true
                }
            }
            catch(ignored: ClassNotFoundException)
            {
            }
            
        }
        
        return "[]: "
    }
    
    if(!TextUtils.isEmpty(dataToPrint))
    {
        while(dataToPrint.isNotEmpty())
        {
            val currentPrint: String
            
            if(dataToPrint.length > CHAR_LIMITS)
            {
                currentPrint = dataToPrint.substring(0, CHAR_LIMITS)
                dataToPrint = dataToPrint.substring(CHAR_LIMITS)
                printLog(Log.DEBUG, tag(), "exceeded char limits : $CHAR_LIMITS chars")
            }
            else
            {
                currentPrint = dataToPrint
                dataToPrint = ""
            }
            
            when(iLogType)
            {
                Log.VERBOSE -> Log.v(iTag, getLogLocation() + currentPrint)
                Log.DEBUG -> Log.d(iTag, getLogLocation() + currentPrint)
                Log.INFO -> Log.i(iTag, getLogLocation() + currentPrint)
                Log.WARN -> Log.w(iTag, getLogLocation() + currentPrint)
                Log.ERROR -> Log.e(iTag, getLogLocation() + currentPrint)
                else -> Log.wtf(iTag, getLogLocation() + currentPrint)
            }
        }
    }
}

fun Any.tag(): String
{
    return this::class.java.simpleName
}

fun AppUtils.isWindowTouchable(iWindow: Window, iIsTouchable: Boolean)
{
    if(iIsTouchable)
    {
        iWindow.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
    else
    {
        iWindow.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}