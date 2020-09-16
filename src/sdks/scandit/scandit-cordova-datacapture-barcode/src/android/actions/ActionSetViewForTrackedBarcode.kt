/*
 * This file is part of the Scandit Data Capture SDK
 *
 * Copyright (C) 2020- Scandit AG. All rights reserved.
 */

package com.scandit.datacapture.cordova.barcode.actions

import com.scandit.datacapture.cordova.barcode.data.SerializableAdvancedOverlayViewActionData
import com.scandit.datacapture.cordova.core.actions.Action
import com.scandit.datacapture.cordova.core.actions.ActionJsonParseErrorResultListener
import org.apache.cordova.CallbackContext
import org.json.JSONArray
import org.json.JSONException

class ActionSetViewForTrackedBarcode(
        private val listener: ResultListener
) : Action {

    override fun run(args: JSONArray, callbackContext: CallbackContext) {
        try {
            val parsedData = SerializableAdvancedOverlayViewActionData(
                    args.getJSONObject(0)
            )
            listener.onViewForTrackedBarcode(parsedData, callbackContext)
        } catch (e: JSONException) {
            e.printStackTrace()
            listener.onJsonParseError(e, callbackContext)
        } catch (e: RuntimeException) {// TODO [SDC-1851] - fine-catch deserializer exceptions
            e.printStackTrace()
            listener.onJsonParseError(e, callbackContext)
        }
    }

    interface ResultListener : ActionJsonParseErrorResultListener {
        fun onViewForTrackedBarcode(
                data: SerializableAdvancedOverlayViewActionData,
                callbackContext: CallbackContext
        )
    }
}
